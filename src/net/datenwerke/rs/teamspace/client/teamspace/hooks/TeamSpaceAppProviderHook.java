package net.datenwerke.rs.teamspace.client.teamspace.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceApp;

import com.google.inject.Provider;

/**
 * 
 *
 */
public class TeamSpaceAppProviderHook extends ObjectHook<TeamSpaceApp> {

	public TeamSpaceAppProviderHook(Provider<? extends TeamSpaceApp> provider) {
		super(provider);
	}

}
