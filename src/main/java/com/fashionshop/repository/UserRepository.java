package com.fashionshop.repository;

import com.fashionshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByUsername(String username);

    UserEntity findUserEntityByEmail(String email);

    UserEntity findUserEntityByResetPasswordToken(String token);

    @Query("update UserEntity set resetPasswordToken = ?1 where email= ?2")
    void updateResetPasswordToken(String token, String email);

    @Query("update UserEntity  set password= ?1 where username = ?2")
    void updatePassword(String password, String username);
}
