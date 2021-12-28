package net.datenwerke.rs.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class ExceptionServicesImpl implements ExceptionServices {

   /*
    * (non-Javadoc)
    * 
    * @see
    * net.datenwerke.rs.services.utils.ExceptionServices#exceptionToString(java.
    * lang.Throwable)
    */
   public String exceptionToString(Throwable e) {
      Writer result = new StringWriter();
      PrintWriter printWriter = new PrintWriter(result);
      e.printStackTrace(printWriter);
      return result.toString();
   }
}
