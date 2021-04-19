package net.datenwerke.security.ext.client.usermanager.ui;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeManagerPanel;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserManagerPanel extends AbstractTreeManagerPanel {

	@Inject
	public UserManagerPanel(
		UserMainPanel mainPanel,
		UserTreePanel treePanel
		){
		
		super(mainPanel, treePanel);
	}

	@Override
	protected String getHeadingText() {
		return UsermanagerMessages.INSTANCE.usermanagement();
	}
}
