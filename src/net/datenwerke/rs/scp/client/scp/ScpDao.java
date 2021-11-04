package net.datenwerke.rs.scp.client.scp;

import java.util.List;
import java.util.Map;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkDao;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.client.scp.rpc.ScpRpcServiceAsync;

public class ScpDao extends Dao implements DatasinkDao {

   private final ScpRpcServiceAsync rpcService;

   @Inject
   public ScpDao(ScpRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoScp(ReportDto reportDto, String executorToken, ScpDatasinkDto scpDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed, AsyncCallback<Void> callback) {
      rpcService.exportIntoScp(reportDto, executorToken, scpDatasinkDto, format, configs, name, folder, compressed,
            transformAndKeepCallback(callback));
   }

   public void getScpEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getScpEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testScpDatasink(ScpDatasinkDto scpDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testScpDatasink(scpDatasinkDto, transformAndKeepCallback(callback));
   }
   
   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}
