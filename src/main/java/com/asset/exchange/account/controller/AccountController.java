package com.asset.exchange.account.controller;

import com.asset.exchange.account.entity.Account;
import com.asset.exchange.account.service.AccountService;
import com.asset.exchange.transactions.entity.Transactions;
import com.asset.exchange.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService trxService;

    @GetMapping("/getAccounts")
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccount());

    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositToAccount(@RequestBody Transactions trx){

           Account acc = accountService.findByAccountNumber(trx.getToAccountNumber());
           double prevBal = acc.getAccountBalance();
           acc.setAccountBalance(prevBal+Double.parseDouble(trx.getAmount()));
           accountService.updateBalance(acc);
       // trxService.saveTransaction(trx);

        return ResponseEntity.ok().body("Deposit success");

    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFromAccount(@RequestBody Transactions trx){

        Account acc = accountService.findByAccountNumber(trx.getToAccountNumber());
        double prevBal = acc.getAccountBalance();
        if(prevBal-Double.parseDouble(trx.getAmount()) >=0) {
            acc.setAccountBalance(prevBal - Double.parseDouble(trx.getAmount()));
            accountService.updateBalance(acc);
            // trxService.saveTransaction(trx);

            return ResponseEntity.ok().body("Withdraw success");
        }
        else{
            return ResponseEntity.ok().body("Insufficient Balance");
        }


    }

}
