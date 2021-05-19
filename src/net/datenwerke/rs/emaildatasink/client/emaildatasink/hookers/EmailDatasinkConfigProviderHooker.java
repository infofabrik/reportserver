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
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.ui.EmailDatasinkForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class EmailDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {
   private final Provider<EmailDatasinkForm> formProvider;

   @Inject
   public EmailDatasinkConfigProviderHooker(Provider<EmailDatasinkForm> formProvider) {

      /* store objects */
      this.formProvider = formProvider;
   }

   @Override
   public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
      return EmailDatasinkDto.class.equals(datasinkDefinition.getClass());
   }

   @Override
   public Collection<? extends MainPanelView> getAdminViews(DatasinkDefinitionDto datasinkDefinition) {
      return Collections.singleton(formProvider.get());
   }

   @Override
   public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
      return EmailDatasinkDto.class;
   }

   @Override
   public String getDatasinkName() {
      return DatasinksMessages.INSTANCE.email() + " - SMTP";
   }

   @Override
   public AbstractDatasinkManagerNodeDto instantiateDatasink() {
      return new EmailDatasinkDto();
   }

   @Override
   public ImageResource getDatasinkIcon() {
      return BaseIcon.SEND.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }

}