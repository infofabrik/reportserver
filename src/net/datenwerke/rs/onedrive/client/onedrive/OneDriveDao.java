package net.datenwerke.rs.onedrive.client.onedrive;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.gwt.http.client.Request;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.onedrive.client.onedrive.rpc.OneDriveRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class OneDriveDao extends Dao implements HasDefaultDatasink {
   private final OneDriveRpcServiceAsync rpcService;

   @Inject
   public OneDriveDao(OneDriveRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoOneDrive(ReportDto reportDto, String executorToken, OneDriveDatasinkDto oneDriveDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed,
         AsyncCallback<Void> callback) {
      rpcService.exportIntoOneDrive(reportDto, executorToken, oneDriveDatasinkDto, format, configs, name, folder, compressed,
            transformAndKeepCallback(callback));
   }
   
   public void exportFileIntoDatasink(FileServerFileDto file, DatasinkDefinitionDto datasinkDto, String name,
         String folder, AsyncCallback<Void> callback) {
      rpcService.exportFileIntoDatasink(file, datasinkDto, name, folder, callback);
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testOneDriveDatasink(OneDriveDatasinkDto oneDriveDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testOneDriveDatasink(oneDriveDatasinkDto, transformAndKeepCallback(callback));
   }

   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}