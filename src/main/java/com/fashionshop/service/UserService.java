package com.fashionshop.service;

import com.fashionshop.entity.AuthenticationProvider;
import com.fashionshop.entity.UserEntity;
import com.fashionshop.util.CustormException;

public interface UserService {
    UserEntity findUserByUsername(String username);

    void saveUser(UserEntity user);


    void updateResetPasswordToken(String token, String email) throws CustormException;

    UserEntity findUserEntityByResetPasswordToken(String token);


    void updatePassword(String password, String username);

    UserEntity getUserByEmail(String email);

    void registerNewCustomerAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider authProvider);

    void updatePassword(UserEntity user, String newPassword);


}
