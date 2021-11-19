package net.datenwerke.rs.amazons3.client.amazons3;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.client.amazons3.rpc.AmazonS3RpcServiceAsync;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class AmazonS3Dao extends Dao implements HasDefaultDatasink {

   private final AmazonS3RpcServiceAsync rpcService;

   @Inject
   public AmazonS3Dao(AmazonS3RpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoAmazonS3(ReportDto reportDto, String executorToken, AmazonS3DatasinkDto amazonS3DatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed,
         AsyncCallback<Void> callback) {
      rpcService.exportIntoAmazonS3(reportDto, executorToken, amazonS3DatasinkDto, format, configs, name, folder,
            compressed, transformAndKeepCallback(callback));
   }
   
   public void exportFileIntoDatasink(FileServerFileDto file, DatasinkDefinitionDto datasinkDto, String name,
         String folder, AsyncCallback<Void> callback) {
      rpcService.exportFileIntoDatasink(file, datasinkDto, name, folder, callback);
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testAmazonS3Datasink(AmazonS3DatasinkDto amazonS3DatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testAmazonS3Datasink(amazonS3DatasinkDto, transformAndKeepCallback(callback));
   }

   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}
