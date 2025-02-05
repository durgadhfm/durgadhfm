package com.example.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	
	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		
		this.accountRepository = accountRepository;
		
	}
	
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account mapToEntity = AccountMapper.mapToEntity(accountDto);	
		Account account = accountRepository.save(mapToEntity);	
		return AccountMapper.mapToDto(account);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));

		return AccountMapper.mapToDto(account);
	}


	@Override
	public AccountDto deposit(Long id, double amount) {		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account);	
		return AccountMapper.mapToDto(saveAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Amount");
		}
		
		double total = account.getBalance()-amount;
		
		account.setBalance(total);
		
		Account withdrawSave = accountRepository.save(account);
		
		return AccountMapper.mapToDto(withdrawSave);
	}


	@Override
	public List<AccountDto> getAllAccounts() {		
		List<Account> findAll = accountRepository.findAll();				
		return findAll.stream().map(t -> AccountMapper.mapToDto(t)).collect(Collectors.toList());
	}


	@Override
	public void deleteAccount(Long accountId) {
		accountRepository.deleteById(accountId);
		
	}
	
	
	
	


}
