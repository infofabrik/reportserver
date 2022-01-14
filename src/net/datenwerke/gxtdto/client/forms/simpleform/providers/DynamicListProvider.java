package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.shared.event.GroupingHandlerRegistration;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent.LoadExceptionHandler;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.forms.selection.SimpleGridSelectionPopup;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCDynamicList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCDynamicListInPopup;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCEditableDropDown;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCSimpleDynamicList;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.stores.HasLoader;
import net.datenwerke.gxtdto.client.utils.handlers.GenericStoreHandler;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DynamicListProvider extends BaseListProvider {

   private ListStore store;
   private GroupingHandlerRegistration handlerReg;

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return List.class.equals(type) && null != configs
            && (configs[0] instanceof SFFCDynamicList || configs[0] instanceof SFFCSimpleDynamicList);
   }

   protected ListStore getStore() {
      if (null == store) {
         if (configs[0] instanceof SFFCDynamicList)
            store = ((SFFCDynamicList) configs[0]).getStore();
         else
            store = ((SFFCSimpleDynamicList) configs[0]).getStore();
      }
      return store;
   }

   protected ValueProvider getStoreKey() {
      if (configs[0] instanceof SFFCDynamicList)
         return ((SFFCDynamicList) configs[0]).getStoreKeyForDisplay();
      else
         return ((SFFCSimpleDynamicList) configs[0]).getStoreKeyForDisplay();
   }

   protected String getPropertyLabel() {
      if (configs[0] instanceof SFFCDynamicList)
         return ((SFFCDynamicList) configs[0]).getPropertyLabel();

      return FormsMessages.INSTANCE.selectedValues();
   }

   @Override
   public Widget createFormField() {
      if (isMultiSelect())
         return createFormField_multi();
      else
         return createFormField_single();
   }

   @SuppressWarnings("unchecked")
   public Widget createFormField_single() {
      final SimpleComboBox combo = new SimpleComboBox(new LabelProvider() {
         @Override
         public String getLabel(Object item) {
            return String.valueOf(getStoreKey().getValue(item));
         }
      });
      combo.setStore(getStore());

      getStore().clear();

      /* take care of errors when loading */
      /* unmask after load */
      if (getStore() instanceof HasLoader) {
         ((HasLoader) getStore()).getLoader().addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
               combo.unmask();
            }
         });
         ((HasLoader) getStore()).getLoader().addLoadExceptionHandler(new LoadExceptionHandler() {
            @Override
            public void onLoadException(LoadExceptionEvent event) {
               combo.mask(BaseMessages.INSTANCE.error());
            }
         });

         Handler handler = new Handler() {
            @Override
            public void onAttachOrDetach(AttachEvent event) {
               if (event.isAttached()) {
                  ((HasLoader) getStore()).getLoader().load();
               }
            }
         };
         combo.addAttachHandler(handler);

      }

      /* add change listener */
      combo.addSelectionHandler(new SelectionHandler() {
         @Override
         public void onSelection(SelectionEvent event) {
            ValueChangeEvent.fire(DynamicListProvider.this, event.getSelectedItem());
         }
      });

      combo.setTriggerAction(TriggerAction.ALL);
      combo.setForceSelection(true);

      if (!isEditable())
         combo.setEditable(false);
      else {
         Menu menu = new Menu();
         MenuItem removeItem = new DwMenuItem(BaseMessages.INSTANCE.remove(), BaseIcon.REMOVE);
         removeItem.addSelectionHandler(new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> event) {
               combo.setValue(null, true);
            }
         });
         menu.add(removeItem);
         combo.setContextMenu(menu);
      }

      return combo;
   }

   protected boolean isEditable() {
      for (SimpleFormFieldConfiguration c : configs)
         if (c instanceof SFFCEditableDropDown)
            return true;
      return false;
   }

   public Widget createFormField_multi() {
      if (configs[0] instanceof SFFCDynamicListInPopup)
         return createFormField_multi_popup();
      else
         return createFormField_multi_normal();
   }

   @SuppressWarnings("unchecked")
   private Widget createFormField_multi_popup() {
      /* create grid */
      SimpleGridSelectionPopup simpleGridSelectionPopup = new SimpleGridSelectionPopup(getPropertyLabel(),
            getStoreKey(), new ListStore(getStore().getKeyProvider()), getStore()) {
         @Override
         protected void itemsSelected(List selectedItems) {
            ValueChangeEvent.fire(DynamicListProvider.this, selectedItems);
         }
      };
      simpleGridSelectionPopup.setHeader(getPropertyLabel());

      return simpleGridSelectionPopup;
   }

   private Widget createFormField_multi_normal() {
      /* create selection model */
      CheckBoxSelectionModel selectionModel = new CheckBoxSelectionModel(new IdentityValueProvider());

      /* create columns */
      List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

      /* add checkbox column */
      configs.add(selectionModel.getColumn());

      /* add main column */
      ColumnConfig mainColumn = new ColumnConfig(getStoreKey(), 200);
      mainColumn.setHeader(getPropertyLabel());
      configs.add(mainColumn);

      /* create grid */
      final Grid multiValueGrid = new Grid(getStore(), new ColumnModel(configs));
      multiValueGrid.setHeight(120);
      multiValueGrid.setWidth(300);
      multiValueGrid.setSelectionModel(selectionModel);
      multiValueGrid.getView().setStripeRows(true);
      multiValueGrid.getView().setColumnLines(true);

      /* unmask after load */
      if (getStore() instanceof HasLoader) {
         ((HasLoader) getStore()).getLoader().addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
               multiValueGrid.unmask();
            }
         });
         ((HasLoader) getStore()).getLoader().addLoadExceptionHandler(new LoadExceptionHandler() {
            @Override
            public void onLoadException(LoadExceptionEvent event) {
               multiValueGrid.mask(BaseMessages.INSTANCE.error());
            }
         });

         ((HasLoader) getStore()).getLoader().load();
      }

      /* add selection changed listener */
      multiValueGrid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler() {
         @Override
         public void onSelectionChanged(SelectionChangedEvent event) {
            ValueChangeEvent.fire(DynamicListProvider.this, new ArrayList(event.getSelection()));
         }
      });

      return multiValueGrid;
   }

   @Override
   public Object getValue(Widget field) {
      if (isMultiSelect() && configs[0] instanceof SFFCDynamicListInPopup)
         return ((Grid) field).getStore().getAll();
      else if (isMultiSelect())
         return ((Grid) field).getSelectionModel().getSelectedItems();

      return ((Field) field).getValue();
   }

   @Override
   @SuppressWarnings("unchecked")
   public void addFieldBindings(final Object model, final ValueProvider vp, Widget field) {
      if (null != handlerReg)
         handlerReg.removeHandler();

      if (field instanceof SimpleComboBox) {
         SimpleComboBox cb = (SimpleComboBox) field;
         fieldBinding = new HasValueFieldBinding(cb, model, vp);
      } else if (field instanceof Grid) {
         final Grid multiValueGrid = (Grid) field;

         handlerReg = new GroupingHandlerRegistration();

         /* set values */
         Object listData = vp.getValue(model);
         if (listData instanceof List) {
            if (multiValueGrid instanceof SimpleGridSelectionPopup) {
               multiValueGrid.getStore().clear();
               multiValueGrid.getStore().addAll((Collection) listData);

               HandlerRegistration reg = multiValueGrid.getStore().addStoreHandlers(new GenericStoreHandler() {
                  @Override
                  protected void handleDataChangeEvent() {
                     vp.setValue(model, new ArrayList(multiValueGrid.getStore().getAll()));
                  }
               });

               handlerReg.add(reg);
            } else {
               HandlerRegistration reg = multiValueGrid.getStore().addStoreHandlers(new GenericStoreHandler() {
                  @Override
                  protected void handleDataChangeEvent() {
                     multiValueGrid.getSelectionModel().setSelection((List) vp.getValue(model));
                  }
               });

               handlerReg.add(reg);

               /* change model on selection change */
               reg = multiValueGrid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler() {
                  @Override
                  public void onSelectionChanged(SelectionChangedEvent event) {
                     vp.setValue(model, event.getSelection());
                  }
               });

               handlerReg.add(reg);
            }
         }

      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public Widget reload(Widget field) {
      if (null != handlerReg)
         handlerReg.removeHandler();

      /* clear old value */
      if (field instanceof SimpleComboBox) {
         SimpleComboBox cb = (SimpleComboBox) field;
         cb.setValue(null, true);
         if (null != fieldBinding)
            fieldBinding.updateModel();
      } else if (field instanceof Grid) {
         Grid multiValueGrid = (Grid) field;

         if (multiValueGrid instanceof SimpleGridSelectionPopup) {
            multiValueGrid.getStore().clear();
            ((SimpleGridSelectionPopup) multiValueGrid).reloadData();
         } else
            multiValueGrid.getSelectionModel().deselectAll();
      } else
         throw new IllegalArgumentException("Strange configuration"); //$NON-NLS-1$

      /* clear store */
      getStore().clear();

      /* create new field */
      return createFormField();
   }

   @Override
   public void setValue(Widget field, Object value) {
      if (field instanceof Grid && value instanceof Collection) {
         ((Grid) field).getStore().clear();
         ((Grid) field).getStore().addAll(((Collection) value));
      } else if (field instanceof SimpleComboBox) {
         ((SimpleComboBox) field).setValue(value);
      }
   }
}
