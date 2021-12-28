package net.datenwerke.rs.core.client.i18tools.hookers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.rs.core.client.i18tools.I18nToolsDao;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.userprofile.UserProfileViewContentHook;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public class LocalDecimalFormatUserProfileViewContentHooker implements UserProfileViewContentHook {

   private SimpleForm form;
   private String fKey;
   private I18nToolsDao i18nTools;

   @Inject
   public LocalDecimalFormatUserProfileViewContentHooker(I18nToolsDao i18nTools) {
      this.i18nTools = i18nTools;
   }

   @Override
   public void submitPressed(final SubmitTrackerToken token) {
      final RsAsyncCallback<Void> cb = new RsAsyncCallback<Void>() {
         @Override
         public void onSuccess(Void result) {
            token.setCompleted();
         }

         @Override
         public void onFailure(Throwable caught) {
            token.failure(caught);
         }
      };

      i18nTools.setDecimalSeparator((String) form.getValue(fKey), cb);
   }

   @Override
   public Widget getComponent(UserDto user) {
      form = SimpleForm.getInlineInstance();

      form.setFieldWidth(150);
      fKey = form.addField(List.class, ReportexecutorMessages.INSTANCE.decimalSeparator(),
            new SFFCStaticDropdownList<String>() {

               @Override
               public Map<String, String> getValues() {
                  HashMap<String, String> map = new HashMap<String, String>();
                  map.put(",", ",");
                  map.put(".", ".");
                  return map;
               }
            });

      i18nTools.getDecimalSeparator(new AsyncCallback<String>() {

         @Override
         public void onSuccess(String result) {
            form.setValue(fKey, result);
         }

         @Override
         public void onFailure(Throwable caught) {
            // TODO Auto-generated method stub

         }
      });

      form.loadFields();

      return form;
   }

}
