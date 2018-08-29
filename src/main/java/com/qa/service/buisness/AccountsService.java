package com.qa.service.buisness;

public interface AccountsService{
	
	String getAllAccounts();
	String createAccount(String account);
	String updateAccount(Long id, String accountToUpdate);
	String deleteAccount(Long id);

	
}
