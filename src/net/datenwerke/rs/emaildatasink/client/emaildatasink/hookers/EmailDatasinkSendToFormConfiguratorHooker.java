package net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkUiModule;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class EmailDatasinkSendToFormConfiguratorHooker implements DatasinkSendToFormConfiguratorHook {

   private final Provider<LoginService> loginServiceProvider;

   private String rcptKey;
   private String subjectKey;
   private String msgKey;

   @Inject
   public EmailDatasinkSendToFormConfiguratorHooker(Provider<LoginService> loginServiceProvider) {
      this.loginServiceProvider = loginServiceProvider;
   }

   @Override
   public boolean consumes(Class<? extends DatasinkDefinitionDto> datasinkType) {
      return EmailDatasinkDto.class.equals(datasinkType);
   }

   @Override
   public void installAdditionalFields(SimpleForm form) {
      rcptKey = form.addField(StrippedDownUser.class, ReportExporterMessages.INSTANCE.selectUserLabel());

      subjectKey = form.addField(String.class, ReportExporterMessages.INSTANCE.subjectLabel());
      msgKey = form.addField(String.class, ReportExporterMessages.INSTANCE.messageLabel(), new SFFCTextAreaImpl());

      form.setValue(subjectKey, "");
      form.setValue(msgKey, "");
      UserDto user = loginServiceProvider.get().getCurrentUser();
      form.setValue(rcptKey, Arrays.asList(StrippedDownUser.fromUser(user)));
   }

   @Override
   public Optional<Map<String, Object>> getAdditionalFieldsValues(SimpleForm form) {
      Map<String, Object> values = new HashMap<>();
      values.put(EmailDatasinkUiModule.DATASINK_SUBJECT, (String) form.getValue(subjectKey));
      values.put(EmailDatasinkUiModule.DATASINK_MESSSAGE, (String) form.getValue(msgKey));
      values.put(EmailDatasinkUiModule.DATASINK_RCPTLIST, (List<StrippedDownUser>) form.getValue(rcptKey));

      return Optional.of(values);
   }

   @Override
   public String getWindowTitle() {
      return EmailDatasinkUiModule.NAME;
   }

   @Override
   public BaseIcon getIcon() {
      return EmailDatasinkUiModule.ICON;
   }

   @Override
   public int getWindowHeight() {
      return 715;
   }

   @Override
   public boolean isFolderedDatasink() {
      return false;
   }
   
   @Override
   public boolean isCanCompress() {
      return true;
   }

}