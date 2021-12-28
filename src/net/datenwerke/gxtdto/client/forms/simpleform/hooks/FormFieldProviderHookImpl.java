package net.datenwerke.gxtdto.client.forms.simpleform.hooks;

import java.util.Arrays;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

import net.datenwerke.gxtdto.client.eventbus.EventBusHelper;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormFieldJson;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;

/**
 * Base class for providers that simply create a Field.
 * 
 *
 */
public abstract class FormFieldProviderHookImpl implements FormFieldProviderHook {

   protected String name;
   protected Class<?> type;
   protected SimpleFormFieldConfiguration[] configs;
   protected SimpleForm form;

   protected boolean json;
   protected String jsonType;
   protected SimpleFormFieldJson jsonConfig;

   public String getName() {
      return name;
   }

   public Class<?> getType() {
      return type;
   }

   public SimpleForm getForm() {
      return form;
   }

   public SimpleFormFieldConfiguration[] getConfigs() {
      return configs;
   }

   public boolean consumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      boolean consumes = doConsumes(type, configs);
      if (consumes) {
         this.type = type;
         this.configs = configs;
      }
      return consumes;
   }

   @Override
   public final boolean consumes(String type, SimpleFormFieldJson config) {
      boolean consumes = doConsumes(type, config);
      if (consumes) {
         this.json = true;
         this.jsonType = type;
         this.jsonConfig = config;
      }
      return consumes;
   }

   public boolean doConsumes(String type, SimpleFormFieldJson config) {
      return false;
   }

   public abstract boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs);

   public void init(String name, SimpleForm form) {
      this.name = name;
      this.form = form;
   }

   protected HasValueFieldBinding fieldBinding;

   public abstract Widget createFormField();

   @Override
   public Object getValue(Widget field) {
      if (field instanceof HasValue)
         return ((HasValue) field).getValue();
      return null;
   }

   @Override
   public String getStringValue(Widget field) {
      if (field instanceof HasValue)
         return String.valueOf(((HasValue) field).getValue());
      return null;
   }

   @Override
   public void setValue(Widget field, Object object) {
      if (json) {
         setStringValue(field, (String) object);
         return;
      }
      if (field instanceof HasValue)
         ((HasValue) field).setValue(object, true);
   }

   protected void setStringValue(Widget field, String object) {

   }

   public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
      if (field instanceof HasValueChangeHandlers)
         fieldBinding = new HasValueFieldBinding((HasValueChangeHandlers) field, model, vp);
      else
         fieldBinding = new HasValueFieldBinding(this, model, vp);
   }

   public void removeFieldBindings(Object model, Widget field) {
      if (null != fieldBinding) {
         fieldBinding.unbind();
         fieldBinding = null;
      }
   }

   @Override
   public Widget reload(Widget field) {
      return createFormField();
   }

   public HandlerRegistration addValueChangeHandler(ValueChangeHandler handler) {
      return EventBusHelper.EVENT_BUS.addHandlerToSource(ValueChangeEvent.getType(), this, handler);
   }

   public void fireEvent(GwtEvent<?> event) {
      EventBusHelper.EVENT_BUS.fireEventFromSource(event, this);
   }

   @Override
   public boolean isDecorateable() {
      return true;
   }

   private SFFCAllowBlank getAllowBlankConfig() {
      return Arrays.stream(configs).filter(config -> config instanceof SFFCAllowBlank)
            .map(config -> (SFFCAllowBlank) config).findAny().orElse(null);
   }

   @Override
   public void installBlankValidation(final Widget field) {
      if (field instanceof ValueBaseField) {
         /* blank */
         SFFCAllowBlank allowBlank = getAllowBlankConfig();
         if (null != allowBlank && !allowBlank.allowBlank()) {
            final ValueBaseField ff = ((ValueBaseField) field);
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
               @Override
               public void execute() {
                  ff.setAllowBlank(false);
               }
            });
         }
      }
   }

}
