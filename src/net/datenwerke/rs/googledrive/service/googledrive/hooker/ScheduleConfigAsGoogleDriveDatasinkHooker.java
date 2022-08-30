package net.datenwerke.rs.googledrive.service.googledrive.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;
import net.datenwerke.rs.googledrive.client.googledrive.dto.ScheduleAsGoogleDriveFileInformation;
import net.datenwerke.rs.googledrive.service.googledrive.action.ScheduleAsGoogleDriveFileAction;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsGoogleDriveDatasinkHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsGoogleDriveFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsGoogleDriveDatasinkHooker(DtoService dtoService,
         Provider<ScheduleAsGoogleDriveFileAction> actionProvider, Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsGoogleDriveFileInformation info = scheduleDTO
            .getAdditionalInfo(ScheduleAsGoogleDriveFileInformation.class);
      if (null == info)
         return;

      if (null == info.getGoogleDriveDatasinkDto())
         throw new InvalidConfigurationException("No Google Drive datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");

      ScheduleAsGoogleDriveFileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setCompressed(info.isCompressed());
      action.setGoogleDriveDatasink(
            (GoogleDriveDatasink) dtoServiceProvider.get().loadPoso(info.getGoogleDriveDatasinkDto()));
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsGoogleDriveFileAction action = job.getAction(ScheduleAsGoogleDriveFileAction.class);
      if (null == action)
         return;

      ScheduleAsGoogleDriveFileInformation info = new ScheduleAsGoogleDriveFileInformation();

      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setCompressed(Boolean.TRUE.equals(action.isCompressed()));
      info.setGoogleDriveDatasinkDto(
            (GoogleDriveDatasinkDto) dtoServiceProvider.get().createDto(action.getGoogleDriveDatasink()));

      if (null == info.getGoogleDriveDatasinkDto())
         throw new IllegalArgumentException("No Google Drive datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");

      rsd.addAdditionalInfo(info);

   }

}
