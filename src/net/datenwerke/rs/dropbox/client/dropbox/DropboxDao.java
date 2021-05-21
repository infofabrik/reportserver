package net.datenwerke.rs.dropbox.client.dropbox;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.client.dropbox.rpc.DropboxRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DropboxDao extends Dao {

   private final DropboxRpcServiceAsync rpcService;

   @Inject
   public DropboxDao(DropboxRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoDropbox(ReportDto reportDto, String executorToken, DropboxDatasinkDto dropboxDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder,
         AsyncCallback<Void> callback) {
      rpcService.exportIntoDropbox(reportDto, executorToken, dropboxDatasinkDto, format, configs, name, folder,
            transformAndKeepCallback(callback));
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testDropboxDataSink(DropboxDatasinkDto dropboxDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testDropboxDatasink(dropboxDatasinkDto, transformAndKeepCallback(callback));
   }

}
