package net.datenwerke.rs.legacysaiku.service.hooker;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeStoredHook;
import net.datenwerke.rs.legacysaiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;

import org.legacysaiku.olap.query.IQuery;

public class VariantStoreHooker implements VariantToBeStoredHook {

	private Provider<SaikuSessionContainer> saikuSessionContainer;

	@Inject
	public VariantStoreHooker(
			Provider<SaikuSessionContainer> saikuSessionContainer) {
		
		this.saikuSessionContainer = saikuSessionContainer;
	}

	@Override
	public void variantToBeStored(Report report, String executerToken) {
		if(report instanceof SaikuReportVariant) {
			SaikuReportVariant variant = (SaikuReportVariant) report;
			SaikuReport report2 = saikuSessionContainer.get().getReport(executerToken);
			IQuery query = saikuSessionContainer.get().getQueryForReport(report2);
			if(null == query)
				return;
				
			String queryXml = query.toXml();
			variant.setQueryXml(queryXml);
			if(null != report2)
				variant.setHideParents(report2.isHideParents());
		} else if(report instanceof TableReport && ((TableReport)report).isCubeFlag()){
			TableReportVariant variant = (TableReportVariant) report;
			
			SaikuReport report2 = saikuSessionContainer.get().getReport(executerToken);
			if(null == report2)
				return;
			IQuery query = saikuSessionContainer.get().getQueryForReport(report2);
			if(null == query)
				return;
			
			variant.setHideParents(report2.isHideParents());
			
			String queryXml = query.toXml();
			variant.setCubeXml(queryXml);
		}
	}

}
