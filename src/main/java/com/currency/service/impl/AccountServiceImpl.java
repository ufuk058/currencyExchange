package com.currency.service.impl;

import com.currency.client.CurrencyApiClient;
import com.currency.dto.AccountDTO;
import com.currency.dto.UserDTO;
import com.currency.entity.Account;
import com.currency.entity.User;
import com.currency.enums.Base;
import com.currency.repository.AccountRepository;
import com.currency.service.AccountService;
import com.currency.service.UserService;
import com.currency.util.MapperUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Value("${access_key}")
    private String access_key;
    private final AccountRepository accountRepository;
    private final UserService userService;
    private final MapperUtil mapperUtil;
    private final CurrencyApiClient client;

    public AccountServiceImpl(AccountRepository accountRepository, UserService userService, MapperUtil mapperUtil, CurrencyApiClient client) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.mapperUtil = mapperUtil;
        this.client = client;
    }




    @Override
    public List<AccountDTO> findAllByUsername(String username) {
        return accountRepository.findAllByUser_Username(username)
                .stream().map(account -> {
                    AccountDTO accountDTO= mapperUtil.convert(account,new AccountDTO());
                    accountDTO.setUsername(username);
                    /// add currency information
                    Map<String, BigDecimal> otherCurrencies= new HashMap<>();

                accountDTO.setOtherCurrencies(getAllCurrenciesByBalance(accountDTO.getBalance()));


                    return accountDTO;
                })
                .collect(Collectors.toList());
    }





    private Map<String, BigDecimal> getAllCurrenciesByBalance(BigDecimal balance){
        Map<String, BigDecimal> allCurrencies=client.getAllCurrencies(access_key).getRates();
        Map<String, BigDecimal> otherCurrencies= new HashMap<>();

        otherCurrencies=allCurrencies.entrySet().stream().collect(Collectors.toMap(
                Map.Entry :: getKey,
                entry -> balance.multiply(entry.getValue())
        ));


        return otherCurrencies;
    }

    private Map<String, BigDecimal> getSelectedCurrenciesByBalance(BigDecimal balance, List<String> currencies){
        Map<String, BigDecimal> allCurrencies=client.getSelectedCurrencies(access_key,currencies).getRates();
        Map<String, BigDecimal> otherCurrencies= new HashMap<>();

        otherCurrencies=allCurrencies.entrySet().stream().collect(Collectors.toMap(
                Map.Entry :: getKey,
                entry -> balance.multiply(entry.getValue())
        ));


        return otherCurrencies;
    }






    private Long generateAccountNumber(){
        return (long) (Math.random()*100000000000L);
    }


    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        /// Find the user by username using the user service
        UserDTO userDto=userService.findByUsername(accountDTO.getUsername());

        /// Convert AccountDTO to Account entity for database storage
        Account accountToSave=mapperUtil.convert(accountDTO, new Account());

        /// set the user of the account by converting  UserDTO to User
        accountToSave.setUser(mapperUtil.convert(userDto, new User()));

        /// Generate a random Account Number value for the new account
        accountToSave.setAccountNumber(generateAccountNumber());

        /// Save the newly created account to the database
        Account newAccount= accountRepository.save(accountToSave);

        /// Convert the saved account entity back to AccountDTO
        AccountDTO accountToReturn= mapperUtil.convert(newAccount, new AccountDTO());

        /// Set the username in the returned AccountDTO for consistency
        accountToReturn.setUsername(userDto.getUsername());


        return accountToReturn;
    }

    @Override
    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream().map(account -> mapperUtil.convert(account,new AccountDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> findAllByUsernameAndCurrencyList(String username, List<String> currecnyList) {
        return List.of();
    }
}
