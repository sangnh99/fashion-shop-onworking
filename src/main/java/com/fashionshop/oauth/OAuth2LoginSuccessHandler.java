package com.fashionshop.oauth;

import com.fashionshop.entity.AuthenticationProvider;
import com.fashionshop.entity.UserEntity;
import com.fashionshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String name = oAuth2User.getFullName();
        System.out.println(email);

        UserEntity user = userService.getUserByEmail(email);

        if (user == null){
            userService.registerNewCustomerAfterOAuthLoginSuccess(email,name, AuthenticationProvider.FACEBOOK);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
