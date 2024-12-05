package com.currency.controller;

import com.currency.dto.AccountDTO;
import com.currency.dto.ResponseWrapper;
import com.currency.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ufuk/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseWrapper> getAll(){

        return ResponseEntity.ok(ResponseWrapper.builder()
                .success(true)
                .message("Successful")
                .code(HttpStatus.OK.value())
                .data(accountService.findAll()).build());
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<ResponseWrapper> getUserAllAccount(@PathVariable String username){
        return ResponseEntity.ok(ResponseWrapper.builder()
                .success(true)
                .message("Successful")
                .code(HttpStatus.OK.value())
                .data(accountService.findAllByUsername(username)).build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> creteAccount(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseWrapper.builder()
                        .success(true)
                        .message("AccountSuccessfully Created")
                        .code(HttpStatus.CREATED.value())
                        .data(accountService.create(accountDTO)).build());

    }
}
