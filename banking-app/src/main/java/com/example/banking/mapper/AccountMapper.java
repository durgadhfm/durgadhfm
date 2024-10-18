package com.example.banking.mapper;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;

public class AccountMapper {
	
	public static Account mapToEntity(AccountDto accountDto) {
		
		
		Account account = new Account(accountDto.getId(),
				accountDto.getAccountHolderName(),accountDto.getBalance());
		
		return account;
	}
	
	
	public static AccountDto mapToDto(Account account) {
		
		AccountDto accountDto = new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());
		
		return accountDto;
		
	}

}
