package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*AccountService class is a service component responsible for handling business logic related
to the Account entity. It interacts with the AccountRepository to perform CRUD (Create, Read, Update, Delete) operations on accounts
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /*This method retrieves a list of all accounts from the database using the AccountRepository's findAll() method.
It returns a List<Account> containing all account entities.
 */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    /*This method retrieves a single account by its ID from the database using the AccountRepository's findById(id) method.
 It uses an Optional<Account> to handle the possibility that the account with the given ID might not exist.
 If the account exists, it's returned; otherwise, null is returned.
     */
    public Account getAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.orElse(null);
    }
   /* This method is used to create a new account. It takes an Account object as a parameter,which represents the account to be created.
The AccountRepository's save(account) method is used to persist the new account entity in the database.
The method then returns the newly created account entity.
    */
    public String createAccount(Account account) {
        if(account.getAccountNumber().length()>10){
            return "Account number more than 10 digits";
        }
        else if(account.getSortCode().length()>8){
            return "Account sort code more than 8 digits";
        }
        else{
            accountRepository.save(account);
            return "Account Created";
        }
    }

}

