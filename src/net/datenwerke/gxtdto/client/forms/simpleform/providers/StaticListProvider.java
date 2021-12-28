package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.XTemplates.FormatterFactoryMethod;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormFieldJson;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.StaticListProvider.StaticListResources.ListCssResource;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticRadioList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCFancyStaticList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.gxtdto.client.xtemplates.NullSafeFormatter;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.CssIconImageResource;

/**
 * 
 *
 */
public class StaticListProvider extends BaseListProvider {

   public static class SimpleFormDropDownJsonConfig extends JavaScriptObject {
      protected SimpleFormDropDownJsonConfig() {
      }

      public final native SimpleFormDropDownJsonConfigValues getValues() /*-{ return this.values; }-*/;
   }

   public static class SimpleFormDropDownJsonConfigValues extends JavaScriptObject {
      protected SimpleFormDropDownJsonConfigValues() {
      }

      public final native int length() /*-{ return this.length; }-*/;

      public final native JavaScriptObject get(int i) /*-{ return this[i];  }-*/;

      public final native String getString(int i) /*-{ return this[i];  }-*/;
   }

   public static class KeyValueContainer {
      private final String key;
      private final String value;

      public KeyValueContainer(String key, String value) {
         super();
         this.key = key;
         this.value = value;
      }

      public KeyValueContainer(JavaScriptObject jsO) {
         JSONObject json = new JSONObject(jsO);
         key = json.keySet().iterator().next();
         value = ((JSONString) json.get(key)).stringValue();
      }

      public String getKey() {
         return key;
      }

      public String getValue() {
         return value;
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ((key == null) ? 0 : key.hashCode());
         return result;
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj)
            return true;
         if (obj == null)
            return false;
         if (getClass() != obj.getClass())
            return false;
         KeyValueContainer other = (KeyValueContainer) obj;
         if (key == null) {
            if (other.key != null)
               return false;
         } else if (!key.equals(other.key))
            return false;
         return true;
      }

