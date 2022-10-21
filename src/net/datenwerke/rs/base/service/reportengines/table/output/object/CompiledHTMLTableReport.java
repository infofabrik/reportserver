package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.client.reportexecutor.dto.CompiledHtmlReportDto;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledHtmlReport;

/**
 * 
 *
 */
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.reportengines.table.dto", generateDto2Poso = false, dtoImplementInterfaces = CompiledHtmlReportDto.class)
public class CompiledHTMLTableReport extends CompiledTableReport implements CompiledHtmlReport {

   /**
    * 
    */
   private static final long serialVersionUID = 5784381788512286605L;

   @ExposeToClient
   private final String report;

   public CompiledHTMLTableReport(String htmlReport) {
      this.report = htmlReport;
   }

   public String getFileExtension() {
      return "html"; //$NON-NLS-1$
   }

   public String getMimeType() {
      return "text/html"; //$NON-NLS-1$
   }

   public String getReport() {
      return report;
   }

}
