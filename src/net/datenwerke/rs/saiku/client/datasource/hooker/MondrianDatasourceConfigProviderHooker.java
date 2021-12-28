package net.datenwerke.rs.saiku.client.datasource.hooker;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.saiku.client.datasource.config.MondrianDatasourceConfigConfigurator;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.datasource.ui.MondrianDatasourceForm;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class MondrianDatasourceConfigProviderHooker implements DatasourceDefinitionConfigProviderHook {

   private final Provider<MondrianDatasourceConfigConfigurator> configurator;
   private final Provider<MondrianDatasourceForm> formProvider;

   @Inject
   public MondrianDatasourceConfigProviderHooker(Provider<MondrianDatasourceConfigConfigurator> configurator,
         Provider<MondrianDatasourceForm> formProvider) {

      this.configurator = configurator;
      this.formProvider = formProvider;
   }

   @Override
   public boolean consumes(DatasourceDefinitionDto dsd) {
      return dsd instanceof MondrianDatasourceDto;
   }

   @Override
   public Collection<? extends MainPanelView> getAdminViews(DatasourceDefinitionDto dsd) {
      return Collections.singleton(formProvider.get());
   }

   @Override
   public Map<? extends Class<? extends DatasourceDefinitionDto>, ? extends Provider<? extends DatasourceDefinitionConfigConfigurator>> getConfigs() {
      Map<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>> map = new HashMap<>();
      map.put(MondrianDatasourceDto.class, configurator);
      return map;
   }

   @Override
   public Class<? extends AbstractDatasourceManagerNodeDto> getDatasourceClass() {
      return MondrianDatasourceDto.class;
   }

   @Override
   public String getDatasourceName() {
      return SaikuMessages.INSTANCE.databaseDatasourceTypeName();
   }

   @Override
   public AbstractDatasourceManagerNodeDto instantiateDatasource() {
      return new MondrianDatasourceDto();
   }

   @Override
   public ImageResource getDatasourceIcon() {
      return BaseIcon.CUBES.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }
}
