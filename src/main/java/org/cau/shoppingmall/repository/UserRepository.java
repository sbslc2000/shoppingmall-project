package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
