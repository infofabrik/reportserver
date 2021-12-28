package net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;

public class JxlsReportTypeProviderHooker implements ReportTypeProviderHook {

   @Override
   public Collection<? extends Class<? extends Report>> getReportTypes() {
      Set<Class<? extends Report>> types = new HashSet<Class<? extends Report>>();

      types.add(JxlsReport.class);

      return types;
   }

}
