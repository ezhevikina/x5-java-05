package com.ezhevikina.daohomework.initializer;

import com.ezhevikina.daohomework.dao.Dao;
import com.ezhevikina.daohomework.dao.exceptions.DaoException;
import com.ezhevikina.daohomework.domain.Account;
import com.ezhevikina.daohomework.initializer.exceptions.DataSourceInitException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

@SuppressWarnings("unchecked")
public class JsonAccountDataSourceInit {
  private String filePath = "./src/main/resources/accounts.json";
  private Dao<Account> dao;

  public void init(Dao<Account> dao) throws DataSourceInitException {
    this.dao = dao;

    File accounts = new File(filePath);

    try {
      Files.deleteIfExists(accounts.toPath());


      if (accounts.createNewFile()) {
        JSONObject accountsList = new JSONObject();
        accountsList.put("accounts", new JSONArray());

        try (
            FileWriter file = new FileWriter(filePath)
        ) {
          file.write(accountsList.toJSONString());

        } catch (IOException e) {
          throw new DataSourceInitException("IOException when initializing accounts.json", e);
        }

        fillWithData();
      }
    } catch (IOException e) {
      throw new DataSourceInitException("IOException when initializing accounts.json", e);
    } catch (DaoException e) {
      throw new DataSourceInitException("DAO when when filling accounts.json with data", e);
    }
  }

  private void fillWithData() throws DaoException {
    for (int i = 1; i <= 5; i++) {
      dao.create(new Account(
          i, "HolderName" + i, 100 * i, true));
    }
  }
}
