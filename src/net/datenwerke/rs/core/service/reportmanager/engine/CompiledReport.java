package net.datenwerke.rs.core.service.reportmanager.engine;

import java.io.Serializable;

/**
 * 
 *
 */
public interface CompiledReport extends Serializable {

   /**
    * Returns the actual report.
    */
   public Object getReport();

   /**
    * Returns the mime type of this report.
    */
   public String getMimeType();

   /**
    * Returns a file extension for files of this type
    */
   public String getFileExtension();

   /**
    * Returns true if this object contains reporting data.
    */
   public boolean hasData();

   /**
    * Returns true if the report is String based (in contrast to binary)
    */
   public boolean isStringReport();
}
