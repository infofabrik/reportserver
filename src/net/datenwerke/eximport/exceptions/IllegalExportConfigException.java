package net.datenwerke.eximport.exceptions;

public class IllegalExportConfigException extends ExportException {

   /**
    * 
    */
   private static final long serialVersionUID = -7159839444159564248L;

   public IllegalExportConfigException() {
      super("Configuration is illegal.");
   }

   public IllegalExportConfigException(String msg) {
      super(msg);
   }

   public IllegalExportConfigException(Throwable cause) {
      super("Configuration is illegal.", cause);
   }

   public IllegalExportConfigException(String msg, Throwable cause) {
      super(msg, cause);
   }

}
