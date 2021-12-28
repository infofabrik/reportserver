package net.datenwerke.rs.birt.service.reportengine.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;

public class BirtReportTypeProviderHooker implements ReportTypeProviderHook {

   @Override
   public Collection<? extends Class<? extends Report>> getReportTypes() {
      Set<Class<? extends Report>> types = new HashSet<Class<? extends Report>>();

      types.add(BirtReport.class);

      return types;
   }

}
