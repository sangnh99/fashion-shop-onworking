package com.fashionshop.service;

import com.fashionshop.entity.UserEntity;
import com.fashionshop.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(Message.USER_NOT_FOUND.getDetail());
        }
        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
        grantList.add(authority);
        UserDetails userDetails = (UserDetails) new User(user.getUsername(), user.getPassword(), grantList);
        return userDetails;
    }
}
