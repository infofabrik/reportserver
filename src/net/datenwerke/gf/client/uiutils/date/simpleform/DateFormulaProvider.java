package net.datenwerke.gf.client.uiutils.date.simpleform;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.uiutils.date.DateFormulaContainer;
import net.datenwerke.gf.client.uiutils.date.DateFormulaPicker;
import net.datenwerke.gf.client.uiutils.date.FormulaEvaluatedEvent;
import net.datenwerke.gf.client.uiutils.date.simpleform.SFFCDateFormula.Mode;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

/**
 * 
 *
 */
public class DateFormulaProvider extends FormFieldProviderHookImpl {

   private final EventBus eventBus;

   protected HasValueFieldBinding fieldBinding;
   protected HasValueFieldBinding fieldBindingTime;
   protected HasValueFieldBinding fieldBindingDate;

   protected Date lastEvalDate;

   @Inject
   public DateFormulaProvider(EventBus eventBus) {
      super();
      this.eventBus = eventBus;
   }

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return DateFormulaContainer.class.equals(type);
   }

   protected SFFCDateFormula getDateFormulaConfig() {
      for (SimpleFormFieldConfiguration c : getConfigs())
         if (c instanceof SFFCDateFormula)
            return (SFFCDateFormula) c;
      return new SFFCDateFormula() {
         @Override
         public Mode getMode() {
            return Mode.Date;
         }

         @Override
         public String getDatePattern() {
            return null;
         }

         @Override
         public String getTimePattern() {
            return null;
         }
      };
   }

   private SFFCAllowBlank getAllowBlank() {
      for (SimpleFormFieldConfiguration c : getConfigs())
         if (c instanceof SFFCAllowBlank)
            return (SFFCAllowBlank) c;
      return null;
   }

   protected void initContextMenu(final Field field) {
      Menu menu = new DwMenu();

      MenuItem delete = new DwMenuItem(BaseMessages.INSTANCE.remove());
      menu.add(delete);

      delete.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            field.setValue(null, true);
         }
      });
      field.setContextMenu(menu);
   }

   @Override
   public Widget createFormField() {
      SFFCDateFormula dateConfig = getDateFormulaConfig();

      if (dateConfig.getMode().equals(Mode.Date))
         return createDateFormField(dateConfig.getDatePattern());
      else if (dateConfig.getMode().equals(Mode.DateTime))
         return createDateTimeFormField(dateConfig.getDatePattern(), dateConfig.getTimePattern());
      else
         return createTimeFormField(dateConfig.getTimePattern());
   }

   private Widget createDateTimeFormField(String datePattern, String timePattern) {
      if (null == datePattern)
         datePattern = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).getPattern();

      if (null == timePattern)
         timePattern = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.TIME_SHORT).getPattern();

      DwFlowContainer flowWrap = new DwFlowContainer();

      flowWrap.add(createDateFormField(datePattern));
      flowWrap.add(createTimeFormField(timePattern));

      return flowWrap;
   }

   public Widget createDateFormField(String pattern) {
      if (null == pattern)
         pattern = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).getPattern();

      final DateFormulaPicker field = new DateFormulaPicker(DateTimeFormat.getFormat(pattern));
      field.setName(name);
      field.setValidateOnBlur(true);
      initContextMenu(field);

      field.addValueChangeHandler(new ValueChangeHandler<DateFormulaContainer>() {
         @Override
         public void onValueChange(ValueChangeEvent<DateFormulaContainer> event) {
            ValueChangeEvent.fire(DateFormulaProvider.this, field.getValue());
         }
      });

      field.addChangeHandler(new ChangeHandler() {
         @Override
         public void onChange(ChangeEvent event) {
            if (null == field.getText() || field.getText().isEmpty()) {
               field.setValue(null, true);
               ValueChangeEvent.fire(DateFormulaProvider.this, null);
            }
         }
      });

      SFFCAllowBlank allowBlank = getAllowBlank();
      if (null != allowBlank)
         field.setAllowBlank(allowBlank.allowBlank());

      return field;
   }

   private Field createTimeFormField(String pattern) {
      if (null == pattern)
         pattern = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.TIME_SHORT).getPattern();

      final TimeField field = new TimeField();
      field.setFormat(DateTimeFormat.getFormat(pattern));
      field.setTriggerAction(TriggerAction.ALL);
      field.setName(name);
      field.setValidateOnBlur(true);
      initContextMenu(field);

      field.addValueChangeHandler(new ValueChangeHandler<Date>() {
         @Override
         public void onValueChange(ValueChangeEvent<Date> event) {
            ValueChangeEvent.fire(DateFormulaProvider.this, field.getValue());
         }
      });
      field.addChangeHandler(new ChangeHandler() {
         @Override
         public void onChange(ChangeEvent event) {
            if (null == field.getText() || field.getText().isEmpty()) {
               field.setValue(null, true);
               ValueChangeEvent.fire(DateFormulaProvider.this, null);
            }
         }
      });

      SFFCAllowBlank allowBlank = getAllowBlank();
      if (null != allowBlank)
         field.setAllowBlank(allowBlank.allowBlank());

      return field;
   }

   @Override
   public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
      SFFCDateFormula dateConfig = getDateFormulaConfig();

      if (null != dateConfig && dateConfig.getMode().equals(Mode.DateTime)) {
         final DateFormulaPicker dateField = (DateFormulaPicker) ((Container) field).getWidget(0);
         final TimeField timeField = (TimeField) ((Container) field).getWidget(1);

         eventBus.addHandler(FormulaEvaluatedEvent.getType(), new FormulaEvaluatedEvent.Handler() {
            @Override
            public void handleFormulaEvaluatedEvent(FormulaEvaluatedEvent formulaEvaluatedEvent) {
               timeField.setValue(formulaEvaluatedEvent.getDate());
               lastEvalDate = formulaEvaluatedEvent.getDate();
            }
         });

         timeField.addValueChangeHandler(new ValueChangeHandler<Date>() {

            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
               DateFormulaContainer container = dateField.getValue();
               DateWrapper time = new DateWrapper(timeField.getValue());
               if (null != timeField.getValue() && null != lastEvalDate) {
                  container.setDate(lastEvalDate);
                  container.getDate().setMinutes(time.getMinutes());
                  container.getDate().setHours(time.getHours());
               }
               container.setFormula(null);
               dateField.setValue(container);
            }
         });

         fieldBindingDate = new HasValueFieldBinding(dateField, model, vp) {
            @Override
            protected Object convertFieldValue(Object value) {
               if (null == value || !(value instanceof DateFormulaContainer))
                  return null;

               DateFormulaContainer container = (DateFormulaContainer) value;

               if (null != timeField.getValue() && null != container.getDate()) {
                  DateWrapper time = new DateWrapper(timeField.getValue());
                  container.getDate().setMinutes(time.getMinutes());
                  container.getDate().setHours(time.getHours());
               }

               if (container.getDate() == null && container.getFormula() != null) {
                  timeField.clearInvalid();
               }
               return value;
            }

         };
         fieldBindingTime = new HasValueFieldBinding(timeField, model, vp) {
            @Override
            protected Object convertFieldValue(Object value) {
               DateFormulaContainer container = dateField.getValue();

               if (null != timeField.getValue() && null != container.getDate()) {
                  DateWrapper time = new DateWrapper(timeField.getValue());
                  container.getDate().setMinutes(time.getMinutes());
                  container.getDate().setHours(time.getHours());
               }

               if (container.getDate() == null && container.getFormula() != null) {
                  timeField.clearInvalid();
               }
               return container;
            }

            @Override
            protected Object convertModelValue(Object object) {
               return null == object ? null : ((DateFormulaContainer) object).getDate();
            }
         };
      } else if (null != dateConfig && dateConfig.getMode().equals(Mode.Time)) {
         final Field f = (Field) field;
         final TimeField timeField = (TimeField) field;

         eventBus.addHandler(FormulaEvaluatedEvent.getType(), new FormulaEvaluatedEvent.Handler() {
            @Override
            public void handleFormulaEvaluatedEvent(FormulaEvaluatedEvent formulaEvaluatedEvent) {
               timeField.setValue(formulaEvaluatedEvent.getDate());
               lastEvalDate = formulaEvaluatedEvent.getDate();
            }
         });

         fieldBindingTime = new HasValueFieldBinding(f, model, vp) {
            @Override
            protected Object convertFieldValue(Object value) {
               return new DateFormulaContainer((Date) value, null);
            }

            @Override
            protected Object convertModelValue(Object object) {
               return null == object ? null : ((DateFormulaContainer) object).getDate();
            }
         };
      } else {
         Field f = (Field) field;
         fieldBinding = new HasValueFieldBinding(f, model, vp);
      }
   }

   public Object getValue(Widget field) {
      SFFCDateFormula dateConfig = getDateFormulaConfig();

      if (null != dateConfig && dateConfig.getMode().equals(Mode.DateTime)) {
         Field dateField = (Field) ((DwFlowContainer) field).getWidget(0);
         Field timeField = (Field) ((DwFlowContainer) field).getWidget(1);

         DateFormulaContainer date = (DateFormulaContainer) dateField.getValue();
         Date time = (Date) timeField.getValue();
         if (null != date) {
            if (null != date.getDate() && null != time)
               date.getDate().setHours(time.getHours());
            date.getDate().setMinutes(time.getMinutes());
            return date;
         }
         return new DateFormulaContainer(time, null);
      } else if (null != dateConfig && dateConfig.getMode().equals(Mode.Time)) {
         return new DateFormulaContainer((Date) ((Field) field).getValue(), null);
      } else {
         return ((Field) field).getValue();
      }
   }

   public void removeFieldBindings(Object model, Widget field) {
      if (null != fieldBinding) {
         fieldBinding.unbind();
         fieldBinding = null;
      }
      if (null != fieldBindingDate) {
         fieldBindingDate.unbind();
         fieldBindingDate = null;
      }
      if (null != fieldBindingTime) {
         fieldBindingTime.unbind();
         fieldBindingTime = null;
      }
   }

   @Override
   public Widget reload(Widget field) {
      return createFormField();
   }
}
