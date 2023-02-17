package net.datenwerke.rs.base.client.parameters.datasource;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent.StoreDataChangeHandler;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.messages.client.XMessages;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BeforeQueryEvent;
import com.sencha.gxt.widget.core.client.event.BeforeQueryEvent.BeforeQueryHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import net.datenwerke.gf.client.uiutils.info.FieldErrorHandler;
import net.datenwerke.gf.client.uiutils.info.FieldInfoHandler;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwHorizontalFlowLayoutContainer;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.forms.selection.SimpleGridSelectionPopup;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec;
import net.datenwerke.rs.base.client.parameters.datasource.dto.pa.DatasourceParameterDataDtoPA;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.helper.DefaultValueSetter;
import net.datenwerke.rs.core.client.parameters.helper.ParameterFieldWrapperForFrontend;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * 
 *
 */
public class DatasourceEditComponentForInstance {

   private final XMessages xmessages = GWT.create(XMessages.class);

   private static final String DATA_KEY = "DATA";

   private final DatasourceParameterDao datasourceParamDao;

   private FieldInfoHandler fieldInfoHandler;

   private Widget editComponent;

   private FieldInfoHandler comboBoxInfo;

   @Inject
   public DatasourceEditComponentForInstance(DatasourceParameterDao queryRPCService) {

      /* store object */
      this.datasourceParamDao = queryRPCService;
   }

   public Widget getEditComponent(DatasourceParameterDefinitionDto definition, DatasourceParameterInstanceDto instance,
         Collection<ParameterInstanceDto> relevantInstances, int labelWidth, boolean initial, ReportDto report) {
      /* what mode */
      if (ModeDto.Single.equals(definition.getMode())
            && SingleSelectionModeDto.Dropdown.equals(definition.getSingleSelectionMode()))
         return getEditComponent_single(definition, instance, relevantInstances, initial, labelWidth, report);
      else if (ModeDto.Single.equals(definition.getMode())
            && SingleSelectionModeDto.Popup.equals(definition.getSingleSelectionMode()))
         return getEditComponent_popup(definition, instance, relevantInstances, labelWidth, report);
      else if (ModeDto.Single.equals(definition.getMode())
            && SingleSelectionModeDto.Radio.equals(definition.getSingleSelectionMode()))
         return getEditComponent_cb(definition, instance, relevantInstances, initial, false, labelWidth, report);
      else if (ModeDto.Single.equals(definition.getMode())
            && SingleSelectionModeDto.Listbox.equals(definition.getSingleSelectionMode()))
         return getEditComponent_lb(definition, instance, relevantInstances, initial, false, labelWidth, report);
      else if (ModeDto.Multi.equals(definition.getMode())
            && MultiSelectionModeDto.Popup.equals(definition.getMultiSelectionMode()))
         return getEditComponent_popup(definition, instance, relevantInstances, labelWidth, report);
      else if (ModeDto.Multi.equals(definition.getMode())
            && MultiSelectionModeDto.Checkbox.equals(definition.getMultiSelectionMode()))
         return getEditComponent_cb(definition, instance, relevantInstances, initial, true, labelWidth, report);
      else if (ModeDto.Multi.equals(definition.getMode())
            && MultiSelectionModeDto.Listbox.equals(definition.getMultiSelectionMode()))
         return getEditComponent_lb(definition, instance, relevantInstances, initial, true, labelWidth, report);

      return null;
   }

   protected Widget getEditComponent_cb(final DatasourceParameterDefinitionDto definition,
         final DatasourceParameterInstanceDto instance, Collection<ParameterInstanceDto> relevantInstances,
         boolean initial, final boolean multi, int labelWidth, ReportDto report) {
      final DwHorizontalFlowLayoutContainer container = new DwHorizontalFlowLayoutContainer();
//		container.setNobreak(false);

      ListLoader<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>> loader = getLoader(definition,
            relevantInstances, report);
      loader.addLoadHandler(new LoadHandler<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>>() {
         @Override
         public void onLoad(LoadEvent<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>> event) {
            fillContainer(container, definition, instance, event.getLoadResult().getData(), multi);
         }
      });

      loader.load();

      editComponent = container;
      return new ParameterFieldWrapperForFrontend(definition, instance, container, labelWidth,
            new DefaultValueSetter() {
               @Override
               public void setDefaultValue() {
                  setDefaultValueToggleGroup(container, instance, definition, multi);
               }
            });
   }

