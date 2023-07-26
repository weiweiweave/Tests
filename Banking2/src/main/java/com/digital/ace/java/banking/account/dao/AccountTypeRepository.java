package com.digital.ace.java.banking.account.dao;

import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.account.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long>  {
}
