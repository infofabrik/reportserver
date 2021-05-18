package net.datenwerke.rs.emaildatasink.service.emaildatasink.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.ScheduleAsEmailDatasinkFileInformation;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.action.ScheduleAsEmailFileAction;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsEmailDatasinkHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsEmailFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsEmailDatasinkHooker(DtoService dtoService, Provider<ScheduleAsEmailFileAction> actionProvider,
         Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsEmailDatasinkFileInformation info = scheduleDTO
            .getAdditionalInfo(ScheduleAsEmailDatasinkFileInformation.class);
      if (null == info)
         return;

      if (null == info.getEmailDatasinkDto())
         throw new InvalidConfigurationException("No Email datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");

      ScheduleAsEmailFileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setEmailDatasink((EmailDatasink) dtoServiceProvider.get().loadPoso(info.getEmailDatasinkDto()));
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsEmailFileAction action = job.getAction(ScheduleAsEmailFileAction.class);
      if (null == action)
         return;

      ScheduleAsEmailDatasinkFileInformation info = new ScheduleAsEmailDatasinkFileInformation();

      info.setName(action.getName());
      info.setEmailDatasinkDto((EmailDatasinkDto) dtoServiceProvider.get().createDto(action.getEmailDatasink()));

      if (null == info.getEmailDatasinkDto())
         throw new IllegalArgumentException("No Email datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");

      rsd.addAdditionalInfo(info);

   }

}