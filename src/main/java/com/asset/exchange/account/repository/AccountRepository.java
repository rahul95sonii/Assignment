package com.asset.exchange.account.repository;

import com.asset.exchange.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Modifying
    @Query("update Account a set a.accountBalance =:balance where a.accountNumber=:accNo")
    void updateBalance(@Param(value = "balance") double balance, @Param(value = "accNo") String accNo);

   Account findByAccountNumber(String accountNumber);
}