package com.intuit.app.db.statements;

import com.intuit.app.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class SQLStatements {

  Connection con;

  @Inject
  public SQLStatements(Connection con) {
    this.con = con;
  }

  private PreparedStatement GET_USER;
  private PreparedStatement CREATE_USER;
  private PreparedStatement UPDATE_USER;
  private PreparedStatement GET_ALL_USERS;

  public void getUser(String userId) throws SQLException {
    if (GET_USER == null) {
      GET_USER = con.prepareStatement("select * from user where user_id=?");
    }
    GET_USER.setString(1, userId);
    ResultSet rs = GET_USER.executeQuery();
  }

  public void updateUser(User user) throws SQLException {
    if (UPDATE_USER == null) {
      UPDATE_USER =
          con.prepareStatement("update user set first_name= ?, last_name=? where user_id= ?");
    }
    UPDATE_USER.setString(1, user.getFirstName());
    UPDATE_USER.setString(2, user.getLastName());
    UPDATE_USER.setString(3, user.getUserId());
    UPDATE_USER.executeUpdate();
  }

  public void createUser(User user) throws SQLException {
    if (CREATE_USER == null) {
      CREATE_USER =
          con.prepareStatement("insert into user (first_name, last_name, user_id) values(?,?,?)");
    }
    CREATE_USER.setString(1, user.getFirstName());
    CREATE_USER.setString(2, user.getLastName());
    CREATE_USER.setString(3, user.getUserId());
    CREATE_USER.executeUpdate();
  }

  public List<User> getAllUsers() throws SQLException {
    List<User> allUsers = new ArrayList<>();
    if(GET_ALL_USERS == null){
      GET_ALL_USERS = con.prepareStatement("select * from user");
    }
    ResultSet rs = GET_ALL_USERS.executeQuery();
    while(rs.next()){
      User user = new User(rs.getString("first_name"),rs.getString("last_name"),rs.getString("user_id"));
      allUsers.add(user);
    }
    return allUsers;
  }
}
