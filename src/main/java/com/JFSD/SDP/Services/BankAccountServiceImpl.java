package com.JFSD.SDP.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JFSD.SDP.Model.BankAccount;
import com.JFSD.SDP.Model.User;
import com.JFSD.SDP.Services.BankAccountRepository;
import com.JFSD.SDP.Services.UserRepository;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }
    @Override
    public BankAccount getBankAccountById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }
 
    @Override
    public BankAccount createBankAccount(BankAccount bankAccount, int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null; // Handle user not found
        }
        bankAccount.setUser(user);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount updateBankAccount(Long id, BankAccount updatedAccount) {
        BankAccount existingAccount = getBankAccountById(id);
        if (existingAccount == null) {
            return null; // Handle not found
        }

        // Update the existing account with the new data
        existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
        existingAccount.setAccountHolderName(updatedAccount.getAccountHolderName());
        existingAccount.setBalance(updatedAccount.getBalance());

        return bankAccountRepository.save(existingAccount);
    }

    @Override
    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
