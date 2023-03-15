package net.datenwerke.rs.saiku.service.hooker;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.stream.Stream;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;

public class SaikuReportTypeProviderHooker implements ReportTypeProviderHook {

   @Override
   public Collection<? extends Class<? extends Report>> getReportTypes() {
      return Stream.of(SaikuReport.class)
            .collect(toSet());
   }

   @Override
   public Collection<? extends Class<? extends Report>> getReportVariantTypes() {
      return Stream.of(SaikuReportVariant.class)
            .collect(toSet());
   }

}
