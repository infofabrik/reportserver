package net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScheduleAsScriptDatasinkInformation;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.action.ScheduleAsScriptDatasinkFileAction;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.definitions.ScriptDatasink;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsScriptDatasinkHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsScriptDatasinkFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsScriptDatasinkHooker(DtoService dtoService,
         Provider<ScheduleAsScriptDatasinkFileAction> actionProvider, Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsScriptDatasinkInformation info = scheduleDTO
            .getAdditionalInfo(ScheduleAsScriptDatasinkInformation.class);
      if (null == info)
         return;

      if (null == info.getScriptDatasinkDto())
         throw new InvalidConfigurationException("No script datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");

      ScheduleAsScriptDatasinkFileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setCompressed(info.isCompressed());
      action.setScriptDatasink(
            (ScriptDatasink) dtoServiceProvider.get().loadPoso(info.getScriptDatasinkDto()));
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsScriptDatasinkFileAction action = job.getAction(ScheduleAsScriptDatasinkFileAction.class);
      if (null == action)
         return;

      ScheduleAsScriptDatasinkInformation info = new ScheduleAsScriptDatasinkInformation();

      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setCompressed(action.isCompressed());
      info.setScriptDatasinkDto(
            (ScriptDatasinkDto) dtoServiceProvider.get().createDto(action.getScriptDatasink()));

      if (null == info.getScriptDatasinkDto())
         throw new IllegalArgumentException("No script datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");

      rsd.addAdditionalInfo(info);

   }

}