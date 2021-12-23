package net.datenwerke.rs.emaildatasink.client.emaildatasink.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public interface EmailDatasinkRpcServiceAsync {

   void exportToEmail(ReportDto reportDto, String executorToken, EmailDatasinkDto emailDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String subject, String message, boolean compressed,
         List<StrippedDownUser> recipients, AsyncCallback<Void> callback);

   void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback);

   Request testEmailDatasink(EmailDatasinkDto emailDatasinkDto, AsyncCallback<Boolean> callback);

   void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback);

   void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto, String name,
         String folder, boolean compressed, String subject, String message,
         List<StrippedDownUser> recipients, AsyncCallback<Void> transformAndKeepCallback);

}