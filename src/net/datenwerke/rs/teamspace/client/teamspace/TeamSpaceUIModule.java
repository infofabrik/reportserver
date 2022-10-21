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
   public static final String TEAMSPACE_FILE_IMPORT_HANDLER_ID = "teamSpaceFileImportHandlerId";
   public static final String TEAMSPACE_IMPORT_TEAMSPACE_ID = "teamSpaceImportTeamspaceId";
   public static final String TEAMSPACE_IMPORT_FOLDER_ID = "teamSpaceImportFolderId";
   public static final String TEAMSPACE_IMPORT_NAME = "teamSpaceImportName";
   public static final String TEAMSPACE_IMPORT_DESCRIPTION = "teamSpaceImportDescription";

   @Override
   protected void configure() {
      bind(TeamSpaceUIService.class).to(TeamSpaceUIServiceImpl.class).in(Singleton.class);

      /* startup */
      bind(TeamSpaceUIStartup.class).asEagerSingleton();
   }

}
