package net.datenwerke.rs.core.client.datasourcemanager.ui;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceTreeManagerDao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The actual implementation of the user managers main component.
 * 
 *
 */
@Singleton
public class DatasourceManagerMainPanel extends AbstractTreeMainPanel {

	@Inject
	public DatasourceManagerMainPanel(
		DatasourceTreeManagerDao datasourceTreeManager
		){
	
		super(datasourceTreeManager);
	}
	
	@Override
	protected String getToolbarName() {
		return "datasource:admin:view:toolbar";
	}
}