   protected Widget getEditComponent_lb(final DatasourceParameterDefinitionDto definition,
         final DatasourceParameterInstanceDto instance, Collection<ParameterInstanceDto> relevantInstances,
         boolean initial, final boolean multi, int labelWidth, ReportDto report) {
      final ListStore<DatasourceParameterDataDto> store = new ListStore<DatasourceParameterDataDto>(
            new BasicObjectModelKeyProvider());
      store.setAutoCommit(true);
      final ListLoader<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>> loader = getLoader(definition,
            relevantInstances, report);
      loader.addLoadHandler(
            new LoadResultListStoreBinding<ListLoadConfig, DatasourceParameterDataDto, ListLoadResult<DatasourceParameterDataDto>>(
                  store));
      loader.load();

      final CheckBoxSelectionModel<DatasourceParameterDataDto> selection = new CheckBoxSelectionModel<DatasourceParameterDataDto>();
      selection.setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.MULTI);

      ColumnConfig<DatasourceParameterDataDto, String> column = new ColumnConfig<DatasourceParameterDataDto, String>(
            DatasourceParameterDataDtoPA.INSTANCE.key(), 200, "");

      List<ColumnConfig<DatasourceParameterDataDto, ?>> columns = new ArrayList<>();
      columns.add(selection.getColumn());
      columns.add(column);

      final Grid<DatasourceParameterDataDto> grid = new Grid<DatasourceParameterDataDto>(store,
            new ColumnModel<DatasourceParameterDataDto>(columns));
      grid.setBorders(true);
      grid.setSelectionModel(selection);
      grid.getView().setForceFit(true);
      grid.getView().setAutoExpandColumn(column);

      selection.addSelectionChangedHandler(event -> {
         final List<DatasourceParameterDataDto> selectedItems = selection.getSelectedItems();
         if (ModeDto.Single.equals(definition.getMode())) {
            if (null != selectedItems && selectedItems.size() > 0)
               instance.setSingleValue(DatasourceParameterDataDtoDec.cloneDataObject(selectedItems.get(0)));
            else
               instance.setSingleValue(null);
         } else {
            instance.setMultiValue(selectedItems
               .stream()
               .map(DatasourceParameterDataDtoDec::cloneDataObject)
               .collect(toList()));
         }

         validateParameter(definition, instance);
      });

