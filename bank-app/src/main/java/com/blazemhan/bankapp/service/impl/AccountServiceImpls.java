package com.blazemhan.bankapp.service.impl;

import com.blazemhan.bankapp.dto.AccountDto;
import com.blazemhan.bankapp.entity.Account;
import com.blazemhan.bankapp.mapper.AccountMapper;
import com.blazemhan.bankapp.repository.AccountRepository;
import com.blazemhan.bankapp.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpls implements AccountService {


    private AccountRepository accountRepository;

    public AccountServiceImpls(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
       Account savedAccount =  accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto findById(Long id) {
        Account account = accountRepository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Account not found"));

        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts= accountRepository.findAll();
        return accounts;

    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).
                orElseThrow(()->new RuntimeException("Account not found"));

            double total = account.getBalance() + amount;

            account.setBalance(total);
            Account savedAccount  = accountRepository.save(account);

            return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).
                orElseThrow(()->new RuntimeException("Account not found"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance");
        }

        double newBalance = account.getBalance() - amount;

        account.setBalance(newBalance);
        Account savedAccount  = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).
                orElseThrow(()->new RuntimeException("Account not found"));

        accountRepository.deleteById(id);

    }


}
