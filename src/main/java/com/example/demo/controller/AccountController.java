package com.example.demo.controller;

import com.example.demo.enums.ActivationStatus;
import com.example.demo.enums.TransactionType;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
/*The AccountController class is responsible for handling incoming HTTP requests related to accounts
and transactions. It uses the AccountService and TransactionService to perform the necessary operations.
 */


@RestController /*Combines @Controller and @ResponseBody. It's used to indicate that a class defines a REST controller that handles
 HTTP requests and returns data directly in the response body. */

@RequestMapping("/accounts") //It defines which URL paths should be handled by the annotated class or method.
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

   /* getAllAccounts(): Handles a GET request to retrieve a list of all accounts.
    Calls the getAllAccounts() method in AccountService.*/

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    /*getAccountById(Long id): Handles a GET request to retrieve an account by its ID.
    Calls the getAccountById(Long id) method in AccountService.*/


    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    /*createAccount(Account account): Handles a POST request to create a new account.
    Calls the createAccount(Account account) method in AccountService.*/


    @PostMapping
    public String createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
   /* depositToAccount(Long accountId, BigDecimal amount):
    Deposits a specified amount to the account with the given ID.
    Calls the depositToAccount(Long accountId, BigDecimal amount) method in AccountRepository.*/


    @PostMapping("/{id}/deposit")//method will be invoked when an HTTP POST request is made to this URL.

    public ResponseEntity<String> deposit(@PathVariable Long id, @RequestBody Transaction transactions ) {
     /*   id: A @PathVariable that represents the account's ID extracted from the URL.
        transactions: A @RequestBody parameter that represents the deposit transaction data received in the request's body as JSON.*/

        Account account = accountService.getAccountById(id);// Retrieving the account by ID from AccountService.

        if (account == null) {
            return ResponseEntity.notFound().build();//If account is not found,it returns a 404 "Not Found" Response
        }

        // Create a deposit transaction
        Transaction transaction = new Transaction();
        transaction.setToAccountId(account.getId()); //sets the receiver's account id for transaction
        transaction.setAmount(transactions.getAmount()); //sets the deposit amount based on data received in the request's JSON body.
        transaction.setTimestamp(LocalDateTime.now());


        // Save the transaction in db
        transactionService.createTransaction(transaction);

        return ResponseEntity.ok("Deposit transaction created."); //"200 OK" response is returned.
    }

    /* withdrawFromAccount(Long accountId, BigDecimal amount):
    Withdraws a specified amount from the account with the given ID.
    Calls the withdrawFromAccount(Long accountId, BigDecimal amount) method in AccountRepository.*/
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long id, @RequestBody Transaction transactions) {
        Account account = accountService.getAccountById(id);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        // Check daily withdrawal limit
        if (transactions.getAmount().compareTo(account.getDailyWithdrawalLimit()) > 0) {
            return ResponseEntity.badRequest().body("Exceeded daily withdrawal limit.");
        }

        // Check transaction type
        if (account.getTransactionType() == TransactionType.CREDIT_ONLY) {
            return ResponseEntity.badRequest().body("Credit transactions only.");
        } else if (account.getTransactionType() == TransactionType.DEBIT_ONLY) {
            return ResponseEntity.badRequest().body("Debit transactions only.");
        }

        // Create a withdrawal transaction
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(account.getId());
        transaction.setAmount(transactions.getAmount());
        transaction.setTimestamp(LocalDateTime.now());

        // Save the transaction
        transactionService.createTransaction(transaction);

        return ResponseEntity.ok("Withdrawal transaction created.");
    }
    /*getAccountBalance(Long accountId):
    Retrieves the balance of the account with the given ID.
    Calls the calculateAccountBalance(Account account) method in TransactionService to calculate the balance.*/
    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        // Calculate balance based on transactions
        BigDecimal balance = transactionService.calculateAccountBalance(account);

        return ResponseEntity.ok(balance);
    }
}

