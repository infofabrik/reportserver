package net.datenwerke.rs.dashboard.service.dashboard.dagets.post;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

public class ReportDadget2DtoPost implements Poso2DtoPostProcessor<ReportDadget, ReportDadgetDto> {

   private final SecurityService securityService;
   private final ReportDtoService reportDtoService;

   @Inject
   public ReportDadget2DtoPost(SecurityService securityService, Provider<DtoService> dtoServiceProvider,
         ReportDtoService reportDtoService) {
      this.securityService = securityService;
      this.reportDtoService = reportDtoService;
   }

   @Override
   public void dtoCreated(ReportDadget poso, ReportDadgetDto dto) {
      if (dto.getDtoView().compareTo(DtoView.NORMAL) >= 0) {
         Report report = poso.getReport();
         if (null != poso.getReportReference())
            report = poso.getReportReference().getReport();

         if (null != report) {
            securityService.assertRights(report, Read.class);

            ((ReportDadgetDtoDec) dto).setReportForDisplay(reportDtoService.loadFullDtoForExecution(report));
         }
      }
   }

   @Override
   public void dtoInstantiated(ReportDadget reportDadget, ReportDadgetDto reportDadgetDto) {
      // TODO Auto-generated method stub

   }

}