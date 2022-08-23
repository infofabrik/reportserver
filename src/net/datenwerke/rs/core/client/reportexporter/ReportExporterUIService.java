package net.datenwerke.rs.core.client.reportexporter;

import java.util.List;

import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportExporterUIService {

   public List<ReportExporter> getAvailableExporters(ReportDto report);

   /**
    * This method should generally be preferred over
    * {@link #getAvailableExporters(ReportDto)}. It returns the exporters for a
    * report which are allowed and in the right order.
    * 
    * @param report
    * @param removeHiddenInExportList if true, removes the exporters for which
    *                                 {@link ReportExporter#showInExportList()} is
    *                                 false.
    */
   public List<ReportExporter> getCleanedUpAvailableExporters(ReportDto report, boolean removeHiddenInExportList);

   /**
    * Returns all exporters that are able to export the report in practice
    */
   public String getExportServletPath();

   /**
    * Returns all exporters that are able to export the report as it is configured
    * at the moment
    * 
    * @param report
    */
   List<ReportExporter> getUsableExporters(ReportDto report);

}
