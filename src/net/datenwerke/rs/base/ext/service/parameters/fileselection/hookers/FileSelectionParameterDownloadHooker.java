package net.datenwerke.rs.base.ext.service.parameters.fileselection.hookers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.gf.service.download.hooks.FileDownloadHandlerHook;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.FileSelectionParameterUiModule;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterService;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile;
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.treedb.actions.ReadAction;

public class FileSelectionParameterDownloadHooker implements FileDownloadHandlerHook {

   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<FileSelectionParameterService> fileSelectionParameterServiceProvider;
   private final Provider<ReportParameterService> parameterServiceProvider;
   private final Provider<Injector> injectorProvider;
   private final Provider<HttpUtils> httpUtilsProvider;

   @Inject
   public FileSelectionParameterDownloadHooker(Provider<SecurityService> securityServiceProvider,
         Provider<FileSelectionParameterService> fileSelectionParameterServiceProvider,
         Provider<ReportParameterService> parameterServiceProvider, Provider<Injector> injectorProvider,
         Provider<HttpUtils> httpUtilsProvider) {
      this.securityServiceProvider = securityServiceProvider;
      this.fileSelectionParameterServiceProvider = fileSelectionParameterServiceProvider;
      this.parameterServiceProvider = parameterServiceProvider;
      this.injectorProvider = injectorProvider;
      this.httpUtilsProvider = httpUtilsProvider;
   }

   @Override
   public boolean consumes(String handler) {
      return FileSelectionParameterUiModule.SELECTED_FILE_DOWNLOAD_HANDLER.equals(handler);
   }

   @Override
   public void processDownload(String id, String handler, Map<String, String> metadata, HttpServletResponse response)
         throws IOException {
      Long lid = Long.valueOf(id);

      FileSelectionParameterService fileSelectionParameterService = fileSelectionParameterServiceProvider.get();
      SelectedParameterFile file = fileSelectionParameterService.getSelectedFileById(lid);

      FileSelectionParameterInstance instance = fileSelectionParameterService.getParameterInstanceWithFile(file);
      if (!instance.getDefinition().isAllowDownload())
         throw new ViolatedSecurityException();

      Report report = parameterServiceProvider.get().getReportWithInstance(instance);

      securityServiceProvider.get().assertActions(report, ReadAction.class);

      if (!file.mayAccess(injectorProvider.get()))
         throw new ViolatedSecurityException();

      response.setHeader(HttpUtils.CONTENT_DISPOSITION,
            httpUtilsProvider.get().makeContentDispositionHeader(true, file.getName()));
      response.setCharacterEncoding("UTF-8"); //$NON-NLS-1$
      if (null != file.getContent())
         response.getOutputStream().write(file.getContent());
   }

}
