package com.example.demo.model;

import com.example.demo.enums.ActivationStatus;
import com.example.demo.enums.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Account {

    @Id /*@Id and @GeneratedValue annotations indicate that this field will be the primary key of the entity
     and will be automatically generated (auto-incremented) by the database.*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id; //Long is used to accommodate a wide range of possible values for IDs.
    private String accountNumber;
    /*For example, an account number "0012345678" might become "12345678" if stored as an integer or long, causing loss of
     information. By storing them as strings, you can preserve leading zeros.In certain cases,
     account numbers and sort codes might not be purely numeric. They might include special characters or letters.*/
    private String sortCode;
    private ActivationStatus activationStatus = ActivationStatus.ACTIVE;//enum is used when you need to represent a fixed set of constants
    private TransactionType transactionType = TransactionType.CREDIT_AND_DEBIT;//by default CREDIT_AND_DEBIT is made as constant
    private BigDecimal dailyWithdrawalLimit; //Represents the maximum amount that can be withdrawn from the account per day.
    // BigDecimal is used because it can handle precision for financial calculations without loss of accuracy.
    private BigDecimal balance= BigDecimal.ZERO;
    @ManyToOne //multiple bank accounts can belong to a single business.

    @JoinColumn(name = "business_id") /*@JoinColumn annotation specifies the column in the database that will be used to establish the
     foreign key relationship.In this case, the column named business_id will store the reference to the related Business entity's ID.*/

    private Business business;//establishing many-to-one connection between account and business.

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public ActivationStatus getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(BigDecimal dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
