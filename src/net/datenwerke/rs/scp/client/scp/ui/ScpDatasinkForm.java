package net.datenwerke.rs.scp.client.scp.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
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

public class ScpDatasinkForm extends SimpleFormView {
   
   private List<DatasinkAuthenticatorConfiguratorHook> configs;

   private String authTypeKey;
   
   /**
    * Contains the specific fields depending on the authentication type value
    * selected.
    */
   private Widget authenticationTypeForm;
   
   @Inject
   public ScpDatasinkForm(
         HookHandlerService hookHandler) {
      this.configs = hookHandler.getHookers(DatasinkAuthenticatorConfiguratorHook.class);
   }

   @Override
   protected void configureSimpleForm(SimpleForm form) {
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
                     final Optional<String> authType = Optional.ofNullable(datasink.getAuthenticationType());
                     configs
                        .stream()
                        .filter(config -> config.consumes(datasink))
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
            final ScpDatasinkDto datasink = (ScpDatasinkDto) getSelectedNode();
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

}
