package net.datenwerke.rs.core.server.versioninfo;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

@Singleton
public class VersionInfoServlet extends HttpServlet {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private static final long serialVersionUID = -3667983893196665074L;

   private HookHandlerService hookHandler;

   @Inject
   public VersionInfoServlet(
         HookHandlerService hookHandler
         ) {
      this.hookHandler = hookHandler;
   }

   @Override
   protected void doGet(final HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         InputStream propfile = getClass().getClassLoader().getResourceAsStream("rsversion.properties");
         Properties p = new Properties();
         p.load(propfile);

         resp.setContentType("text/plain");

         String style = "full";
         if (req.getParameterMap().containsKey("style"))
            style = req.getParameter("style");

         PrintWriter pw = new PrintWriter(resp.getOutputStream());
         if ("full".equals(style)) {
            propfile = getClass().getClassLoader().getResourceAsStream("rsversion.properties");
            pw.print(IOUtils.toString(propfile));

         } else if ("num".equals(style)) {
            pw.print(p.get("version"));

         } else if ("banner".equals(style)) {
            String date = p.getProperty("buildDate");
            pw.print(p.getProperty("version") + "<br/>" + date.substring(date.indexOf("-") + 1));

         } else if ("ext".equals(style)) {
            Map<String, Object> res = new HashMap<>();
            res.put("version.num", p.get("version"));
            String date = p.getProperty("buildDate");
            res.put("version.banner", p.getProperty("version") + "<br/>" + date.substring(date.indexOf("-") + 1));

            res.putAll(
               hookHandler.getHookers(VersionInfoExtensionHook.class)
                  .stream()
                  .collect(toMap(VersionInfoExtensionHook::getKey, vie -> vie.getValue(req.getParameterMap()))));
            
            String json = new ObjectMapper().writeValueAsString(res);

            if (req.getParameterMap().containsKey("callback")) {
               json = req.getParameter("callback") + "(" + json + ");";
            }
            pw.print(json);
         }

         pw.close();
      } catch (Exception e) {
         logger.debug("Could not read version file", e);
      }
   }

}
