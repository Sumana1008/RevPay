package com.example.demo.controller;
import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TransactionController that handles HTTP requests related to transactions.
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    /* getAllTransactions(): Handles a GET request to retrieve a list of all transactions.
    Calls the getAllTransactions() method in TransactionService.*/
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }


    /*getTransactionById(Long id): Handles a GET request to retrieve a transaction by its ID.
    Calls the getTransactionById(Long id) method in TransactionService.*/
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }


   /* createTransaction(Transaction transaction): Handles a POST request to create a new transaction.
    Calls the createTransaction(Transaction transaction) method in TransactionService.*/
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

}

