package com.JFSD.SDP.Services;

import java.util.List;

import com.JFSD.SDP.Model.BankAccount;
public interface BankAccountService {
    List<BankAccount> getAllBankAccounts();

    BankAccount getBankAccountById(Long id);

    BankAccount createBankAccount(BankAccount bankAccount, int userId);

    BankAccount updateBankAccount(Long id, BankAccount updatedAccount);

    void deleteBankAccount(Long id);
}
