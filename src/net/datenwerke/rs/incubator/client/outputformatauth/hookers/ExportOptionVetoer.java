package net.datenwerke.rs.incubator.client.outputformatauth.hookers;

import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.hooks.VetoReportExporterHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;

public class ExportOptionVetoer implements VetoReportExporterHook {

   @Override
   public boolean doesVetoExporter(ReportExporter exporter, ReportDto report) {
      ReportDtoDec rep = (ReportDtoDec) report;

      ReportPropertyDto property = rep
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_FORMAT_AUTH.getValue());

      if (null != property && property instanceof ReportStringPropertyDto) {
         String allowedFormatsString = ((ReportStringPropertyDto) property).getStrValue();
         String[] formats = allowedFormatsString.split(",");

         boolean found = false;
         for (String f : formats) {
            if (f.trim().toUpperCase().equals(exporter.getOutputFormat().toUpperCase())) {
               found = true;
               break;
            }
         }
         return !found;
      }

      return false;
   }

}
