package net.datenwerke.rs.installation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.db.H2;
import net.datenwerke.rs.configservice.service.configservice.ConfigDirService;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;

public class DemoDataInstallTask implements DbInstallationTask {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private static final String DEMO_DATASOURCES_FOLDER_NAME = "sample data";
   private static final String DEMO_DATA_SOURCE_NAME = "Demo Data";

   private DatasourceService datasourceService;
   private DBHelperService dbHelperService;
   private Provider<ConfigDirService> configDirServiceProvider;

   @Inject
   public DemoDataInstallTask(
         DatasourceService datasourceService, 
         DBHelperService dbHelperService,
         Provider<ConfigDirService> configDirServiceProvider
         ) {
      this.datasourceService = datasourceService;
      this.dbHelperService = dbHelperService;
      this.configDirServiceProvider = configDirServiceProvider;
   }

   @Override
   public void executeOnStartup() {
   }

   @Override
   public void executeOnFirstRun() {
      ConfigDirService configDirService = configDirServiceProvider.get();
      if (configDirService.isEnabled()) {
         File initpropsfile = new File(configDirService.getConfigDir(), InitConfigTask.RSINIT_PROPERTIES);
         if (initpropsfile.exists()) {
            try {
               Properties props = new Properties();
               FileReader reader = new FileReader(initpropsfile);
               props.load(reader);
               IOUtils.closeQuietly(reader);

               if (Boolean.valueOf(props.getProperty("democontent.install", "false"))) {
                  installDatasource();
               }

            } catch (IOException e) {
               logger.warn("Failed to initialize using rsinit.properties", e);
            } 
         }
      }
   }

   protected void installDatasource() {
      logger.info("install demo data datasources");

      DatasourceFolder folder = new DatasourceFolder();
      folder.setName(DEMO_DATASOURCES_FOLDER_NAME); // $NON-NLS-1$

      AbstractDatasourceManagerNode root = datasourceService.getRoots().get(0);
      root.addChild(folder);
      datasourceService.persist(folder);

      String url = "rs:demodata";
      String username = "demo";
      String password = "demo";
      final String driver = "org.h2.Driver";
      final String dbHelperName = dbHelperService.getDatabaseHelpers().stream()
            .filter(dh -> driver.equals(dh.getDriver())).map(DatabaseHelper::getDescriptor).findAny().orElse("");

      DatabaseDatasource demoDataSource = new DatabaseDatasource();
      demoDataSource.setDatabaseDescriptor(dbHelperName);
      demoDataSource.setUrl(url);
      demoDataSource.setName(DEMO_DATA_SOURCE_NAME);
      demoDataSource.setUsername(username);
      demoDataSource.setPassword(password);
      folder.addChild(demoDataSource);
      datasourceService.persist(demoDataSource);

      MondrianDatasource mds = new MondrianDatasource();
      InputStream is = getClass().getClassLoader().getResourceAsStream("resources/demo/FoodMart-schema.xml");
      mds.setName("Foodmart");

      mds.setUsername(username);
      mds.setPassword(password);
      mds.setUrl("rs:mondrian:demodata");

      try {
         mds.setMondrianSchema(IOUtils.toString(is));
      } catch (IOException e) {
      }
      String props = "type=OLAP\n" + "name=foodmart\n" + "driver=mondrian.olap4j.MondrianOlap4jDriver\n"
            + "jdbcDrivers=" + H2.DB_DRIVER + "";
      mds.setProperties(props);

      folder.addChild(mds);
      datasourceService.persist(mds);

   }
}
