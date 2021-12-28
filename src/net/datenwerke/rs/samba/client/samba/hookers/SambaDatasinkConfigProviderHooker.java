package net.datenwerke.rs.samba.client.samba.hookers;

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
import net.datenwerke.rs.samba.client.samba.SambaUiModule;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.samba.client.samba.ui.SambaDatasinkForm;

public class SambaDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

   private final Provider<SambaDatasinkForm> formProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public SambaDatasinkConfigProviderHooker(Provider<SambaDatasinkForm> formProvider,
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
      return SambaUiModule.TYPE;
   }

   @Override
   public String getDatasinkName() {
      return SambaUiModule.NAME;
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new SambaDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return SambaUiModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return enterpriseServiceProvider.get().isEnterprise();
   }
}