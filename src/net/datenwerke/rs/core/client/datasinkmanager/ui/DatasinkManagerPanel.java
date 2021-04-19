package net.datenwerke.rs.core.client.datasinkmanager.ui;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeManagerPanel;

@Singleton
public class DatasinkManagerPanel extends AbstractTreeManagerPanel {

	@Inject
	public DatasinkManagerPanel(
		DatasinkManagerMainPanel mainPanel,
		DatasinkManagerTreePanel treePanel
		){
		
		super(mainPanel, treePanel);
	}
	
	@Override
	protected String getHeadingText() {
		return "Datasinks";
	}
	
}
