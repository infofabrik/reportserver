package net.datenwerke.rs.ftp.client.ftp.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormSubmissionCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldChanged;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.datasinks.hooks.DatasinkAuthenticatorConfiguratorHook;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.pa.FtpsDatasinkDtoPA;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpsDatasinkConfigProviderHooker;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;

/**
 * Form used to edit {@link FtpsDatasink}s in the administration view.
 *
 */
public class FtpsDatasinkForm extends SimpleFormView {

   private final Provider<FileUploadUiService> fileUploadServiceProvider;

   private List<DatasinkAuthenticatorConfiguratorHook> configs;

   private String authTypeKey;

   /**
    * Contains the specific fields depending on the authentication type value
    * selected.
    */
   private Widget authenticationTypeForm;

   @Inject
   public FtpsDatasinkForm(Provider<FileUploadUiService> fileUploadServiceProvider, HookHandlerService hookHandler) {
      this.fileUploadServiceProvider = fileUploadServiceProvider;

      this.configs = hookHandler.getHookers(DatasinkAuthenticatorConfiguratorHook.class);
   }

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name name */
      form.addField(String.class, FtpsDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      form.addField(String.class, FtpsDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);
      form.beginFloatRow();

      /* host */
      form.addField(String.class, FtpsDatasinkDtoPA.INSTANCE.host(), BaseMessages.INSTANCE.host());

      form.setFieldWidth(50);
      /* port */
      form.addField(Integer.class, FtpsDatasinkDtoPA.INSTANCE.port(), BaseMessages.INSTANCE.port());

      form.endRow();

      form.setFieldWidth(810);

      /* folder */
      form.addField(String.class, FtpsDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

      form.setFieldWidth(0.3);

      // active / passive mode
      form.addField(List.class, FtpsDatasinkDtoPA.INSTANCE.ftpMode(), BaseMessages.INSTANCE.mode(),
            new SFFCStaticDropdownList<String>() {

               private Map<String, String> map;

               @Override
               public Map<String, String> getValues() {
                  if (null == map) {
                     map = new HashMap<>();
                     map.put(BaseMessages.INSTANCE.active(), FtpsDatasinkConfigProviderHooker.ACTIVE_MODE);
                     map.put(BaseMessages.INSTANCE.passive(), FtpsDatasinkConfigProviderHooker.PASSIVE_MODE);
                  }

                  return map;
               }
            });

      // dataChannelProtectionLevel
      form.addField(List.class, FtpsDatasinkDtoPA.INSTANCE.dataChannelProtectionLevel(),
            BaseMessages.INSTANCE.protectionLevel(), new SFFCStaticDropdownList<String>() {

               private Map<String, String> map;

               @Override
               public Map<String, String> getValues() {
                  if (null == map) {
                     map = new HashMap<>();
                     map.put("Clear (C)", "C");
                     map.put("Safe (S)", "S");
                     map.put("Confidential (E)", "E");
                     map.put("Private (P)", "P");
                  }

                  return map;
               }
            });

      addAuthenticationType(form);

   }

   private void addAuthenticationType(SimpleForm form) {
      form.setFieldWidth(0.3);
      authTypeKey = form.addField(List.class, FtpsDatasinkDtoPA.INSTANCE.authenticationType(),
            DatasinksMessages.INSTANCE.authenticationType(), new SFFCStaticDropdownList<String>() {

               private Map<String, String> map;

               @Override
               public Map<String, String> getValues() {
                  if (null == map) {
                     map = new HashMap<>();

                     final FtpsDatasinkDto datasink = (FtpsDatasinkDto) getSelectedNode();
                     final Optional<String> authType = Optional.ofNullable(datasink.getAuthenticationType());
                     configs.stream().filter(config -> config.consumes(datasink))
                           .forEach(config -> map.put(config.getAuthenticatorLabel(),
                                 authType.isPresent() ? authType.get() : config.getAuthenticatorName()));
                  }

                  return map;
               }
            });

      form.setFieldWidth(1);

      final DwContentPanel specificsWrapper = DwContentPanel.newInlineInstance();

      form.addField(CustomComponent.class, new SFFCCustomComponent() {
         @Override
         public Widget getComponent() {
            return specificsWrapper;
         }
      });

      form.addCondition(authTypeKey, new FieldChanged(), new SimpleFormAction() {
         public void onSuccess(SimpleForm form) {
            final FtpsDatasinkDto datasink = (FtpsDatasinkDto) getSelectedNode();
            final Optional<String> authType = Optional.ofNullable((String) form.getValue(authTypeKey));
            if (authType.isPresent())
               datasink.setAuthenticationType(authType.get());
            else
               datasink.setAuthenticationType(null);

            if (authType.isPresent()) {
               if (null != authenticationTypeForm)
                  authenticationTypeForm.removeFromParent();

               configs.stream().filter(config -> config.consumes(datasink))
                     .filter(config -> authType.isPresent() && authType.get().equals(config.getAuthenticatorName()))
                     .findAny().ifPresent(config -> {
                        authenticationTypeForm = config.configureForm(FtpsDatasinkForm.this, datasink);
                        if (null != authenticationTypeForm)
                           specificsWrapper.add(authenticationTypeForm, new MarginData(10));
                     });
            }
         }

         public void onFailure(SimpleForm form) {
         }
      });

   }

   @Override
   protected void onSubmit(SimpleFormSubmissionCallback callback) {
      /* submit the main form */
      super.onSubmit(callback);

      /* we have to submit forms containing upload forms */
      final Optional<String> authType = Optional.ofNullable((String) form.getValue(authTypeKey));
      final FtpsDatasinkDto datasink = (FtpsDatasinkDto) getSelectedNode();
      if (authType.isPresent()) {
         configs.stream().filter(config -> config.consumes(datasink) && config.isUploadForm())
               .filter(config -> authType.isPresent() && authType.get().equals(config.getAuthenticatorName())).findAny()
               .ifPresent(config -> {
                  if (null != authenticationTypeForm && authenticationTypeForm instanceof SimpleForm)
                     ((SimpleForm) authenticationTypeForm).submit();
               });
      }
   }

   @Override
   protected String getFormAction() {
      return fileUploadServiceProvider.get().getFormAction();
   }

}