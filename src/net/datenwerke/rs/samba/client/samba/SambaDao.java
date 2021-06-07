package net.datenwerke.rs.samba.client.samba;

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
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.samba.client.samba.rpc.SambaRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class SambaDao extends Dao implements DatasinkDao {

   private final SambaRpcServiceAsync rpcService;

   @Inject
   public SambaDao(SambaRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoSamba(ReportDto reportDto, String executorToken, SambaDatasinkDto sambaDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder,
         AsyncCallback<Void> callback) {
      rpcService.exportIntoSamba(reportDto, executorToken, sambaDatasinkDto, format, configs, name, folder,
            transformAndKeepCallback(callback));
   }
   
   public void getSambaEnabledConfigs(AsyncCallback<Map<StorageType,Boolean>> callback) {
      rpcService.getSambaEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testSambaDatasink(SambaDatasinkDto sambaDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testSambaDatasink(sambaDatasinkDto, transformAndKeepCallback(callback));
   }
   
   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}