      @Override
      public String toString() {
         return key;
      }
   }

   public interface StaticListResources extends ClientBundle {
      public interface ListCssResource extends CssResource {
         String staticlistItem();

         String staticlistTitle();

         String staticlistImage();

         String staticlistDescription();
      }

      @Source("staticlist.gss")
      public ListCssResource listStyles();
   }

   @FormatterFactories(@FormatterFactory(factory = NullSafeFormatter.class, methods = @FormatterFactoryMethod(name = "nullsafe")))
   interface ListTemplates extends XTemplates {
      @XTemplate("<div class=\"{style.staticlistItem}\" style=\"{heightStyle}\">"
            + "<div class=\"{style.staticlistImage}\">{entry.icon}</div>"
            + "<div class=\"{style.staticlistTitle}\">{entry.label:nullsafe}</div>"
            + "<div class=\"{style.staticlistDescription}\">{entry.description:nullsafe}</div>" + "</div>")
      public SafeHtml render(FancyModel entry, SafeStyles heightStyle, ListCssResource style);
   }

   class FancyModel {
      private Object value;
      private String label;
      private ImageResource icon;
      private String description;
      private String height;

      public String getLabel() {
         return label;
      }

      public void setLabel(String label) {
         this.label = label;
      }

      public SafeHtml getIcon() {
         if (icon instanceof CssIconImageResource)
            return ((CssIconImageResource) icon).getCssIcon(2);
         return AbstractImagePrototype.create(icon).getSafeHtml();
      }

      public void setIcon(AbstractImagePrototype icon) {

      }

      public void setIcon(ImageResource icon) {
         this.icon = icon;
      }

      public String getDescription() {
         return description;
      }

      public void setDescription(String description) {
         this.description = description;
      }

      public String getHeight() {
         return height;
      }

      public void setHeight(String height) {
         this.height = height;
      }

      public void setValue(Object value) {
         this.value = value;
      }

      public Object getValue() {
         return value;
      }

      @Override
      public String toString() {
         return getLabel();
      }
   }

   private static final String FIELD_DATA_KEY = "FIELD_DATA_KEY";

   private Map<Object, FancyModel> modelMap = new HashMap<Object, StaticListProvider.FancyModel>();
   private ToggleGroup toggleGroup;

   private StaticListResources staticListResources;

   public StaticListProvider() {
      staticListResources = GWT.create(StaticListResources.class);
      staticListResources.listStyles().ensureInjected();
   }

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return List.class.equals(type) && null != configs && (configs[0] instanceof SFFCStaticList);
   }

   @Override
   public boolean doConsumes(String type, SimpleFormFieldJson config) {
      return "dd".equals(type);
   }

   protected Map getValues() {
      return ((SFFCStaticList) configs[0]).getValues();
   }

   @Override
   public Widget createFormField() {
      if (json)
         return createFormFieldJson();

      switch (((SFFCStaticList) configs[0]).getType()) {
      case Dropdown:
         return createDropDown();
      default:
         return createRadio();
      }

   }

   protected Widget createFormFieldJson() {
      if ("dd".equals(jsonType))
         return createDropDownJson();
      return null;
   }

   protected Widget createDropDownJson() {
      SimpleFormDropDownJsonConfig config = jsonConfig.cast();

      SimpleFormDropDownJsonConfigValues values = config.getValues();
      if (values.length() == 0)
         return new SimpleComboBox<String>(new StringLabelProvider<String>());

      boolean isString = false;
      try {
         values.getString(0);
         isString = true;
      } catch (ClassCastException e) {
      }

      SimpleComboBox<?> comboBox = null;

      if (isString) {
         comboBox = new SimpleComboBox<String>(new StringLabelProvider<String>());
      } else {
         comboBox = new SimpleComboBox<KeyValueContainer>(new StringLabelProvider<KeyValueContainer>() {
            @Override
            public String getLabel(KeyValueContainer item) {
               return item.getValue();
            }
         });
      }

      comboBox.setTriggerAction(TriggerAction.ALL);
      comboBox.setForceSelection(true);
      comboBox.setEditable(false);
//		comboBox.setAllowBlank(((SFFCStaticList)configs[0]).allowBlank());
//		comboBox.setAllowTextSelection(((SFFCStaticList)configs[0]).allowTextSelection());

      for (int i = 0; i < values.length(); i++) {
         if (isString) {
            ((SimpleComboBox<String>) comboBox).add(values.getString(i));
         } else {
            ((SimpleComboBox<KeyValueContainer>) comboBox).add(new KeyValueContainer(values.get(i)));
         }
      }

      if (isString) {
         ((SimpleComboBox<String>) comboBox).addSelectionHandler(new SelectionHandler<String>() {
            @Override
            public void onSelection(SelectionEvent<String> event) {
               ValueChangeEvent.fire(StaticListProvider.this, event.getSelectedItem());
            }
         });
      } else {
         ((SimpleComboBox<KeyValueContainer>) comboBox)
               .addSelectionHandler(new SelectionHandler<StaticListProvider.KeyValueContainer>() {
                  @Override
                  public void onSelection(SelectionEvent<KeyValueContainer> event) {
                     ValueChangeEvent.fire(StaticListProvider.this, event.getSelectedItem().getKey());
                  }
               });
      }

      return comboBox;
   }

   protected Widget createRadio() {
      Container container = null;
      if (configs[0] instanceof SFFCStaticRadioList)
         container = ((SFFCStaticRadioList) configs[0]).getRadioContainer();
      else
         container = new VerticalLayoutContainer();

      toggleGroup = new ToggleGroup();
      for (Entry<String, ?> entry : ((Map<String, ?>) ((SFFCStaticList) configs[0]).getValues()).entrySet()) {
         CheckBox field = new Radio();
         field.setBoxLabel(entry.getKey());
         container.add(field);
         field.setData(FIELD_DATA_KEY, entry.getValue());

         toggleGroup.add(field);
      }

      toggleGroup.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
         @Override
         public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
            ValueChangeEvent.fire(StaticListProvider.this, event.getValue());
         }
      });

      return container;
   }

   protected Widget createDropDown() {
      ListStore<FancyModel> store = new ListStore<StaticListProvider.FancyModel>(
            new BasicObjectModelKeyProvider<FancyModel>());

      for (String label : (Set<String>) getValues().keySet()) {
         FancyModel model = new FancyModel();
         model.setLabel(label);
         if (configs[0] instanceof SFFCFancyStaticList) {
            ImageResource icon = (ImageResource) ((SFFCFancyStaticList) configs[0]).getIconMap().get(label);
            model.setIcon(null == icon ? BaseResources.INSTANCE.emptyImage() : icon);

            String desc = (String) ((SFFCFancyStaticList) configs[0]).getDescriptionMap().get(label);
            model.setDescription(null == desc ? "" : desc);

            model.setHeight(String.valueOf(((SFFCFancyStaticList) configs[0]).getHeight()));
         }
         model.setValue(getValues().get(label));

         modelMap.put(model.getValue(), model);

         store.add(model);
      }

      ComboBox<FancyModel> combo = null;
      if (configs[0] instanceof SFFCFancyStaticList) {
         final ListTemplates template = GWT.create(ListTemplates.class);

         // TODO: Theme
         /*
          * ListView<FancyModel, FancyModel> view = new ListView<FancyModel, FancyModel>(
          * store, new IdentityValueProvider<FancyModel>(),
          * GWT.<ListViewAppearance<FancyModel>>create(RepserListViewFancyAppearance.
          * class));
          */
         ListView<FancyModel, FancyModel> view = new ListView<FancyModel, FancyModel>(store,
               new IdentityValueProvider<FancyModel>());

         view.setCell(new AbstractCell<FancyModel>() {
            @Override
            public void render(com.google.gwt.cell.client.Cell.Context context, FancyModel value, SafeHtmlBuilder sb) {
               sb.append(template.render(value,
                     SafeStylesUtils
                           .fromTrustedString("height: " + SafeHtmlUtils.htmlEscape(value.getHeight()) + "px;"),
                     staticListResources.listStyles()));
            }
         });

         combo = new ComboBox<FancyModel>(
               new ComboBoxCell<FancyModel>(store, new StringLabelProvider<FancyModel>(), view));
         combo.setMinListWidth(((SFFCFancyStaticList) configs[0]).getWidth());
      } else
         combo = new ComboBox<FancyModel>(store, new StringLabelProvider<FancyModel>());
      combo.setTriggerAction(TriggerAction.ALL);
      combo.setForceSelection(true);
      combo.setEditable(false);
      combo.setAllowBlank(((SFFCStaticList) configs[0]).allowBlank());
      combo.setAllowTextSelection(((SFFCStaticList) configs[0]).allowTextSelection());

      if (configs[0] instanceof SFFCStaticDropdownList)
         combo.setWidth(((SFFCStaticDropdownList) configs[0]).getWidth());

      if (((SFFCStaticList) configs[0]).allowBlank()) {
         Menu menu = new DwMenu();
         MenuItem remove = new DwMenuItem(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
         remove.setData("combo", combo);
         menu.add(remove);
         combo.setContextMenu(menu);

         remove.addSelectionHandler(new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> event) {
               ComboBox combo = ((ComboBox) event.getSelectedItem().getData("combo"));

               /* bug: http://www.sencha.com/forum/showthread.php?244061 */
               combo.clear();
               combo.setText("");
               combo.finishEditing();
               ValueChangeEvent.fire(StaticListProvider.this, null);
            }
         });
      }

      /* add listener for change events */
      combo.addSelectionHandler(new SelectionHandler<StaticListProvider.FancyModel>() {
         @Override
         public void onSelection(SelectionEvent<FancyModel> event) {
            ValueChangeEvent.fire(StaticListProvider.this, event.getSelectedItem().getValue());
         }
      });

      return combo;
   }

   @SuppressWarnings("unchecked")
   @Override
   public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
      switch (((SFFCStaticList) configs[0]).getType()) {
      case Dropdown:
         addFieldBindingsDropdown(model, vp, field);
         break;
      default:
         addFieldBindingsRadio(model, vp, field);
         break;
      }
   }

   private void addFieldBindingsRadio(Object model, ValueProvider vp, Widget field) {
      if (!((SFFCStaticList) configs[0]).isMultiselect()) {
         fieldBinding = new HasValueFieldBinding(toggleGroup, model, vp) {
            protected Object convertFieldValue(Object value) {
               return ((Field<Boolean>) value).getData(FIELD_DATA_KEY);
            };

            protected Object convertModelValue(Object object) {
               if (null == object)
                  return null;

               for (HasValue<Boolean> field : toggleGroup)
                  if (object.equals(((Field) field).getData(FIELD_DATA_KEY)))
                     return field;

               return null;
            };
         };
      }
   }

   private void addFieldBindingsDropdown(Object model, ValueProvider vp, Widget field) {
      ComboBox cb = (ComboBox) field;

      fieldBinding = new HasValueFieldBinding(cb, model, vp) {
         protected Object convertFieldValue(Object value) {
            if (null == value)
               return null;

            Map values = getValues();
            FancyModel realvalue = (FancyModel) value;
            for (String key : (Set<String>) values.keySet())
               if (key.equals(realvalue.getLabel()))
                  return realvalue.getValue();

            return modelMap.get(values.get(values.keySet().iterator().next())).getValue();
         }

         protected Object convertModelValue(Object value) {
            if (null == value)
               return null;

            Map values = getValues();
            for (Object target : getValues().values())
               if (value.equals(target))
                  return modelMap.get(target);

            /* return first key */
            return modelMap.get(values.values().iterator().next());
         }
      };
   }

   @SuppressWarnings("unchecked")
   @Override
   public Object getValue(Widget field) {
      switch (((SFFCStaticList) configs[0]).getType()) {
      case Dropdown:
         return getValueDropdown(field);
      default:
         return getValueRadio(field);
      }
   }

   protected Object getValueRadio(Widget field) {
      for (Widget cb : (HasWidgets) field)
         if (Boolean.TRUE.equals(((CheckBox) cb).getValue()))
            return (((CheckBox) cb).getData(FIELD_DATA_KEY));
      return null;
   }

   protected Object getValueDropdown(Widget field) {
      FancyModel selectedValue = ((ValueBaseField<FancyModel>) field).getCurrentValue();
      if (null == selectedValue)
         return null;

      /* transform back */
      Map values = getValues();
      for (String key : (Set<String>) values.keySet())
         if (key.equals(selectedValue.getLabel()))
            return values.get(key);

      return values.get(values.keySet().iterator().next());
   }

   @Override
   public void setValue(Widget field, Object object) {
      if (json) {
         setStringValue(field, (String) object);
         return;
      }

      switch (((SFFCStaticList) configs[0]).getType()) {
      case Dropdown:
         if (null != object) {
            Map values = getValues();
            for (Object target : getValues().values())
               if (object.equals(target))
                  super.setValue(field, modelMap.get(target));
         } else
            super.setValue(field, object);
         break;
      default:
         if (null == object)
            for (Widget cb : (HasWidgets) field)
               ((CheckBox) cb).setValue(false);
         else {
            for (Widget cb : (HasWidgets) field)
               if (object.equals(((CheckBox) cb).getData(FIELD_DATA_KEY)))
                  ((CheckBox) cb).setValue(true);
         }
         break;
      }
   }

   @Override
   protected void setStringValue(Widget field, String value) {
      List<?> all = ((SimpleComboBox<?>) field).getStore().getAll();
      if (null == all || all.isEmpty())
         return;

      if (all.get(0) instanceof String) {
         ((HasValue<String>) field).setValue(value);
      } else {
         for (KeyValueContainer kv : ((List<KeyValueContainer>) all)) {
            if (value.equals(kv.getKey())) {
               ((HasValue) field).setValue(kv);
               return;
            }

         }
      }

   }
}
