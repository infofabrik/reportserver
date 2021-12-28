package net.datenwerke.rs.base.service.reportengines.table.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.rs.core.service.reportmanager.engine.config.RECSinglePage;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.hooks.ReportExecutionConfigFromPropertyMapHook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public class PagingExecutionConfigFromPropertyMapHooker implements ReportExecutionConfigFromPropertyMapHook {

   @Override
   public Collection<ReportExecutionConfig> parse(Report report, HttpServletRequest request,
         Map<String, String[]> properties) {
      HashSet<ReportExecutionConfig> configs = new HashSet<ReportExecutionConfig>();
      try {
         if (properties.containsKey("page")) {
            int page = Integer.parseInt(properties.get("page")[0]);
            RECSinglePage pageconfig = new RECSinglePage(page);

            if (properties.containsKey("pagesize")) {
               int size = Integer.parseInt(properties.get("pagesize")[0]);
               pageconfig.setPageSize(size);
            }
            configs.add(pageconfig);
         }
      } catch (RuntimeException e) {
      }
      return configs;
   }

}
