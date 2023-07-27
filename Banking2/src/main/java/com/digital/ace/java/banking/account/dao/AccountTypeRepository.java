package com.digital.ace.java.banking.account.dao;

import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long>  {

    @Query(value = "SELECT * FROM account_Type a WHERE a.account_description= ?1", nativeQuery = true)
    Optional<AccountType> findByAccountDescription(String description);
}
