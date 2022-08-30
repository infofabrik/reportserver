package net.datenwerke.rs.dropbox.service.dropbox.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.client.dropbox.dto.ScheduleAsDropboxFileInformation;
import net.datenwerke.rs.dropbox.service.dropbox.action.ScheduleAsDropboxFileAction;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsDropboxDatasinkHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsDropboxFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsDropboxDatasinkHooker(DtoService dtoService,
         Provider<ScheduleAsDropboxFileAction> actionProvider, Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsDropboxFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsDropboxFileInformation.class);
      if (null == info)
         return;

      if (null == info.getDropboxDatasinkDto())
         throw new InvalidConfigurationException("No Dropbox datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");

      ScheduleAsDropboxFileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setCompressed(info.isCompressed());
      action.setDropboxDatasink((DropboxDatasink) dtoServiceProvider.get().loadPoso(info.getDropboxDatasinkDto()));
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsDropboxFileAction action = job.getAction(ScheduleAsDropboxFileAction.class);
      if (null == action)
         return;

      ScheduleAsDropboxFileInformation info = new ScheduleAsDropboxFileInformation();

      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setCompressed(Boolean.TRUE.equals(action.isCompressed()));
      info.setDropboxDatasinkDto((DropboxDatasinkDto) dtoServiceProvider.get().createDto(action.getDropboxDatasink()));

      if (null == info.getDropboxDatasinkDto())
         throw new IllegalArgumentException("No Dropbox datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");

      rsd.addAdditionalInfo(info);

   }

}
