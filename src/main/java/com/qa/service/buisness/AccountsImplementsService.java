package com.qa.service.buisness;

import javax.inject.Inject;

import com.qa.service.repository.AccountRepository;

public class AccountsImplementsService implements AccountsService {
	
	@Inject
	private AccountRepository accountRepo;

	@Override
	public String getAllAccounts() {
		return null;
		
	}

	@Override
	public String createAccount(String account) {
		if (account == "9999") {
			return "{“message”: “This account is blocked”}";
		} else {
			
		}
		accountRepo.createAccount(account);
		return account;
		
	}

	@Override
	public String updateAccount(Long id, String accountToUpdate) {
		accountRepo.updateAccount(id, accountToUpdate);
		return accountToUpdate;
	}

	@Override
	public String deleteAccount(Long id) {
		accountRepo.deleteAccount(id);
		return null;
	}
	
	
}
