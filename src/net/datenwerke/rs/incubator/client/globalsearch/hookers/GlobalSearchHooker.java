package net.datenwerke.rs.incubator.client.globalsearch.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.XTemplates.FormatterFactoryMethod;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.DataReader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.BlurEvent.BlurHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.client.homepage.hooks.HomepageHeaderContentHook;
import net.datenwerke.gf.client.homepage.ui.DwMainViewportTopBarElement;
import net.datenwerke.gf.client.homepage.ui.DwMainViewportTopBarWidget;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwComboBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.utils.labelprovider.DisplayTitleLabelProvider;
import net.datenwerke.gxtdto.client.utils.loadconfig.SearchLoadConfig;
import net.datenwerke.gxtdto.client.utils.loadconfig.SearchLoadConfigBean;
import net.datenwerke.gxtdto.client.xtemplates.NullSafeFormatter;
import net.datenwerke.rs.incubator.client.globalsearch.locale.GlobalSearchMessages;
import net.datenwerke.rs.search.client.search.SearchDao;
import net.datenwerke.rs.search.client.search.SearchUiService;
import net.datenwerke.rs.search.client.search.dto.EmptySearchResultDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.pa.SearchResultEntryDtoPA;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class GlobalSearchHooker implements HomepageHeaderContentHook {

	@CssClassConstant
	public static final String CSS_NAME_COMBO = "rs-search-box";

	@CssClassConstant
	public static final String CSS_NAME_ICON = "rs-search-box-icon";
	
	private NativeEvent currentNativeEvent;

	@FormatterFactories(@FormatterFactory(factory=NullSafeFormatter.class,methods=@FormatterFactoryMethod(name="nullsafe")))
	interface GlobalSearchTemplates extends XTemplates {
		@XTemplate("<div class=\"rs-search-li\">" +
				"<div class=\"rs-search-li-icon\"><i class=\"{icon}\"></i></div>" +
				"<div class=\"rs-search-li-info\">" +
				"<div class=\"rs-search-li-title\">{entry.title:nullsafe}" +
				"<tpl for=\"entry.links\">" +
				"<tpl if=\"# == 1\">" +
				" ({historyLinkBuilderName})" +
				"</tpl>" +
				"</tpl>" +
				"</div>" +
				"</div>" +
				"<div class=\"rs-search-li-done\"></div>" +
				"</div>")
		public SafeHtml render(SearchResultEntryDto entry, String icon); 
	}

	private final SearchDao searcher;
	private final SearchUiService searchService;
	private final HistoryUiService historyService;

	private HTML icon;
	
	@Inject
	public GlobalSearchHooker(
			SearchDao searcher,
			HistoryUiService historyService,
			SearchUiService searchService
			){

		/* store objects */
		this.searcher = searcher;
		this.historyService = historyService;
		this.searchService = searchService;
	}



	@Override
	public DwMainViewportTopBarElement homepageHeaderContentHook_addTopRight(final HBoxLayoutContainer container) {
		RpcProxy<SearchLoadConfig, SearchResultListDto> proxy = new RpcProxy<SearchLoadConfig, SearchResultListDto>() {
			@Override
			public void load(SearchLoadConfig loadConfig, AsyncCallback<SearchResultListDto> callback) {
				String query = loadConfig.getQuery();
				if(null != query && query.length() > 1){
					query = searchService.enhanceQuery(query);
					searcher.find(query, callback);
				}
			}
		};

		DataReader<PagingLoadResult<SearchResultEntryDto>, SearchResultListDto> reader = new DataReader<PagingLoadResult<SearchResultEntryDto>, SearchResultListDto>() {
			@Override
			public PagingLoadResult<SearchResultEntryDto> read(Object loadConfig, SearchResultListDto data) {
				if( data.getTotalLength() > 0)
					return new PagingLoadResultBean<SearchResultEntryDto>(data.getData(), data.getTotalLength(), data.getOffset());
				List<SearchResultEntryDto> emptyList = new ArrayList<SearchResultEntryDto>();
				emptyList.add(new EmptySearchResultDto(GlobalSearchMessages.INSTANCE.noResultTitle(), GlobalSearchMessages.INSTANCE.noResultDesc(), BaseIcon.EXCLAMATION.toString()));
				return new PagingLoadResultBean<SearchResultEntryDto>(emptyList, data.getTotalLength(), data.getOffset());
			}
		};


		PagingLoader<SearchLoadConfig, PagingLoadResult<SearchResultEntryDto>> listLoader = new PagingLoader<SearchLoadConfig, PagingLoadResult<SearchResultEntryDto>>(proxy, reader);

		ListStore<SearchResultEntryDto> store = new ListStore<SearchResultEntryDto>(SearchResultEntryDtoPA.INSTANCE.dtoId());
		listLoader.addLoadHandler(new LoadResultListStoreBinding<SearchLoadConfig, SearchResultEntryDto, PagingLoadResult<SearchResultEntryDto>>(store));

		final GlobalSearchTemplates template = GWT.create(GlobalSearchTemplates.class);

		ListView<SearchResultEntryDto, SearchResultEntryDto> view = new ListView<SearchResultEntryDto, SearchResultEntryDto>(store, new IdentityValueProvider<SearchResultEntryDto>());
		view.setCell(new AbstractCell<SearchResultEntryDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					SearchResultEntryDto value, SafeHtmlBuilder sb) {
				sb.append(template.render(value, BaseIcon.from(value.getIconSmall()).getCssName()));
			}
		});

		final DwComboBox<SearchResultEntryDto> combo = new DwComboBox<SearchResultEntryDto>(new ComboBoxCell<SearchResultEntryDto>(store, new DisplayTitleLabelProvider<SearchResultEntryDto>(), view){

			/* gxt workarround
			 * http://www.sencha.com/forum/showthread.php?185967
			 * */
			@Override
			protected PagingLoadConfig getParams(String query) {
				SearchLoadConfig config = null;
				if (loader.isReuseLoadConfig()) {
					config = (SearchLoadConfig) loader.getLastLoadConfig();
				} else {
					config = new SearchLoadConfigBean();
				}
				config.setLimit(pageSize);
				config.setOffset(0);
				config.setQuery(query);

				return config;
			}
			@Override
			protected void onViewClick(XElement parent, NativeEvent event, boolean focus, boolean takeSelected) {
				currentNativeEvent = event;
				super.onViewClick(parent, event, focus, takeSelected);
			}

		});
		combo.setTriggerIcon(BaseIcon.SEARCH);
		
		combo.setWidth(200); 
		combo.addStyleName(CSS_NAME_COMBO);
		combo.setLoader(listLoader);

		combo.setEmptyText(GlobalSearchMessages.INSTANCE.emptyText());
		combo.setMinListWidth(400);
		combo.setMinChars(3);
		
		combo.addTriggerClickHandler(new TriggerClickHandler() {
			
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				searchService.runSearch(searchService.enhanceQuery(combo.getText()));
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					
					@Override
					public void execute() {
						combo.fireEvent(new BlurEvent());
					}
				});
			}
		});

		combo.addSelectionHandler(new SelectionHandler<SearchResultEntryDto>() {

			@Override
			public void onSelection(SelectionEvent<SearchResultEntryDto> event) {
				combo.clear();
				
				if(currentNativeEvent.getCtrlKey()){
					return;
				}

				SearchResultEntryDto selection = event.getSelectedItem();
				combo.clear();

				if(null == selection)
					return;
				SearchResultEntryDto result = selection;
				if(null == result || result instanceof EmptySearchResultDto)
					return;

				NativeEvent e = currentNativeEvent;

				if(result.getLinks().size() == 1 || (null != e && e.getButton() != NativeEvent.BUTTON_RIGHT))
					fire(result);
				else {
					Menu menu = new DwMenu();

					for(final HistoryLinkDto link : result.getLinks()){
						MenuItem linkItem = new DwMenuItem(link.getHistoryLinkBuilderName(), BaseIcon.from(link.getHistoryLinkBuilderIcon()));
						linkItem.addSelectionHandler(new SelectionHandler<Item>() {
							@Override
							public void onSelection(SelectionEvent<Item> event) {
								historyService.fire(link);
							}
						});
						menu.add(linkItem);
					}

					int x = e.getClientX();
					int y = e.getClientY();
					menu.showAt(x, y);
				}

				Scheduler.get().scheduleDeferred(new ScheduledCommand(){
					@Override
					public void execute() {
						combo.clear();
						combo.setText("");
					}
				});
			}
		});
		
		combo.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				int index = container.getWidgetIndex(combo);
				if(-1 != index){
					combo.clear();
					container.insert(icon, index);
					container.remove(combo);
					container.forceLayout();
				}
			}
		});
		
		combo.setLayoutData(new MarginData(0,5,0,0));

		icon = new HTML(BaseIcon.SEARCH.toSafeHtml());
		icon.setStyleName(CSS_NAME_ICON);
		icon.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				container.insert(combo, container.getWidgetIndex(icon));
				container.remove(icon);
				combo.focus();
				container.forceLayout();
			}
		});
		
		
		return new DwMainViewportTopBarWidget() {

			@Override
			public Widget getComponent() {
				return icon;
			}
			
			@Override
			public int getSize() {
				return 20;
			}
		};
	}


	protected void fire(SearchResultEntryDto result) {
		for(HistoryLinkDto link : result.getLinks()){
			historyService.fire(link);
			break;
		}
	}



}
