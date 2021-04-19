package net.datenwerke.rs.incubator.client.managerhelpersearch.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.client.managerhelper.hooks.TreeSelectionToolbarEnhancerHook;
import net.datenwerke.gf.client.treedb.TreeDBHistoryCallback;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.search.client.search.SearchDao;
import net.datenwerke.rs.search.client.search.SearchUiService;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.treedb.client.treedb.TreeDbManagerContainer;

public class TreeSelectionSearchBar extends ManagerHelperSearchBar implements TreeSelectionToolbarEnhancerHook{

	@Inject
	public TreeSelectionSearchBar(SearchDao searcher, SearchUiService searchService, ToolbarService toolbarService) {
		super(searcher, searchService, toolbarService);
	}

	@Override
	public void treeNavigationToolbarEnhancerHook_addLeft(final ToolBar toolbar, final UITree tree, final TreeDbManagerContainer treeManagerContainer) {
		
		SelectionHandler<SearchResultEntryDto> selectionHandler = new SelectionHandler<SearchResultEntryDto>() {
			
			@Override
			public void onSelection(SelectionEvent<SearchResultEntryDto> event) {
				SearchResultEntryDto selection = event.getSelectedItem();
				if(null == selection)
					return;
				
				List<HistoryLinkDto> links = selection.getLinks();
				for (HistoryLinkDto link: links) {
					String historyToken = link.getHistoryToken();
					if (historyToken.contains("tsselect"))
						continue;
					
					String[] pathElements = HistoryLocation.fromString(historyToken).getParameterValue(TreeDBHistoryCallback.HISTORY_PARAMETER_TREE_PATH).split("\\.");
					final List<Long> nodes = new ArrayList<Long>();
					for(String elem : pathElements){
						nodes.add(Long.valueOf(elem));
					}
					
					tree.expandPathByIds(nodes);
					break;
				}
				
			}
		};
		
		addSearchBar(toolbar, tree, treeManagerContainer, selectionHandler);
	}
}
