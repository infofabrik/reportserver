package net.datenwerke.rs.localfsdatasink.client.localfsdatasink;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.rpc.LocalFileSystemRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import com.google.gwt.http.client.Request;

public class LocalFileSystemDao extends Dao implements HasDefaultDatasink {

   private final LocalFileSystemRpcServiceAsync rpcService;

   @Inject
   public LocalFileSystemDao(LocalFileSystemRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoLocalFileSystem(ReportDto reportDto, String executorToken,
         LocalFileSystemDatasinkDto localFileSystemDatasinkDto, String format, List<ReportExecutionConfigDto> configs,
         String name, String folder, boolean compressed, AsyncCallback<Void> callback) {
      rpcService.exportIntoLocalFileSystem(reportDto, executorToken, localFileSystemDatasinkDto, format, configs, name,
            folder, compressed, transformAndKeepCallback(callback));
   }
   
   public void exportFileIntoDatasink(FileServerFileDto file, DatasinkDefinitionDto datasinkDto, String name,
         String folder, AsyncCallback<Void> callback) {
      rpcService.exportFileIntoDatasink(file, datasinkDto, name, folder, transformAndKeepCallback(callback));
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testLocalFileSystemDatasink(LocalFileSystemDatasinkDto localFileSystemDatasinkDto,
         AsyncCallback<Boolean> callback) {
      return rpcService.testLocalFileSystemDatasink(localFileSystemDatasinkDto, transformAndKeepCallback(callback));
   }

   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}