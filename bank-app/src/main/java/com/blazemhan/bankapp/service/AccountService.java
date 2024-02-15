package com.blazemhan.bankapp.service;

import com.blazemhan.bankapp.dto.AccountDto;
import com.blazemhan.bankapp.entity.Account;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto findById(Long id);

    List<Account> getAllAccounts();

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    void deleteAccount(Long id);

}
