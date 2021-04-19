package net.datenwerke.rs.ftp.client.ftp;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.rpc.FtpRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class FtpDao extends Dao {

   private final FtpRpcServiceAsync rpcService;

   @Inject
   public FtpDao(FtpRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoFtp(ReportDto reportDto, String executorToken, FtpDatasinkDto ftpDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, AsyncCallback<Void> callback) {
      rpcService.exportIntoFtp(reportDto, executorToken, ftpDatasinkDto, format, configs, name, folder,
            transformAndKeepCallback(callback));
   }

   public void exportIntoSftp(ReportDto reportDto, String executorToken, SftpDatasinkDto sftpDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, AsyncCallback<Void> callback) {
      rpcService.exportIntoSftp(reportDto, executorToken, sftpDatasinkDto, format, configs, name, folder,
            transformAndKeepCallback(callback));
   }
   
   public void exportIntoFtps(ReportDto reportDto, String executorToken, FtpsDatasinkDto ftpsDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, AsyncCallback<Void> callback) {
      rpcService.exportIntoFtps(reportDto, executorToken, ftpsDatasinkDto, format, configs, name, folder,
            transformAndKeepCallback(callback));
   }


   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testFtpDataSink(FtpDatasinkDto ftpDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testFtpDataSink(ftpDatasinkDto, transformAndKeepCallback(callback));
   }

   public Request testSftpDataSink(SftpDatasinkDto sftpDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testSftpDataSink(sftpDatasinkDto, transformAndKeepCallback(callback));
   }
   
   public Request testFtpsDataSink(FtpsDatasinkDto ftpsDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testFtpsDataSink(ftpsDatasinkDto, transformAndKeepCallback(callback));
   }
}
