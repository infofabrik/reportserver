package net.datenwerke.rs.core.client.reportexecutor.ui.preview;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewFactory;

/**
 * Marker interface to tell that the view presents a preview.
 *
 */
public abstract class PreviewViewFactory implements ReportViewFactory {
	
	@Override
	public String getViewId() {
		return AbstractReportPreviewView.VIEW_ID;
	}
}
