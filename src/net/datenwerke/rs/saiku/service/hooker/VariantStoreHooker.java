package net.datenwerke.rs.saiku.service.hooker;

import javax.inject.Inject;
import javax.inject.Provider;

import org.saiku.olap.query2.ThinQuery;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeStoredHook;
import net.datenwerke.rs.saiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.ThinQueryService;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;

public class VariantStoreHooker implements VariantToBeStoredHook {

   private Provider<SaikuSessionContainer> saikuSessionContainer;
   private ThinQueryService tqService;

   @Inject
   public VariantStoreHooker(Provider<SaikuSessionContainer> saikuSessionContainer, ThinQueryService tqService) {
      this.saikuSessionContainer = saikuSessionContainer;
      this.tqService = tqService;
   }

   @Override
   public void variantToBeStored(Report report, String executerToken) {
      if (report instanceof SaikuReportVariant) {
         SaikuReportVariant variant = (SaikuReportVariant) report;
         SaikuReport report2 = saikuSessionContainer.get().getReport(executerToken);
         if (null != report2) {
        	 variant.setHideParents(report2.isHideParents());
         }
         ThinQuery query = saikuSessionContainer.get().getQueryForReport(report2);
         String xml = tqService.toJSONString(query);
         variant.setQueryXml(xml);
         saikuSessionContainer.get().putQuery(query);
      }
   }
}
