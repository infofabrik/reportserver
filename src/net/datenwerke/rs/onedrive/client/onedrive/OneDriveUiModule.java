package net.datenwerke.rs.onedrive.client.onedrive;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.onedrive.client.onedrive.provider.OneDriveTreeProvider;
import net.datenwerke.rs.onedrive.client.onedrive.provider.annotations.DatasinkTreeOneDrive;

public class OneDriveUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeOneDrive.class).toProvider(OneDriveTreeProvider.class);
      bind(OneDriveUiStartup.class).asEagerSingleton();

   }

}