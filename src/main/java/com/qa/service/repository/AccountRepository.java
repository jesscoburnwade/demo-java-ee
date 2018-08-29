package com.qa.service.repository;

public interface AccountRepository {
	
	String getAllAccounts();
	String createAccount(String account);
	String updateAccount(Long id, String accountToUpdate);
	String deleteAccount(Long id);

}
