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

    @PostMapping("/acctranscation")
    public ResponseEntity<?> fromAccountToAccount(@RequestBody Transactions trx) {

        Account acc = accountService.findByAccountNumber(trx.getToAccountNumber());
        Account acc1 = accountService.findByAccountNumber(trx.getFromAccountNumber());
        double toBal = acc.getAccountBalance();
        double fromBal = acc1.getAccountBalance();
        double prevBal = acc.getAccountBalance();
        double txnBal = Double.parseDouble(trx.getAmount());

        String acc_assettype = acc.getToasset_type();
        String des_assettype = trx.getAssetType();
        String toBalance = String.valueOf(prevBal - Double.parseDouble(trx.getAmount()));
        double trnBalance = Double.parseDouble(trx.getAmount());
        if(acc.getAccountBalance()>0) {
            //Transaction in Between Two Accounts
            acc.setAccountBalance(prevBal - Double.parseDouble(trx.getAmount()));

            accountService.updateBalance(acc);
            acc1.setAccountBalance(txnBal);
            accountService.updateBalance(acc1);
            return ResponseEntity.ok().body("From Account Transaction Done");
        }
        if (acc_assettype.equals(des_assettype))
        {
            accountService.transferAmount(trx.getToAccountNumber(), trx.getFromAccountNumber(), toBalance, trx.getAmount() );
        }
        else if (acc_assettype.equals("USD") && des_assettype.equals("BTC") )
        {
            double result=0.000034*Double.parseDouble(trx.getAmount());
            accountService.transferAmount(trx.getToAccountNumber(), trx.getFromAccountNumber(),toBalance, trx.getAmount());
        }

        else if (acc_assettype.equals("BTC") && des_assettype.equals("USD") )
        {
            double result=29842.80*Double.parseDouble(trx.getAmount());
            accountService.transferAmount(trx.getToAccountNumber(), trx.getFromAccountNumber(), toBalance, trx.getAmount() );
        }

        else if (acc_assettype.equals("ETH") && des_assettype.equals("USD") )
        {
            double result=2023.14*Double.parseDouble(trx.getAmount());
            accountService.transferAmount(trx.getToAccountNumber(), trx.getFromAccountNumber(), toBalance, trx.getAmount() );
        }
        else if (acc_assettype.equals("ETH") && des_assettype.equals("BTC") )
        {
            double result=0.06864*Double.parseDouble(trx.getAmount());
            accountService.transferAmount(trx.getToAccountNumber(), trx.getFromAccountNumber(), toBalance, trx.getAmount() );
        }
        else if (acc_assettype.equals("BTC") && des_assettype.equals("ETH") )
        {
            double result=14.80*Double.parseDouble(trx.getAmount());
            accountService.transferAmount(trx.getToAccountNumber(), trx.getFromAccountNumber(), toBalance, trx.getAmount() );
        }
        else if (acc_assettype.equals("USD") && des_assettype.equals("ETH") )
        {
            double result=0.00050*Double.parseDouble(trx.getAmount());
            accountService.transferAmount(trx.getToAccountNumber(), trx.getFromAccountNumber(), toBalance, trx.getAmount() );
        }
        //return "amount transferred succesfully";
        return ResponseEntity.ok().body("From Account Transaction Done");
    }
}
