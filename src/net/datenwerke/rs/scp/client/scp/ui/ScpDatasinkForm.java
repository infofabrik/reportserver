package net.datenwerke.rs.scp.client.scp.ui;

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
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.client.scp.dto.pa.ScpDatasinkDtoPA;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

/**
 * Form used to edit {@link ScpDatasink}s in the administration view.
 *
 */
public class ScpDatasinkForm extends SimpleFormView {

   private final Provider<FileUploadUiService> fileUploadServiceProvider;

   private List<DatasinkAuthenticatorConfiguratorHook> configs;

   private String authTypeKey;

   /**
    * Contains the specific fields depending on the authentication type value
    * selected.
    */
   private Widget authenticationTypeForm;

   @Inject
   public ScpDatasinkForm(
         Provider<FileUploadUiService> fileUploadServiceProvider, 
         HookHandlerService hookHandler) {
      this.fileUploadServiceProvider = fileUploadServiceProvider;

      this.configs = hookHandler.getHookers(DatasinkAuthenticatorConfiguratorHook.class);
   }

   public void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name name */
      form.addField(String.class, ScpDatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      form.addField(String.class, ScpDatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);
      form.beginFloatRow();

      /* host */
      form.addField(String.class, ScpDatasinkDtoPA.INSTANCE.host(), BaseMessages.INSTANCE.host());

      form.setFieldWidth(50);
      /* port */
      form.addField(Integer.class, ScpDatasinkDtoPA.INSTANCE.port(), BaseMessages.INSTANCE.port());

      form.endRow();

      form.setFieldWidth(810);

      /* folder */
      form.addField(String.class, ScpDatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

      addAuthenticationType(form);
   }

   private void addAuthenticationType(SimpleForm form) {
      form.setFieldWidth(0.3);
      
      authTypeKey = form.addField(List.class, ScpDatasinkDtoPA.INSTANCE.authenticationType(), DatasinksMessages.INSTANCE.authenticationType(),
            new SFFCStaticDropdownList<String>() {

               private Map<String, String> map;

               @Override
               public Map<String, String> getValues() {
                  if (null == map) {
                     map = new HashMap<>();

                     final ScpDatasinkDto datasink = (ScpDatasinkDto) getSelectedNode();
                     configs
                        .stream()
                        .filter(config -> config.consumes(datasink))
                        .forEach(config -> map.put(config.getAuthenticatorLabel(),
                              config.getAuthenticatorName()));
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
            final ScpDatasinkDto datasink = (ScpDatasinkDto) getSelectedNode();
            if (null==datasink) //new datasink
               return;
            
            final Optional<String> authType = Optional.ofNullable((String) form.getValue(authTypeKey));
            if (authType.isPresent()) 
               datasink.setAuthenticationType(authType.get());
            else
               datasink.setAuthenticationType(null);
            

            if (authType.isPresent()) {
               if (null != authenticationTypeForm)
                  authenticationTypeForm.removeFromParent();

               configs
                  .stream()
                  .filter(config -> config.consumes(datasink))
                  .filter(config -> authType.isPresent() && authType.get().equals(config.getAuthenticatorName()))
                  .findAny()
                  .ifPresent(config -> {
                     authenticationTypeForm = config.configureForm(ScpDatasinkForm.this, datasink);
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
      final ScpDatasinkDto datasink = (ScpDatasinkDto) getSelectedNode();
      if (authType.isPresent()) {
         configs
            .stream()
            .filter(config -> config.consumes(datasink) && config.isUploadForm())
            .filter(config -> authType.isPresent() && authType.get().equals(config.getAuthenticatorName()))
            .findAny()
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
