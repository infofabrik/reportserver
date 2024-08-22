package net.datenwerke.rs.fileserver.server.fileserver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface IndexRedirectHook extends Hook {

   boolean consumes(HttpServletRequest req, HttpServletResponse resp);

   void doGet(HttpServletRequest req, HttpServletResponse resp);

   boolean isLast();

}
