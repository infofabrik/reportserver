package net.datenwerke.rs.saikupivot.client.table;

import java.util.Collection;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class SaikuTableReportPreviewViewFactory extends PreviewViewFactory {
	
	private final Provider<SaikuTableReportPreviewView> brpvProvider;

	@Inject
	public SaikuTableReportPreviewViewFactory(
			Provider<SaikuTableReportPreviewView> brpvProvider 
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
		return (report instanceof TableReportDto) && ((TableReportDto)report).isCubeFlag();
	}

}
