package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
