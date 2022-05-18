package com.asset.exchange.account.service;

import com.asset.exchange.account.entity.Account;
import com.asset.exchange.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }

    @Override
    public Account saveTransaction(Account AccountFrom, Account AccountTo) {
        return AccountTo;
    }

    @Override
    public Account findByAccountNumber(String accNo) {
        return accountRepo.findByAccountNumber(accNo);
    }

    @Override
    public void updateBalance(Account acc) {
        accountRepo.updateBalance(acc.getAccountBalance(),acc.getAccountNumber());
    }
}
