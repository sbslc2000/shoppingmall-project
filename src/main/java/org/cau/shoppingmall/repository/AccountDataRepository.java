package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDataRepository extends JpaRepository<AccountData,Long> {
}
