package net.datenwerke.rs.incubator.client.jaspertotable.hooker;

import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2Excel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.incubator.client.jaspertotable.JasperToTableUIModule;
import net.datenwerke.rs.incubator.client.jaspertotable.locale.JasperToTableMessages;

/**
 * 
 *
 */
public class Jasper2TableExcel extends Export2Excel {

   public String getExportDescription() {
      return JasperToTableMessages.INSTANCE.exportDescription();
   }

   public String getExportTitle() {
      return JasperToTableMessages.INSTANCE.exportDescription();
   }

   public String getOutputFormat() {
      return "TABLE_EXCEL"; //$NON-NLS-1$
   }

   public boolean consumes(ReportDto report) {
      return report instanceof JasperReportDto
            && ((ReportDtoDec) report).hasReportPropertyWithName(JasperToTableUIModule.PROPERTY_NAME);
   }
}
