package net.datenwerke.rs.core.client.reportmanager.hookers;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.Optional;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent.BeforeShowContextMenuHandler;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.loadconfig.SearchLoadConfig;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeLoaderDao;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.ReportSelectionCardConfig;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.RepositoryProviderConfig;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportSelectionRepositoryProviderHook;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;
import net.datenwerke.rs.search.client.search.SearchDao;
import net.datenwerke.rs.search.client.search.SearchUiService;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

public class ReportCatalogOnDemandRepositoryProvider implements ReportSelectionRepositoryProviderHook {

   public interface Config extends ReportSelectionDialog.RepositoryProviderConfig {
      public boolean includeVariants();

      boolean showCatalog();

      /**
       * Schedulable reports are those type of reports that may be directly scheduled.
       * E.g., dynamic list variants are schedulable, while dynamic list base reports
       * are not. For pixel-exact reports, both base report and variants are
       * schedulable.
       * 
       * @return true if it should be filtered on schedulable reports
       */
      boolean filterOnSchedulableReports();

      /**
       * Entries with unaccessible history path are those that cannot be opened by the
       * user by a history link because the user does not have sufficient rights for
       * this. Normally, these are not shown in search results. Sometimes it is
       * necessary, though, e.g. when the action clicking on the search result is
       * *not* opening the history link.
       * 
       * @return true if entries without a history path should be shown
       */
      boolean showEntriesWithUnaccessibleHistoryPath();

      /**
       * Reports that may be imported into a TeamSpace. These are base reports.
       * 
       * @return true if it should be filtered on teamspace-importable reports.
       */
      boolean filterOnTeamSpaceImportableReports();
   }

   private final ReportManagerUIService reportService;
   private final ReportManagerTreeLoaderDao treeLoaderDao;
   private final ToolbarService toolbarService;

   private final SearchDao searcher;
   private final SearchUiService searchService;

   private Optional<SearchFilterDto> filter = Optional.empty();

   @Inject
   public ReportCatalogOnDemandRepositoryProvider(ReportManagerUIService reportService,
         ReportManagerTreeLoaderDao treeLoaderDao, ToolbarService toolbarService, SearchDao searcher,
         SearchUiService searchService) {
      this.reportService = reportService;
      this.treeLoaderDao = treeLoaderDao;
      this.toolbarService = toolbarService;
      this.searcher = searcher;
      this.searchService = searchService;
   }

   @Override
   public void addCards(final ReportSelectionDialog dialog, Optional<ReportDto> selectedReport,
         RepositoryProviderConfig[] configs) {
      Config conf = getConfig(configs);
      if (null != conf && !conf.showCatalog())
         return;

      if (null != conf) {
         if (conf.filterOnSchedulableReports() && conf.filterOnTeamSpaceImportableReports())
            throw new IllegalStateException("Only one filter permitted");

         if (conf.filterOnSchedulableReports())
            installSchedulableReportsFilter();

         if (conf.filterOnTeamSpaceImportableReports())
            installTeamSpaceImportableReportsFilter();

         if (conf.showEntriesWithUnaccessibleHistoryPath())
            installShowEntriesWithUnaccessibleHistoryPath();
      } else
         filter = Optional.empty();

      addBasicCard(dialog, selectedReport, null != conf && conf.includeVariants());
   }

   private void installShowEntriesWithUnaccessibleHistoryPath() {
      if (!filter.isPresent()) {
         SearchFilterDtoDec filter = new SearchFilterDtoDec();
         filter.setLimit(25);
         this.filter = Optional.of(filter);
      }

      filter.get().setShowEntriesWithUnaccessibleHistoryPath(true);
   }

   protected <C extends ReportSelectionDialog.RepositoryProviderConfig> C getConfig(
         ReportSelectionDialog.RepositoryProviderConfig... configs) {
      if (null == configs)
         return null;
      for (ReportSelectionDialog.RepositoryProviderConfig c : configs) {
         if (c instanceof Config)
            return (C) c;
      }
      return null;
   }

