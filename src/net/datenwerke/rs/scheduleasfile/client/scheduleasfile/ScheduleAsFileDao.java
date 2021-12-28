package net.datenwerke.rs.scheduleasfile.client.scheduleasfile;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.rpc.ScheduleAsFileRpcServiceAsync;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

public class ScheduleAsFileDao extends Dao {

   private final ScheduleAsFileRpcServiceAsync rpcService;

   @Inject
   public ScheduleAsFileDao(ScheduleAsFileRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoTeamSpace(ReportDto reportDto, String executorToken, String format,
         List<ReportExecutionConfigDto> configs, AbstractTsDiskNodeDto folder, String name, String description,
         AsyncCallback<Void> callback) {
      rpcService.exportIntoTeamSpace(reportDto, executorToken, format, configs, folder, name, description,
            transformAndKeepCallback(callback));
   }

}
