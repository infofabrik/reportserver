package net.datenwerke.rs.ftp.client.ftp;

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
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.rpc.FtpsRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class FtpsDao extends Dao implements HasDefaultDatasink {

   private final FtpsRpcServiceAsync rpcService;

   @Inject
   public FtpsDao(FtpsRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoFtps(ReportDto reportDto, String executorToken, FtpsDatasinkDto ftpsDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed, AsyncCallback<Void> callback) {
      rpcService.exportIntoFtps(reportDto, executorToken, ftpsDatasinkDto, format, configs, name, folder, compressed,
            transformAndKeepCallback(callback));
   }
   
   public void exportFileIntoDatasink(FileServerFileDto file, DatasinkDefinitionDto datasinkDto, String name,
         String folder, AsyncCallback<Void> callback) {
      rpcService.exportFileIntoDatasink(file, datasinkDto, name, folder, transformAndKeepCallback(callback));
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testFtpsDatasink(FtpsDatasinkDto ftpsDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testFtpsDatasink(ftpsDatasinkDto, transformAndKeepCallback(callback));
   }

   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }
}
