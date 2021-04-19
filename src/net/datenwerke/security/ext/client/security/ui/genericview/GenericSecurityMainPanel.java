package net.datenwerke.security.ext.client.security.ui.genericview;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;
import net.datenwerke.security.ext.client.security.ui.aclview.GenericTargetACLView;

@Singleton
public class GenericSecurityMainPanel extends DwContentPanel {

	private final Provider<GenericTargetACLView> aclViewProvider;
	
	@Inject
	public GenericSecurityMainPanel(
		Provider<GenericTargetACLView> aclViewProvider
		){
		/* store objects */
		this.aclViewProvider = aclViewProvider;
		
		initializeUI();
	}

	private void initializeUI() {
		setHeading(SecurityMessages.INSTANCE.securityManagement()); //$NON-NLS-1$
	}
	
	public void displayGenericRightsItem(final GenericSecurityViewDomainHook domain){
		/* clear panel */
		clear();
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		add(container);
		
		/* load */
		GenericTargetIdentifier genericTargetId = domain.genericSecurityViewDomainHook_getTargetId();
		
		GenericTargetACLView view = aclViewProvider.get();
		view.initialize(genericTargetId);
		
		/* description */
		view.addInfoText(domain.genericSecurityViewDomainHook_getDescription());
		
		/* panel */
		container.add(view, new VerticalLayoutData(1,-1, new Margins(10)));
		
		/* redo layout */
		forceLayout();
	}

}
