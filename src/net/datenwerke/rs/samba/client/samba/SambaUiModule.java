package net.datenwerke.rs.samba.client.samba;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.samba.client.samba.provider.SambaTreeProvider;
import net.datenwerke.rs.samba.client.samba.provider.annotations.DatasinkTreeSamba;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SambaUiModule extends AbstractGinModule {

   public final static String NAME = "Samba - SMB/CIFS";
   public final static BaseIcon ICON = BaseIcon.ANGLE_DOUBLE_UP;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = SambaDatasinkDto.class;
   
   @Override
   protected void configure() {
      bind(SambaUiService.class).to(SambaUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeSamba.class).toProvider(SambaTreeProvider.class);

      bind(SambaUiStartup.class).asEagerSingleton();
   }

}