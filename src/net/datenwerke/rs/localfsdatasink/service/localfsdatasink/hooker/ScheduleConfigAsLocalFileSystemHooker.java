package net.datenwerke.rs.localfsdatasink.service.localfsdatasink.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.ScheduleAsLocalFileSystemInformation;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.action.ScheduleAsLocalFileSystemFileAction;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsLocalFileSystemHooker implements ScheduleConfigProviderHook{
   
   private final Provider<ScheduleAsLocalFileSystemFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;
   
   @Inject
   public ScheduleConfigAsLocalFileSystemHooker(
      DtoService dtoService,
      Provider<ScheduleAsLocalFileSystemFileAction> actionProvider,
      Provider<DtoService> dtoServiceProvider
      ) {
      
      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsLocalFileSystemInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsLocalFileSystemInformation.class);
      if(null == info)
         return;
      
      if (null == info.getLocalFileSystemDatasinkDto())
         throw new InvalidConfigurationException("No Local file system datasink specified");
      if(null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if(null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");
      
      ScheduleAsLocalFileSystemFileAction action = actionProvider.get();
      
      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setCompressed(info.isCompressed());
      action.setLocalFileSystemDatasink((LocalFileSystemDatasink)dtoServiceProvider.get().loadPoso(info.getLocalFileSystemDatasinkDto()));      
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }
      
   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsLocalFileSystemFileAction action = job.getAction(ScheduleAsLocalFileSystemFileAction.class);
      if(null == action)
         return;
      
      ScheduleAsLocalFileSystemInformation info = new ScheduleAsLocalFileSystemInformation();
      
      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setCompressed(action.isCompressed());
      info.setLocalFileSystemDatasinkDto((LocalFileSystemDatasinkDto)dtoServiceProvider.get().createDto(action.getLocalFileSystemDatasink()));
      
      if (null == info.getLocalFileSystemDatasinkDto())
         throw new IllegalArgumentException("No Local file system datasink specified");
      if(null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if(null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");
      
      rsd.addAdditionalInfo(info);
      
   }

}