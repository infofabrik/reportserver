package net.datenwerke.rs.core.service.reportmanager.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;

import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;

public class ReportExecutorException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = -3738087665438361986L;

   private String details;
   
   public ReportExecutorException(String msg) {
      super(msg);
   }
   
   public ReportExecutorException(String msg, String details, Throwable cause) {
      super(ReportManagerMessages.INSTANCE
            .exceptionReportCouldNotBeExecuted(null != cause ? ExceptionUtils.getRootCauseMessage(cause) : ""), cause);
      this.details = details;
   }

   public ReportExecutorException(Throwable cause) {
      super(ReportManagerMessages.INSTANCE.exceptionReportCouldNotBeExecuted(null != cause ? cause.getMessage() : ""),
            cause);
   }

   public ReportExecutorException(String msg, Throwable cause) {
      super(msg, cause);
   }
   
   public String getDetails() {
      return details;
   }
}
