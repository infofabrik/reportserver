package net.datenwerke.rs.amazons3.client.amazons3.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.amazons3.client.amazons3.AmazonS3UiModule;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.client.amazons3.ui.AmazonS3DatasinkForm;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;

public class AmazonS3DatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

   private final Provider<AmazonS3DatasinkForm> formProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public AmazonS3DatasinkConfigProviderHooker(Provider<AmazonS3DatasinkForm> formProvider,
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
      return AmazonS3UiModule.TYPE;
   }

   @Override
   public String getDatasinkName() {
      return AmazonS3UiModule.NAME;
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new AmazonS3DatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return AmazonS3UiModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return enterpriseServiceProvider.get().isEnterprise();
   }

}
