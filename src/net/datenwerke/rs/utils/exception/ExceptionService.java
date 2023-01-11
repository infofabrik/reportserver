package net.datenwerke.rs.utils.exception;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

@ImplementedBy(ExceptionServiceImpl.class)
public interface ExceptionService {
   
   public static final String CONFIG_FILE = "main/error-log.cf";
   public static final String PROPERTIES = "reportExecutionException.properties.property";
   
   public enum LogProperties {
      ERROR,
      
      // report properties
      REPORT_ID, REPORT_NAME, REPORT_KEY, REPORT_TYPE, REPORT_OUTPUT_FORMAT, REPORT_UUID,
      BASE_REPORT_ID, BASE_REPORT_NAME, BASE_REPORT_KEY, BASE_REPORT_TYPE,
      
      // user properties
      EXECUTING_USER_ID, EXECUTING_USER_USERNAME, EXECUTING_USER_FIRSTNAME, EXECUTING_USER_LASTNAME,
      
      // general properties
      JAVA_VERSION, JAVA_VM_ARGUMENTS, APPLICATION_SERVER, OS_VERSION, REPORTSERVER_VERSION, LOCALE, JVM_LOCALE,
      SUPPORTED_SSL_PROTOCOLS, DEFAULT_SSL_PROTOCOLS, ENABLED_SSL_PROTOCOLS, 
      GROOVY_VERSION, STATIC_PAMS,
      
      // memory properties
      MEMORY_USED, MEMORY_FREE, MEMORY_TOTAL, MEMORY_MAX
   }
   
   String exceptionToString(Throwable e);

   String reportExecutionExceptionDetailsMessage(Throwable e, Report report, User user, String outputFormat, String uuid);

}