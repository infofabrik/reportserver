package net.datenwerke.rs.utils.exception;

import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_AUTHENTICATION;
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_DATASOURCE;
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_DATASOURCE_DATABASE;
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_ERROR;
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_EXECUTING_USER;
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_GENERAL;
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_MEMORY;
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_REPORT;
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_SECURITY;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

@ImplementedBy(ExceptionServiceImpl.class)
public interface ExceptionService {
   
   public static final String CONFIG_FILE = "main/error-log.cf";
   public static final String PROPERTIES = "reportExecutionException.properties.property";
   
   public enum LogPropertyType {
      TYPE_ERROR, TYPE_REPORT, TYPE_EXECUTING_USER, TYPE_GENERAL, TYPE_AUTHENTICATION, TYPE_SECURITY, TYPE_MEMORY,
      TYPE_DATASOURCE, TYPE_DATASOURCE_DATABASE
   }
   
   public enum LogProperty {
      
      // error description
      ERROR(TYPE_ERROR),
      
      // report properties
      REPORT_ID(TYPE_REPORT), REPORT_NAME(TYPE_REPORT), REPORT_KEY(TYPE_REPORT), REPORT_TYPE(TYPE_REPORT), 
      REPORT_OUTPUT_FORMAT(TYPE_REPORT), REPORT_UUID(TYPE_REPORT),
      // base report properties
      BASE_REPORT_ID(TYPE_REPORT), BASE_REPORT_NAME(TYPE_REPORT), BASE_REPORT_KEY(TYPE_REPORT), BASE_REPORT_TYPE(TYPE_REPORT),
      
      // user properties
      EXECUTING_USER_ID(TYPE_EXECUTING_USER), EXECUTING_USER_USERNAME(TYPE_EXECUTING_USER), 
      EXECUTING_USER_FIRSTNAME(TYPE_EXECUTING_USER), EXECUTING_USER_LASTNAME(TYPE_EXECUTING_USER),
      
      // general properties
      JAVA_VERSION(TYPE_GENERAL), JAVA_HOME(TYPE_GENERAL), JAVA_VM_ARGUMENTS(TYPE_GENERAL), 
      APPLICATION_SERVER(TYPE_GENERAL), 
      CATALINA_HOME(TYPE_GENERAL), CATALINA_BASE(TYPE_GENERAL), LOG_FILES_DIRECTORY(TYPE_GENERAL),
      INSTALLATION_PATH(TYPE_GENERAL), HEADLESS(TYPE_GENERAL),
      
      REST_URL(TYPE_GENERAL), REQUEST_URL(TYPE_GENERAL), REQUEST_SCHEME(TYPE_GENERAL), 
      REQUEST_SERVER_NAME(TYPE_GENERAL), REQUEST_SERVER_PORT(TYPE_GENERAL), 
      REQUEST_CONTEXT_PATH(TYPE_GENERAL), REQUEST_PROTOCOL(TYPE_GENERAL),
      
      CONFIG_DIRECTORY(TYPE_GENERAL), IO_TMP_DIRECTORY(TYPE_GENERAL),
      JVM_SERVER_TIME(TYPE_GENERAL),
      OS_VERSION(TYPE_GENERAL), REPORTSERVER_VERSION(TYPE_GENERAL),
      LOCALE(TYPE_GENERAL), 
      JVM_LOCALE(TYPE_GENERAL),
      JVM_USER_LANGUAGE(TYPE_GENERAL), JVM_USER_COUNTRY(TYPE_GENERAL), JVM_USER_TIMEZONE(TYPE_GENERAL),
      JVM_FILE_ENCODING(TYPE_GENERAL),
      GROOVY_VERSION(TYPE_GENERAL), 
      EXTERNAL_LIBS(TYPE_GENERAL), INTERNAL_LIBS(TYPE_GENERAL),
      
      // authentication properties
      ACTIVE_PAMS(TYPE_AUTHENTICATION),
      
      // security properties
      KNOWN_HOSTS_FILE(TYPE_SECURITY),
      SUPPORTED_SSL_PROTOCOLS(TYPE_SECURITY), DEFAULT_SSL_PROTOCOLS(TYPE_SECURITY), ENABLED_SSL_PROTOCOLS(TYPE_SECURITY), 
      
      // memory properties
      MEMORY_USED(TYPE_MEMORY), MEMORY_FREE(TYPE_MEMORY), MEMORY_TOTAL(TYPE_MEMORY), MEMORY_MAX(TYPE_MEMORY),
      
      // datasource properties
      DATASOURCE_ID(TYPE_DATASOURCE), DATASOURCE_NAME(TYPE_DATASOURCE), DATASOURCE_PATH(TYPE_DATASOURCE),
      DATASOURCE_TYPE(TYPE_DATASOURCE),
      
      // db datasource properties
      DATASOURCE_DATABASE_QUERY(TYPE_DATASOURCE_DATABASE), DATASOURCE_DATABASE_INFORMATION(TYPE_DATASOURCE_DATABASE),
      DATASOURCE_DATABASE_JDBC_PROPERTIES(TYPE_DATASOURCE_DATABASE);
      
      private final LogPropertyType type;
      
      LogProperty(LogPropertyType type) {
         this.type = type;
      }
      
      public LogPropertyType getType() {
         return type;
      }
   }
   
   String exceptionToString(Throwable e);

   String reportExecutionExceptionDetailsMessage(Throwable e, Report report, User user, String outputFormat, String uuid);
   
   Optional<String> getReportExecutionExceptionDetailsMessage(Throwable e);

}