package net.datenwerke.rs.core.client.datasourcemanager;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionFieldFactory;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DatasourceUIServiceImpl implements DatasourceUIService {

   private final HookHandlerService hookHandler;
   private final DatasourceDao datasourceDao;

   private Map<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>> configuratorLookup;
   private final DatasourceSelectionFieldFactory fieldFactory;

   @Inject
   public DatasourceUIServiceImpl(HookHandlerService hookHandler, DatasourceDao generalPropertiesDao,
         DatasourceSelectionFieldFactory fieldFactory) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.datasourceDao = generalPropertiesDao;
      this.fieldFactory = fieldFactory;
   }

   public DatasourceDefinitionConfigConfigurator getConfigurator(Class<? extends DatasourceDefinitionDto> configClazz) {
      if (null == configuratorLookup)
         initConfigurator();

      Provider<? extends DatasourceDefinitionConfigConfigurator> provider = configuratorLookup.get(configClazz);
      if (null == provider)
         throw new IllegalStateException("I should probably know the provider for " + configClazz.getName()); //$NON-NLS-1$
      return provider.get();
   }

   protected void initConfigurator() {
      configuratorLookup = new HashMap<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>>();

      for (DatasourceDefinitionConfigProviderHook configProvider : hookHandler
            .getHookers(DatasourceDefinitionConfigProviderHook.class))
         configuratorLookup.putAll(configProvider.getConfigs());
   }

   public DatasourceSelectionField getSelectionField(Container container, boolean displayConfigFields,
         Provider<UITree> datasourceTreeProvider, Class<? extends DatasourceDefinitionDto>... types) {
      return fieldFactory.create(displayConfigFields, container, datasourceTreeProvider.get(), datasourceDao, types);
   }

   public DatasourceSelectionField getSelectionField(Container container, Provider<UITree> datasourceTreeProvider) {
      return fieldFactory.create(true, container, datasourceTreeProvider.get(), datasourceDao, new Class[] {});
   }

   @Override
   public DatasourceSelectionField getSelectionField(BaseIcon icon, Container container, UITree datasinkTreeProvider,
         Class<? extends DatasourceDefinitionDto>... types) {
      return fieldFactory.create(false, container, datasinkTreeProvider, datasourceDao, types);
   }

}
