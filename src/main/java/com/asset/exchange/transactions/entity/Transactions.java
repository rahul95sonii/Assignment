package com.asset.exchange.transactions.entity;

        import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    public Transactions() {

    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    private String fromAccountNumber;
    private String toAccountNumber;

    public long getId() {
        return id;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAmountTransferType() {
        return amountTransferType;
    }

    public void setAmountTransferType(String amountTransferType) {
        this.amountTransferType = amountTransferType;
    }

    private String assetType;

    private String amountTransferType;

    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
