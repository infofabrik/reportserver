package net.datenwerke.rs.scriptdatasink.client.scriptdatasink;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.provider.ScriptDatasinkTreeProvider;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.provider.annotations.DatasinkTreeScriptDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ScriptDatasinkUiModule extends AbstractGinModule {

   public final static String NAME = "Script Datasink";
   public final static BaseIcon ICON = BaseIcon.FILE_CODE_O;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = ScriptDatasinkDto.class;

   @Override
   protected void configure() {
      bind(ScriptDatasinkUiService.class).to(ScriptDatasinkUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeScriptDatasink.class).toProvider(ScriptDatasinkTreeProvider.class);
      bind(ScriptDatasinkUiStartup.class).asEagerSingleton();
   }

}