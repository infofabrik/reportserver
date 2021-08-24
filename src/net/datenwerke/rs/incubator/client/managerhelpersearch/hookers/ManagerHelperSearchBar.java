package net.datenwerke.rs.incubator.client.managerhelpersearch.hookers;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.XTemplates.FormatterFactoryMethod;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.client.managerhelper.hooks.ManagerHelperTreeToolbarEnhancerHook;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.loadconfig.SearchLoadConfig;
import net.datenwerke.gxtdto.client.xtemplates.NullSafeFormatter;
import net.datenwerke.rs.search.client.search.SearchDao;
import net.datenwerke.rs.search.client.search.SearchUiService;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.treedb.client.treedb.TreeDbManagerContainer;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

public class ManagerHelperSearchBar implements
		ManagerHelperTreeToolbarEnhancerHook {
	
	@FormatterFactories(@FormatterFactory(factory=NullSafeFormatter.class,methods=@FormatterFactoryMethod(name="nullsafe")))
	public interface SearchTemplates extends XTemplates {
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
	private final ToolbarService toolbarService;
	private final SearchUiService searchService;
	
	@Inject
	public ManagerHelperSearchBar(
		SearchDao searcher,
		SearchUiService searchService,
		ToolbarService toolbarService
		){
		
		/* store objects */
		this.searcher = searcher;
		this.searchService = searchService;
		this.toolbarService = toolbarService;
	}
	
	@Override
	public void treeNavigationToolbarEnhancerHook_addLeft(final ToolBar toolbar, final UITree tree, final TreeDbManagerContainer treeManagerContainer) {
		SelectionHandler<SearchResultEntryDto> selectionHandler = new SelectionHandler<SearchResultEntryDto>() {
			
			@Override
			public void onSelection(SelectionEvent<SearchResultEntryDto> event) {
				SearchResultEntryDto selection = event.getSelectedItem();
				if(null == selection)
					return;

				for(HistoryLinkDto link : selection.getLinks()){
					if(link.getHistoryToken().equals(History.getToken()))
						History.fireCurrentHistoryState();
					else
						History.newItem(link.getHistoryToken(), true);
					break;
				}
			}
		};
		
		addSearchBar(toolbar, tree, treeManagerContainer, selectionHandler);
	}

	@Override
	public void treeNavigationToolbarEnhancerHook_addRight(ToolBar toolbar,
			final UITree tree, final TreeDbManagerContainer treeManagerContainer) {
	}

	protected void addSearchBar(ToolBar toolbar, final UITree tree, final TreeDbManagerContainer treeManagerContainer, final SelectionHandler<SearchResultEntryDto> selectionHandler) {
		RpcProxy<SearchLoadConfig, SearchResultListDto> proxy = new RpcProxy<SearchLoadConfig, SearchResultListDto>() {
			@Override
			public void load(SearchLoadConfig loadConfig, AsyncCallback<SearchResultListDto> callback) {
				String query = loadConfig.getQuery();
				if(null != query && query.length() > 1){
					TreeDbManagerDao treeManager = treeManagerContainer.getTreeManager();
					List<Class<? extends RsDto>> types = tree.getTypes();
					if (null == types || types.isEmpty()) 
						searcher.find(treeManager.getBaseNodeMapper(), searchService.enhanceQuery(query), callback);
					else 
                  searcher.find(searchService.enhanceQuery(query), searchService.createFilterFor(types, false), callback);
					
				}
			}
		};
		toolbarService.addSearchBar(toolbar, selectionHandler, proxy);
	}
	
}
