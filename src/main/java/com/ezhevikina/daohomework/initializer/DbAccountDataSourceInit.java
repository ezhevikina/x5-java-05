package com.ezhevikina.daohomework.initializer;

import com.ezhevikina.daohomework.dao.DataSourcePool;
import com.ezhevikina.daohomework.initializer.exceptions.DataSourceInitException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class DbAccountDataSourceInit {

  public void init() throws DataSourceInitException {
    try (
        Connection connection = DataSourcePool.getConnection()
    ) {
      Statement statement = connection.createStatement();
      statement.executeUpdate(readSchemaFile("./src/main/resources/schema.sql"));
    } catch (SQLException throwables) {
      throw new DataSourceInitException("Exception when initializing Account Db", throwables);
    }
  }

  private String readSchemaFile(String filePath) throws DataSourceInitException {
    StringBuilder schemaBuilder = new StringBuilder();

    try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
      stream.forEach(s -> schemaBuilder.append(s).append("\n"));
    } catch (IOException e) {
      throw new DataSourceInitException("Exception when reading schema file", e);
    }

    return schemaBuilder.toString();
  }
}