      /* selection type */
      if (ModeDto.Single.equals(definition.getMode()))
         selection.setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.SINGLE);

      /* size */
      grid.setWidth(definition.getWidth());
      if (1 != definition.getHeight())
         grid.setHeight(definition.getHeight());

      /* disable edit form, if parameter is not editable */
      if (!definition.isEditable())
         grid.disable();

      /* set initial selection after grid was rendered */
      Scheduler.get().scheduleDeferred(() -> {
         /* schedule again as grid is not yet ready */
         Scheduler.get().scheduleDeferred(() -> {
            if (instance.isStillDefault()) {
               setDefaultValueSelectionModel(selection, instance, definition);
            } else {
               if (ModeDto.Single.equals(definition.getMode())) {
                  selection.setSelection(Collections.singletonList(instance.getSingleValue()));
               } else {
                  selection.setSelection(instance.getMultiValue());
               }
            }
         });
      });

      editComponent = grid;
      return new ParameterFieldWrapperForFrontend(definition, instance, grid, labelWidth,
            () -> setDefaultValueSelectionModel(selection, instance, definition));
   }

   private void setDefaultValueSelectionModel(CheckBoxSelectionModel<DatasourceParameterDataDto> selection,
         DatasourceParameterInstanceDto instance, DatasourceParameterDefinitionDto definition) {

      if (ModeDto.Single.equals(definition.getMode())) {
         selection.setSelection(Collections.singletonList(definition.getSingleDefaultValueSimpleData()));
      } else {
         GWT.log("Setting default selection");
         selection.setSelection(definition.getMultiDefaultValueSimpleData());
      }

      /* mark default */
      boolean isSilent = instance.isSilenceEvents();
      instance.silenceEvents(true);
      instance.setStillDefault(true);
      instance.silenceEvents(isSilent);
   }

   protected void setDefaultValueToggleGroup(Container container, DatasourceParameterInstanceDto instance,
         DatasourceParameterDefinitionDto definition, boolean multi) {

      if (multi) {
         for (Widget vc : container) {
            for (Widget w : ((VerticalLayoutContainer) vc)) {
               Field<Boolean> f = (Field<Boolean>) w;
               f.setValue(null != definition.getMultiDefaultValueSimpleData()
                     && definition.getMultiDefaultValueSimpleData().contains(f.getData(DATA_KEY)));
            }
         }
         ArrayList<DatasourceParameterDataDto> multiValue = new ArrayList<DatasourceParameterDataDto>();
         for (DatasourceParameterDataDto dataObject : definition.getMultiDefaultValueSimpleData())
            multiValue.add(DatasourceParameterDataDtoDec.cloneDataObject(dataObject));
         instance.setMultiValue(multiValue);
      } else {
         for (Widget vc : container) {
            for (Widget w : ((VerticalLayoutContainer) vc)) {
               Field<Boolean> f = (Field<Boolean>) w;
               f.setValue(f.getData(DATA_KEY).equals(definition.getSingleDefaultValueSimpleData()));
            }
         }
         if (null != definition.getSingleDefaultValueSimpleData())
            instance.setSingleValue(
                  DatasourceParameterDataDtoDec.cloneDataObject(definition.getSingleDefaultValueSimpleData()));
         else
            instance.setSingleValue(null);
      }

      boolean isSilent = instance.isSilenceEvents();
      instance.silenceEvents(true);
      instance.setStillDefault(true);
      instance.silenceEvents(isSilent);
   }

   protected void fillContainer(Container container, final DatasourceParameterDefinitionDto definition,
         final DatasourceParameterInstanceDto instance, List<DatasourceParameterDataDto> data, boolean multi) {
      int cols = definition.getBoxLayoutPackColSize();
      if (cols <= 0)
         cols = 1;
      int perCol = definition.getBoxLayoutPackColSize();
      if (BoxLayoutPackModeDto.Packages == definition.getBoxLayoutPackMode())
         cols = data.size() / cols + (data.size() % cols == 0 ? 0 : 1);
      else
         perCol = data.size() / cols + (data.size() % cols == 0 ? 0 : 1);

      VerticalLayoutContainer[] containers = new VerticalLayoutContainer[cols];
      for (int i = 0; i < cols; i++) {
         containers[i] = new VerticalLayoutContainer();
         container.add(containers[i]);
      }

      final ToggleGroup group = new ToggleGroup();
      for (int i = 0; i < data.size(); i++) {
         final DatasourceParameterDataDto dataObject = data.get(i);
         Field<Boolean> box = multi ? new CheckBox() : new Radio();
         if (box instanceof CheckBox)
            ((CheckBox) box).setBoxLabel(dataObject.getKey());
         if (box instanceof Radio)
            ((Radio) box).setBoxLabel(dataObject.getKey());
         box.setData(DATA_KEY, dataObject);
         box.getElement().getStyle().setDisplay(Display.BLOCK);

         if (!multi) {
            box.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
               @Override
               public void onValueChange(ValueChangeEvent<Boolean> event) {
                  if (Boolean.TRUE.equals(event.getValue()))
                     instance.setSingleValue(DatasourceParameterDataDtoDec.cloneDataObject(dataObject));
                  else if (dataObject.equals(instance.getSingleValue()))
                     instance.setSingleValue(null);

                  instance.setStillDefault(false);

                  validateParameter(definition, instance);
               }
            });

            if (!instance.isStillDefault() && dataObject.equals(instance.getSingleValue()))
               box.setValue(true, true);
         } else {
            box.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
               @Override
               public void onValueChange(ValueChangeEvent<Boolean> event) {
                  /* copy old value (otherwise event is not fired on change */
                  List<DatasourceParameterDataDto> multiValue = new ArrayList<DatasourceParameterDataDto>(
                        instance.getMultiValue());

                  multiValue.remove(dataObject);
                  if (Boolean.TRUE.equals(event.getValue()))
                     multiValue.add(DatasourceParameterDataDtoDec.cloneDataObject(dataObject));
                  instance.setMultiValue(multiValue);

                  instance.setStillDefault(false);

                  validateParameter(definition, instance);
               }
            });

            if (!instance.isStillDefault()
                  && (null != instance.getMultiValue() && instance.getMultiValue().contains(dataObject)))
               box.setValue(true, true);
         }

         if (!multi)
            group.add(box);

         switch (definition.getBoxLayoutMode()) {
         case LeftRightTopDown:
            containers[i % cols].add(box);
            break;
         case TopDownLeftRight:
            containers[i / perCol].add(box);
            break;
         }
      }

      /* set default value if necessary */
      if (instance.isStillDefault())
         setDefaultValueToggleGroup(container, instance, definition, multi);
   }

   protected Widget getEditComponent_single(final DatasourceParameterDefinitionDto definition,
         final DatasourceParameterInstanceDto instance, Collection<ParameterInstanceDto> relevantInstances,
         boolean initial, int labelWidth, ReportDto report) {
      final ListStore<DatasourceParameterDataDto> store = new ListStore<DatasourceParameterDataDto>(
            new BasicObjectModelKeyProvider());
      final ListLoader<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>> loader = getLoader(definition,
            relevantInstances, report);
      loader.addLoadHandler(
            new LoadResultListStoreBinding<ListLoadConfig, DatasourceParameterDataDto, ListLoadResult<DatasourceParameterDataDto>>(
                  store));

      /* create combo box */
      final ComboBox<DatasourceParameterDataDto> comboBox = new ComboBox<DatasourceParameterDataDto>(store,
            new LabelProvider<DatasourceParameterDataDto>() {
               @Override
               public String getLabel(DatasourceParameterDataDto item) {
                  return item.getKey();
               }
            });
      comboBox.setForceSelection(true);
      comboBox.setTypeAhead(true);
      comboBox.setEditable(true);
      comboBox.setTriggerAction(TriggerAction.ALL);
      comboBox.setWidth(definition.getWidth());

      comboBoxInfo = new FieldInfoHandler(comboBox);

      if (1 != definition.getHeight())
         comboBox.setHeight(definition.getHeight());

      /* default values */
      store.addStoreDataChangeHandler(new StoreDataChangeHandler<DatasourceParameterDataDto>() {
         @Override
         public void onDataChange(StoreDataChangeEvent<DatasourceParameterDataDto> event) {
            if (instance.isStillDefault())
               setDefaultValueSingle(store, comboBox, instance, definition);
            else {
               comboBox.setValue(instance.getSingleValue(), true);
               comboBoxInfo.clear();
            }
         }
      });

      comboBox.addSelectionHandler(new SelectionHandler<DatasourceParameterDataDto>() {
         @Override
         public void onSelection(SelectionEvent<DatasourceParameterDataDto> event) {
            instance.setSingleValue(event.getSelectedItem());
            comboBoxInfo.clear();
         }
      });

      /* reset on null value */
      comboBox.addChangeHandler(new ChangeHandler() {
         @Override
         public void onChange(ChangeEvent event) {
            if (null == comboBox.getCurrentValue()) {
               Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                  @Override
                  public void execute() {
                     setDefaultValueSingle(store, comboBox, instance, definition);
                  }
               });
            }
         }
      });

      /* load data */
      if (!initial || (instance.isStillDefault()
            && (null == definition.getDependsOn() || definition.getDependsOn().isEmpty())))
         loader.load();
      else {
         comboBox.setValue(instance.getSingleValue(), true);

         /* http://www.sencha.com/forum/showthread.php?185967 */
         /* comboBox.setLoader(loader); */
         comboBox.addBeforeQueryHandler(new BeforeQueryHandler<DatasourceParameterDataDto>() {
            boolean loaded = false;

            @Override
            public void onBeforeQuery(BeforeQueryEvent<DatasourceParameterDataDto> event) {
               if (loaded)
                  return;

               loaded = true;
               loader.load();
            }
         });
      }

      /* disable edit form, if parameter is not editable */
      if (!definition.isEditable())
         comboBox.disable();

      editComponent = comboBox;
      return new ParameterFieldWrapperForFrontend(definition, instance, comboBox, labelWidth, new DefaultValueSetter() {
         @Override
         public void setDefaultValue() {
            setDefaultValueSingle(store, comboBox, instance, definition);
         }
      });
   }

   protected void setDefaultValueSingle(ListStore<DatasourceParameterDataDto> store,
         ComboBox<DatasourceParameterDataDto> comboBox, DatasourceParameterInstanceDto instance,
         DatasourceParameterDefinitionDto definition) {
      DatasourceParameterDataDto value = null;

      comboBoxInfo.clear();

      boolean choseFirst = false;
      if (null != definition.getSingleDefaultValueSimpleData())
         value = definition.getSingleDefaultValueSimpleData();
      else {
         store.removeFilters(); // ensure that we do not have any accidental filters left (due to e.g., type
                                // ahead)
         if (store.getAll().size() > 0) {
            value = store.get(0);
            choseFirst = true;
         }
      }

      if (null != value) {
         // TODO : Dirty Hack ->
         DatasourceParameterDataDto valueCopy = DatasourceParameterDataDtoDec.cloneDataObject(value);

         /* set value in combobox and! instance listener only listens to selects */
         comboBox.setValue(valueCopy, true);
         instance.setSingleValue(valueCopy);

         if (choseFirst)
            comboBoxInfo.mark(RsMessages.INSTANCE.dropdownFirstValuePerDefaultInfo());
      }

      boolean isSilent = instance.isSilenceEvents();
      instance.silenceEvents(true);
      instance.setStillDefault(true);
      instance.silenceEvents(isSilent);
   }

   protected Widget getEditComponent_popup(final DatasourceParameterDefinitionDto definition,
         final DatasourceParameterInstanceDto instance, final Collection<ParameterInstanceDto> relevantInstances,
         int labelWidth, ReportDto report) {
      /* create store and add selected values */
      final ListStore<DatasourceParameterDataDto> gridStore = new ListStore<DatasourceParameterDataDto>(
            new BasicObjectModelKeyProvider());

      /* create grid */
      final ListStore<DatasourceParameterDataDto> allStore = new ListStore<DatasourceParameterDataDto>(
            new BasicObjectModelKeyProvider());
      final SimpleGridSelectionPopup<DatasourceParameterDataDto> multiValueGrid = new SimpleGridSelectionPopup<DatasourceParameterDataDto>(
            BaseMessages.INSTANCE.name(), DatasourceParameterDataDtoPA.INSTANCE.key(), gridStore, allStore) {
         @Override
         protected void itemsSelected(List<DatasourceParameterDataDto> selectedItems) {
            if (ModeDto.Single.equals(definition.getMode())) {
               if (null != selectedItems && selectedItems.size() > 0)
                  instance.setSingleValue(selectedItems.get(0));
               else
                  instance.setSingleValue(null);
            } else
               instance.setMultiValue(selectedItems);

            validateParameter(definition, instance);
         }
      };
      multiValueGrid.setAllItemsStoreLoader(getLoader(definition, relevantInstances, report));
      multiValueGrid.getView().setEmptyText(RsMessages.INSTANCE.noDataSelected());
      multiValueGrid.setHeader(BaseMessages.INSTANCE.selectValueLabel(definition.getName()));
      multiValueGrid.setBorders(true);

      /* selection type */
      if (ModeDto.Single.equals(definition.getMode()))
         multiValueGrid.setPopupSelectionMode(SelectionMode.SINGLE);

      /* size */
      multiValueGrid.setWidth(definition.getWidth());
      if (1 != definition.getHeight())
         multiValueGrid.setHeight(definition.getHeight());

      /* disable edit form, if parameter is not editable */
      if (!definition.isEditable())
         multiValueGrid.disable();

      editComponent = multiValueGrid;

      /*
       * populate values - neeeds to be done in deferred way to allow parameter view
       * to capture change events (parameter view starts listening only after we
       * returned the widget)
       */
      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
         @Override
         public void execute() {
            if (instance.isStillDefault()) {
               setDefaultValuePopup(gridStore, instance, definition);
            } else {
               if (ModeDto.Single.equals(definition.getMode()))
                  gridStore.add(instance.getSingleValue());
               else
                  for (DatasourceParameterDataDto value : new ArrayList<DatasourceParameterDataDto>(
                        instance.getMultiValue()))
                     gridStore.add(value);

               /* fire change event */
               instance.fireObjectChangedEvent();
            }
         }
      });

      return new ParameterFieldWrapperForFrontend(definition, instance, multiValueGrid, labelWidth,
            new DefaultValueSetter() {
               @Override
               public void setDefaultValue() {
                  setDefaultValuePopup(gridStore, instance, definition);
               }
            });
   }

   protected void setDefaultValuePopup(ListStore<DatasourceParameterDataDto> gridStore,
         DatasourceParameterInstanceDto instance, DatasourceParameterDefinitionDto definition) {
      /* set data in instance and display store */
      gridStore.clear();
      if (ModeDto.Single.equals(definition.getMode())) {
         if (null != definition.getSingleDefaultValueSimpleData()) {
            DatasourceParameterDataDto clonedValue = DatasourceParameterDataDtoDec
                  .cloneDataObject(definition.getSingleDefaultValueSimpleData());
            gridStore.add(clonedValue);
            instance.setSingleValue(clonedValue);
         } else
            instance.setSingleValue(null);
      } else {
         for (DatasourceParameterDataDto defaultValue : definition.getMultiDefaultValueSimpleData()) {
            // TODO : Dirty Hack ->
            DatasourceParameterDataDto valueCopy = DatasourceParameterDataDtoDec.cloneDataObject(defaultValue);

            gridStore.add(valueCopy);
         }

         if (gridStore.size() > 0)
            instance.setMultiValue(new ArrayList<DatasourceParameterDataDto>(gridStore.getAll()));
         else
            instance.setMultiValue(new ArrayList<DatasourceParameterDataDto>());
      }

      boolean isSilent = instance.isSilenceEvents();
      instance.silenceEvents(true);
      instance.setStillDefault(true);
      instance.silenceEvents(isSilent);
   }

   /**
    * Prepares the store.
    * 
    * @param definition
    */
   private ListLoader<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>> getLoader(
         final DatasourceParameterDefinitionDto definition, final Collection<ParameterInstanceDto> relevantInstances,
         final ReportDto report) {
      RpcProxy<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>>() {

         @Override
         public void load(ListLoadConfig loadConfig,
               AsyncCallback<ListLoadResult<DatasourceParameterDataDto>> callback) {
            datasourceParamDao.loadDatasourceParameterData(definition, relevantInstances, false, report, callback);
         }
      };

      return new ListLoader<ListLoadConfig, ListLoadResult<DatasourceParameterDataDto>>(proxy);
   }

   protected boolean isValueSet(DatasourceParameterDefinitionDto definition, DatasourceParameterInstanceDto instance) {
      if (ModeDto.Single.equals(definition.getMode()) && null == instance.getSingleValue()
            && !SingleSelectionModeDto.Dropdown.equals(definition.getSingleSelectionMode()))
         return false;
      else if (ModeDto.Multi.equals(definition.getMode())) {
         List<DatasourceParameterDataDto> multiValue = instance.getMultiValue();
         if (null == multiValue || multiValue.isEmpty())
            return false;
      }
      return true;
   }

   protected void validateParameter(DatasourceParameterDefinitionDto definition,
         DatasourceParameterInstanceDto instance) {

      if (isValueSet(definition, instance)) {
         if (null != fieldInfoHandler)
            fieldInfoHandler.clear();

         if (editComponent instanceof Field<?>)
            ((Field) editComponent).clearInvalid();
      }

   }

   public List<String> validateParameter(DatasourceParameterDefinitionDto definition,
         DatasourceParameterInstanceDto instance, Widget widget) {
      List<String> errList = new ArrayList<String>();
      if (!definition.isMandatory())
         return errList;

      if (null != fieldInfoHandler)
         fieldInfoHandler.clear();

      if (!isValueSet(definition, instance)) {
         if (null != editComponent)
            widget = editComponent;

         errList.add(RsMessages.INSTANCE.invalidParameter(definition.getName()));

         if (widget instanceof Field<?>) {
            ((Field) widget).markInvalid(xmessages.textField_blankText());
         } else if (null != widget) {
            if (null == fieldInfoHandler || widget != fieldInfoHandler.getTarget())
               fieldInfoHandler = new FieldErrorHandler(widget);
            fieldInfoHandler.mark(xmessages.textField_blankText());
         }
      }

      return errList;
   }

}
