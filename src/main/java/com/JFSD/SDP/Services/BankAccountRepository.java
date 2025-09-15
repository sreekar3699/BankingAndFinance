package com.JFSD.SDP.Services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JFSD.SDP.Model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    // Custom query methods can be added here, if needed
}
