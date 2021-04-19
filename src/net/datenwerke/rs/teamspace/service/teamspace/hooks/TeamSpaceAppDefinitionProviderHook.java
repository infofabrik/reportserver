package net.datenwerke.rs.teamspace.service.teamspace.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceAppDefinition;

import com.google.inject.Provider;

/**
 * 
 *
 */
public class TeamSpaceAppDefinitionProviderHook extends ObjectHook<TeamSpaceAppDefinition> {

	public TeamSpaceAppDefinitionProviderHook(Provider<? extends TeamSpaceAppDefinition> provider) {
		super(provider);
	}

}
