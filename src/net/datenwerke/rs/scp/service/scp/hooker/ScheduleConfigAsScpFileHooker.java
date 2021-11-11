package net.datenwerke.rs.scp.service.scp.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scp.client.scp.dto.ScheduleAsScpFileInformation;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.service.scp.action.ScheduleAsScpFileAction;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsScpFileHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsScpFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsScpFileHooker(DtoService dtoService, Provider<ScheduleAsScpFileAction> actionProvider,
         Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsScpFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsScpFileInformation.class);
      if (null == info)
         return;

      if (null == info.getScpDatasinkDto())
         throw new InvalidConfigurationException("No Scp datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");

      ScheduleAsScpFileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setCompressed(info.isCompressed());
      action.setScpDatasink((ScpDatasink) dtoServiceProvider.get().loadPoso(info.getScpDatasinkDto()));

      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsScpFileAction action = job.getAction(ScheduleAsScpFileAction.class);
      if (null == action)
         return;

      ScheduleAsScpFileInformation info = new ScheduleAsScpFileInformation();

      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setCompressed(action.isCompressed());
      info.setScpDatasinkDto((ScpDatasinkDto) dtoServiceProvider.get().createDto(action.getScpDatasink()));

      if (null == info.getScpDatasinkDto())
         throw new IllegalArgumentException("No Scp datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");

      rsd.addAdditionalInfo(info);

   }

}
