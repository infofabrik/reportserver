package net.datenwerke.rs.emaildatasink.client.emaildatasink;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.rpc.EmailDatasinkRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class EmailDatasinkDao extends Dao {

   private final EmailDatasinkRpcServiceAsync rpcService;

   @Inject
   public EmailDatasinkDao(EmailDatasinkRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportToEmail(ReportDto reportDto, String executorToken, EmailDatasinkDto emailDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String subject, String message,
         List<StrippedDownUser> recipients, AsyncCallback<Void> callback) {
      rpcService.exportToEmail(reportDto, executorToken, emailDatasinkDto, format, configs, name, subject, message,
            recipients, transformAndKeepCallback(callback));
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testEmailDatasink(EmailDatasinkDto emailDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testEmailDatasink(emailDatasinkDto, transformAndKeepCallback(callback));
   }
   
   public void getDefaultEmailDatasink(AsyncCallback<EmailDatasinkDto> callback) {
      rpcService.getDefaultEmailDatasink(transformAndKeepCallback(callback));
   }

}