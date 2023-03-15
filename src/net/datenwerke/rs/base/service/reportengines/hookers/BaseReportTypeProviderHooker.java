package net.datenwerke.rs.base.service.reportengines.hookers;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.stream.Stream;

import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportVariant;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;

public class BaseReportTypeProviderHooker implements ReportTypeProviderHook {

   @Override
   public Collection<? extends Class<? extends Report>> getReportTypes() {
      return Stream.of(JasperReport.class, TableReport.class)
            .collect(toSet());
   }

   @Override
   public Collection<? extends Class<? extends Report>> getReportVariantTypes() {
      return Stream.of(JasperReportVariant.class, TableReportVariant.class)
            .collect(toSet());
   }

}
