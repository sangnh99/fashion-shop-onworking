package com.fashionshop.service;

import com.fashionshop.entity.AuthenticationProvider;
import com.fashionshop.entity.RoleEntity;
import com.fashionshop.entity.UserEntity;
import com.fashionshop.repository.UserRepository;
import com.fashionshop.util.CustormException;
import com.fashionshop.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws CustormException {
        UserEntity user = userRepository.findUserEntityByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new CustormException(Message.USER_NOT_FOUND);
        }
    }

    @Override
    public void updatePassword(String password, String username) {
        userRepository.updatePassword(password, username);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findUserEntityByEmail(email);
    }

    @Override
    public void registerNewCustomerAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider authProvider) {
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setEmail(email);
        user.setAuthProvider(authProvider);
        user.setRole(new RoleEntity(1, "ROLE_USER"));
        user.setUsername(name);
        userRepository.save(user);
    }

    @Override
    public UserEntity findUserEntityByResetPasswordToken(String token) {
        return userRepository.findUserEntityByResetPasswordToken(token);
    }

    public UserEntity getByResetPasswordToken(String token) {
        return userRepository.findUserEntityByResetPasswordToken(token);
    }

    public void updatePassword(UserEntity user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
}
