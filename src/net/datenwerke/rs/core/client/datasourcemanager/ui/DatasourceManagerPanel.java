package net.datenwerke.rs.core.client.datasourcemanager.ui;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeManagerPanel;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DatasourceManagerPanel extends AbstractTreeManagerPanel {

	@Inject
	public DatasourceManagerPanel(
		DatasourceManagerMainPanel mainPanel,
		DatasourceManagerTreePanel treePanel
		){
		
		super(mainPanel, treePanel);
	}
	
	@Override
	protected String getHeadingText() {
		return DatasourcesMessages.INSTANCE.datasources();
	}
	
}
