package com.ezhevikina.daohomework.dao;

import com.ezhevikina.daohomework.dao.exceptions.DaoException;
import com.ezhevikina.daohomework.domain.StorageType;

public interface DaoFactory<T> {
  Dao<T> getDao(StorageType type) throws DaoException;
}
