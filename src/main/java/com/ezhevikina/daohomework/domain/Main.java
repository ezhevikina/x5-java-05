package com.ezhevikina.daohomework.domain;

import com.ezhevikina.daohomework.dao.Dao;
import com.ezhevikina.daohomework.dao.implementation.AccountDaoFactory;
import com.ezhevikina.daohomework.initializer.exceptions.DataSourceInitException;
import com.ezhevikina.daohomework.initializer.AccountInitializer;
import com.ezhevikina.daohomework.service.AccountManager;

public class Main {
  public static void main(String[] args) {
    StorageType accountsStorageType = StorageType.JSON;

    try {
      Dao<Account> dao = new AccountDaoFactory().getDao(accountsStorageType);
      AccountInitializer initializer = new AccountInitializer();
      initializer.init(accountsStorageType, dao);
      AtmScreen atm = new AtmScreen(new AccountManager(dao));
      atm.start();
    } catch (DataSourceInitException e) {
      e.printStackTrace();
    }
  }
}
