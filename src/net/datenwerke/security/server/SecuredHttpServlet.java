package net.datenwerke.security.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

import net.datenwerke.security.service.security.annotation.SecurityChecked;

/**
 * Remember to make doGet and doPost public for the security check to take
 * effect.
 * 
 */
@Singleton
@SecurityChecked(bypassInheritedMethods = true)
public class SecuredHttpServlet extends HttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = -9144544932195265859L;

   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      super.doGet(req, resp);
   }

   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      super.doPost(req, resp);
   }

}
