package net.datenwerke.rs.googledrive.client.googledrive;

import com.google.gwt.inject.client.AbstractGinModule;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.googledrive.client.googledrive.provider.GoogleDriveTreeProvider;
import net.datenwerke.rs.googledrive.client.googledrive.provider.annotations.DatasinkTreeGoogleDrive;

public class GoogleDriveUiModule extends AbstractGinModule {
   
   public final static String GOOGLE_DRIVE_NAME = "Google Drive";

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeGoogleDrive.class).toProvider(GoogleDriveTreeProvider.class);
      bind(GoogleDriveUiStartup.class).asEagerSingleton();

   }

}