package net.datenwerke.rs.core.service.reportserver;

/**
 * 
 *
 */
public interface ReportServerService {
   public static final String CONFIG_FILE = "main/main.cf";
   public static final String DEFAULT_CHARSET = "UTF-8";

   /**
    * 
    */
   public String getCharset();

   /**
    * Provides information on the server. This information is only available if a
    * user is logged in.
    */
   public ServerInfoContainer getServerInfo();
}
