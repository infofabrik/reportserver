package net.datenwerke.rs.grideditor.client.grideditor.ui;

import java.util.Collection;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GridEditorReportPreviewViewFactory extends PreviewViewFactory {
	
	private final Provider<GridEditorReportPreviewView> brpvProvider;

	@Inject
	public GridEditorReportPreviewViewFactory(
			Provider<GridEditorReportPreviewView> brpvProvider 
	) {
		this.brpvProvider = brpvProvider;
	}
	
	@Override
	public ReportExecutorMainPanelView newInstance(ReportDto report, Collection<? extends ReportViewConfiguration> configs) {
		AbstractReportPreviewView view = brpvProvider.get();
		view.setReport(report);
		
		return view;
	}

	@Override
	public boolean consumes(ReportDto report) {
		return (report instanceof GridEditorReportDto);
	}

}
