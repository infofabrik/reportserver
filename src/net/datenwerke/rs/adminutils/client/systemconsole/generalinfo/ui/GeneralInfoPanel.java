package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.ui;

import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCSpace;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticLabel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.Separator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.GeneralInfoDao;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.GeneralInfoUiModule;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.core.client.helper.ObjectHolder;

/**
 * 
 *
 */
@Singleton
public class GeneralInfoPanel extends DwContentPanel {

   private final GeneralInfoDao generalInfoDao;

   private VerticalLayoutContainer wrapper;
   
   @Inject
   public GeneralInfoPanel(GeneralInfoDao licenseDao) {

      this.generalInfoDao = licenseDao;

      /* initialize ui */
      initializeUI();
   }

   private void initializeUI() {
      setHeading(SystemConsoleMessages.INSTANCE.generalInfo());
      addStyleName("rs-generalinfo");

      wrapper = new VerticalLayoutContainer();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      add(wrapper);

      updateView();
   }

   protected void updateView() {
      mask(BaseMessages.INSTANCE.loadingMsg());

      wrapper.clear();

      generalInfoDao.loadGeneralInfo(new RsAsyncCallback<String>() {
         @Override
         public void onSuccess(String result) {
            super.onSuccess(result);
            init(result);
            unmask();
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
            unmask();
         }
      });
   }

   protected void init(final String generalInfo) {
      final SimpleForm form = SimpleForm.getNewInstance();
      form.setHeading(SystemConsoleMessages.INSTANCE.generalInfo());
      form.setLabelAlign(LabelAlign.LEFT);

      form.setLabelWidth(200);
      
      JSONValue parsed = JSONParser.parseStrict(generalInfo);
      JSONObject categoriesObj = parsed.isObject();
      Set<String> categories = categoriesObj.keySet();
      categories.forEach(category -> addCategoryToForm(category, categoriesObj.get(category), form));

      form.loadFields();

      wrapper.add(form, new VerticalLayoutData(1, -1, new Margins(10)));

      form.clearButtonBar();

      Scheduler.get().scheduleDeferred(forceLayoutCommand);
   }
   
   private void addCategoryToForm(String categoryKey, JSONValue category,
         final SimpleForm form) {
      form.addField(StaticLabel.class, extractTitle(categoryKey), new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return "";
         }
      });
      
      JSONObject propertiesObj = category.isObject();
      Set<String> properties = propertiesObj.keySet();
      
      final ObjectHolder<Boolean> disabled = new ObjectHolder<>();
      final ObjectHolder<String> disabledMsg = new ObjectHolder<>();
      disabled.set(false);
      properties.forEach(property -> {
         if (extractTitle(property).equals(GeneralInfoUiModule.ENABLED)
               && extractValue(propertiesObj.get(property)).equals(false))
            disabled.set(true);
         if (extractTitle(property).equals(GeneralInfoUiModule.DISABLED_MESSAGE))
            disabledMsg.set(extractValue(propertiesObj.get(property)).toString());
      });
      
      if (!disabled.get()) {
         properties.forEach(property -> {
            if (!extractTitle(property).equals(GeneralInfoUiModule.ENABLED)
                  && !extractTitle(property).equals(GeneralInfoUiModule.DISABLED_MESSAGE))
               addFieldToForm(property, propertiesObj.get(property), form);
         });
      } else {
         form.addField(StaticLabel.class, "Message", new SFFCStaticLabel() {
            @Override
            public String getLabel() {
               return disabledMsg.get();
            }
         });
      }
      
      form.addField(Separator.class, new SFFCSpace());
      form.addField(Separator.class, new SFFCSpace());
   }

   
   private void addFieldToForm(String property, JSONValue value, SimpleForm form) {
      form.addField(StaticLabel.class, extractTitle(property), new SFFCStaticLabel() {
         @Override
         public String getLabel() {
            return extractValue(value).toString();
         }
      });
   }
   
   private String extractTitle(String pair) {
      // (RS_VERSION,Version) -> Version
      return pair.substring(pair.indexOf(",")+1, pair.length()-1);
   }
   
   private Object extractValue(JSONValue value) {
      if (null != value.isNull())
         return "";
      if (null != value.isString())
         return value.isString().stringValue();
      if (null != value.isNumber())
         return value.isNumber().doubleValue();
      if (null != value.isBoolean())
         return value.isBoolean().booleanValue();
      if (null != value.isArray())
         return value.isArray().toString().replace("\"", "");
      if (null != value.isObject())
         return value.isObject().toString().replace("\"", "");
      return "";
   }
}
