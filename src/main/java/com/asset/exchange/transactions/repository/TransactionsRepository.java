package com.asset.exchange.transactions.repository;

import com.asset.exchange.transactions.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}