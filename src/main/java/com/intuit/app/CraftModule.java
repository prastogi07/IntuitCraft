package com.intuit.app;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.intuit.app.db.SQLDBConnectionPool;

public class CraftModule extends AbstractModule {

  @Override
  protected void configure() {}

  @Singleton
  @Named("server_port")
  @Provides
  private Integer providesServerPort() {
    return 8000;
  }

  @Singleton
  @Provides
  private SQLDBConnectionPool providesConnectionPool() {
    SQLDBConnectionPool connectionPool = new SQLDBConnectionPool();
    connectionPool.connect();
    return connectionPool;
  }
}
