package net.datenwerke.rs.adminutils.client.systemconsole;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;

@Singleton
public class SystemConsoleMainPanel extends DwBorderContainer {

	@Inject
	public SystemConsoleMainPanel(
		){
		initializeUI();
	}

	private void initializeUI() {
		/* set layout */
		BorderLayoutData westData = new BorderLayoutData(200);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(0,15,0,0));

		setCenterWidget(DwContentPanel.newInlineInstance());
	}
	
	public void displaySpecificConsoleItem(final SystemConsoleViewDomainHook domain){
		/* clear panel */
		clear();
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		add(container);
		
		/* add module's widget */
		Widget widget = domain.getMainWidget();
		setCenterWidget(widget);
		
		/* redo layout */
		forceLayout();
	}

}
