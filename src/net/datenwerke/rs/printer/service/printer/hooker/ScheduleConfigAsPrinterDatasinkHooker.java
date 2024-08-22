package net.datenwerke.rs.printer.service.printer.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto;
import net.datenwerke.rs.printer.client.printer.dto.ScheduleAsPrinterFileInformation;
import net.datenwerke.rs.printer.service.printer.action.ScheduleAsPrinterFileAction;
import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsPrinterDatasinkHooker implements ScheduleConfigProviderHook {

   private final Provider<ScheduleAsPrinterFileAction> actionProvider;
   private final Provider<DtoService> dtoServiceProvider;

   @Inject
   public ScheduleConfigAsPrinterDatasinkHooker(DtoService dtoService,
         Provider<ScheduleAsPrinterFileAction> actionProvider, Provider<DtoService> dtoServiceProvider) {

      /* store objects */
      this.actionProvider = actionProvider;
      this.dtoServiceProvider = dtoServiceProvider;
   }

   @Override
   public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
         throws InvalidConfigurationException {
      ScheduleAsPrinterFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsPrinterFileInformation.class);
      if (null == info)
         return;

      if (null == info.getPrinterDatasinkDto())
         throw new InvalidConfigurationException("No Printer datasink specified");

      ScheduleAsPrinterFileAction action = actionProvider.get();

      action.setPrinterDatasink((PrinterDatasink) dtoServiceProvider.get().loadPoso(info.getPrinterDatasinkDto()));
      try {
         job.addAction(action);
      } catch (ActionNotSupportedException e) {
         throw new InvalidConfigurationException(e);
      }

   }

   @Override
   public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
      ScheduleAsPrinterFileAction action = job.getAction(ScheduleAsPrinterFileAction.class);
      if (null == action)
         return;

      ScheduleAsPrinterFileInformation info = new ScheduleAsPrinterFileInformation();

      info.setPrinterDatasinkDto((PrinterDatasinkDto) dtoServiceProvider.get().createDto(action.getPrinterDatasink()));

      if (null == info.getPrinterDatasinkDto())
         throw new IllegalArgumentException("No Printer datasink specified");

      rsd.addAdditionalInfo(info);

   }

}
