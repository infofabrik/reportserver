package net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.TableDatasinkUiModule;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.ui.TableDatasinkForm;

public class TableDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

   private final Provider<TableDatasinkForm> formProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public TableDatasinkConfigProviderHooker(
         Provider<TableDatasinkForm> formProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider
         ) {

      /* store objects */
      this.formProvider = formProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
   }

   @Override
   public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
      return getDatasinkClass().equals(datasinkDefinition.getClass());
   }

   @Override
   public Collection<? extends MainPanelView> getAdminViews(DatasinkDefinitionDto datasinkDefinition) {
      return Collections.singleton(formProvider.get());
   }

   @Override
   public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
      return TableDatasinkUiModule.TYPE;
   }

   @Override
   public String getDatasinkName() {
      return TableDatasinkUiModule.NAME;
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new TableDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return TableDatasinkUiModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return enterpriseServiceProvider.get().isEnterprise();
   }

}
