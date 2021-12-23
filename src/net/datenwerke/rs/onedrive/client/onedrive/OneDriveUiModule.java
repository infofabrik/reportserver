package net.datenwerke.rs.onedrive.client.onedrive;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.onedrive.client.onedrive.provider.OneDriveTreeProvider;
import net.datenwerke.rs.onedrive.client.onedrive.provider.annotations.DatasinkTreeOneDrive;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class OneDriveUiModule extends AbstractGinModule {

   public final static String NAME = "OneDrive - SharePoint (O365)";
   public final static BaseIcon ICON = BaseIcon.CLOUD_UPLOAD;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = OneDriveDatasinkDto.class;
   
   @Override
   protected void configure() {
      bind(OneDriveUiService.class).to(OneDriveUiServiceImpl.class).in(Singleton.class);
      
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeOneDrive.class).toProvider(OneDriveTreeProvider.class);
      bind(OneDriveUiStartup.class).asEagerSingleton();

   }

}