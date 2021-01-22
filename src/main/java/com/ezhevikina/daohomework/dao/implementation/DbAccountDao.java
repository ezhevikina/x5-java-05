package com.ezhevikina.daohomework.dao.implementation;

import com.ezhevikina.daohomework.dao.Dao;
import com.ezhevikina.daohomework.dao.DataSourcePool;
import com.ezhevikina.daohomework.dao.exceptions.DaoException;
import com.ezhevikina.daohomework.dao.exceptions.NotFoundByIdException;
import com.ezhevikina.daohomework.domain.Account;

import java.sql.*;

public class DbAccountDao implements Dao<Account> {
  private static final String CREATE_ACCOUNT = "INSERT INTO ACCOUNTS(id, holder, amount, is_active) VALUES (?, ?, ?, ?)";
  private static final String GET_ACCOUNT_BY_ID = "SELECT amount, holder, is_active FROM accounts WHERE id = ?";
  private static final String UPDATE_ACCOUNT = "UPDATE accounts SET amount = ?, holder = ?, is_active = ? WHERE id = ?";

  @Override
  public void create(Account account) throws DaoException {
    checkIfNull(account);

    try (
        Connection connection = DataSourcePool.getConnection();
        PreparedStatement statement = connection.prepareStatement(CREATE_ACCOUNT)
    ) {
      statement.setInt(1, account.getId());
      statement.setString(2, account.getHolder());
      statement.setInt(3, account.getBalance());
      statement.setBoolean(4, account.isActive());
      statement.executeUpdate();

    } catch (SQLException throwables) {
      throw new DaoException("SQL exception in DAO layer", throwables);
    }
  }

  @Override
  public Account getById(int accountId) throws NotFoundByIdException, DaoException {
    String holder;
    int amount;
    boolean isActive;

    try (
        Connection connection = DataSourcePool.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_BY_ID)
    ) {
      statement.setInt(1, accountId);
      ResultSet resultSet = statement.executeQuery();

      if (!resultSet.next()) {
        throw new NotFoundByIdException();
      }

      amount = resultSet.getInt("amount");
      holder = resultSet.getString("holder");
      isActive = resultSet.getBoolean("is_active");

      if(!isActive) {
        throw new NotFoundByIdException();
      }

      return new Account(accountId, holder, amount, isActive);

    } catch (SQLException throwables) {
      throw new DaoException("Exception in DAO layer", throwables);
    }
  }

  @Override
  public void update(Account account) throws DaoException, NotFoundByIdException {
    checkIfNull(account);
    getById(account.getId());

    try (
        Connection connection = DataSourcePool.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT)
    ) {
      statement.setInt(1, account.getBalance());
      statement.setString(2, account.getHolder());
      statement.setBoolean(3, account.isActive());
      statement.setInt(4, account.getId());
      statement.executeUpdate();

    } catch (SQLException throwables) {
      throw new DaoException("SQL Exception in DAO layer", throwables);
    }
  }

  @Override
  public void delete(int accountId) throws NotFoundByIdException, DaoException {
    Account account = getById(accountId);
    account.setActive(false);
    update(account);
  }

  private void checkIfNull(Account account) throws DaoException {
    if (account == null) {
      throw new DaoException("Created account should not be null");
    }
  }
}
