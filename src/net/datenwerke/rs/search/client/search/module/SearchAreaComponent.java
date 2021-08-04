package net.datenwerke.rs.search.client.search.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwAccordionLayout;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.objectinformation.ObjectPreviewTabPanel;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwPagingToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.rs.search.client.search.SearchDao;
import net.datenwerke.rs.search.client.search.SearchUiService;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec;
import net.datenwerke.rs.search.client.search.dto.pa.SearchResultEntryDtoPA;
import net.datenwerke.rs.search.client.search.locale.SearchMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SearchAreaComponent extends DwContentPanel {

   private static final double LISTPANEL_DEFAULT_SIZE = 0.4f;

   @CssClassConstant
   public static final String CSS_NAME = "rs-search-view";

   @CssClassConstant
   public static final String CSS_NAME_FILTER = "rs-search-view-filter";

   @CssClassConstant
   public static final String CSS_NAME_RESULTS = "rs-search-view-results";

   @CssClassConstant
   public static final String CSS_NAME_PREVIEW = "rs-search-view-preview";

   @CssClassConstant
   public static final String CSS_NAME_TYPE = "rs-search-view-type";

   @CssClassConstant
   public static final String CSS_NAME_TAG = "rs-search-view-tag";

   @CssClassConstant
   public static final String CSS_NAME_TAG_SELECTED = "rs-search-view-tag-s";

   private final Provider<ObjectPreviewTabPanel> objectInformationPanelProvider;
   private final ToolbarService toolbarService;
   private final SearchDao searchDao;
   private final FormatUiHelper formatUiHelper;

   private ToolBar topToolbar;
   private DwContentPanel filterPanel;
   private DwContentPanel listPanel;
   private DwContentPanel previewPanel;

   private TextField searchField;
   private Set<SearchResultTagDto> selectedTags = new HashSet<SearchResultTagDto>();

   private VerticalLayoutContainer mainContainer;

   private SearchUiService searchService;

   private PagingLoader<PagingLoadConfig, PagingLoadResult<SearchResultEntryDto>> loader;

   private VerticalLayoutContainer filterPanelContainer;
   private VerticalLayoutContainer previewPanelContainer;

   @Inject
   public SearchAreaComponent(
         Provider<ObjectPreviewTabPanel> objectInformationPanelProvider, 
         SearchDao searchDao,
         SearchUiService searchService, 
         ToolbarService toolbarService, 
         FormatUiHelper formatUiHelper
         ) {

      this.objectInformationPanelProvider = objectInformationPanelProvider;
      this.searchDao = searchDao;
      this.searchService = searchService;
      this.toolbarService = toolbarService;
      this.formatUiHelper = formatUiHelper;

      /* init */
      initUi();
   }

   protected void initUi() {
      addStyleName(CSS_NAME);

      setHeaderVisible(false);

      mainContainer = new VerticalLayoutContainer();
      add(mainContainer);

      /* init component parts */
      initTopToolbar();
      initFilterPanel();
      initListPanel();
      initPreviewPanel();

      /* init layout */
      BorderLayoutContainer borderContainer = new DwBorderContainer();
      mainContainer.add(borderContainer, new VerticalLayoutData(1, 1));

      BorderLayoutData west = new BorderLayoutData(250);
      west.setCollapsible(true);
      west.setSplit(true);

      BorderLayoutData center = new BorderLayoutData(LISTPANEL_DEFAULT_SIZE);
      center.setMaxSize(Integer.MAX_VALUE);
      center.setSplit(true);

      /* add panels */
      borderContainer.setWestWidget(filterPanel, west);

      BorderLayoutContainer centerContainer = new BorderLayoutContainer();
      borderContainer.setCenterWidget(centerContainer);

      centerContainer.setWestWidget(listPanel, center);
      centerContainer.setCenterWidget(previewPanel);
   }

   private void initPreviewPanel() {
      previewPanel = new DwContentPanel();
      previewPanel.addStyleName(CSS_NAME_PREVIEW);
      previewPanel.setHeaderVisible(false);

      previewPanelContainer = new VerticalLayoutContainer();
      previewPanel.add(previewPanelContainer);

      previewPanelContainer.add(new DwToolBar(), new VerticalLayoutData(1, 29));
      previewPanelContainer.add(new SimpleContainer(), new VerticalLayoutData(1, 1));
      previewPanelContainer.add(new DwToolBar(), new VerticalLayoutData(1, 40));
   }

   private void initFilterPanel() {
      filterPanel = new DwContentPanel();
      filterPanel.addStyleName(CSS_NAME_FILTER);
      filterPanel.setHeaderVisible(false);

      filterPanelContainer = new VerticalLayoutContainer();
      filterPanel.add(filterPanelContainer);

      filterPanelContainer.add(new DwToolBar(), new VerticalLayoutData(1, 29));
      filterPanelContainer.add(new SimpleContainer(), new VerticalLayoutData(1, 1));
      filterPanelContainer.add(new DwToolBar(), new VerticalLayoutData(1, 40));
   }

   private void initListPanel() {
      listPanel = new DwContentPanel();
      listPanel.addStyleName(CSS_NAME_RESULTS);
      listPanel.setHeaderVisible(false);

      /* create store */
      final ListStore<SearchResultEntryDto> store = renewStore();
      loader = renewLoader();
      loader.addLoadHandler(
            new LoadResultListStoreBinding<PagingLoadConfig, SearchResultEntryDto, PagingLoadResult<SearchResultEntryDto>>(
                  store));
      loader.addLoadHandler(new LoadHandler<PagingLoadConfig, PagingLoadResult<SearchResultEntryDto>>() {
         @Override
         public void onLoad(LoadEvent<PagingLoadConfig, PagingLoadResult<SearchResultEntryDto>> event) {
            listPanel.unmask();
         }
      });

      /* create columns */
      List<ColumnConfig<SearchResultEntryDto, ?>> columns = new ArrayList<ColumnConfig<SearchResultEntryDto, ?>>();

      ColumnConfig<SearchResultEntryDto, SearchResultEntryDto> iconColumn = new ColumnConfig<SearchResultEntryDto, SearchResultEntryDto>(
            new IdentityValueProvider<SearchResultEntryDto>(), 30);
      iconColumn.setCell(new AbstractCell<SearchResultEntryDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, SearchResultEntryDto model,
               SafeHtmlBuilder sb) {
            if (null != model.getIconSmall())
               sb.append(BaseIcon.from(model.getIconSmall()).toSafeHtml());
         }
      });
      columns.add(iconColumn);

      ColumnConfig<SearchResultEntryDto, SearchResultEntryDto> idColumn = new ColumnConfig<SearchResultEntryDto, SearchResultEntryDto>(
            new IdentityValueProvider<SearchResultEntryDto>("__id"), 90, BaseMessages.INSTANCE.id());
      idColumn.setMenuDisabled(true);
      idColumn.setCell(new AbstractCell<SearchResultEntryDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, SearchResultEntryDto model,
               SafeHtmlBuilder sb) {
            sb.append(model.getObjectId());
         }
      });
      columns.add(idColumn);

      ColumnConfig<SearchResultEntryDto, SearchResultEntryDto> entryColumn = new ColumnConfig<SearchResultEntryDto, SearchResultEntryDto>(
            new IdentityValueProvider<SearchResultEntryDto>("__entry"), 200,
            SearchMessages.INSTANCE.entryColumnLabel());
      columns.add(entryColumn);
      entryColumn.setCell(new AbstractCell<SearchResultEntryDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, SearchResultEntryDto value,
               SafeHtmlBuilder sb) {
            String title = null == value.getTitle() ? BaseMessages.INSTANCE.unknown()
                  : Format.ellipse(value.getTitle(), 100);
            String description = null == value.getDescription() || "".equals(value.getDescription())
                  ? BaseMessages.INSTANCE.unknown()
                  : Format.ellipse(value.getDescription(), 1000);
            sb.appendHtmlConstant("<div class=\"rs-list-name-desc\"><div class=\"title\">" + title
                  + "</div><div class=\"description\">" + description + "</div></div>");
         }
      });
      entryColumn.setMenuDisabled(true);
      entryColumn.setSortable(false);

      ColumnConfig<SearchResultEntryDto, SearchResultEntryDto> ccLastModified = new ColumnConfig<SearchResultEntryDto, SearchResultEntryDto>(
            new IdentityValueProvider<SearchResultEntryDto>("__last"), 150, BaseMessages.INSTANCE.lastModified());
      ccLastModified.setMenuDisabled(true);
      ccLastModified.setCell(new AbstractCell<SearchResultEntryDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, SearchResultEntryDto model,
               SafeHtmlBuilder sb) {
            Date lastUpdated = model.getLastModified();
            if (null == lastUpdated) {
               sb.appendEscaped(BaseMessages.INSTANCE.unknown());
            } else {
               try {
                  sb.appendEscaped(formatUiHelper.getShortDateTimeFormat().format(lastUpdated));
               } catch (IllegalArgumentException e) {
                  sb.appendEscaped(BaseMessages.INSTANCE.unknown());
               }
            }
         }
      });
      columns.add(ccLastModified);

      /* create grid */
      final Grid<SearchResultEntryDto> grid = new Grid<SearchResultEntryDto>(store,
            new ColumnModel<SearchResultEntryDto>(columns));
      grid.getView().setAutoExpandColumn(entryColumn);
      grid.getView().setEmptyText(SearchMessages.INSTANCE.noResultsFound());
      grid.getView().setAutoExpandMax(Integer.MAX_VALUE);
      grid.addRowDoubleClickHandler(event -> {
         final SearchResultEntryDto item = grid.getStore().get(event.getRowIndex());
         if (null != item)
            opened(item);
      });
      grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      grid.getSelectionModel().addSelectionChangedHandler(event -> {
         if (null != event.getSelection() && !event.getSelection().isEmpty())
            selected(event.getSelection().get(0));
      });

      /* toolbar */
      final PagingToolBar toolBar = new DwPagingToolBar(25);
      toolBar.bind(loader);

      /* add grid to list panel */
      DwNorthSouthContainer wrapper = new DwNorthSouthContainer();
      wrapper.setWidget(grid);
      wrapper.setSouthWidget(toolBar);

      listPanel.setWidget(wrapper);
      listPanel.forceLayout();
   }

   private void initTopToolbar() {
      topToolbar = new DwToolBar();
      mainContainer.add(topToolbar, new VerticalLayoutData(1, -1));

      topToolbar.add(toolbarService.createPlainToolbarItem(BaseIcon.SEARCH));

      searchField = new TextField();
      searchField.setWidth(400);
      searchField.addKeyDownHandler(event -> {
         if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
            runSearch(searchField.getCurrentValue());
      });
      topToolbar.add(searchField);

      DwTextButton searchBtn = new DwTextButton(SearchMessages.INSTANCE.searchBtnLabel());
      searchBtn.addSelectHandler(event -> runSearch(searchField.getValue()));
      topToolbar.add(searchBtn);
   }

   public void runSearch(String searchStr) {
      if (null == searchStr || searchStr.length() <= 1)
         return;

      selectedTags.clear();
      doRunSearch(searchStr);
   }

   public void doRunSearch() {
      doRunSearch(searchField.getValue());
   }

   public void doRunSearch(String searchStr) {
      searchField.setValue(searchStr);

      listPanel.mask(BaseMessages.INSTANCE.loadingMsg());

      loader.load(0, 25);
   }

   protected void selected(SearchResultEntryDto searchResultEntryDto) {
      if (null == searchResultEntryDto.getResultObject())
         return;

      previewPanelContainer.clear();

      ObjectPreviewTabPanel infoPanel = objectInformationPanelProvider.get();
      infoPanel.setBottomTabs(true);
      infoPanel.setBodyBorder(false);
      infoPanel.setBorders(false);
      infoPanel.displayInformationOn(((SearchResultEntryDtoDec) searchResultEntryDto).getResultObject());

      previewPanelContainer.add(new DwToolBar(), new VerticalLayoutData(1, 29));
      previewPanelContainer.add(infoPanel, new VerticalLayoutData(1, 1));

      previewPanel.forceLayout();
   }

   private void updateFilterPanel(SearchResultListDto result) {
      /* only update if no filters are set */
      if (!selectedTags.isEmpty())
         return;

      filterPanelContainer.clear();

      DwAccordionLayout wrapper = new DwAccordionLayout();
      wrapper.setExpandMode(ExpandMode.MULTI);
      filterPanelContainer.setScrollMode(ScrollMode.AUTOY);
      filterPanelContainer.add(wrapper, new VerticalLayoutData(1, 1));

      for (SearchResultTagTypeDto type : ((SearchResultListDtoDec) result).getTagTypes()) {
         DwContentPanel panel = new DwContentPanel();
         panel.setHeading(type.getDisplay());

         final DwFlowContainer typeContainer = new DwFlowContainer();
         panel.setWidget(typeContainer);
         
         result.getTags()
            .stream()
            .filter(tag -> tag.getType().getType().equals(type.getType()))
            .forEach(tag -> {
               final Label tagComp = new Label(tag.getDisplay());
               tagComp.addStyleName(CSS_NAME_TAG);
               typeContainer.add(tagComp);

               tagComp.addDomHandler(event -> {
                  if (!selectedTags.contains(tag)) {
                     selectedTags.add(tag);
                     tagComp.addStyleName(CSS_NAME_TAG_SELECTED);
                  } else {
                     selectedTags.remove(tag);
                     tagComp.removeStyleName(CSS_NAME_TAG_SELECTED);
                  }
                  doRunSearch();
               }, ClickEvent.getType());
            });

         wrapper.add(panel);
         panel.expand();
      }
      if (wrapper.getWidgetCount() > 0)
         wrapper.setActiveWidget(wrapper.getWidget(0));

      filterPanel.forceLayout();
   }

   protected void opened(SearchResultEntryDto item) {
      item.getLinks()
         .stream()
         .findAny()
         .ifPresent(this::fire);
   }

   protected void fire(HistoryLinkDto link) {
      if (link.getHistoryToken().equals(History.getToken()))
         History.fireCurrentHistoryState();
      else
         History.newItem(link.getHistoryToken(), true);
   }

   protected PagingLoader<PagingLoadConfig, PagingLoadResult<SearchResultEntryDto>> renewLoader() {
      RpcProxy<PagingLoadConfig, PagingLoadResult<SearchResultEntryDto>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<SearchResultEntryDto>>() {

         @Override
         public void load(PagingLoadConfig loadConfig,
               final AsyncCallback<PagingLoadResult<SearchResultEntryDto>> callback) {
            SearchFilterDto filter = new SearchFilterDtoDec();
            filter.setLimit(((PagingLoadConfig) loadConfig).getLimit());
            filter.setOffset(((PagingLoadConfig) loadConfig).getOffset());
            filter.setTags(selectedTags);

            searchDao.find(searchService.enhanceQuery(searchField.getValue()), filter,
                  new RsAsyncCallback<SearchResultListDto>() {
                     @Override
                     public void onSuccess(SearchResultListDto result) {
                        callback.onSuccess(result);
                        updateFilterPanel(result);
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        callback.onFailure(caught);
                     }
                  });
         }
      };

      final PagingLoader<PagingLoadConfig, PagingLoadResult<SearchResultEntryDto>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<SearchResultEntryDto>>(
            proxy);
      loader.setRemoteSort(true);

      return loader;
   }

   protected ListStore<SearchResultEntryDto> renewStore() {
      ListStore<SearchResultEntryDto> store = new ListStore<>(
            SearchResultEntryDtoPA.INSTANCE.dtoId());
      return store;
   }

}
