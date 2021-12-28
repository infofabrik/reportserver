package net.datenwerke.rs.ftp.service.ftp.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.ftp.client.ftp.dto.ScheduleAsSftpFileInformation;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.service.ftp.action.ScheduleAsSftpFileAction;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsSftpFileHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsSftpFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsSftpFileHooker(DtoService dtoService, Provider<ScheduleAsSftpFileAction> actionProvider,
         Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsSftpFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsSftpFileInformation.class);
      if (null == info)
         return;

      if (null == info.getSftpDatasinkDto())
         throw new InvalidConfigurationException("No SFTP datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");

      ScheduleAsSftpFileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setCompressed(info.isCompressed());
      action.setSftpDatasink((SftpDatasink) dtoServiceProvider.get().loadPoso(info.getSftpDatasinkDto()));

      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }
   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsSftpFileAction action = job.getAction(ScheduleAsSftpFileAction.class);
      if (null == action)
         return;

      ScheduleAsSftpFileInformation info = new ScheduleAsSftpFileInformation();

      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setCompressed(action.isCompressed());
      info.setSftpDatasinkDto((SftpDatasinkDto) dtoServiceProvider.get().createDto(action.getSftpDatasink()));

      if (null == info.getSftpDatasinkDto())
         throw new IllegalArgumentException("No SFTP datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");

      rsd.addAdditionalInfo(info);
   }

}
