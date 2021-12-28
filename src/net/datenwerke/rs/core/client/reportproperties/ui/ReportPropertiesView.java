package net.datenwerke.rs.core.client.reportproperties.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.impl.DwRemoveButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportStringPropertyDtoPA;
import net.datenwerke.rs.core.client.reportproperties.ReportPropertiesDao;
import net.datenwerke.rs.core.client.reportproperties.locale.ReportPropertiesMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class ReportPropertiesView extends MainPanelView {

   public static final String VIEW_ID = "ReportPropertiesView";

   private final ReportPropertiesDao propertiesDao;

   private ListStore<ReportStringPropertyDto> propertiesStore;
   private List<ReportStringPropertyDto> removedProperties = new ArrayList<ReportStringPropertyDto>();
   private List<ReportStringPropertyDto> addedProperties = new ArrayList<ReportStringPropertyDto>();

   private ListStore<ReportStringPropertyDto> inheritedPropertiesStore;

   private Grid<ReportStringPropertyDto> grid;
   private Grid<ReportStringPropertyDto> inheritedPropertiesGrid;

   @Inject
   public ReportPropertiesView(ReportPropertiesDao propertiesDao) {

      /* store objects */
      this.propertiesDao = propertiesDao;
   }

   @Override
   public String getViewId() {
      return VIEW_ID;
   }

   @Override
   public boolean isSticky() {
      return true;
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.LIST_ALT.toImageResource();
   }

   @Override
   public String getComponentHeader() {
      return ReportPropertiesMessages.INSTANCE.header();
   }

   @Override
   public Widget getViewComponent(AbstractNodeDto selectedNode) {
      NorthSouthContainer nsContainer = new NorthSouthContainer();

      /* prepare store */
      propertiesStore = new ListStore<ReportStringPropertyDto>(new DtoIdModelKeyProvider());
      propertiesStore.addSortInfo(
            new StoreSortInfo<ReportStringPropertyDto>(ReportStringPropertyDtoPA.INSTANCE.name(), SortDir.ASC));
      updateStore((ReportDto) selectedNode);

      /* create grid */
      createGrid();
      nsContainer.setWidget(grid);

      /* create toolbar */
      ToolBar toolbar = createToolbar();
      nsContainer.setNorthWidget(toolbar);

      DwContentPanel main = new DwContentPanel();
      main.setLightHeader();
      main.setHeading(ReportPropertiesMessages.INSTANCE.header());
      main.add(nsContainer);
      main.setInfoText(ReportPropertiesMessages.INSTANCE.description());

      VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      wrapper.add(main, new VerticalLayoutData(1, -1, new Margins(10)));
      if (getReport() instanceof ReportVariantDto) {
         DwContentPanel inheritedPanel = new DwContentPanel();
         inheritedPanel.setLightHeader();
         inheritedPanel.setHeading(ReportPropertiesMessages.INSTANCE.inheritedReportProperties());
         inheritedPanel.add(createInheritedPropertiesGrid());
         loadInheritedPropertiesData();
         wrapper.add(inheritedPanel, new VerticalLayoutData(1, -1, new Margins(10)));
      }

      return wrapper;
   }

   private Component createInheritedPropertiesGrid() {
      /* prepare store */
      inheritedPropertiesStore = new ListStore<>(new DtoIdModelKeyProvider());
      inheritedPropertiesStore.addSortInfo(new StoreSortInfo<>(ReportStringPropertyDtoPA.INSTANCE.name(), SortDir.ASC));

      /* configure columns */
      List<ColumnConfig<ReportStringPropertyDto, ?>> columns = new ArrayList<>();

      /* icon column: here always empty */
      ColumnConfig<ReportStringPropertyDto, ReportStringPropertyDto> icConfig = new ColumnConfig<>(
            new IdentityValueProvider<>(), 40);
      icConfig.setCell(new AbstractCell<ReportStringPropertyDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, ReportStringPropertyDto value,
               SafeHtmlBuilder sb) {
         }
      });
      icConfig.setMenuDisabled(true);
      columns.add(icConfig);

      /* name column */
      ColumnConfig<ReportStringPropertyDto, String> nameConfig = new ColumnConfig<>(
            ReportStringPropertyDtoPA.INSTANCE.name(), 200, ReportPropertiesMessages.INSTANCE.gridNameHeader());
      nameConfig.setMenuDisabled(true);

      columns.add(nameConfig);

      /* value */
      ColumnConfig<ReportStringPropertyDto, String> valueConfig = new ColumnConfig<>(
            ReportStringPropertyDtoPA.INSTANCE.strValue(), 300, ReportPropertiesMessages.INSTANCE.gridValueHeader());
      valueConfig.setMenuDisabled(true);

      columns.add(valueConfig);

      /* create grid */
      inheritedPropertiesGrid = new Grid<ReportStringPropertyDto>(inheritedPropertiesStore, new ColumnModel<>(columns));
      inheritedPropertiesGrid.getView().setAutoExpandColumn(valueConfig);
      inheritedPropertiesGrid.setSelectionModel(new GridSelectionModel<>());
      inheritedPropertiesGrid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

      return inheritedPropertiesGrid;
   }

   private void loadInheritedPropertiesData() {
      propertiesDao.getInheritedProperties(getReport(), new RsAsyncCallback<List<ReportStringPropertyDto>>() {
         @Override
         public void onSuccess(List<ReportStringPropertyDto> result) {
            inheritedPropertiesStore.clear();
            inheritedPropertiesStore.addAll(result);
         }

      });
   }

   private void getSupportedPropertyKeys(final Menu addBtnMenu) {
      propertiesDao.getSupportedPropertyKeys(getReport(), new RsAsyncCallback<List<String>>() {
         @Override
         public void onSuccess(List<String> result) {
            SeparatorMenuItem separator = new SeparatorMenuItem();
            addBtnMenu.add(separator);

            result.forEach(entry -> {
               MenuItem item = new DwMenuItem(entry);
               addBtnMenu.add(item);
               item.addSelectionHandler(event -> addProperty(entry));
            });
         }
      });
   }

   private ToolBar createToolbar() {
      ToolBar toolbar = new DwToolBar();

      DwSplitButton addBtn = new DwSplitButton(BaseMessages.INSTANCE.add());
      addBtn.setIcon(BaseIcon.COG_ADD);

      final Menu addBtnMenu = new DwMenu();
      addBtnMenu.setMinWidth(80);
      addBtnMenu.setMaxHeight(300);
      addBtn.setMenu(addBtnMenu);
      propertiesDao.getPropertyKeys(getReport(), new RsAsyncCallback<List<String>>() {
         @Override
         public void onSuccess(List<String> result) {
            result.forEach(entry -> {
               MenuItem item = new DwMenuItem(entry);
               addBtnMenu.add(item);
               item.addSelectionHandler(event -> addProperty(entry));
            });
            getSupportedPropertyKeys(addBtnMenu);
         }
      });

      addBtn.addSelectHandler(event -> addProperty(null));
      toolbar.add(addBtn);

      toolbar.add(new SeparatorToolItem());

      DwRemoveButton removeButton = new DwRemoveButton() {
         @Override
         protected void onRemove() {
            List<ReportStringPropertyDto> selectedItems = grid.getSelectionModel().getSelectedItems();

            for (ReportStringPropertyDto r : selectedItems) {
               if (addedProperties.remove(r))
                  propertiesStore.remove(r);
               else {
                  removedProperties.add(r);
                  propertiesStore.update(r);
               }
            }
         }

         @Override
         protected void onRemoveAll() {
            for (ReportStringPropertyDto r : grid.getStore().getAll()) {
               if (addedProperties.remove(r))
                  propertiesStore.remove(r);
               else {
                  removedProperties.add(r);
                  propertiesStore.update(r);
               }
            }
         }
      };
      toolbar.add(removeButton);

      toolbar.add(new FillToolItem());

      DwTextButton resetBtn = new DwTextButton(BaseMessages.INSTANCE.revert(), BaseIcon.ROTATE_LEFT);
      resetBtn.addSelectHandler(event -> {
         propertiesStore.rejectChanges();
         addedProperties.forEach(p -> propertiesStore.remove(p));
         addedProperties.clear();
         removedProperties.clear();

         grid.getStore().getAll().forEach(r -> propertiesStore.update(r));
      });
      toolbar.add(resetBtn);

      DwTextButton storeBtn = new DwTextButton(BaseMessages.INSTANCE.save(), BaseIcon.ACCEPT);
      toolbar.add(storeBtn);
      storeBtn.addSelectHandler(event -> {
         mask(BaseMessages.INSTANCE.storingMsg());

         List<ReportStringPropertyDto> rP = new ArrayList<>(removedProperties);
         List<ReportStringPropertyDto> aP = new ArrayList<>(addedProperties);

         addedProperties.clear();
         removedProperties.clear();

         rP.forEach(p -> propertiesStore.remove(p));

         Collection<Store<ReportStringPropertyDto>.Record> modifiedRecords = propertiesStore.getModifiedRecords();
         List<ReportStringPropertyDto> mP = new ArrayList<>();
         for (Store<ReportStringPropertyDto>.Record r : modifiedRecords) {
            r.commit(true);
            mP.add(r.getModel());
         }

         propertiesDao.updateProperties(getReport(), aP, mP, rP, new RsAsyncCallback<ReportDto>() {
            @Override
            public void onSuccess(ReportDto result) {
               super.onSuccess(result);
               unmask();
               propertiesStore.clear();
               updateStore(result);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
               unmask();
            }
         });
      });

      return toolbar;
   }

   protected void addProperty(String name) {
      ReportStringPropertyDto property = new ReportStringPropertyDto();

      name = getUniqueName(name);

      property.setName(name);
      addedProperties.add(property);
      propertiesStore.add(property);
   }

   protected String getUniqueName(String name) {
      if (null == name)
         name = "unnamed";
      String c = name;
      int i = 0;
      while (hasPropertyWith(name))
         name = c + (++i);
      return name;
   }

   protected boolean hasPropertyWith(final String name) {
      return propertiesStore.getAll().stream().anyMatch(p -> name.equals(p.getName()));
   }

   protected void updateStore(ReportDto report) {
      propertiesStore.addAll(((ReportDtoDec) report).getReportStringProperties());
   }

   protected ReportDto getReport() {
      AbstractNodeDto node = getSelectedNode();
      assert node instanceof ReportDto;

      return (ReportDto) node;
   }

   private void createGrid() {
      /* configure columns */
      List<ColumnConfig<ReportStringPropertyDto, ?>> columns = new ArrayList<ColumnConfig<ReportStringPropertyDto, ?>>();

      /* icon column */
      ColumnConfig<ReportStringPropertyDto, ReportStringPropertyDto> icConfig = new ColumnConfig<>(
            new IdentityValueProvider<ReportStringPropertyDto>(), 40);
      icConfig.setMenuDisabled(true);
      icConfig.setCell(new AbstractCell<ReportStringPropertyDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, ReportStringPropertyDto value,
               SafeHtmlBuilder sb) {
            if (removedProperties.contains(value))
               sb.append(BaseIcon.DELETE.toSafeHtml());
            else if (addedProperties.contains(value))
               sb.append(BaseIcon.ADD.toSafeHtml());
         }
      });
      columns.add(icConfig);

      /* name column */
      ColumnConfig<ReportStringPropertyDto, String> nameConfig = new ColumnConfig<>(
            ReportStringPropertyDtoPA.INSTANCE.name(), 200, ReportPropertiesMessages.INSTANCE.gridNameHeader());
      nameConfig.setMenuDisabled(true);

      columns.add(nameConfig);

      /* value */
      ColumnConfig<ReportStringPropertyDto, String> valueConfig = new ColumnConfig<>(
            ReportStringPropertyDtoPA.INSTANCE.strValue(), 300, ReportPropertiesMessages.INSTANCE.gridValueHeader());
      valueConfig.setMenuDisabled(true);

      columns.add(valueConfig);

      /* create grid */
      grid = new Grid<ReportStringPropertyDto>(propertiesStore, new ColumnModel<ReportStringPropertyDto>(columns));
      grid.getView().setAutoExpandColumn(valueConfig);
      grid.setSelectionModel(new GridSelectionModel<ReportStringPropertyDto>());
      grid.getView().setShowDirtyCells(true);

      GridEditing<ReportStringPropertyDto> editing = new GridInlineEditing<ReportStringPropertyDto>(grid);

      editing.addEditor(nameConfig, new TextField());
      editing.addEditor(valueConfig, new TextField());
   }

}
