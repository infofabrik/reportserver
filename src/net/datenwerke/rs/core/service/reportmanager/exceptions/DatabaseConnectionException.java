package net.datenwerke.rs.core.service.reportmanager.exceptions;

import org.apache.commons.lang3.StringUtils;

public class DatabaseConnectionException extends ReportManagerRuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = -2032418006003893781L;

   public DatabaseConnectionException(String url, String username, Throwable e) {
      super("Could not open connection to: " + url + " with user: " + username + ". " //$NON-NLS-1$ //$NON-NLS-2$
            + StringUtils.left(e.getMessage(), 200));
      initCause(e);
   }

   public DatabaseConnectionException(String message) {
      super(message);
   }
}
