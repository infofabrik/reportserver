package net.datenwerke.rs.teamspace.client.teamspace.hooks;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceApp;

/**
 * 
 *
 */
public class TeamSpaceAppProviderHook extends ObjectHook<TeamSpaceApp> {

   public TeamSpaceAppProviderHook(Provider<? extends TeamSpaceApp> provider) {
      super(provider);
   }

}
