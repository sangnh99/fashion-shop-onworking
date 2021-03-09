package com.fashionshop.controller;

import com.fashionshop.entity.RoleEntity;
import com.fashionshop.entity.UserEntity;
import com.fashionshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

@Controller
public class RegisterController {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/startRegister"}, method = RequestMethod.GET)
    public String getLogin(Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("reason", "");
        redirectAttributes.addAttribute("name", "");
        redirectAttributes.addAttribute("phone", "");
        redirectAttributes.addAttribute("email", "");
        redirectAttributes.addAttribute("address", "");
        redirectAttributes.addAttribute("username", "");
        return "redirect:register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(@RequestParam("reason") String reason, @RequestParam("name") String name, @RequestParam("phone") String phone,
                              @RequestParam("email") String email, @RequestParam("address") String address,
                              @RequestParam("username") String username, Model model) {
        model.addAttribute("reason", reason);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        model.addAttribute("username", username);
        return "register";
    }

    @RequestMapping(value = "/handleRegister", method = RequestMethod.POST)
    public String handleRegister(@RequestParam("name") String name, @RequestParam("phone") String phone,
                                 @RequestParam("email") String email, @RequestParam("address") String address,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
                                 RedirectAttributes redirectAttributes, HttpSession session, @RequestParam("photo") MultipartFile photo) {

        boolean checkSuccessful = true;
        if ("".equals(name) || "".equals(phone) || "".equals(email) || "".equals(address) ||
                "".equals(password) || "".equals(confirmPassword)) {
            redirectAttributes.addAttribute("reason", "Vui lòng nhập đầy đủ thông tin");
            checkSuccessful = false;
        } else if (!Pattern.matches("[0-9]+", phone)) {
            redirectAttributes.addAttribute("reason", "Số điện thoại bạn nhập không đúng");
            checkSuccessful = false;
        } else if (!Pattern.matches("^[\\w.+\\-]+@gmail\\.com$", email)) {
            redirectAttributes.addAttribute("reason", "Email bạn nhập không đúng");
            checkSuccessful = false;
        } else if (!(userService.findUserByUsername(username) == null)) {
            redirectAttributes.addAttribute("reason", "Tài khoản đã tồn tại");
            checkSuccessful = false;
        } else if (!password.equals(confirmPassword)) {
            redirectAttributes.addAttribute("reason", "Xác nhận mật khẩu không đúng");
            checkSuccessful = false;
        } else if (!(userService.getUserByEmail(email) == null)) {
            redirectAttributes.addAttribute("reason", "Email đã tồn tại");
            checkSuccessful = false;
        }
        if (checkSuccessful == false) {
            redirectAttributes.addAttribute("name", name);
            redirectAttributes.addAttribute("phone", phone);
            redirectAttributes.addAttribute("email", email);
            redirectAttributes.addAttribute("address", address);
            redirectAttributes.addAttribute("username", username);
            return "redirect:register?error=true";
        }
        else{
            Path path = Paths.get("uploads/");
            try {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                UserEntity user = new UserEntity(name, phone, address, email,
                        new RoleEntity(1, "ROLE_USER"), username, bCryptPasswordEncoder.encode(password));
                if (!photo.isEmpty()) {
                    InputStream inputStream = photo.getInputStream();

                    Files.copy(inputStream,path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                    user.setPhoto(photo.getOriginalFilename().toLowerCase());
                }
                userService.saveUser(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:login";
        }
    }


}
