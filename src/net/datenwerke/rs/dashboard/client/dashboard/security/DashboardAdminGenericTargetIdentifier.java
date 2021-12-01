package net.datenwerke.rs.dashboard.client.dashboard.security;

import net.datenwerke.rs.dashboard.service.dashboard.genrights.DashboardAdminSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

@GenericTargetIdentifierMapper(DashboardAdminSecurityTarget.class)
public class DashboardAdminGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7570274216654109692L;


}