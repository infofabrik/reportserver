package net.datenwerke.rs.googledrive.client.googledrive;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;
import net.datenwerke.rs.googledrive.client.googledrive.provider.GoogleDriveTreeProvider;
import net.datenwerke.rs.googledrive.client.googledrive.provider.annotations.DatasinkTreeGoogleDrive;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class GoogleDriveUiModule extends AbstractGinModule {

   public final static String NAME = "Google Drive";
   public final static BaseIcon ICON = BaseIcon.GOOGLE;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = GoogleDriveDatasinkDto.class;

   @Override
   protected void configure() {
      bind(GoogleDriveUiService.class).to(GoogleDriveUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeGoogleDrive.class).toProvider(GoogleDriveTreeProvider.class);
      bind(GoogleDriveUiStartup.class).asEagerSingleton();

   }

}