package com.ezhevikina.daohomework.initializer;

import com.ezhevikina.daohomework.dao.Dao;
import com.ezhevikina.daohomework.domain.Account;
import com.ezhevikina.daohomework.domain.StorageType;
import com.ezhevikina.daohomework.initializer.exceptions.DataSourceInitException;

/**
 * Класс для инициализации и заполнения хранилища данных
 */
public class AccountInitializer {

  /**
   * Метод создания и заполнения хранилища данных
   * @param type тип хранилища данных
   * @param dao Data Access Object интерфейс для работы с Account
   * @throws DataSourceInitException - ошибка при создании или заполнении хранилища данных
   */
  public void init(StorageType type, Dao<Account> dao)
      throws DataSourceInitException {
    switch (type) {
      case DATABASE:
        DbAccountDataSourceInit db = new DbAccountDataSourceInit();
        db.init();
        break;
      case JSON:
        JsonAccountDataSourceInit json = new JsonAccountDataSourceInit();
        json.init(dao);
        break;
      default:
        throw new UnsupportedOperationException(String.format(
            "Initializer type '%s' is not supported.", type));
    }
  }
}

