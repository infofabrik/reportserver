package net.datenwerke.rs.teamspace.service.teamspace.hooks;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceAppDefinition;

/**
 * 
 *
 */
public class TeamSpaceAppDefinitionProviderHook extends ObjectHook<TeamSpaceAppDefinition> {

	public TeamSpaceAppDefinitionProviderHook(Provider<? extends TeamSpaceAppDefinition> provider) {
		super(provider);
	}

}
