package net.datenwerke.rs.legacysaiku.service.hooker;

import javax.inject.Inject;
import javax.inject.Provider;

import org.legacysaiku.olap.query.IQuery;

import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.legacysaiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;

public class ReportExportViaSessionHooker implements ReportExportViaSessionHook {

   private Provider<SaikuSessionContainer> saikuSessionContainer;

   @Inject
   public ReportExportViaSessionHooker(Provider<SaikuSessionContainer> saikuSessionContainer) {
      this.saikuSessionContainer = saikuSessionContainer;
   }

   @Override
   public void adjustReport(Report adjustedReport, ReportExecutionConfig... configs) {
      RECReportExecutorToken executorToken = getConfig(RECReportExecutorToken.class, configs);
      if (null != executorToken && adjustedReport instanceof SaikuReportVariant) {
         SaikuReport origReport = saikuSessionContainer.get().getReport(executorToken.getToken());
         IQuery query = saikuSessionContainer.get().getQueryForReport(origReport);
         if (null != query) {
            String xml = query.toXml();
            ((SaikuReportVariant) adjustedReport).setQueryXml(xml);
         }
         if (null != origReport)
            ((SaikuReportVariant) adjustedReport).setHideParents(origReport.isHideParents());
      } else if (null != executorToken && adjustedReport instanceof TableReportVariant
            && ((TableReport) adjustedReport).isCubeFlag()) {
         SaikuReport origReport = saikuSessionContainer.get().getReport(executorToken.getToken());
         IQuery query = saikuSessionContainer.get().getQueryForReport(origReport);
         if (null != query) {
            String xml = query.toXml();
            ((TableReportVariant) adjustedReport).setCubeXml(xml);
         }
         if (null != origReport)
            ((TableReportVariant) adjustedReport).setHideParents(origReport.isHideParents());
      }
   }

   private <C extends ReportExecutionConfig> C getConfig(Class<C> type, ReportExecutionConfig... configs) {
      if (null == configs || configs.length == 0)
         return null;

      for (ReportExecutionConfig config : configs)
         if (type.isAssignableFrom(config.getClass()))
            return (C) config;

      return null;
   }

}
