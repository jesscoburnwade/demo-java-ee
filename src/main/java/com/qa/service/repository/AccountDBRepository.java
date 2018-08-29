package com.qa.service.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.models.Accounts;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Accounts a");
		Collection<Accounts> accounts = (Collection<Accounts>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Accounts anAccount = util.getObjectForJSON(account, Accounts.class);
		manager.persist(anAccount);
		return account;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		Accounts updatedAccount = util.getObjectForJSON(accountToUpdate, Accounts.class);
		Accounts accountFromDB = manager.find(Accounts.class, id);
		if (accountToUpdate != null) {
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return accountToUpdate;
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Accounts accountInDB = manager.find(Accounts.class, id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return null;	
	}

}