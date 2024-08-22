package net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkUiModule;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.ui.EmailDatasinkForm;

public class EmailDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {
   private final Provider<EmailDatasinkForm> formProvider;

   @Inject
   public EmailDatasinkConfigProviderHooker(Provider<EmailDatasinkForm> formProvider) {

      /* store objects */
      this.formProvider = formProvider;
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
      return EmailDatasinkUiModule.TYPE;
   }

   @Override
   public String getDatasinkName() {
      return EmailDatasinkUiModule.NAME;
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new EmailDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return EmailDatasinkUiModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }

}