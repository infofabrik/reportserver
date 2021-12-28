package net.datenwerke.rs.crystal.client.crystal.reportengines;

import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2XML;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;

public class Crystal2XML extends Export2XML {

   @Override
   public boolean consumes(ReportDto report) {
      return report instanceof CrystalReportDto;
   }

}
