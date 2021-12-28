package net.datenwerke.rs.dropbox.client.dropbox;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.client.dropbox.rpc.DropboxRpcServiceAsync;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DropboxDao extends Dao implements HasDefaultDatasink {

   private final DropboxRpcServiceAsync rpcService;

   @Inject
   public DropboxDao(DropboxRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportReportIntoDatasink(ReportDto reportDto, String executorToken, DatasinkDefinitionDto datasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed,
         AsyncCallback<Void> callback) {
      rpcService.exportReportIntoDatasink(reportDto, executorToken, datasinkDto, format, configs, name, folder, compressed,
            transformAndKeepCallback(callback));
   }
   
   public void exportFileIntoDatasink(AbstractFileServerNodeDto   file, DatasinkDefinitionDto datasinkDto, String name,
         String folder, boolean compressed,AsyncCallback<Void> callback) {
      rpcService.exportFileIntoDatasink(file, datasinkDto, name, folder, compressed ,transformAndKeepCallback(callback));
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testDropboxDatasink(DropboxDatasinkDto dropboxDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testDropboxDatasink(dropboxDatasinkDto, transformAndKeepCallback(callback));
   }
   
   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}
