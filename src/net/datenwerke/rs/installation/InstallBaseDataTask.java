package net.datenwerke.rs.installation;

import java.io.File;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.gf.service.jpa.annotations.JpaUnit;
import net.datenwerke.rs.ReportServerPUStartup;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder;

public class InstallBaseDataTask implements DbInstallationTask {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   public final static String REPORTSERVER_DATA_SOURCE_NAME = "ReportServer Data Source"; //$NON-NLS-1$
   public static final String INTERNAL_DATASOURCES_FOLDER_NAME = "internal datasources"; //$NON-NLS-1$
   public static final String ADMINISTRATIVE_REPORTS_FOLDER = "administrative reports"; //$NON-NLS-1$

   private final AuditLoggingInstallReportService auditLoggingInstallService;
   private final DatasourceService datasourceService;
   private final ReportService reportService;
   private final DBHelperService dbHelperService;
   private final ConfigDirService configDirService;

   @Inject
   public InstallBaseDataTask(@JpaUnit String persistenceUnitName,
         AuditLoggingInstallReportService auditLoggingInstallService, DatasourceService datasourceService,
         DBHelperService dbHelperService, ConfigDirService configDirService, ReportService reportService) {

      /* store objects */
      this.auditLoggingInstallService = auditLoggingInstallService;
      this.datasourceService = datasourceService;
      this.dbHelperService = dbHelperService;
      this.configDirService = configDirService;
      this.reportService = reportService;
   }

   @Override
   public void executeOnStartup() {

   }

   @Override
   public void executeOnFirstRun() {
      DatasourceDefinition rsDatasource = installDatasource();
      ReportFolder adminFolder = createAdminReportFolder();

      /* create logging report */
      auditLoggingInstallService.install(adminFolder, rsDatasource);
   }

   protected ReportFolder createAdminReportFolder() {
      ReportFolder adminFolder = new ReportFolder();
      adminFolder.setName(ADMINISTRATIVE_REPORTS_FOLDER);

      ReportFolder root = (ReportFolder) reportService.getRoots().get(0);
      root.addChild(adminFolder);

      reportService.persist(adminFolder);

      return adminFolder;
   }

   protected DatasourceDefinition installDatasource() {
      DatasourceFolder folder = new DatasourceFolder();
      folder.setName(INTERNAL_DATASOURCES_FOLDER_NAME); // $NON-NLS-1$

      AbstractDatasourceManagerNode root = datasourceService.getRoots().get(0);
      root.addChild(folder);
      datasourceService.persist(folder);

      /* read properties from persistence.properties */
      String url = "";
      String username = "";
      String password = "";

      DatabaseDatasource rsDataSource = new DatabaseDatasource();

      try {
         PropertiesConfiguration peProps = null;
         try {
            FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
                  PropertiesConfiguration.class).configure(
                        new Parameters().properties().setFileName(ReportServerPUStartup.PERSISTENCE_PROP_NAME));
            peProps = builder.getConfiguration();
         } catch (Exception e) {
         }
         if (configDirService.isEnabled()) {
            try {
               File configDir = configDirService.getConfigDir();
               FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
                     PropertiesConfiguration.class)
                           .configure(new Parameters().properties()
                                 .setFile(new File(configDir, ReportServerPUStartup.PERSISTENCE_PROP_NAME)));

               peProps = builder.getConfiguration();
            } catch (Exception e) {
            }
         }

         if (null == peProps)
            throw new IllegalStateException("Could not load persistence.properties");

         url = peProps.getString("hibernate.connection.url", "");
         username = peProps.getString("hibernate.connection.username", "");
         password = peProps.getString("hibernate.connection.password", "");
         final String driver = peProps.getString("hibernate.connection.driver_class", "");

         final String dbHelperName = dbHelperService.getDatabaseHelpers().stream()
               .filter(dh -> driver.equals(dh.getDriver())).map(DatabaseHelper::getDescriptor).findAny()
               .orElse("DBHelper_MySQL");

         rsDataSource = new DatabaseDatasource();
         rsDataSource.setDatabaseDescriptor(dbHelperName);
         rsDataSource.setUrl(url);
         rsDataSource.setName(REPORTSERVER_DATA_SOURCE_NAME);
         rsDataSource.setUsername(username);
         rsDataSource.setPassword(password);
         folder.addChild(rsDataSource);
         datasourceService.persist(rsDataSource);

      } catch (Exception e) {
         logger.warn("Could not extract internal datasource properties", e);
      }

      return rsDataSource;

   }

}
