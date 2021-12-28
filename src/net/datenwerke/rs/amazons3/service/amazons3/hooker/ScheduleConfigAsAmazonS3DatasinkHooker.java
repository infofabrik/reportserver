package net.datenwerke.rs.amazons3.service.amazons3.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.client.amazons3.dto.ScheduleAsAmazonS3FileInformation;
import net.datenwerke.rs.amazons3.service.amazons3.action.ScheduleAsAmazonS3FileAction;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsAmazonS3DatasinkHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsAmazonS3FileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsAmazonS3DatasinkHooker(DtoService dtoService,
         Provider<ScheduleAsAmazonS3FileAction> actionProvider, Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsAmazonS3FileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsAmazonS3FileInformation.class);
      if (null == info)
         return;

      if (null == info.getAmazonS3DatasinkDto())
         throw new InvalidConfigurationException("No AmazonS3 datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");

      ScheduleAsAmazonS3FileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setAmazonS3Datasink((AmazonS3Datasink) dtoServiceProvider.get().loadPoso(info.getAmazonS3DatasinkDto()));
      action.setCompressed(info.isCompressed());
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsAmazonS3FileAction action = job.getAction(ScheduleAsAmazonS3FileAction.class);
      if (null == action)
         return;

      ScheduleAsAmazonS3FileInformation info = new ScheduleAsAmazonS3FileInformation();

      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setCompressed(action.isCompressed());
      info.setAmazonS3DatasinkDto(
            (AmazonS3DatasinkDto) dtoServiceProvider.get().createDto(action.getAmazonS3Datasink()));

      if (null == info.getAmazonS3DatasinkDto())
         throw new IllegalArgumentException("No AmazonS3 datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");

      rsd.addAdditionalInfo(info);

   }

}
