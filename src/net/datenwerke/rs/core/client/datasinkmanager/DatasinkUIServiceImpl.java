package net.datenwerke.rs.core.client.datasinkmanager;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.config.DatasinkDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionFieldFactory;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DatasinkUIServiceImpl implements DatasinkUIService {

   private final HookHandlerService hookHandler;

   private Map<Class<? extends DatasinkDefinitionDto>, Provider<? extends DatasinkDefinitionConfigConfigurator>> configuratorLookup;
   private final DatasinkSelectionFieldFactory fieldFactory;

   @Inject
   public DatasinkUIServiceImpl(
         HookHandlerService hookHandler, 
         DatasinkSelectionFieldFactory fieldFactory
         ) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.fieldFactory = fieldFactory;
   }

   public DatasinkDefinitionConfigConfigurator getConfigurator(Class<? extends DatasinkDefinitionDto> configClazz) {
      if (null == configuratorLookup)
         initConfigurator();

      Provider<? extends DatasinkDefinitionConfigConfigurator> provider = configuratorLookup.get(configClazz);
      if (null == provider)
         throw new IllegalStateException("I should probably know the provider for " + configClazz.getName()); //$NON-NLS-1$
      return provider.get();
   }

   protected void initConfigurator() {
      configuratorLookup = new HashMap<Class<? extends DatasinkDefinitionDto>, Provider<? extends DatasinkDefinitionConfigConfigurator>>();

   }

   @Override
   public DatasinkSelectionField getSelectionField(Provider<? extends DatasinkDao> datasinkDaoProvider, BaseIcon defaultDatasinkIcon,
         Container container, Provider<UITree> datasinkTreeProvider, Class<? extends DatasinkDefinitionDto>... types) {
      return fieldFactory.create(datasinkDaoProvider, defaultDatasinkIcon, container, datasinkTreeProvider.get(), types);
   }

   @Override
   public DatasinkSelectionField getSelectionField(Provider<? extends DatasinkDao> datasinkDaoProvider, BaseIcon defaultDatasinkIcon,
         Container container, Provider<UITree> datasinkTreeProvider) {
      return fieldFactory.create(datasinkDaoProvider, defaultDatasinkIcon,  container, datasinkTreeProvider.get(), new Class[] {});
   }

   @Override
   public DatasinkSelectionField getSelectionField(Provider<? extends DatasinkDao> datasinkDaoProvider, BaseIcon defaultDatasinkIcon,
         Container container, UITree datasinkTree) {
      return fieldFactory.create(datasinkDaoProvider, defaultDatasinkIcon, container, datasinkTree, new Class[] {});
   }

}
