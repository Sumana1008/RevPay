package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
/*The AccountRepository interface extends the JpaRepository interface, which is part of the Spring Data JPA framework.
Spring Data JPA provides abstractions and methods for interacting with the database, allowing you to perform
CRUD (Create, Read, Update, Delete) operations on entities without writing explicit SQL queries.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    /*These methods can be reused throughout your codebase whenever you need to retrieve an Account entity based on account number
    or sort code.Using findByAccountNumber and findBySortCode methods allows you to retrieve Account entities without manually writing the
    corresponding SQL queries. Spring Data JPA automatically generates the SQL queries based on the method names and parameter names.
    For example, the findByAccountNumber method would generate a SQL query like:
    SELECT * FROM Account WHERE accountNumber = :accountNumber
     */
    Account findByAccountNumber(String accountNumber);
    Account findBySortCode(String sortCode);


}

