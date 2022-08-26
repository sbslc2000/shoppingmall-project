package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserId(String userId);
}
