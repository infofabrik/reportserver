package net.datenwerke.rs.birt.service.reportengine.hookers;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.stream.Stream;

import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportVariant;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;

public class BirtReportTypeProviderHooker implements ReportTypeProviderHook {

   @Override
   public Collection<? extends Class<? extends Report>> getReportTypes() {
      return Stream.of(BirtReport.class)
            .collect(toSet());
   }

   @Override
   public Collection<? extends Class<? extends Report>> getReportVariantTypes() {
      return Stream.of(BirtReportVariant.class)
            .collect(toSet());
   }

}
