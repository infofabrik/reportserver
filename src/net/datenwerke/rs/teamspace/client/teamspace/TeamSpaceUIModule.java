package net.datenwerke.rs.teamspace.client.teamspace;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * 
 *
 */
public class TeamSpaceUIModule extends AbstractGinModule {

   public static final String TEAMSPACE_PANEL_ID = "teamSpaceModuleMainPanel";
   public static final String TEAMSPACE_ID_KEY = "teamspaceId";

   @Override
   protected void configure() {
      bind(TeamSpaceUIService.class).to(TeamSpaceUIServiceImpl.class).in(Singleton.class);

      /* startup */
      bind(TeamSpaceUIStartup.class).asEagerSingleton();
   }

}
