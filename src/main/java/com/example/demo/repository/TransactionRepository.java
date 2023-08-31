package com.example.demo.repository;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

   /* findByFromAccountIdOrToAccountNumber(Long fromAccountId, String toAccountNumber):
    Retrieves transactions where the sender's account ID matches fromAccountId or the receiver's
    account number matches toAccountNumber.*/

    List<Transaction> findByFromAccountIdOrToAccountId(Long id, Long id1);
}

/*List<Transaction> return type, you're indicating that the method could return more than one Transaction entity.*/