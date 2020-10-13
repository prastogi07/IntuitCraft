package com.intuit.app.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLDBConnectionPool {
  private final HikariConfig config;

  private HikariDataSource ds;

  public SQLDBConnectionPool() {
    config = new HikariConfig();
    config.setJdbcUrl("jdbc:mysql://localhost/craft?serverTimezone=UTC");
    config.setUsername("my_user");
    config.setPassword("s1mplest1");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
  }

  public void connect() {
    ds = new HikariDataSource(config);
  }

  public Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
