package net.datenwerke.rs.installation;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;

public class ReportServerInstallationModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
   }

   @Provides
   @Inject
   protected List<DbInstallationTask> provideDbInstallationTasks(
         ApplicationPropertiesService propertiesService,
         Provider<InstallBaseDataTask> baseDataTaskProvider,
         Provider<ExecutePackagedScriptsTask> executePackagedScriptsTask,
         Provider<DemoDataInstallTask> demoDbInstallTask, 
         Provider<DemoContentInstallTask> demoContentInstallTask,
         Provider<InitConfigTask> initConfigTask, 
         Provider<InstallMissingEntitiesTask> installMissingEntitiesTask,
         Provider<EmailDatasinkMigrationTask> emailMigrationTask
         ) {
      List<DbInstallationTask> tasks = new ArrayList<>();

      if ("true".equals(propertiesService.getString("rs.install.basedata"))) {
         tasks.add(baseDataTaskProvider.get());
         tasks.add(executePackagedScriptsTask.get());
      }

      /* this has to be true per default to install demo data automatically in the .exe installer */
      if ("true".equals(propertiesService.getString("rs.install.demodata", "true"))) {
         tasks.add(demoDbInstallTask.get());
         tasks.add(demoContentInstallTask.get());
      }

      tasks.add(initConfigTask.get());
      tasks.add(installMissingEntitiesTask.get());
      tasks.add(emailMigrationTask.get());

      return tasks;
   }

}
