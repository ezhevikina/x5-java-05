package com.ezhevikina.daohomework.dao.implementation;

import com.ezhevikina.daohomework.dao.Dao;
import com.ezhevikina.daohomework.dao.DaoFactory;
import com.ezhevikina.daohomework.domain.Account;
import com.ezhevikina.daohomework.domain.StorageType;

public class AccountDaoFactory implements DaoFactory<Account> {
  @Override
  public Dao<Account> getDao(StorageType type) {
    Dao<Account> dao = null;
    switch (type) {
      case DATABASE:
        dao = new DbAccountDao();
        break;
      case JSON:
        dao = new JsonAccountDao();
        break;
      default:
        throw new UnsupportedOperationException(String.format(
            "DAO type %s is not supported", type));
    }
    return dao;
  }
}
