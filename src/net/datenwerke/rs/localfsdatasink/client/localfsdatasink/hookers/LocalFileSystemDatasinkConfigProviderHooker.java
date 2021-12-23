package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers;

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
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.LocalFileSystemUiModule;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.ui.LocalFileSystemDatasinkForm;

public class LocalFileSystemDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

   private final Provider<LocalFileSystemDatasinkForm> formProvider;
   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public LocalFileSystemDatasinkConfigProviderHooker(
         Provider<LocalFileSystemDatasinkForm> formProvider,
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
      return LocalFileSystemUiModule.TYPE;
   }

   @Override
   public String getDatasinkName() {
      return LocalFileSystemUiModule.NAME;
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new LocalFileSystemDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return LocalFileSystemUiModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return enterpriseServiceProvider.get().isEnterprise();
   }

}