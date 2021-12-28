package net.datenwerke.rs.crystal.client.crystal.reportengines;

import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2Text;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;

public class Crystal2Text extends Export2Text {

   @Override
   public boolean consumes(ReportDto report) {
      return report instanceof CrystalReportDto;
   }

}
