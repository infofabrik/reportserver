package net.datenwerke.rs.box.service.box.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto;
import net.datenwerke.rs.box.client.box.dto.ScheduleAsBoxFileInformation;
import net.datenwerke.rs.box.service.box.action.ScheduleAsBoxFileAction;
import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsBoxDatasinkHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsBoxFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsBoxDatasinkHooker(DtoService dtoService, Provider<ScheduleAsBoxFileAction> actionProvider,
         Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsBoxFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsBoxFileInformation.class);
      if (null == info)
         return;

      if (null == info.getBoxDatasinkDto())
         throw new InvalidConfigurationException("No Box datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new InvalidConfigurationException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new InvalidConfigurationException("No folder specified");

      ScheduleAsBoxFileAction action = actionProvider.get();

      action.setName(info.getName());
      action.setFolder(info.getFolder());
      action.setCompressed(info.isCompressed());
      action.setBoxDatasink((BoxDatasink) dtoServiceProvider.get().loadPoso(info.getBoxDatasinkDto()));
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsBoxFileAction action = job.getAction(ScheduleAsBoxFileAction.class);
      if (null == action)
         return;

      ScheduleAsBoxFileInformation info = new ScheduleAsBoxFileInformation();

      info.setName(action.getName());
      info.setFolder(action.getFolder());
      info.setCompressed(action.isCompressed());
      info.setBoxDatasinkDto((BoxDatasinkDto) dtoServiceProvider.get().createDto(action.getBoxDatasink()));

      if (null == info.getBoxDatasinkDto())
         throw new IllegalArgumentException("No Box datasink specified");
      if (null == info.getName() || info.getName().trim().isEmpty())
         throw new IllegalArgumentException("No name specified");
      if (null == info.getFolder() || info.getFolder().trim().isEmpty())
         throw new IllegalArgumentException("No folder specified");

      rsd.addAdditionalInfo(info);

   }

}
