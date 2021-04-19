package net.datenwerke.rs.base.client.reportengines.table.columnfilter.propertywidgets;

import java.util.Collection;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.security.client.security.dto.ExecuteDto;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class FilterViewFactory implements ReportViewFactory{
	
	private final Provider<FilterView> filterViewProvider;
	
	@Inject
	public FilterViewFactory(
			Provider<FilterView> filterViewProvider
		) {
		this.filterViewProvider = filterViewProvider;
	}
	
	public FilterView newInstance(ReportDto report, Collection<? extends ReportViewConfiguration> configs) {
		FilterView fw = filterViewProvider.get();
		fw.setReport((TableReportDto) report);
		return fw;
	}

	public boolean consumes(ReportDto report) {
		return 
			report instanceof TableReportDto &&
			report.hasAccessRight(ExecuteDto.class) && ! ((TableReportDto)report).isCubeFlag() && !report.isConfigurationProtected();
	}
	
	@Override
	public String getViewId() {
		return FilterView.VIEW_ID;
	}
}
