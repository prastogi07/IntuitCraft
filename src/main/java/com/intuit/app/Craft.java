package com.intuit.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.intuit.app.services.TestServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Craft extends AbstractHandler {
  public void handle(
      String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);
    baseRequest.setHandled(true);
    response.getWriter().println("<h1>Hello World</h1>");
  }

  public static void main(String[] args) throws Exception {
    Injector injector = Guice.createInjector(new CraftModule());
    int port = injector.getInstance(Key.get(Integer.class, Names.named("server_port")));

    Server server = new Server(port);
    ServletContextHandler handler = new ServletContextHandler();
    handler.addServlet(new ServletHolder(injector.getInstance(TestServlet.class)), "/");

    server.setHandler(handler);
    server.start();
    server.join();
  }
}
