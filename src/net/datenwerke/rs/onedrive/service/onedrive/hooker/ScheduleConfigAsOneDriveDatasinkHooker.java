package net.datenwerke.rs.onedrive.service.onedrive.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.onedrive.client.onedrive.dto.ScheduleAsOneDriveFileInformation;
import net.datenwerke.rs.onedrive.service.onedrive.action.ScheduleAsOneDriveFileAction;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsOneDriveDatasinkHooker implements ScheduleConfigProviderHook {
   private final Provider<ScheduleAsOneDriveFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsOneDriveDatasinkHooker(
         DtoService dtoService,
         Provider<ScheduleAsOneDriveFileAction> actionProvider, 
         Provider<DtoService> dtoServiceProvider
         ) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsOneDriveFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsOneDriveFileInformation.class);
      if (null == info)
         return;

      if (null == info.getOneDriveDatasinkDto())
         throw new InvalidConfigurationException("No OneDrive datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");

      ScheduleAsOneDriveFileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setOneDriveDatasink((OneDriveDatasink) dtoServiceProvider.get().loadPoso(info.getOneDriveDatasinkDto()));
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsOneDriveFileAction action = job.getAction(ScheduleAsOneDriveFileAction.class);
      if (null == action)
         return;

      ScheduleAsOneDriveFileInformation info = new ScheduleAsOneDriveFileInformation();

      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setOneDriveDatasinkDto(
            (OneDriveDatasinkDto) dtoServiceProvider.get().createDto(action.getOneDriveDatasink()));

      if (null == info.getOneDriveDatasinkDto())
         throw new IllegalArgumentException("No OneDrive datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");

      rsd.addAdditionalInfo(info);

   }
}