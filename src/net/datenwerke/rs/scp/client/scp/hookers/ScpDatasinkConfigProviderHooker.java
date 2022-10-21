package net.datenwerke.rs.scp.client.scp.hookers;

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
import net.datenwerke.rs.scp.client.scp.ScpUiModule;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.client.scp.ui.ScpDatasinkForm;

public class ScpDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

   private final Provider<ScpDatasinkForm> formProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public ScpDatasinkConfigProviderHooker(Provider<ScpDatasinkForm> formProvider,
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
      return ScpUiModule.TYPE;
   }

   @Override
   public String getDatasinkName() {
      return ScpUiModule.NAME;
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new ScpDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return ScpUiModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return enterpriseServiceProvider.get().isEnterprise();
   }

}
