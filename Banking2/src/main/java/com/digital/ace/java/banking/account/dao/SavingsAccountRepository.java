package com.digital.ace.java.banking.account.dao;

import com.digital.ace.java.banking.account.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long>  {
}
