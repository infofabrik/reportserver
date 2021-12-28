package net.datenwerke.rs.base.service.reportengines.jasper.hookers;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.base.client.reportengines.jasper.JasperUiModule;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.treedb.actions.UpdateAction;

public class JasperReportMasterUploadHooker implements FileUploadHandlerHook {

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<ReportService> reportServiceProvider;
   private final Provider<JasperUtilsService> jasperReportManagerProvider;

   @Inject
   public JasperReportMasterUploadHooker(Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<SecurityService> securityServiceProvider, Provider<ReportService> reportServiceProvider,
         Provider<JasperUtilsService> jasperReportManagerProvider) {
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
      this.jasperReportManagerProvider = jasperReportManagerProvider;
   }

   @Override
   public boolean consumes(String handler) {
      return JasperUiModule.MASTER_UPLOAD_HANDLER_ID.equals(handler);
   }

   @Override
   public String uploadOccured(UploadedFile uploadedFile) {
      Map<String, String> metadataMap = uploadedFile.getMetadata();

      long reportId = Long.valueOf(metadataMap.get(JasperUiModule.META_REPORT_ID));
      String reportName = uploadedFile.getFileName();
      String reportContents = new String(uploadedFile.getFileBytes());

      if (null == reportName || null == reportContents || reportContents.isEmpty() || reportName.isEmpty())
         return null;

      if (!reportName.endsWith(".jrxml"))
         throw new RuntimeException("Expects .jrxml file");

      SecurityService securityService = securityServiceProvider.get();
      securityService.assertUserLoggedIn();

      ReportService reportService = reportServiceProvider.get();
      AbstractReportManagerNode rmn = reportService.getNodeById(reportId);

      securityService.assertActions(rmn, UpdateAction.class);

      if (rmn instanceof JasperReport) {
         JasperReport jasperReport = (JasperReport) rmn;

         JasperReportJRXMLFile oldFile = jasperReport.getMasterFile();

         JasperReportJRXMLFile reportFile = new JasperReportJRXMLFile();
         reportFile.setName(reportName);
         reportFile.setContent(reportContents);

         jasperReport.setMasterFile(reportFile);

         /* delete old file */
         if (null != oldFile) {
            JasperUtilsService service = jasperReportManagerProvider.get();
            service.removeJRXMLFile(oldFile);
         }

         /* merge new file */
         reportService.merge(jasperReport);
      }

      return null;
   }

}
