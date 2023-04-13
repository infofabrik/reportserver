package net.datenwerke.rs.core.service.internaldb.hookers;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.GeneralInfoUiModule;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.core.service.internaldb.locale.InternalDbMessages;
import net.datenwerke.rs.utils.properties.PropertiesUtilService;

public class InternalDbCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<TempTableService> tempTableServiceProvider;
   private final Provider<DatasourceHelperService> datasourceHelperServiceProvider;
   private final Provider<HistoryService> historyServiceProvider;
   
   private static final String INTERNAL_DATASOURCE_DISABLED_MSG = "INTERNAL_DATASOURCE_DISABLED_MSG";
   private static final String INTERNAL_DATASOURCE_ENABLED = "INTERNAL_DATASOURCE_ENABLED";
   private static final String INTERNAL_DS_ID = "INTERNAL_DS_ID";
   private static final String INTERNAL_DS_PATH = "INTERNAL_DS_PATH";
   private static final String INTERNAL_DS_NAME = "INTERNAL_DS_NAME";
   private static final String INTERNAL_DS_DB_NAME = "INTERNAL_DS_DB_NAME";
   private static final String INTERNAL_DS_DB_VERSION = "INTERNAL_DS_DB_VERSION";
   private static final String INTERNAL_DS_JDBC_DRIVER_NAME = "INTERNAL_DS_JDBC_DRIVER_NAME";
   private static final String INTERNAL_DS_JDBC_DRIVER_VERSION = "INTERNAL_DS_JDBC_DRIVER_VERSION";
   private static final String INTERNAL_DS_JDBC_MAJOR_VERSION = "INTERNAL_DS_JDBC_MAJOR_VERSION";
   private static final String INTERNAL_DS_JDBC_MINOR_VERSION = "INTERNAL_DS_JDBC_MINOR_VERSION";
   private static final String INTERNAL_DS_JDBC_URL = "INTERNAL_DS_JDBC_URL";
   private static final String INTERNAL_DS_JDBC_USERNAME = "INTERNAL_DS_JDBC_USERNAME";
   private static final String INTERNAL_DS_JDBC_PROPERTIES = "INTERNAL_DS_JDBC_PROPERTIES";
   private static final String INTERNAL_DS_ERROR = "INTERNAL_DS_ERROR";
   private static final String INTERNAL_DATASOURCE = "INTERNAL_DATASOURCE";
   
   @Inject
   public InternalDbCategoryProviderHooker(
         Provider<TempTableService> tempTableServiceProvider,
         Provider<DatasourceHelperService> datasourceHelperServiceProvider,
         Provider<HistoryService> historyServiceProvider
         ) {
      this.tempTableServiceProvider = tempTableServiceProvider;
      this.datasourceHelperServiceProvider = datasourceHelperServiceProvider;
      this.historyServiceProvider = historyServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      DatasourceHelperService datasourceHelperService = datasourceHelperServiceProvider.get();
      
      final Map<ImmutablePair<String, String>, Object> props = new LinkedHashMap<>();
      
      DatabaseDatasource internalDbDatasource = tempTableServiceProvider.get().getInternalDbDatasource();
      props.put(ImmutablePair.of(INTERNAL_DATASOURCE_DISABLED_MSG, GeneralInfoUiModule.DISABLED_MESSAGE),
            InternalDbMessages.INSTANCE.noInternalDbFound());
      
      if (null == internalDbDatasource) {
         props.put(ImmutablePair.of(INTERNAL_DATASOURCE_ENABLED, GeneralInfoUiModule.ENABLED), false);
         props.put(ImmutablePair.of(INTERNAL_DS_DB_NAME, InternalDbMessages.INSTANCE.internalDatasourceName()),
               InternalDbMessages.INSTANCE.noInternalDbFound());
         return Collections.singletonMap(
               ImmutablePair.of(INTERNAL_DATASOURCE, InternalDbMessages.INSTANCE.internalDatasource()), props);
      }
      
      try {
         Map<String, Object> datasourceMetadata = datasourceHelperService
               .fetchInfoDatasourceMetadata(internalDbDatasource, true, true, false, false);
         
         props.put(ImmutablePair.of(INTERNAL_DATASOURCE_ENABLED, GeneralInfoUiModule.ENABLED), true);
         props.put(ImmutablePair.of(INTERNAL_DS_NAME, InternalDbMessages.INSTANCE.internalDatasourceName()),
               internalDbDatasource.getName());
         props.put(ImmutablePair.of(INTERNAL_DS_ID, InternalDbMessages.INSTANCE.internalDatasourceId()),
               internalDbDatasource.getId());
         final List<String> paths = historyServiceProvider.get().getFormattedObjectPaths(internalDbDatasource);
         props.put(ImmutablePair.of(INTERNAL_DS_PATH, InternalDbMessages.INSTANCE.internalDatasourcePath()),
               paths.isEmpty() ? "path not found" : paths.get(0));
         
         props.put(ImmutablePair.of(INTERNAL_DS_DB_NAME, InternalDbMessages.INSTANCE.internalDbName()),
               datasourceMetadata.get("getDatabaseProductName").toString());
         props.put(ImmutablePair.of(INTERNAL_DS_DB_VERSION, InternalDbMessages.INSTANCE.internalDbVersion()),
               datasourceMetadata.get("getDatabaseProductVersion").toString());
         props.put(ImmutablePair.of(INTERNAL_DS_JDBC_DRIVER_NAME, InternalDbMessages.INSTANCE.internalDbDriverName()),
               datasourceMetadata.get("getDriverName").toString());
         props.put(
               ImmutablePair.of(INTERNAL_DS_JDBC_DRIVER_VERSION,
                     InternalDbMessages.INSTANCE.internalDbDriverVersion()),
               datasourceMetadata.get("getDriverVersion").toString());
         props.put(
               ImmutablePair.of(INTERNAL_DS_JDBC_MAJOR_VERSION,
                     InternalDbMessages.INSTANCE.internalDbJdbcMajorVersion()),
               datasourceMetadata.get("getJDBCMajorVersion"));
         props.put(
               ImmutablePair.of(INTERNAL_DS_JDBC_MINOR_VERSION,
                     InternalDbMessages.INSTANCE.internalDbJdbcMinorVersion()),
               datasourceMetadata.get("getJDBCMinorVersion"));
         props.put(ImmutablePair.of(INTERNAL_DS_JDBC_URL, InternalDbMessages.INSTANCE.internalDbJdbcUrl()),
               datasourceMetadata.get("getURL").toString());
         props.put(ImmutablePair.of(INTERNAL_DS_JDBC_USERNAME, InternalDbMessages.INSTANCE.internalDbUsername()),
               datasourceMetadata.get("getUserName").toString());
         try {
            props.put(ImmutablePair.of(INTERNAL_DS_JDBC_PROPERTIES, InternalDbMessages.INSTANCE.internalDbJdbcProperties()),
                  (null != internalDbDatasource.parseJdbcProperties()
                        ? PropertiesUtilService.convert(internalDbDatasource.parseJdbcProperties())
                        : null));
         } catch (Exception e) {
            props.put(ImmutablePair.of(INTERNAL_DS_JDBC_PROPERTIES,
                  InternalDbMessages.INSTANCE.internalDbJdbcProperties()), new HashMap<String, String>() {
                     private static final long serialVersionUID = 1L;
            {
                        props.put(ImmutablePair.of(INTERNAL_DS_ERROR, "Error"), ExceptionUtils.getRootCauseMessage(e));
            }});
         }
      } catch (Exception e) {
         props.put(ImmutablePair.of(INTERNAL_DS_DB_NAME, InternalDbMessages.INSTANCE.internalDbName()),
               ExceptionUtils.getRootCauseMessage(e));
      }
      
      return Collections.singletonMap(
            ImmutablePair.of(INTERNAL_DATASOURCE, InternalDbMessages.INSTANCE.internalDatasource()), props);
   }
   

}
