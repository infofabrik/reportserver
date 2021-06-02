package net.datenwerke.rs.onedrive.client.onedrive.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.onedrive.client.onedrive.ui.OneDriveDatasinkForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class OneDriveDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {
   private final Provider<OneDriveDatasinkForm> formProvider;

   @Inject
   public OneDriveDatasinkConfigProviderHooker(Provider<OneDriveDatasinkForm> formProvider) {

      /* store objects */
      this.formProvider = formProvider;
   }

   @Override
   public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
      return OneDriveDatasinkDto.class.equals(datasinkDefinition.getClass());
   }

   @Override
   public Collection<? extends MainPanelView> getAdminViews(DatasinkDefinitionDto datasinkDefinition) {
      return Collections.singleton(formProvider.get());
   }

   @Override
   public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
      return OneDriveDatasinkDto.class;
   }

   @Override
   public String getDatasinkName() {
      return "OneDrive";
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new OneDriveDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return BaseIcon.CLOUD_UPLOAD.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }

}