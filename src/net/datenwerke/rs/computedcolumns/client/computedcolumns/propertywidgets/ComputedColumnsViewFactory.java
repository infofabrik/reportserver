package net.datenwerke.rs.computedcolumns.client.computedcolumns.propertywidgets;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ComputedColumnsViewFactory implements ReportViewFactory {

	private final Provider<ComputedColumnsView> viewProvider;
	
	
	@Inject
	public ComputedColumnsViewFactory(
		Provider<ComputedColumnsView> filterViewProvider
		) {
		
		this.viewProvider = filterViewProvider;
	}
	
	public ComputedColumnsView newInstance(ReportDto report, Collection<? extends ReportViewConfiguration> configs) {
		ComputedColumnsView fw = viewProvider.get();
		fw.setReport((TableReportDto) report);
		return fw;
	}
	
	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof TableReportDto && !report.isConfigurationProtected();
	}
	
	@Override
	public String getViewId() {
		return ComputedColumnsView.VIEW_ID;
	}

}
