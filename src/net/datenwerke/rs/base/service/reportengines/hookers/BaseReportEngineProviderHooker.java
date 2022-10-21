package net.datenwerke.rs.base.service.reportengines.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.base.service.reportengines.jasper.JasperReportEngine;
import net.datenwerke.rs.base.service.reportengines.table.TableReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;

public class BaseReportEngineProviderHooker implements ReportEngineProviderHook {

   @Override
   public Collection<? extends Class<? extends ReportEngine>> getReportEngines() {
      Set<Class<? extends ReportEngine>> engines = new HashSet<Class<? extends ReportEngine>>();

      engines.add(JasperReportEngine.class);
      engines.add(TableReportEngine.class);

      return engines;
   }

}
