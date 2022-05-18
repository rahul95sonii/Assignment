package com.asset.exchange.account.service;

import com.asset.exchange.account.entity.Account;

import java.util.List;

public interface AccountService {

    public List<Account> getAllAccount();

    public Account saveTransaction(Account AccountFrom ,Account AccountTo);

    public Account findByAccountNumber(String accNo);

    public void updateBalance(Account acc);

}
