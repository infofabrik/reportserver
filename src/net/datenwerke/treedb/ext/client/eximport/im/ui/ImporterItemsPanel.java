package net.datenwerke.treedb.ext.client.eximport.im.ui;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwTreePanel;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportNodeConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.pa.ImportTreeModelPA;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.ShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.event.ShowContextMenuEvent.ShowContextMenuHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckCascade;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;
import com.sencha.gxt.widget.core.client.tree.TreeSelectionModel;

/**
 * 
 *
 * @param <C>
 */
public class ImporterItemsPanel<C extends ImportConfigDto> extends DwContentPanel {

	@CssClassConstant
	public static final String CSS_IMPORT_MAIN_PANEL = "rs-import-item-panel";

	@CssClassConstant
	public static final String CSS_IMPORT_MAIN_TREE = "rs-import-item-tree";

	@CssClassConstant
	public static final String CSS_IMPORT_MAIN_DETAIL = "rs-import-item-detail";

	
	protected DwContentPanel navigationPanel;
	protected DwContentPanel mainPanel;
	
	protected TreeStore<ImportTreeModel> store;
	protected Tree<ImportTreeModel,String> tree;
	
	private ImportTreeModel contextMenuItem;
	
	@Inject
	public ImporterItemsPanel(){
		
		/* init */
		initializeUI();
	}
	
	@Override
	public String getCssName() {
		return super.getCssName() + " " + CSS_IMPORT_MAIN_PANEL;
	}

	private void initializeUI() {
		/* init us */
		setHeaderVisible(false);
		setBodyBorder(false);
		setBorders(false);
		
		/* init panels */
		instantiateNavigationPanel();
		instantiateMainPanel();
		
		/* layout */
		BorderLayoutContainer borderContainer = new DwBorderContainer();
		setWidget(borderContainer);
		
		BorderLayoutData westData = new BorderLayoutData(400);
		westData.setSplit(true);
		westData.setMargins(new Margins(0,2,0,0));
		
		/* add panels */
		borderContainer.setWestWidget(navigationPanel, westData);
		borderContainer.setCenterWidget(mainPanel);
	}

	protected void buildTree(List<ImportTreeModel> roots) {
		store = new TreeStore<ImportTreeModel>(ImportTreeModelPA.INSTANCE.dtoId());
		store.addSubTree(0,roots);
		
		tree = new DwTreePanel<ImportTreeModel>(store, ImportTreeModelPA.INSTANCE.name());
		tree.addStyleName(CSS_IMPORT_MAIN_TREE);
		tree.setCheckable(true);
		tree.setAutoLoad(true);
		tree.setCheckStyle(CheckCascade.CHILDREN);
		
		configureTree();
		
		TreeSelectionModel<ImportTreeModel> sModel = new TreeSelectionModel<ImportTreeModel>();
		sModel.setSelectionMode(SelectionMode.SINGLE);
		sModel.addSelectionChangedHandler(new SelectionChangedHandler<ImportTreeModel>() {
			@Override
			public void onSelectionChanged(
					SelectionChangedEvent<ImportTreeModel> event) {
				if(null == event.getSelection() || event.getSelection().isEmpty())
					return;
				
				ImportTreeModel model = event.getSelection().get(0);
				if(null != model)
					modelSelected(model);
			}
		});
		tree.setSelectionModel(sModel);
		
		
		navigationPanel.add(tree, new MarginData(4, 0, 0, 0));
		navigationPanel.forceLayout();
	}
	
	protected void configureTree() {
		configureContextMenu();
	}

	protected void configureContextMenu() {
		Menu cMenu = new DwMenu();
		tree.setContextMenu(cMenu);
		tree.addShowContextMenuHandler(new ShowContextMenuHandler() {
			@Override
			public void onShowContextMenu(ShowContextMenuEvent event) {
				contextMenuItem = tree.getSelectionModel().getSelectedItem();
			}
		});
		
		MenuItem selectChildren = new DwMenuItem(ExImportMessages.INSTANCE.selectChildrenLabel(), BaseIcon.PLUS_SQUARE);
		cMenu.add(selectChildren);
		selectChildren.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				if(null == contextMenuItem)
					return;
				
				for(ImportTreeModel model : store.getChildren(contextMenuItem))
					tree.setChecked(model, CheckState.CHECKED);
			}
		});
		
		MenuItem deselectChildren = new DwMenuItem(ExImportMessages.INSTANCE.deselectChildrenLabel(), BaseIcon.MINUS_SQUARE);
		cMenu.add(deselectChildren);
		deselectChildren.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				if(null == contextMenuItem)
					return;
				
				for(ImportTreeModel model : store.getChildren(contextMenuItem))
					tree.setChecked(model, CheckState.UNCHECKED);
			}
		});
	}

	public ImportTreeModel getContextMenuItem() {
		return contextMenuItem;
	}
	
	protected void modelSelected(ImportTreeModel model) {
		
	}

	private void instantiateMainPanel() {
		mainPanel = DwContentPanel.newInlineInstance();
		mainPanel.addStyleName(CSS_IMPORT_MAIN_DETAIL);
	}

	private void instantiateNavigationPanel() {
		navigationPanel = DwContentPanel.newInlineInstance();
	}

	public void populateConfig(C config) {
		for(ImportTreeModel model : tree.getCheckedSelection()){
			TreeImportNodeConfigDto configNode = new TreeImportNodeConfigDto(model);
			config.addConfig(configNode);
		}
	}

	public void validateConfig(C config) {
	}

	public void resetConfig() {
		tree.setCheckedSelection(new ArrayList<ImportTreeModel>());
	}
}
