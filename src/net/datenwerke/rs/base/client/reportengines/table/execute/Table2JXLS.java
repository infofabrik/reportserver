package net.datenwerke.rs.base.client.reportengines.table.execute;

import com.google.inject.Inject;

import net.datenwerke.gf.client.config.ClientConfigXmlService;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2JXLS;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class Table2JXLS extends Export2JXLS {

   @Inject
   public Table2JXLS(
         ReportExporterDao exporterDao, 
         ClientConfigXmlService jsonService
         ) {
      super(exporterDao, jsonService);
   }

   public boolean consumes(ReportDto report) {
      return report instanceof TableReportDto && !((TableReportDto) report).isCubeFlag();
   }
}