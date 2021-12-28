package net.datenwerke.rs.crystal.client.crystal.hookers;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.crystal.client.crystal.CrystalUiModule;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReport;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportFile;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.treedb.actions.UpdateAction;

public class CrystalReportUploadHooker implements FileUploadHandlerHook {

   private Provider<SecurityService> securityServiceProvider;
   private Provider<ReportService> reportServiceProvider;

   @Inject
   public CrystalReportUploadHooker(Provider<SecurityService> securityServiceProvider,
         Provider<ReportService> reportServiceProvider) {
      this.securityServiceProvider = securityServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
   }

   @Override
   public boolean consumes(String handler) {
      return CrystalUiModule.UPLOAD_HANDLER_ID.equals(handler);
   }

   @Override
   public String uploadOccured(UploadedFile uploadedFile) {
      Map<String, String> metadataMap = uploadedFile.getMetadata();

      long reportId = Long.valueOf(metadataMap.get(CrystalUiModule.UPLOAD_REPORT_ID_FIELD));
      String reportName = uploadedFile.getFileName();
      byte[] reportContents = uploadedFile.getFileBytes();

      if (null == reportName || null == reportContents || reportContents.length == 0 || reportName.isEmpty())
         return null;

      if (!reportName.endsWith(".rpt"))
         throw new RuntimeException("Expected .rpt file");

      SecurityService securityService = securityServiceProvider.get();
      securityService.assertUserLoggedIn();

      ReportService reportService = reportServiceProvider.get();
      AbstractReportManagerNode rmn = reportService.getNodeById(reportId);

      securityService.assertActions(rmn, UpdateAction.class);

      if (rmn instanceof CrystalReport) {
         CrystalReport crystalReport = (CrystalReport) rmn;

         CrystalReportFile reportFile = new CrystalReportFile();
         reportFile.setName(reportName);
         reportFile.setContent(reportContents);

         crystalReport.setReportFile(reportFile);

         reportService.merge(crystalReport);
      }

      return null;
   }

}
