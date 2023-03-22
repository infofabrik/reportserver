package net.datenwerke.rs.dsbundle.client.dsbundle.ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.validator.KeyValidator;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldChanged;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCSpace;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCCustomComponentImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.Separator;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.handlers.GenericStoreHandler;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeBasic;
import net.datenwerke.rs.dsbundle.client.dsbundle.DatasourceBundleUiService;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.pa.DatabaseBundleDtoPA;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.pa.DatabaseBundleEntryDtoPA;
import net.datenwerke.rs.dsbundle.client.dsbundle.locale.DatasourceBundleMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class DatabaseBundleForm extends SimpleFormView {

   private final ToolbarService toolbarService;
   private final UITree datasourceTree;

   private SimpleForm keyProviderForm;
   private Grid<DatabaseBundleEntryDto> grid;

   @Inject
   public DatabaseBundleForm(DatasourceBundleUiService dsBundleService, ToolbarService toolbarService,
         @DatasourceTreeBasic UITree datasourceTree) {

      this.toolbarService = toolbarService;
      this.datasourceTree = datasourceTree;
   }

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasourceBundleMessages.INSTANCE.editDataSource()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(600);
      /* name */
      form.addField(String.class, DatabaseBundleDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName());
      form.setFieldWidth(500);
      
      /* key */
      form.addField(String.class, DatabaseBundleDtoPA.INSTANCE.key(),
            BaseMessages.INSTANCE.key(), new SFFCStringValidatorRegex(KeyValidator.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()));
      form.endRow();
      
      form.setFieldWidth(1);

      form.addField(String.class, DatabaseBundleDtoPA.INSTANCE.description(),
            BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());

      /* initialize grid */
      grid = createGrid();

      form.setFieldWidth(0.4);
      final String bundleKeyProviderField = form.addField(List.class, DatabaseBundleDtoPA.INSTANCE.keySource(),
            "Key provider", new SFFCStaticDropdownList<String>() {
               @Override
               public Map<String, String> getValues() {
                  HashMap<String, String> map = new LinkedHashMap<String, String>();

                  map.put(DatasourceBundleMessages.INSTANCE.keyProviderLoginLabel(), "login");
                  map.put(DatasourceBundleMessages.INSTANCE.keyProviderParamLabel(), "param");

                  return map;
               }
            });

      final DwContentPanel keyProviderFormWrapper = DwContentPanel.newInlineInstance();
      form.addField(CustomComponent.class, new SFFCCustomComponent() {
         @Override
         public Widget getComponent() {
            return keyProviderFormWrapper;
         }
      });

      form.addCondition(bundleKeyProviderField, new FieldChanged(), new SimpleFormAction() {
         @Override
         public void onSuccess(SimpleForm form) {
            if ("param".equals(form.getValue(bundleKeyProviderField))) {
               keyProviderForm = SimpleForm.getInlineInstance();
               keyProviderForm.addField(String.class, DatabaseBundleDtoPA.INSTANCE.keySourceParamName(),
                     "Parameter Key");

               keyProviderForm.bind(form.getBoundModel());
               keyProviderFormWrapper.add(keyProviderForm);
            } else {
               if (null != keyProviderForm)
                  keyProviderForm.removeFromParent();
            }
         }

         @Override
         public void onFailure(SimpleForm form) {
         }
      });

      String mappingField = form.addField(List.class, DatabaseBundleDtoPA.INSTANCE.mappingSource(), "Mapping provider",
            new SFFCStaticDropdownList<String>() {
               @Override
               public Map<String, String> getValues() {
                  HashMap<String, String> map = new LinkedHashMap<String, String>();

                  map.put(DatasourceBundleMessages.INSTANCE.mappingProviderStaticLabel(), "static");
                  map.put(DatasourceBundleMessages.INSTANCE.mappingProviderAutoNameLabel(), "auto-ds-byname");
                  map.put(DatasourceBundleMessages.INSTANCE.mappingProviderAutoIdLabel(), "auto-ds-byid");

                  return map;
               }
            });

      DwNorthSouthContainer nsContainer = new DwNorthSouthContainer();
      nsContainer.setWidget(grid);

      /* create toolbar */
      ToolBar toolbar = new DwToolBar();
      nsContainer.setNorthWidget(toolbar);

      /* add toolbar capabilities */
      addAddRemoveButtons(grid, toolbar);

      form.addField(Separator.class, new SFFCSpace());

      form.addField(CustomComponent.class, new SFFCCustomComponentImpl(nsContainer));
      grid.getStore().addStoreHandlers(new GenericStoreHandler<DatabaseBundleEntryDto>() {
         @Override
         protected void handleDataChangeEvent() {
            DatabaseBundleDto node = (DatabaseBundleDto) getSelectedNode();

            Set<DatabaseBundleEntryDto> entries = node.getBundleEntries();
            entries.clear();
            for (DatabaseBundleEntryDto e : grid.getStore().getAll()) {
               if ((null != e.getKey() && !e.getKey().isEmpty()) || (null != e.getDatabase())) {
                  entries.add(e);
               }
            }
         }
      });

   }

   private Grid<DatabaseBundleEntryDto> createGrid() {
      DatabaseBundleDto bundle = (DatabaseBundleDto) getSelectedNode();

      ListStore<DatabaseBundleEntryDto> store = new ListStore<DatabaseBundleEntryDto>(new IdProvider());
      store.setAutoCommit(true);
      store.addAll(bundle.getBundleEntries());

      ColumnConfig<DatabaseBundleEntryDto, String> ccKey = new ColumnConfig<DatabaseBundleEntryDto, String>(
            DatabaseBundleEntryDtoPA.INSTANCE.key(), 120, "key");
      ccKey.setMenuDisabled(true);
      ccKey.setSortable(false);

      ColumnConfig<DatabaseBundleEntryDto, AbstractDatasourceManagerNodeDto> ccDS = new ColumnConfig<DatabaseBundleEntryDto, AbstractDatasourceManagerNodeDto>(
            DatabaseBundleEntryDtoPA.INSTANCE.database(), 220, "datasource");
      ccDS.setCell(new SimpleSafeHtmlCell<AbstractDatasourceManagerNodeDto>(
            new AbstractSafeHtmlRenderer<AbstractDatasourceManagerNodeDto>() {
               @Override
               public SafeHtml render(AbstractDatasourceManagerNodeDto object) {
                  return SafeHtmlUtils.fromString(null != object ? object.toDisplayTitle() : "");
               }
            }));
      ccDS.setMenuDisabled(true);
      ccDS.setSortable(false);

      List<ColumnConfig<DatabaseBundleEntryDto, ?>> columns = new ArrayList<ColumnConfig<DatabaseBundleEntryDto, ?>>();
      columns.add(ccKey);
      columns.add(ccDS);

      ColumnModel<DatabaseBundleEntryDto> cm = new ColumnModel<DatabaseBundleEntryDto>(columns);
      final Grid<DatabaseBundleEntryDto> grid = new Grid<DatabaseBundleEntryDto>(store, cm);
      grid.getView().setAutoExpandColumn(ccDS);

      final GridEditing<DatabaseBundleEntryDto> editing = new GridInlineEditing<DatabaseBundleEntryDto>(grid);
      editing.addEditor(ccKey, new TextField());

      final SingleTreeSelectionField stsf = new SingleTreeSelectionField(
            (Class<? extends AbstractNodeDto>) AbstractDatasourceManagerNodeDto.class);
      stsf.addValueChangeHandler(new ValueChangeHandler<AbstractNodeDto>() {
         @Override
         public void onValueChange(ValueChangeEvent<AbstractNodeDto> event) {
            DatabaseBundleEntryDto selectedItem = grid.getSelectionModel().getSelectedItem();
            if (null != selectedItem) {
               if (null == event.getValue())
                  selectedItem.setDatabase(null);
               else if (event.getValue() instanceof AbstractDatasourceManagerNodeDto)
                  selectedItem.setDatabase((AbstractDatasourceManagerNodeDto) event.getValue());
               grid.getStore().update(selectedItem);
            }
         }
      });
      stsf.setPropertyEditor(new PropertyEditor<AbstractNodeDto>() {

         @Override
         public AbstractNodeDto parse(CharSequence text) throws ParseException {
            if (null != text && null != stsf.getValue()) {
               if (text.equals(stsf.getValue().toDisplayTitle()))
                  return stsf.getValue();
            }
            return null;
         }

         @Override
         public String render(AbstractNodeDto object) {
            return object.toDisplayTitle();
         }
      });
      stsf.setTreePanel(datasourceTree);
      editing.addEditor(ccDS, (Field) stsf);

      return grid;
   }

   private void addAddRemoveButtons(final Grid<DatabaseBundleEntryDto> grid, ToolBar toolbar) {
      /* remove buttons */
      DwTextButton addBtn = toolbarService.createSmallButtonLeft(BaseMessages.INSTANCE.add(), BaseIcon.LOCK_ADD);
      addBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            grid.getStore().add(new DatabaseBundleEntryDto());
         }
      });

      toolbar.add(addBtn);

      DwSplitButton removeButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
      removeButton.setIcon(BaseIcon.DELETE);
      removeButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            List<DatabaseBundleEntryDto> selectedItems = grid.getSelectionModel().getSelectedItems();
            for (DatabaseBundleEntryDto e : selectedItems) {
               grid.getStore().remove(e);
            }
         }
      });
      toolbar.add(removeButton);
   }

   private class IdProvider implements ModelKeyProvider<Dto> {

      private final HashMap<Dto, String> map = new HashMap<Dto, String>();
      private int cnt = 0;

      @Override
      public String getKey(Dto item) {
         if (item instanceof IdedDto && null != ((IdedDto) item).getDtoId())
            return item.getClass().getName() + "--" + String.valueOf(((IdedDto) item).getDtoId());

         if (!map.containsKey(item))
            map.put(item, item.getClass().getName() + "--xxid--" + cnt++);
         return map.get(item);
      }

   }

}
