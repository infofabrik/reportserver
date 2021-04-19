package net.datenwerke.rs.core.client.reportmanager.ui;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeManagerDao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The actual implementation of the user managers main component.
 * 
 *
 */
@Singleton
public class ReportManagerMainPanel extends AbstractTreeMainPanel {

	@Inject
	public ReportManagerMainPanel(
		ReportManagerTreeManagerDao reportManagerTreeManager
		){
	
		super(reportManagerTreeManager);
	}
	
	@Override
	protected String getToolbarName() {
		return "reportmanager:admin:view:toolbar";
	}
}
