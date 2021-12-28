package net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class PreFilterViewFactory implements ReportViewFactory {

	private final Provider<PreFilterView> filterViewProvider;
	
	
	@Inject
	public PreFilterViewFactory(
		Provider<PreFilterView> filterViewProvider
		) {
		
		this.filterViewProvider = filterViewProvider;
	}
	
	public PreFilterView newInstance(ReportDto report, Collection<? extends ReportViewConfiguration> configs) {
		PreFilterView fw = filterViewProvider.get();
		fw.setReport((TableReportDto) report);
		return fw;
	}
	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof TableReportDto && !report.isConfigurationProtected();
	}
	
	@Override
	public String getViewId() {
		return PreFilterView.VIEW_ID;
	}

}
