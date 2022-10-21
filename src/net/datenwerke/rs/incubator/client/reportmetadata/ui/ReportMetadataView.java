package net.datenwerke.rs.incubator.client.reportmetadata.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportMetadataDtoPA;
import net.datenwerke.rs.incubator.client.reportmetadata.ReportMetadataDao;
import net.datenwerke.rs.incubator.client.reportmetadata.locale.ReportMetadataMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class ReportMetadataView extends MainPanelView {

   public static final String VIEW_ID = "ReportMetadataView";

   private static ReportMetadataDtoPA metaPa = GWT.create(ReportMetadataDtoPA.class);

   private final ReportMetadataDao metadataDao;

   private ListStore<ReportMetadataDto> metadataStore;
   private List<ReportMetadataDto> removedProperties = new ArrayList<ReportMetadataDto>();
   private List<ReportMetadataDto> addedProperties = new ArrayList<ReportMetadataDto>();

   private Grid<ReportMetadataDto> grid;

   @Inject
   public ReportMetadataView(ReportMetadataDao propertiesDao) {

      /* store objects */
      this.metadataDao = propertiesDao;
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
      return BaseIcon.EDIT.toImageResource();
   }

   @Override
   public String getComponentHeader() {
      return ReportMetadataMessages.INSTANCE.header();
   }

   @Override
   public Widget getViewComponent(AbstractNodeDto selectedNode) {
      NorthSouthContainer nsContainer = new NorthSouthContainer();

      /* prepare store */
      metadataStore = new ListStore<ReportMetadataDto>(new DtoIdModelKeyProvider());
      metadataStore.addSortInfo(new StoreSortInfo<ReportMetadataDto>(ReportMetadataDtoPA.INSTANCE.name(), SortDir.ASC));
      updateStore(getReport());

      /* create grid */
      createGrid();
      nsContainer.setWidget(grid);

      /* create toolbar */
      ToolBar toolbar = createToolbar();
      nsContainer.setNorthWidget(toolbar);

      DwContentPanel main = new DwContentPanel();
      main.setLightHeader();
      main.setHeading(ReportMetadataMessages.INSTANCE.header());
      main.add(nsContainer);
      main.setInfoText(ReportMetadataMessages.INSTANCE.description());

      VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
      wrapper.add(main, new VerticalLayoutData(1, 1, new Margins(10)));

      return wrapper;
   }

   private ToolBar createToolbar() {
      ToolBar toolbar = new DwToolBar();

      DwSplitButton addBtn = new DwSplitButton(BaseMessages.INSTANCE.add());
      addBtn.setIcon(BaseIcon.COG_ADD);

      final Menu addBtnMenu = new DwMenu();
      addBtnMenu.setMaxHeight(300);
      addBtn.setMenu(addBtnMenu);
      metadataDao.getPropertyKeys(new RsAsyncCallback<List<String>>() {
         @Override
         public void onSuccess(List<String> result) {
            for (final String entry : result) {
               MenuItem item = new DwMenuItem(entry);
               addBtnMenu.add(item);
               item.addSelectionHandler(new SelectionHandler<Item>() {
                  @Override
                  public void onSelection(SelectionEvent<Item> event) {
                     addProperty(entry);
                  }
               });
            }
         }
      });

      addBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            addProperty(null);
         }
      });
      toolbar.add(addBtn);

      toolbar.add(new SeparatorToolItem());

      DwSplitButton removeButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
      removeButton.setIcon(BaseIcon.DELETE);
      removeButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            List<ReportMetadataDto> selectedItems = grid.getSelectionModel().getSelectedItems();

            for (ReportMetadataDto r : selectedItems) {
               if (addedProperties.remove(r))
                  metadataStore.remove(r);
               else {
                  removedProperties.add(r);
                  metadataStore.update(r);
               }
            }
         }
      });
      toolbar.add(removeButton);

      MenuItem removeAllButton = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
      removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {

         @Override
         public void onSelection(SelectionEvent<Item> event) {
            ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.confirmDeleteTitle(),
                  BaseMessages.INSTANCE.confirmDeleteAllMsg());
            cmb.addDialogHideHandler(new DialogHideHandler() {
               @Override
               public void onDialogHide(DialogHideEvent event) {
                  if (event.getHideButton() == PredefinedButton.YES) {
                     for (ReportMetadataDto r : grid.getStore().getAll()) {
                        if (addedProperties.remove(r))
                           metadataStore.remove(r);
                        else {
                           removedProperties.add(r);
                           metadataStore.update(r);
                        }
                     }
                  }
               }
            });
            cmb.show();
         }
      });

      Menu remMenu = new DwMenu();
      remMenu.add(removeAllButton);
      removeButton.setMenu(remMenu);

      toolbar.add(new FillToolItem());

      DwTextButton resetBtn = new DwTextButton(BaseMessages.INSTANCE.revert(), BaseIcon.ROTATE_LEFT);
      resetBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            metadataStore.rejectChanges();
            for (ReportMetadataDto p : addedProperties)
               metadataStore.remove(p);
            addedProperties.clear();
            removedProperties.clear();

            for (ReportMetadataDto r : grid.getStore().getAll())
               metadataStore.update(r);
         }
      });
      toolbar.add(resetBtn);

      DwTextButton storeBtn = new DwTextButton(BaseMessages.INSTANCE.save(), BaseIcon.ACCEPT);
      toolbar.add(storeBtn);
      storeBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            mask(BaseMessages.INSTANCE.storingMsg());

            List<ReportMetadataDto> rP = new ArrayList<ReportMetadataDto>(removedProperties);
            List<ReportMetadataDto> aP = new ArrayList<ReportMetadataDto>(addedProperties);

            addedProperties.clear();
            removedProperties.clear();

            for (ReportMetadataDto p : rP)
               metadataStore.remove(p);

            Collection<Store<ReportMetadataDto>.Record> modifiedRecords = metadataStore.getModifiedRecords();
            List<ReportMetadataDto> mP = new ArrayList<ReportMetadataDto>();
            for (Store<ReportMetadataDto>.Record r : modifiedRecords) {
               r.commit(true);
               mP.add(r.getModel());
            }

            metadataDao.updateProperties(getReport(), aP, mP, rP, new RsAsyncCallback<ReportDto>() {
               @Override
               public void onSuccess(ReportDto result) {
                  super.onSuccess(result);
                  unmask();
                  metadataStore.clear();
                  updateStore(result);
               }

               @Override
               public void onFailure(Throwable caught) {
                  super.onFailure(caught);
                  unmask();
               }
            });
         }
      });

      return toolbar;
   }

   protected void addProperty(String name) {
      ReportMetadataDto property = new ReportMetadataDto();

      name = getUniqueName(name);

      property.setName(name);
      addedProperties.add(property);
      metadataStore.add(property);
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

   protected boolean hasPropertyWith(String name) {
      for (ReportMetadataDto p : metadataStore.getAll())
         if (name.equals(p.getName()))
            return true;
      return false;
   }

   protected void updateStore(ReportDto report) {
      for (ReportMetadataDto prop : report.getReportMetadata())
         metadataStore.add(prop);
   }

   protected ReportDto getReport() {
      AbstractNodeDto node = getSelectedNode();
      assert node instanceof ReportDto;

      return (ReportDto) node;
   }

   private void createGrid() {
      /* configure columns */
      List<ColumnConfig<ReportMetadataDto, ?>> columns = new ArrayList<ColumnConfig<ReportMetadataDto, ?>>();

      /* icon column */
      ColumnConfig<ReportMetadataDto, ReportMetadataDto> icConfig = new ColumnConfig<ReportMetadataDto, ReportMetadataDto>(
            new IdentityValueProvider<ReportMetadataDto>(), 40);
      icConfig.setMenuDisabled(true);
      icConfig.setCell(new AbstractCell<ReportMetadataDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, ReportMetadataDto value,
               SafeHtmlBuilder sb) {
            if (removedProperties.contains(value))
               sb.append(BaseIcon.DELETE.toSafeHtml());
            else if (addedProperties.contains(value))
               sb.append(BaseIcon.ADD.toSafeHtml());
         }
      });
      columns.add(icConfig);

      /* name column */
      ColumnConfig<ReportMetadataDto, String> nameConfig = new ColumnConfig<ReportMetadataDto, String>(metaPa.name(),
            200, ReportMetadataMessages.INSTANCE.gridNameHeader());
      nameConfig.setMenuDisabled(true);

      columns.add(nameConfig);

      /* value */
      ColumnConfig<ReportMetadataDto, String> valueConfig = new ColumnConfig<ReportMetadataDto, String>(metaPa.value(),
            300, ReportMetadataMessages.INSTANCE.gridValueHeader());
      valueConfig.setMenuDisabled(true);

      columns.add(valueConfig);

      /* create grid */
      grid = new Grid<ReportMetadataDto>(metadataStore, new ColumnModel<ReportMetadataDto>(columns));
      grid.getView().setAutoExpandColumn(valueConfig);
      grid.setSelectionModel(new GridSelectionModel<ReportMetadataDto>());
      grid.getView().setShowDirtyCells(true);

      GridEditing<ReportMetadataDto> editing = new GridInlineEditing<ReportMetadataDto>(grid);

      editing.addEditor(nameConfig, new TextField());
      editing.addEditor(valueConfig, new TextField());
   }

}
