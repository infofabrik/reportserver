package net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.stream.Stream;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportVariant;

public class JxlsReportTypeProviderHooker implements ReportTypeProviderHook {

   @Override
   public Collection<? extends Class<? extends Report>> getReportTypes() {
      return Stream.of(JxlsReport.class)
            .collect(toSet());
   }

   @Override
   public Collection<? extends Class<? extends Report>> getReportVariantTypes() {
      return Stream.of(JxlsReportVariant.class)
            .collect(toSet());
   }

}
