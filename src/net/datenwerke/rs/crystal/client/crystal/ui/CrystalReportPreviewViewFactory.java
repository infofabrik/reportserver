package net.datenwerke.rs.crystal.client.crystal.ui;

import java.util.Collection;

import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;

import com.google.inject.Inject;

public class CrystalReportPreviewViewFactory extends PreviewViewFactory {
	
	private ReportExecutorUIService reportExecutorUIService;

	@Inject
	public CrystalReportPreviewViewFactory(ReportExecutorUIService reportExecutorUIService) {
		this.reportExecutorUIService = reportExecutorUIService;
	}
	
	@Override
	public ReportExecutorMainPanelView newInstance(ReportDto report, Collection<? extends ReportViewConfiguration> configs) {
		AbstractReportPreviewView view = reportExecutorUIService.getPdfPreviewView();
		view.setReport(report);
		
		return view;
	}

	@Override
	public boolean consumes(ReportDto report) {
		return (report instanceof CrystalReportDto);
	}

}
