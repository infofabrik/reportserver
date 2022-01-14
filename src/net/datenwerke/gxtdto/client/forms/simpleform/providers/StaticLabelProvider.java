package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import java.util.Arrays;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormFieldJson;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticHtml;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticLabel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticLabelWithCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticTextField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticTextFieldWithCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;

public class StaticLabelProvider implements FormFieldProviderHook {

   private SimpleFormFieldConfiguration[] configs;

   @Override
   public boolean consumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      boolean consumes = type.equals(StaticLabel.class) && configs.length > 0 && (null != getSFFCStaticLabel(configs));
      consumes |= type.equals(StaticLabel.class) && configs.length > 0 && (null != getSFFCStaticHtml(configs));
      if (consumes)
         this.configs = configs;
      return consumes;
   }

   @Override
   public boolean consumes(String type, SimpleFormFieldJson config) {
      return false;
   }

   @Override
   public void init(String name, SimpleForm form) {

   }

   @Override
   public Widget createFormField() {
      Widget field = null;
      if (configs[0] instanceof SFFCStaticLabel) {
         final SFFCStaticLabel config = (SFFCStaticLabel) configs[0];

         field = SeparatorTextLabel.createSmallText(getValue());

         if (config instanceof SFFCStaticLabelWithCallback) {
            field.addDomHandler(new ClickHandler() {
               @Override
               public void onClick(ClickEvent event) {
                  ((SFFCStaticLabelWithCallback) config).onClick();
               }
            }, ClickEvent.getType());
         } else if (config instanceof SFFCStaticTextFieldWithCallback) {
            field.addDomHandler(new ClickHandler() {
               @Override
               public void onClick(ClickEvent event) {
                  ((SFFCStaticTextFieldWithCallback) config).onClick();
               }
            }, ClickEvent.getType());
            field.addStyleName("rs-clickable");
         }
      } else if (configs[0] instanceof SFFCStaticHtml) {
         final SFFCStaticHtml config = (SFFCStaticHtml) configs[0];

         field = new HTML(config.getLabel());
         field.addStyleName("rs-l-text-small");
      }

      return field;
   }

   public String getLabel() {
      SFFCStaticLabel config = getSFFCStaticLabel(configs);
      return config.getLabel();
   }

   public String getValue() {
      SFFCStaticTextField config = getSFFCStaticTextField(configs);
      if (null == config)
         return getLabel();

      return config.getValue();
   }

   @Override
   public void setValue(Widget field, Object value) {
   }

   @Override
   public Object getValue(Widget field) {
      return null;
   }

   @Override
   public String getStringValue(Widget field) {
      return null;
   }

   @Override
   public Widget reload(Widget field) {
      return createFormField();
   }

   @Override
   public HandlerRegistration addValueChangeHandler(ValueChangeHandler handler) {
      throw new IllegalStateException();
   }

   @Override
   public void fireEvent(GwtEvent<?> event) {

   }

   @Override
   public void addFieldBindings(Object model, ValueProvider vp, Widget field) {

   }

   @Override
   public void removeFieldBindings(Object model, Widget field) {

   }

   @Override
   public boolean isDecorateable() {
      return true;
   }

   private SFFCStaticLabel getSFFCStaticLabel(SimpleFormFieldConfiguration[] configArray) {
      return Arrays.stream(configArray).filter(config -> config instanceof SFFCStaticLabel)
            .map(config -> (SFFCStaticLabel) config).findAny().orElse(null);
   }

   private SFFCStaticHtml getSFFCStaticHtml(SimpleFormFieldConfiguration[] configArray) {
      return Arrays.stream(configArray).filter(config -> config instanceof SFFCStaticHtml)
            .map(config -> (SFFCStaticHtml) config).findAny().orElse(null);
   }

   private SFFCStaticTextField getSFFCStaticTextField(SimpleFormFieldConfiguration[] configArray) {
      return Arrays.stream(configArray).filter(config -> config instanceof SFFCStaticTextField)
            .map(config -> (SFFCStaticTextField) config).findAny().orElse(null);
   }

   @Override
   public void installBlankValidation(Widget field) {
      throw new UnsupportedOperationException();
   }

}
