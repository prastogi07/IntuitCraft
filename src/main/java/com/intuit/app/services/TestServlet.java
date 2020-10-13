package com.intuit.app.services;

import com.google.gson.Gson;
import com.intuit.app.controllers.UserController;
import com.intuit.app.db.SQLDBConnectionPool;
import com.intuit.app.db.statements.SQLStatements;
import com.intuit.app.models.User;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {


  private UserController userController;

  private List<String> hellowWorldArr =
      new ArrayList<String>(Arrays.asList(new String[] {"Hello", "Pooja", "barbara", "kyle"}));

  @Inject
  private TestServlet(UserController userController) {
    this.userController = userController;
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
      List<User> users = userController.getAllUsers();

      Gson gson = new Gson();
      gson.toJson(users, response.getWriter());

      // Print results from select statement
      for(User user : users) {
        System.out.println(user.getFirstName() + " " + user.getLastName());
      }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    Gson gson = new Gson();

    Reader reader = new InputStreamReader(request.getInputStream(), Charset.defaultCharset());

    User user = gson.fromJson(reader, User.class);
    if (user == null) {}

    user.setUserId(String.valueOf(UUID.randomUUID()));

    userController.saveUser(user);

//    try (Connection connection = connectionPool.getConnection()) {
//      SQLStatements sqlStatements = new SQLStatements(connection);
//      sqlStatements.createUser(user);
//
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
    response.setStatus(200);
    response.getWriter().print("user created");
  }
}
