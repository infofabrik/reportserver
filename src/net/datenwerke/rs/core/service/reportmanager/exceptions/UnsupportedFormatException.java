package net.datenwerke.rs.core.service.reportmanager.exceptions;

import java.util.Arrays;

public class UnsupportedFormatException extends ReportExecutorRuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = 5665809762679791270L;

   public UnsupportedFormatException(String[] allowedFormats, String requestedFormat) {
      super("Unsupported format " + requestedFormat.toString() + " encountered. The following formats are supported: " //$NON-NLS-1$ //$NON-NLS-2$
            + Arrays.toString(allowedFormats));
   }
}
