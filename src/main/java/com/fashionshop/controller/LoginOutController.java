package com.fashionshop.controller;

import com.fashionshop.entity.UserEntity;
import com.fashionshop.service.UserService;
import com.fashionshop.util.CustormException;
import com.fashionshop.util.Message;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Controller
public class LoginOutController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;
    @Value("${spring.mail.username}")
    private String from;

    @RequestMapping("/login")
    public String login(Principal userPrincipal) {
        if (userPrincipal != null) {
            System.out.println(userPrincipal);
            return "redirect:/home";
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(7);
        try {
            userService.updateResetPasswordToken(token, email);
            String siteURL = request.getRequestURL().toString();
            String resetPasswordLink = siteURL.replace(request.getServletPath(), "") + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", Message.CONTENT_EMAIL.getDetail());
        } catch (CustormException ex) {
            model.addAttribute("error", ex.getErrorType().getDetail());
            return "forgot_password_form";
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", Message.ERROR_SENDING_EMAIL.getDetail());
        }
        return "forgot_password_form";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        UserEntity user = userService.findUserEntityByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (user == null) {
            model.addAttribute("message", Message.INVALID_TOKEN.getDetail());
            return "reset_password_form";
        }
        return "reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        UserEntity user = userService.findUserEntityByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");
        if (user == null) {
            model.addAttribute("message", Message.INVALID_TOKEN.getDetail());
            return "reset_password_form";
        } else {
            userService.updatePassword(user, password);
            return "redirect:/login";
        }
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(from);
        helper.setTo(recipientEmail);
        helper.setSubject(Message.SUBJECT_MAIL.getDetail());
        String content = "<img src='https://www.perthmint.com/images/product/250/0-Minion-2019-Happy-Birthday-1oz-Silver-Proof-Coin-Reverse.jpg'> "
                + "<br>"
                + "<p>Minionmint chào bạn</p>"
                + "<br>"
                + "<p>Miniontmint vừa nhận được yêu cầu thay đổi mật khẩu của bạn.</p>"
                + "<p>Hãy click vào link bên dưới để tiến hành thay đổi mật khẩu của bản nhé!</p>"
                + "<p><a href='" + link + "' style='padding:10px 20px;background-color:#337ab7;text-decoration:none;color:#fffffe;border-radius:5px;display:inline-block;max-width:70%;font-size:16px;margin:10px 0' >Thay đổi mật khẩu</a></p>"
                + "<p>Bỏ qua email này nếu bạn đã nớ mật khẩu của mình."
                + "<br>"
                + "Minionmint chúc bạn một ngày tốt lành.</p>";
        ;
        helper.setText(content, true);
        mailSender.send(message);
    }
}
