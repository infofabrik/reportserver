package net.datenwerke.rs.computedcolumns.client.computedcolumns.propertywidgets;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.event.StoreClearEvent;
import com.sencha.gxt.data.shared.event.StoreClearEvent.StoreClearHandler;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.cells.StringEllipseCell;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoListItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardDtoPasteProcessor;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.selection.SelectionPopup.ItemsSelectedCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCodeMirror;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.ui.helper.grid.ExtendedKeyNav;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.TableReportDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnSelector;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.ComputedColumnsDao;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.pa.ComputedColumnDtoPA;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.locale.ComputedColumnsMessages;
import net.datenwerke.rs.core.client.contexthelp.ContextHelpUiService;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ComputedColumnsView extends ReportExecutorMainPanelView {

   public static final String VIEW_ID = "computedcolumns";

   private final ClipboardUiService clipboardService;
   private final ToolbarService toolbarService;
   private final TableReportUtilityDao tableReportUtilityDao;
   private final ComputedColumnsDao dao;
   private final ContextHelpUiService contextHelperService;

   private TableReportDto report;

   private ListStore<ComputedColumnDto> store;

   private ToolBar toolbar;
   private Grid<ComputedColumnDto> grid;

   private DwNorthSouthContainer mainPanel;

   private static ComputedColumnDtoPA compColPa = GWT.create(ComputedColumnDtoPA.class);

   @Inject
   public ComputedColumnsView(ClipboardUiService clipboardService, ToolbarService toolbarService,
         ComputedColumnsDao dao, TableReportUtilityDao tableReportUtilityDao,
         ContextHelpUiService contextHelperService) {

      /* store objects */
      this.clipboardService = clipboardService;
      this.toolbarService = toolbarService;
      this.dao = dao;
      this.tableReportUtilityDao = tableReportUtilityDao;
      this.contextHelperService = contextHelperService;
   }

   @Override
   public String getViewId() {
      return VIEW_ID;
   }

   @Override
   public String getComponentHeader() {
      return ComputedColumnsMessages.INSTANCE.computedColumnsHeading();
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.CALCULATOR.toImageResource();
   }

   @Override
   public Component getViewComponent() {
      /* init */
      initStore();
      createToolbar();
      createTableGrid();

      /* main panel */
      mainPanel = new DwNorthSouthContainer();
      mainPanel.setNorthWidget(toolbar);
      mainPanel.setWidget(grid);

      initClipboard();

      return mainPanel;
   }

   protected void initClipboard() {
      clipboardService.registerCopyHandler(grid, () -> createClipboardItemFromSelected());

      clipboardService.registerPasteHandler(grid, new ClipboardDtoPasteProcessor(ComputedColumnDto.class) {
         @Override
         protected void doPaste(ClipboardDtoItem dtoItem) {
            handlePaste(dtoItem);
         }

         @Override
         protected void doPaste(ClipboardDtoListItem listItem) {
            handlePaste(listItem);
         }
      });
   }

   protected void handlePaste(ClipboardDtoListItem listItem) {
      listItem.getList()
         .stream()
         .filter(col -> col instanceof ComputedColumnDto)
         .map(col -> (ComputedColumnDto) col)
         .forEach(col -> importColumn(col));
   }

   protected void handlePaste(ClipboardDtoItem dtoItem) {
      Dto dto = dtoItem.getDto();
      if (dto instanceof ComputedColumnDto) {
         ComputedColumnDto col = (ComputedColumnDto) dto;
         importColumn(col);
      }
   }

   protected ClipboardItem createClipboardItemFromSelected() {
      List<ComputedColumnDto> list = grid.getSelectionModel().getSelectedItems();
      if (null != list && list.size() > 1)
         return new ClipboardDtoListItem(list, ComputedColumnDto.class);

      ComputedColumnDto col = grid.getSelectionModel().getSelectedItem();
      if (null != col)
         return new ClipboardDtoItem(col);
      return null;
   }

   protected void importColumn(final ComputedColumnDto toImport) {
      /* check if column is in store */
      String name = toImport.getName();
      if (null == name)
         name = "";

      boolean foundConflicting = false;
      for (final ComputedColumnDto col : store.getAll()) {
         if (name.equals(col.getName())) {
            /* ask if we should update */
            ConfirmMessageBox cmb = new DwConfirmMessageBox(
                  ComputedColumnsMessages.INSTANCE.importColumnConflictTitle(),
                  ComputedColumnsMessages.INSTANCE.importColumnConflictMsg(name));
            cmb.addDialogHideHandler(new DialogHideHandler() {
               @Override
               public void onDialogHide(DialogHideEvent event) {
                  if (event.getHideButton() == PredefinedButton.YES) {
                     col.setDescription(toImport.getDescription());
                     col.setExpression(toImport.getExpression());
                     pasteColumn(col, false);
                  } else
                     pasteColumn(cloneColumn(toImport), true);
               }
            });
            cmb.show();

            foundConflicting = true;
            break;
         }
      }

      if (!foundConflicting)
         pasteColumn(cloneColumn(toImport), true);
   }

   private void pasteColumn(final ComputedColumnDto col, final boolean add) {
      mainPanel.mask(ComputedColumnsMessages.INSTANCE.validateData());

      dao.getColumnType(report, col, new RsAsyncCallback<Integer>() {
         public void onSuccess(Integer result) {
            mainPanel.unmask();

            col.setType(result);

            if (add)
               store.add(col);
            else
               store.update(col);

            col.fireObjectChangedEvent();
            report.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.columns());

            /* focus grid */
            grid.focus();
         }

         public void onFailure(Throwable caught) {
            super.onFailure(caught);
            mainPanel.unmask();
         };

      });
   }

   private void createTableGrid() {
      ColumnConfig<ComputedColumnDto, String> name = new ColumnConfig<ComputedColumnDto, String>(compColPa.name(), 150,
            BaseMessages.INSTANCE.propertyName());
      name.setMenuDisabled(true);

      ColumnConfig<ComputedColumnDto, String> description = new ColumnConfig<ComputedColumnDto, String>(
            compColPa.description(), 300, BaseMessages.INSTANCE.propertyDescription());
      description.setMenuDisabled(true);

      ColumnConfig<ComputedColumnDto, String> expression = new ColumnConfig<ComputedColumnDto, String>(
            compColPa.expression(), 300, ComputedColumnsMessages.INSTANCE.expressionLabel());
      expression.setCell(new StringEllipseCell(40));
      expression.setMenuDisabled(true);

      List<ColumnConfig<ComputedColumnDto, ?>> cols = new ArrayList<ColumnConfig<ComputedColumnDto, ?>>();
      cols.add(name);
      cols.add(description);
      cols.add(expression);

      /* create grid */
      grid = new Grid<ComputedColumnDto>(store, new ColumnModel<ComputedColumnDto>(cols));
      grid.getView().setAutoExpandColumn(description);

      /* selection */
      grid.getSelectionModel().setSelectionMode(SelectionMode.MULTI);
      new ExtendedKeyNav(grid) {
         @Override
         protected void onSelectAll() {
            grid.getSelectionModel().selectAll();
         };

         @Override
         public void onDelete(NativeEvent evt) {
            removeSelected();
         }
      };

      grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
         @Override
         public void onCellClick(CellDoubleClickEvent event) {
            ComputedColumnDto column = grid.getSelectionModel().getSelectedItem();
            if (null != column)
               displayEditColumnDialog(column, false);
         }
      });

      Menu ctxMen = new DwMenu();

      final MenuItem copyItem = new DwMenuItem(BaseMessages.INSTANCE.copy());
      copyItem.addSelectionHandler(event -> clipboardService.setClipboardItem(createClipboardItemFromSelected()));
      ctxMen.add(copyItem);

      final MenuItem pasteItem = new DwMenuItem(BaseMessages.INSTANCE.paste());
      pasteItem.addSelectionHandler(event -> {
         ClipboardItem clipboardItem = clipboardService.getClipboardItem();

         if (null == clipboardItem || clipboardItem.getType() != ComputedColumnDto.class)
            return;

         if (clipboardItem instanceof ClipboardDtoListItem)
            handlePaste((ClipboardDtoListItem) clipboardItem);
         else if (clipboardItem instanceof ClipboardDtoItem)
            handlePaste((ClipboardDtoItem) clipboardItem);
      });
      ctxMen.add(pasteItem);

      grid.setContextMenu(ctxMen);

      grid.addBeforeShowContextMenuHandler(event -> {
         /* clipboard */
         pasteItem.setEnabled(true);
         ClipboardItem clipboardItem = clipboardService.getClipboardItem();
         if (null == clipboardItem || clipboardItem.getType() != ComputedColumnDto.class)
            pasteItem.setEnabled(false);

         copyItem.setEnabled(true);
         List<ComputedColumnDto> list = grid.getSelectionModel().getSelectedItems();
         ColumnDto selected = grid.getSelectionModel().getSelectedItem();
         if ((null == list || list.isEmpty()) && null == selected)
            copyItem.setEnabled(false);
      });

   }

   private void createToolbar() {
      toolbar = new DwToolBar();

      /* add column */
      DwTextButton addBlockButton = toolbarService.createSmallButtonLeft(ComputedColumnsMessages.INSTANCE.addColumn(),
            BaseIcon.CALCULATOR);
      addBlockButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            addColumn();
         }
      });
      toolbar.add(addBlockButton);

      /* add column */
      DwTextButton duplicateBlockButton = toolbarService
            .createSmallButtonLeft(ComputedColumnsMessages.INSTANCE.duplicateColumnLabel(), BaseIcon.CLONE);
      duplicateBlockButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            duplicateSelectedColumn();
         }
      });
      toolbar.add(duplicateBlockButton);

      toolbar.add(new SeparatorToolItem());

      /* remove */
      DwSplitButton deleteColumnButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
      deleteColumnButton.setIcon(BaseIcon.DELETE);
      deleteColumnButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            removeSelected();
         }
      });
      Menu deleteMenu = new DwMenu();
      deleteColumnButton.setMenu(deleteMenu);
      toolbar.add(deleteColumnButton);

      MenuItem deleteAllColumnsItem = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
      deleteMenu.add(deleteAllColumnsItem);
      deleteAllColumnsItem.addSelectionHandler(selectionEvent ->  {
         ConfirmMessageBox cmb = new DwConfirmMessageBox(ComputedColumnsMessages.INSTANCE.removeAllConfirmHeading(),
               ComputedColumnsMessages.INSTANCE.removeAllConfirmText());
         cmb.addDialogHideHandler(dialogHideEvent -> {
            if (dialogHideEvent.getHideButton() == PredefinedButton.YES)
               removeAll();
         });
         cmb.show();
      });

      /* right */
      toolbar.add(new FillToolItem());

      /* info */
      if (contextHelperService.isEnabled()) {
         ContextHelpInfo contextHelpInfo = new ContextHelpInfo("dynamiclist/computedcolumns");
         DwTextButton btn = contextHelperService.createDwTextButton(contextHelpInfo);
         toolbar.add(btn);
      }

   }

   protected void initStore() {
      store = new ListStore<ComputedColumnDto>(new DtoIdModelKeyProvider());
      store.setAutoCommit(true);
      store.addSortInfo(new StoreSortInfo<ComputedColumnDto>(compColPa.name(), SortDir.ASC));

      /* add columns to store */
      report.getAdditionalColumns()
         .stream()
         .filter(column -> column instanceof ComputedColumnDto)
         .map(column -> (ComputedColumnDto) column)
         .forEach(store::add);

      store.addStoreAddHandler(event -> {
         List<AdditionalColumnSpecDto> cols = new ArrayList(report.getAdditionalColumns());
         cols.addAll(event.getItems());
         report.setAdditionalColumns(cols);
      });

      store.addStoreClearHandler(new StoreClearHandler<ComputedColumnDto>() {
         @Override
         public void onClear(StoreClearEvent<ComputedColumnDto> event) {
            /* new additional columns */
            final List<AdditionalColumnSpecDto> additional = new ArrayList<>(report.getAdditionalColumns());
            additional.removeAll(report.getAdditionalColumns()
                  .stream()
                  .filter(col -> col instanceof ComputedColumnDto)
                  .collect(toList()));
            report.setAdditionalColumns(additional);

            /* new columns */
            final List<ColumnDto> columns = new ArrayList<>(report.getColumns());
            columns.removeAll(report.getColumns()
                  .stream()
                  .filter(col -> col instanceof ColumnReferenceDto)
                  .map(col -> (ColumnReferenceDto) col)
                  .filter(col -> col.getReference() instanceof ComputedColumnDto)
                  .collect(toList()));
            report.setColumns(columns);

            report.fireObjectChangedEvent();
         }
      });

      store.addStoreRemoveHandler(event -> {
         AdditionalColumnSpecDto toRemoveSpec = event.getItem();
         if (null == toRemoveSpec)
            return;

         List<AdditionalColumnSpecDto> cols = report.getAdditionalColumns();
         cols.remove(toRemoveSpec);
         report.setAdditionalColumns(cols);

         final List<ColumnDto> columns = new ArrayList<>(report.getColumns());
         columns.removeAll(report.getColumns()
               .stream()
               .filter(col -> col instanceof ColumnReferenceDto)
               .map(col -> (ColumnReferenceDto) col)
               .filter(col -> col.getReference() instanceof ComputedColumnDto
                     && toRemoveSpec.equals(col.getReference()))
               .collect(toList()));
         report.setColumns(columns);
         report.fireObjectChangedEvent();
      });
   }

   protected void removeSelected() {
      final List<ComputedColumnDto> items = grid.getSelectionModel().getSelectedItems();
      if (null != items)
         items.forEach(store::remove);
   }

   protected void removeAll() {
      store.clear();
   }

   protected void addColumn() {
      ComputedColumnDto column = new ComputedColumnDto();
      column.setName(getSafeColumnName());
      column.setDescription(ComputedColumnsMessages.INSTANCE.newDescription());
      store.add(column);
      displayEditColumnDialog(column, true);
   }

   protected void duplicateSelectedColumn() {
      ComputedColumnDto column = grid.getSelectionModel().getSelectedItem();
      if (null != column) {
         ComputedColumnDto clone = cloneColumn(column);
         store.add(clone);
      }
   }

   private ComputedColumnDto cloneColumn(ComputedColumnDto column) {
      ComputedColumnDto clone = new ComputedColumnDto();

      clone.setName(getSafeColumnName(column.getName()));
      clone.setDescription(column.getDescription());
      clone.setExpression(column.getExpression());

      return clone;
   }

   private String getSafeColumnName() {
      return getSafeColumnName(null);
   }

   private String getSafeColumnName(String name) {
      String bname = null == name ? ComputedColumnsMessages.INSTANCE.newName() : name;
      name = bname;
      int i = 1;

      boolean nameOk = false;
      while (!nameOk) {
         boolean nameFound = false;
         for (ComputedColumnDto col : store.getAll()) {
            if (name.equals(col.getName())) {
               name = bname + (i++);
               nameFound = true;
               break;
            }
         }
         if (!nameFound)
            nameOk = true;
      }

      return name;
   }

   protected void displayEditColumnDialog(final ComputedColumnDto selectedColumn, final boolean removeOnCancel) {
      final DwWindow dialog = new DwWindow();
      dialog.setSize(480, 510);
      dialog.setHeading(ComputedColumnsMessages.INSTANCE.editColumnHeading());
      dialog.setHeaderIcon(BaseIcon.CALCULATOR);
      dialog.setOnEsc(false);
      dialog.setModal(true);
      dialog.setBodyBorder(true);
      dialog.setClosable(false);

      final SimpleForm form = SimpleForm.getInlineInstance();
      dialog.add(form, new MarginData(10));

      final ComputedColumnDto tmpColumn = new ComputedColumnDto();
      tmpColumn.setName(selectedColumn.getName());
      tmpColumn.setExpression(selectedColumn.getExpression());
      tmpColumn.setDescription(selectedColumn.getDescription());

      form.addField(String.class, ColumnDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName());

      form.addField(String.class, ComputedColumnDtoPA.INSTANCE.description(),
            BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl() {
               @Override
               public int getHeight() {
                  return 100;
               }
            });

      form.addField(String.class, ComputedColumnDtoPA.INSTANCE.expression(),
            ComputedColumnsMessages.INSTANCE.expressionLabel(), new SFFCCodeMirror() {

               @Override
               public int getWidth() {
                  return -1;
               }

               @Override
               public int getHeight() {
                  return 200;
               }

               @Override
               public String getLanguage() {
                  return "text/x-sql";
               }

               @Override
               public boolean lineNumbersVisible() {
                  return true;
               }

               @Override
               public ToolBarEnhancer getEnhancer() {
                  return new ToolBarEnhancer() {
                     @Override
                     public void enhance(ToolBar toolbar, final CodeMirrorPanel codeMirrorPanel) {
                        DwTextButton selectColBtn = toolbarService.createSmallButtonLeft(
                              ComputedColumnsMessages.INSTANCE.selectColBtnLabel(), BaseIcon.TABLE_ADD);
                        toolbar.add(selectColBtn);

                        selectColBtn.addSelectHandler(new SelectHandler() {

                           private ColumnSelector columnSelector;

                           @Override
                           public void onSelect(SelectEvent event) {
                              final TextArea area = codeMirrorPanel.getTextArea();

                              if (null == columnSelector) {
                                 columnSelector = new ColumnSelector(tableReportUtilityDao, report,
                                       getExecuteReportToken());
                                 columnSelector.displayAdditionalColumns(false);
                                 columnSelector.loadData();
                                 columnSelector.setSelectionMode(
                                       net.datenwerke.gxtdto.client.forms.selection.SelectionMode.MULTI_PERMIT_DOUBLES);
                                 columnSelector.setSelectionCallback(new ItemsSelectedCallback<ColumnDto>() {
                                    @Override
                                    public void itemsSelected(List<ColumnDto> items) {
                                       if (null == items)
                                          return;

                                       boolean first = true;
                                       String text = "";
                                       for (ColumnDto col : items) {
                                          if (first)
                                             first = false;
                                          else
                                             text += ", ";
                                          text += col.getName();
                                       }

                                       // append Text
                                       String value = area.getValue();
                                       value = null == value ? "" : value;
                                       value += text;
                                       area.setValue(value, true);
                                    }
                                 });
                              } else {
                                 columnSelector.setSelectedItems(new ArrayList<ColumnDto>());
                              }

                              columnSelector.show();
                           }
                        });
                     }

                     @Override
                     public boolean allowPopup() {
                        return true;
                     }

                  };
               }
            }, new SFFCStringValidator() {
               @Override
               public Validator<String> getValidator() {
                  return new Validator<String>() {
                     @Override
                     public List<EditorError> validate(Editor<String> editor, String value) {
                        if ("".equals(value)) {
                           List<EditorError> list = new ArrayList<EditorError>();
                           list.add(new DefaultEditorError(editor,
                                 ComputedColumnsMessages.INSTANCE.expressionNotEmpty(), value));
                           return list;
                        }
                        return null;
                     }
                  };
               }

               @Override
               public void setAllowBlank(boolean allowBlank) {
                  throw new RuntimeException("method not implemented: setAllowBlank");
               }
            });

      form.bind(tmpColumn);

      DwTextButton cancelButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
      cancelButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (removeOnCancel)
               store.remove(selectedColumn);
            dialog.hide();
            mainPanel.unmask();

            /* focus grid */
            grid.focus();
         }
      });
      dialog.addButton(cancelButton);

      DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.submit());
      okButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (form.isValid()) {
               mainPanel.mask(ComputedColumnsMessages.INSTANCE.validateData());

               dao.getColumnType(report, selectedColumn, tmpColumn, new RsAsyncCallback<Integer>() {
                  public void onSuccess(Integer result) {
                     mainPanel.unmask();

                     selectedColumn.setType(result);
                     selectedColumn.setName(tmpColumn.getName());
                     selectedColumn.setExpression(tmpColumn.getExpression());
                     selectedColumn.setDescription(tmpColumn.getDescription());

                     store.update(selectedColumn);

                     selectedColumn.fireObjectChangedEvent();
                     report.fireObjectChangedEvent(TableReportDtoPA.INSTANCE.columns());

                     dialog.hide();

                     /* focus grid */
                     grid.focus();
                  }

                  public void onFailure(Throwable caught) {
                     super.onFailure(caught);
                     mainPanel.unmask();
                  };

               });
            }
         }
      });
      dialog.addButton(okButton);

      dialog.show();
   }

   public void setReport(TableReportDto report) {
      this.report = report;
   }

}
