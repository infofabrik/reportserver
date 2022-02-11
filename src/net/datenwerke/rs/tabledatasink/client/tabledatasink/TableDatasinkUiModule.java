package net.datenwerke.rs.tabledatasink.client.tabledatasink;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.provider.TableDatasinkTreeProvider;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.provider.annotations.DatasinkTreeTableDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class TableDatasinkUiModule extends AbstractGinModule {

   public final static String NAME = "Table Datasink";
   public final static BaseIcon ICON = BaseIcon.TABLE;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = TableDatasinkDto.class;

   @Override
   protected void configure() {
      bind(TableDatasinkUiService.class).to(TableDatasinkUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeTableDatasink.class).toProvider(TableDatasinkTreeProvider.class);
      bind(TableDatasinkUiStartup.class).asEagerSingleton();

   }

}
