package com.currency.service;

import com.currency.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAllByUsername(String username);
    AccountDTO create (AccountDTO accountDTO);
    List<AccountDTO> findAll();
    List<AccountDTO> findAllByUsernameAndCurrencyList(String username, List<String> currecnyList);
}
