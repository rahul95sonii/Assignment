package com.asset.exchange.transactions.service;

import com.asset.exchange.transactions.entity.Transactions;
import com.asset.exchange.transactions.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements  TransactionService{

    @Autowired
    private TransactionsRepository trxRepo;
    @Override
    public Transactions saveTransaction(Transactions trx) {
        return trxRepo.save(trx);
    }
}
