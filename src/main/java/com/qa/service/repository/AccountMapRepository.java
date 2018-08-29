package com.qa.service.repository;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.models.Accounts;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountMapRepository implements AccountRepository {

	private Map<Long, Accounts> accountsMap;
	private Long ID;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllAccounts() {	
		return util.getJSONForObject(accountsMap.values());
	}

	@Override
	public String createAccount(String account) {
		Accounts creatingAccounts = util.getObjectForJSON(account, Accounts.class);
		accountsMap.put(ID, creatingAccounts);
		return account;
		
	}

	@Override
	public String updateAccount(Long id, String accountToUpdate) {
		Accounts updatingAccounts = util.getObjectForJSON(accountToUpdate, Accounts.class);
		accountsMap.put(id, updatingAccounts);
		return accountToUpdate;
	}

	@Override
	public String deleteAccount(Long id) {
		accountsMap.remove(id);
		return null;
	}


}