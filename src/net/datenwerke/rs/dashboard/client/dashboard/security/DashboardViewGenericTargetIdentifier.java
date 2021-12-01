package net.datenwerke.rs.dashboard.client.dashboard.security;

import net.datenwerke.rs.dashboard.service.dashboard.genrights.DashboardViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

@GenericTargetIdentifierMapper(DashboardViewSecurityTarget.class)
public class DashboardViewGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522620164668778717L;

}