package net.datenwerke.rs.core.client.reportmanager.ui;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeManagerPanel;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ReportManagerPanel extends AbstractTreeManagerPanel {
	
	@Inject
	public ReportManagerPanel(
		ReportManagerMainPanel mainPanel,
		ReportManagerTreePanel treePanel
		){
		
		super(mainPanel, treePanel);
	}
	
	@Override
	protected String getHeadingText() {
		return ReportmanagerMessages.INSTANCE.reportmanager();
	}

}
