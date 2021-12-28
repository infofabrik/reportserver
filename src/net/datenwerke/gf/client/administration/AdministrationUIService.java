package net.datenwerke.gf.client.administration;

import com.google.inject.ImplementedBy;

import net.datenwerke.gf.client.homepage.modules.ClientMainModule;

@ImplementedBy(AdministrationUIServiceImpl.class)
public interface AdministrationUIService extends ClientMainModule {

	/**
	 * Event is thrown if user has admin rights
	 */
	public static final String REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS = "REPORTSERVER_EVENT_ADMINISTRATION_HAS_ADMIN_RIGHTS"; //$NON-NLS-1$

	
}
