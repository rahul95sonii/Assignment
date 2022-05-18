package com.asset.exchange.account.entity;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(name="account_number")
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
@Column(name="account_balance")
    private double accountBalance;

    public Account() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
