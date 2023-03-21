package net.datenwerke.rs;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.core.service.internaldb.locale.InternalDbMessages;

public class GeneralInfoHibernateCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<EnvironmentValidatorHelperService> envServiceProvider;
   private final Provider<DatasourceHelperService> datasourceHelperServiceProvider;
   
   private final static String HIBERNATE_DIALECT = "HIBERNATE_DIALECT";
   private final static String HIBERNATE_DEFAULT_SCHEMA = "HIBERNATE_DEFAULT_SCHEMA";
   private final static String DATABASE_NAME = "DATABASE_NAME";
   private final static String DATABASE_VERSION = "DATABASE_VERSION";
   private final static String JDBC_DRIVER_NAME = "JDBC_DRIVER_NAME";
   private final static String JDBC_DRIVER_VERSION = "JDBC_DRIVER_VERSION";
   private final static String JDBC_MAJOR_VERSION = "JDBC_MAJOR_VERSION";
   private final static String JDBC_MINOR_VERSION = "JDBC_MINOR_VERSION";
   private final static String JDBC_URL = "JDBC_URL";
   private final static String JDBC_USERNAME = "JDBC_USERNAME";
   private final static String SCHEMA_VERSION = "SCHEMA_VERSION";
   private final static String DB = "DB";
   
   @Inject
   public GeneralInfoHibernateCategoryProviderHooker(
         Provider<EnvironmentValidatorHelperService> envServiceProvider,
         Provider<DatasourceHelperService> datasourceHelperServiceProvider
         ) {
      this.envServiceProvider = envServiceProvider;
      this.datasourceHelperServiceProvider = datasourceHelperServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      final Map<ImmutablePair<String, String>, Object> props = new LinkedHashMap<>();

      final Properties jpaProperties = envServiceProvider.get().getJpaProperties();
      props.put(ImmutablePair.of(HIBERNATE_DIALECT, "hibernate.dialect"), jpaProperties.getProperty("hibernate.dialect"));
      props.put(ImmutablePair.of(HIBERNATE_DEFAULT_SCHEMA, "hibernate.default_schema"),
            jpaProperties.getProperty("hibernate.default_schema"));

      DatasourceHelperService datasourceHelperService = datasourceHelperServiceProvider.get();

      try {
         final Map<String, Object> datasourceMetadata = datasourceHelperService.fetchInfoDatasourceMetadata(
               jpaProperties.getProperty("hibernate.connection.driver_class"),
               jpaProperties.getProperty("hibernate.connection.url"),
               jpaProperties.getProperty("hibernate.connection.username"),
               jpaProperties.getProperty("hibernate.connection.password"), true, true, false, false);
         props.put(ImmutablePair.of(DATABASE_NAME, InternalDbMessages.INSTANCE.internalDbName()),
               datasourceMetadata.get("getDatabaseProductName").toString());
         props.put(ImmutablePair.of(DATABASE_VERSION, InternalDbMessages.INSTANCE.internalDbVersion()),
               datasourceMetadata.get("getDatabaseProductVersion").toString());
         props.put(ImmutablePair.of(JDBC_DRIVER_NAME, InternalDbMessages.INSTANCE.internalDbDriverName()),
               datasourceMetadata.get("getDriverName").toString());
         props.put(ImmutablePair.of(JDBC_DRIVER_VERSION, InternalDbMessages.INSTANCE.internalDbDriverVersion()),
               datasourceMetadata.get("getDriverVersion").toString());
         props.put(ImmutablePair.of(JDBC_MAJOR_VERSION, InternalDbMessages.INSTANCE.internalDbJdbcMajorVersion()),
               datasourceMetadata.get("getJDBCMajorVersion"));
         props.put(ImmutablePair.of(JDBC_MINOR_VERSION, InternalDbMessages.INSTANCE.internalDbJdbcMinorVersion()),
               datasourceMetadata.get("getJDBCMinorVersion"));
         props.put(ImmutablePair.of(JDBC_URL, InternalDbMessages.INSTANCE.internalDbJdbcUrl()),
               datasourceMetadata.get("getURL").toString());
         props.put(ImmutablePair.of(JDBC_USERNAME, InternalDbMessages.INSTANCE.internalDbUsername()),
               datasourceMetadata.get("getUserName").toString());
         setSchemaVersion(props);
      } catch (Exception e) {
         props.put(ImmutablePair.of(DATABASE_NAME, InternalDbMessages.INSTANCE.internalDbName()),
               ExceptionUtils.getRootCauseMessage(e));
      }
      
      return Collections.singletonMap(ImmutablePair.of(DB, InternalDbMessages.INSTANCE.dbConfiguration()), props);
   }
   
   private void setSchemaVersion(Map<ImmutablePair<String, String>, Object> props) {
      try {
         props.put(ImmutablePair.of(SCHEMA_VERSION, InternalDbMessages.INSTANCE.schemaVersion()),
               envServiceProvider.get().getSchemaVersion());
      } catch (SQLException e) {
         props.put(ImmutablePair.of(SCHEMA_VERSION, InternalDbMessages.INSTANCE.schemaVersion()),
               "Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")");
      }
   }

}
