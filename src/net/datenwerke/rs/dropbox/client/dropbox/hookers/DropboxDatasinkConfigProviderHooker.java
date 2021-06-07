package net.datenwerke.rs.dropbox.client.dropbox.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.client.dropbox.ui.DropboxDatasinkForm;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DropboxDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

   private final Provider<DropboxDatasinkForm> formProvider;
   
   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public DropboxDatasinkConfigProviderHooker(
         Provider<DropboxDatasinkForm> formProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider
         ) {

      /* store objects */
      this.formProvider = formProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
   }

   @Override
   public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
      return DropboxDatasinkDto.class.equals(datasinkDefinition.getClass());
   }

   @Override
   public Collection<? extends MainPanelView> getAdminViews(DatasinkDefinitionDto datasinkDefinition) {
      return Collections.singleton(formProvider.get());
   }

   @Override
   public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
      return DropboxDatasinkDto.class;
   }

   @Override
   public String getDatasinkName() {
      return "Dropbox";
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new DropboxDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return BaseIcon.DROPBOX.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return enterpriseServiceProvider.get().isEnterprise();
   }

}
