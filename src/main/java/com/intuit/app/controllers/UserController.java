package com.intuit.app.controllers;

import com.intuit.app.db.SQLDBConnectionPool;
import com.intuit.app.db.statements.SQLStatements;
import com.intuit.app.models.User;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserController {


  private SQLDBConnectionPool connectionPool;

  @Inject
  public UserController(SQLDBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }


  public List<User> getAllUsers() {
    try (Connection connection = connectionPool.getConnection()) {
      SQLStatements sqlStatements = new SQLStatements(connection);
      return sqlStatements.getAllUsers();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void saveUser(User user){
    try (Connection connection = connectionPool.getConnection()) {
      SQLStatements sqlStatements = new SQLStatements(connection);
       sqlStatements.createUser(user);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