   private void addBasicCard(final ReportSelectionDialog dialog, Optional<ReportDto> selectedReport,
         final boolean showVariants) {
      /* create store */
      final ListStore<ReportDto> store = new ListStore<ReportDto>(new DtoIdModelKeyProvider());

      if (selectedReport.isPresent())
         store.add(selectedReport.get());

      /* create grid */
      List<ColumnConfig<ReportDto, ?>> columns = reportService.getColumnConfigForReportGrid(showVariants, true);

      final Grid<ReportDto> grid = new Grid<ReportDto>(store, new ColumnModel<ReportDto>(columns));
      grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

      grid.setContextMenu(new DwMenu());
      grid.addBeforeShowContextMenuHandler(new BeforeShowContextMenuHandler() {
         @Override
         public void onBeforeShowContextMenu(BeforeShowContextMenuEvent event) {
            ReportDtoDec dto = (ReportDtoDec) grid.getSelectionModel().getSelectedItem();
            if (null == dto) {
               event.setCancelled(true);
               return;
            }

            Menu menu = dialog.getContextMenuFor(dto, ReportCatalogOnDemandRepositoryProvider.this, event);
            if (null == menu) {
               event.setCancelled(true);
               return;
            }
            grid.setContextMenu(menu);
         }
      });

      /* show context menu on doubleclick */
      grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
         @Override
         public void onCellClick(CellDoubleClickEvent event) {
            ReportDtoDec dto = (ReportDtoDec) grid.getStore().get(event.getRowIndex());
            if (null != dto)
               dialog.handleDoubleClick(dto, ReportCatalogOnDemandRepositoryProvider.this, event.getEvent());
         }
      });

      /* create wrapper */
      NorthSouthContainer wrapper = new DwNorthSouthContainer();
      wrapper.add(grid);

      ToolBar tb = new DwToolBar();
      wrapper.setNorthWidget(tb);

      SelectionHandler<SearchResultEntryDto> selectionHandler = new SelectionHandler<SearchResultEntryDto>() {

         @Override
         public void onSelection(SelectionEvent<SearchResultEntryDto> event) {
            SearchResultEntryDto selection = event.getSelectedItem();
            if (null != selection && null != selection.getResultObject()) {
               store.clear();
               if (selection.getResultObject() instanceof ReportDto)
                  store.add((ReportDto) selection.getResultObject());
               else if (selection.getResultObject() instanceof TsDiskReportReferenceDto) 
                  store.add(((TsDiskReportReferenceDto) selection.getResultObject()).getReport());
               else
                  throw new IllegalStateException("Cannot add report of type: " + selection.getResultObject());
               grid.getSelectionModel().select(0, false);
            }
         }
      };

      addSearchBar(tb, selectionHandler);
      toolbarService.addPlainToolbarItem(tb, BaseIcon.FOLDER_OPEN_O);
      tb.add(new FillToolItem());

      /* create card */
      dialog.addCard(ReportmanagerMessages.INSTANCE.catalog(), BaseIcon.HDD_O, wrapper,
            new ReportSelectionCardConfig() {

               @Override
               public void cardSelected() {
               }

               @Override
               public ReportContainerDto getSelectedReport() {
                  return (ReportDtoDec) grid.getSelectionModel().getSelectedItem();
               }

            });
   }

   protected void loadIn(ReportFolderDto folder, final Grid<ReportDto> grid, final ListStore<ReportDto> store,
         boolean showVariants) {
      grid.mask(BaseMessages.INSTANCE.loadingMsg());
      treeLoaderDao.getReportsInCatalog(folder, showVariants, new RsAsyncCallback<List<ReportDto>>() {
         @Override
         public void onSuccess(List<ReportDto> result) {
            store.removeFilters();
            store.clear();
            store.addAll(result);
            grid.unmask();
         }
      });
   }

   private void installSchedulableReportsFilter() {
      filter = Optional.of(searchService.createFilterFor(asList(
            // variants
            TableReportVariantDto.class, SaikuReportVariantDto.class,
            // base reports
            BirtReportDto.class, CrystalReportDto.class, GridEditorReportDto.class, JasperReportDto.class,
            JxlsReportDto.class, ScriptReportDto.class), false));
   }

   private void installTeamSpaceImportableReportsFilter() {
      filter = Optional.of(searchService.createFilterFor(asList(
            // base reports
            TableReportDto.class, SaikuReportDto.class, BirtReportDto.class, CrystalReportDto.class,
            GridEditorReportDto.class, JasperReportDto.class, JxlsReportDto.class, ScriptReportDto.class), true));
   }

   protected void addSearchBar(ToolBar toolbar, final SelectionHandler<SearchResultEntryDto> selectionHandler) {
      RpcProxy<SearchLoadConfig, SearchResultListDto> proxy = new RpcProxy<SearchLoadConfig, SearchResultListDto>() {
         @Override
         public void load(SearchLoadConfig loadConfig, AsyncCallback<SearchResultListDto> callback) {
            String query = loadConfig.getQuery();
            if (null != query && query.length() > 1)
               if (filter.isPresent())
                  searcher.find(searchService.enhanceQuery(query), filter.get(), callback);
               else
                  searcher.find(ReportDto.newPosoMapper(), searchService.enhanceQuery(query), callback);
         }
      };

      toolbarService.addSearchBar(toolbar, selectionHandler, proxy);
   }

}
