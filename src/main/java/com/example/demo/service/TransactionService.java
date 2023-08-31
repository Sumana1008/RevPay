package com.example.demo.service;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    /*getAllTransactions(): Retrieves a list of all transactions using the TransactionRepository.*/


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    /*getTransactionById(Long id): Retrieves a transaction by its ID using the TransactionRepository.*/

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        return optionalTransaction.orElse(null);
    }
  /*  createTransaction(Transaction transaction): Creates a new transaction using the TransactionRepository.*/


    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public BigDecimal calculateAccountBalance(Account account) {
        List<Transaction> transactions = transactionRepository.findByFromAccountIdOrToAccountId(account.getId(), account.getId());
/*The method first retrieves a list of transactions involving the given account. It uses the transactionRepository to fetch
transactions where either the account is the sender (fromAccountId) or the account is the receiver (toAccountNumber).
This is done using the findByFromAccountIdOrToAccountId method from the TransactionRepository.
The list of transactions is stored in the transactions variable.
 */
        BigDecimal balance = account.getBalance();
        for (Transaction transaction : transactions) { /*The method then iterates through each transaction in the transactions list.*/
            if (transaction.getFromAccountId().equals(account.getId())) {
                balance = balance.subtract(transaction.getAmount());/*If acct is sender,the amount is subtracted from balance*/
                account.setBalance(balance);
            } else if (transaction.getToAccountId().equals(account.getId())) {
                balance = balance.add(transaction.getAmount());/*If acct is receiver,the amount is added to balance*/
                account.setBalance(balance);
            }
        }

        return balance;
    }
}

