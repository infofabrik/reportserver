package net.datenwerke.rs.googledrive.client.googledrive.hookers;

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
import net.datenwerke.rs.googledrive.client.googledrive.GoogleDriveUiModule;
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;
import net.datenwerke.rs.googledrive.client.googledrive.ui.GoogleDriveDatasinkForm;

public class GoogleDriveDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

   private final Provider<GoogleDriveDatasinkForm> formProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public GoogleDriveDatasinkConfigProviderHooker(Provider<GoogleDriveDatasinkForm> formProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider) {

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
      return GoogleDriveUiModule.TYPE;
   }

   @Override
   public String getDatasinkName() {
      return GoogleDriveUiModule.NAME;
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new GoogleDriveDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return GoogleDriveUiModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return enterpriseServiceProvider.get().isEnterprise();
   }

}
