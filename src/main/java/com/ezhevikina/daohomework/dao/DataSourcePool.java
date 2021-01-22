package com.ezhevikina.daohomework.dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourcePool {
  private static BasicDataSource basicDataSource = new BasicDataSource();

  private DataSourcePool() {}

  static {
    basicDataSource.setUsername("postgres");
    basicDataSource.setPassword("postgres");
    basicDataSource.setDriverClassName("org.postgresql.Driver");
    basicDataSource.setUrl("jdbc:postgresql://localhost:15432/atm");
    basicDataSource.setMinIdle(5);
    basicDataSource.setMaxIdle(10);
  }

  public static Connection getConnection() throws SQLException {
    return basicDataSource.getConnection();
  }
}
