package net.datenwerke.rs.box.client.box;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto;
import net.datenwerke.rs.box.client.box.provider.BoxTreeProvider;
import net.datenwerke.rs.box.client.box.provider.annotations.DatasinkTreeBox;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class BoxUiModule extends AbstractGinModule {
   
   public final static String NAME = "Box";
   public final static BaseIcon ICON = BaseIcon.CUBE;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = BoxDatasinkDto.class;

   @Override
   protected void configure() {
      bind(BoxUiService.class).to(BoxUiServiceImpl.class).in(Singleton.class);
      
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeBox.class).toProvider(BoxTreeProvider.class);
      bind(BoxUiStartup.class).asEagerSingleton();

   }

}
