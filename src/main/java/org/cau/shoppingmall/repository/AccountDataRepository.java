package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountDataRepository extends JpaRepository<AccountData,Long> {

}