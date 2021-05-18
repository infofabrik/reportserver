package net.datenwerke.rs.core.service.reportserver;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.SessionScoped;

/**
 * This object exposes information about the server gathered after the user
 * logged in.
 * 
 *
 */
@SessionScoped
public class ServerInfoContainer implements Serializable {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private static final long serialVersionUID = -4485442419955859152L;

   private String serverName;
   private String localAddr;
   private int serverPort;
   private int localPort;
   private String remoteAddr;
   private int remotePort;
   private String remoteHost;
   private String remoteUser;
   private String requestURI;
   private String requestURL;
   private String contextPath;
   private String queryString;
   private String scheme;
   private String servletPath;
   private String pathInfo;
   private String baseUrl;

   void init(HttpServletRequest httpServletRequest) {
      serverName = httpServletRequest.getServerName();
      serverPort = httpServletRequest.getServerPort();
      servletPath = httpServletRequest.getServletPath();
      scheme = httpServletRequest.getScheme();

      pathInfo = httpServletRequest.getPathInfo();

      localAddr = httpServletRequest.getLocalAddr();
      localPort = httpServletRequest.getLocalPort();

      remoteAddr = httpServletRequest.getRemoteAddr();
      remotePort = httpServletRequest.getRemotePort();
      remoteHost = httpServletRequest.getRemoteHost();
      remoteUser = httpServletRequest.getRemoteUser();

      requestURI = httpServletRequest.getRequestURI();
      requestURL = httpServletRequest.getRequestURL().toString();

      contextPath = httpServletRequest.getContextPath();

      queryString = httpServletRequest.getQueryString();

      /* get base uri */
      try {
         StringBuffer url = new StringBuffer();
         url.append(scheme).append("://").append(serverName);

         if ((serverPort != 80) && (serverPort != 443))
            url.append(":").append(serverPort);

         String path = servletPath.contains("/") ? servletPath.substring(0, servletPath.lastIndexOf("/")) : servletPath;

         url.append(contextPath).append(path);

         if (pathInfo != null)
            url.append(pathInfo);
         if (queryString != null)
            url.append("?").append(queryString);

         baseUrl = url.toString();
      } catch (Exception e) {
         logger.warn("Could not retrieve base url", e);
      }
   }

   public String getServerName() {
      return serverName;
   }

   public String getLocalAddr() {
      return localAddr;
   }

   public int getServerPort() {
      return serverPort;
   }

   public int getLocalPort() {
      return localPort;
   }

   public String getRemoteAddr() {
      return remoteAddr;
   }

   public int getRemotePort() {
      return remotePort;
   }

   public String getRemoteHost() {
      return remoteHost;
   }

   public String getRemoteUser() {
      return remoteUser;
   }

   public String getRequestURI() {
      return requestURI;
   }

   public String getRequestURL() {
      return requestURL;
   }

   public String getContextPath() {
      return contextPath;
   }

   public String getBaseURL() {
      return baseUrl;
   }
}
