package net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.ScheduleAsTableDatasinkFileInformation;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.action.ScheduleAsTableDatasinkFileAction;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsTableDatasinkHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsTableDatasinkFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsTableDatasinkHooker(DtoService dtoService,
         Provider<ScheduleAsTableDatasinkFileAction> actionProvider, Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsTableDatasinkFileInformation info = scheduleDTO
            .getAdditionalInfo(ScheduleAsTableDatasinkFileInformation.class);
      if (null == info)
         return;

      if (null == info.getTableDatasinkDto())
         throw new InvalidConfigurationException("No Table datasink specified");

      ScheduleAsTableDatasinkFileAction action = actionProvider.get();

      action.setTableDatasink((TableDatasink) dtoServiceProvider.get().loadPoso(info.getTableDatasinkDto()));
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsTableDatasinkFileAction action = job.getAction(ScheduleAsTableDatasinkFileAction.class);
      if (null == action)
         return;

      ScheduleAsTableDatasinkFileInformation info = new ScheduleAsTableDatasinkFileInformation();

      info.setTableDatasinkDto((TableDatasinkDto) dtoServiceProvider.get().createDto(action.getTableDatasink()));

      if (null == info.getTableDatasinkDto())
         throw new IllegalArgumentException("No Table datasink specified");

      rsd.addAdditionalInfo(info);

   }

}
