package net.datenwerke.rs.core.service.reportmanager.entities.reports.post;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ServerSidePropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;

/**
 * 
 *
 */
public class Report2DtoPostProcessor implements Poso2DtoPostProcessor<Report, ReportDto> {

   private final DtoService dtoService;

   @Inject
   public Report2DtoPostProcessor(DtoService dtoService) {

      /* store objects */
      this.dtoService = dtoService;
   }

   @Override
   public void dtoCreated(Report poso, ReportDto dto) {
      if (poso instanceof ReportVariant) {
         Report parentReport = (Report) poso.getParent();

         if (DtoView.LIST.compareTo(dto.getDtoView()) <= 0) {
            dto.setParentReportName(parentReport.getName());
         }

         if (DtoView.NORMAL.compareTo(dto.getDtoView()) <= 0) {
            HashSet<ReportPropertyDto> properties = new HashSet<ReportPropertyDto>();
            for (ReportProperty prop : parentReport.getReportProperties()) {
               properties.add((ReportPropertyDto) dtoService.createDto(prop));
            }
            ((ReportDtoDec) dto).setParentReportProperties(properties);

            dto.setParentReportKey(parentReport.getKey());
            dto.setParentReportDescription(parentReport.getDescription());
         }
      }

      // remove server side properties */
      Set<ReportPropertyDto> properties = dto.getReportProperties();
      Iterator<ReportPropertyDto> it = properties.iterator();
      while (it.hasNext())
         if (it.next() instanceof ServerSidePropertyDto)
            it.remove();
      dto.setReportProperties(properties);

      // mark report as not having children
      dto.setHasChildren(false);
   }

   @Override
   public void dtoInstantiated(Report poso, ReportDto dto) {

   }

}
