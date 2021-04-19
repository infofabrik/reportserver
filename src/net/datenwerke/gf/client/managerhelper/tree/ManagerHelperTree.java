package net.datenwerke.gf.client.managerhelper.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.tree.TreeSelectionModel;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreePostSelectAsyncHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

public class ManagerHelperTree extends UITree {

	private final HookHandlerService hookHandler;
	private final Provider<TreeDBUIMenuProvider> menuConfigProvider;

	private ArrayList<Class<? extends Dto>> folderTypes = new ArrayList<Class<? extends Dto>>();
	
	private final TreeDbLoaderDao treeLoaderDao;
	private final Class<?> guarantor;
	private boolean doubleClickAction;
	private String historyLocation;
	
	private int doubleClicked = 0;
	private AbstractTreeMainPanel selectionTarget;
	
	@Inject
	public ManagerHelperTree(
		ClipboardUiService clipboardService,
		HookHandlerService hookHandler,
		Provider<TreeDBUIMenuProvider> menuConfigProvider,
		@Assisted Class<?> guarantor, 
		@Assisted EnhancedTreeStore store, 
		@Assisted TreeDbLoaderDao treeLoaderDao,
		@Assisted TreeDbManagerDao treeManagerDao
		) {
		super(clipboardService, store);
		this.hookHandler = hookHandler;
		this.menuConfigProvider = menuConfigProvider;
		this.guarantor = guarantor;
		this.treeLoaderDao = treeLoaderDao;
		
		setStateful(false); 
		setLoaderDao(treeLoaderDao);
		setManagerDao(treeManagerDao);
	}
	
	public Class<?> getGuarantor(){
		return guarantor;
	}
	
	@Override
	public EnhancedTreeStore getStore() {
		return (EnhancedTreeStore) super.getStore();
	}
	
	public void showTab(String id){
		if(null != selectionTarget && null != getSelectionModel().getSelectedItem())
			selectionTarget.showTab(id, getSelectionModel().getSelectedItem(), this);
	}
	
	public void showTabOnSelection(String id) {
		if(null != selectionTarget)
			selectionTarget.showTabOnSelection(id);
	}

	public void configureIconProvider() {
		setIconProvider(new TreeDBUIIconProvider());
		for(TreeConfiguratorHook configurator : hookHandler.getHookers(TreeConfiguratorHook.class))
			if(configurator.consumes(this)){
				configurator.configureTreeIcons((TreeDBUIIconProvider) getIconProvider());
				configurator.configureFolderTypes(this);
			}
	}
	
	public void configureMenuProvider(String menuName) {
		TreeDBUIMenuProvider menuProvider = menuConfigProvider.get();
		menuProvider.setMenuName(menuName);
		
		for(TreeConfiguratorHook configurator : hookHandler.getHookers(TreeConfiguratorHook.class))
			if(configurator.consumes(this))
				configurator.configureTreeMenu(menuProvider);
		setMenuProvider(menuProvider);
	}
	
	
	public void enableDoubleClickAction(){
		doubleClickAction = true;
	}
	
	@Override
	protected void onDoubleClicked(AbstractNodeDto selectedItem, DoubleClickEvent event) {
		if(doubleClickAction){
			doubleClicked++;
			for(TreeConfiguratorHook configurator : hookHandler.getHookers(TreeConfiguratorHook.class))
				if(configurator.consumes(this))
					configurator.onDoubleClick(selectedItem, event);
			
			DelayedTask task = new DelayedTask() {
				@Override
				public void onExecute() {
					doubleClicked--;
					if(doubleClicked < 0)
						doubleClicked = 0;
				}
			};
			task.delay(500);
		}
	}

	/**
	 * Listen for selection events.
	 */
	public void setSelectionProvider(final AbstractTreeMainPanel target){
		this.selectionTarget = target;
		
		/* selection model */
		final TreeSelectionModel<AbstractNodeDto> sModel = new TreeSelectionModel<AbstractNodeDto>();
		sModel.setSelectionMode(SelectionMode.SINGLE);
		sModel.addSelectionChangedHandler(new SelectionChangedHandler<AbstractNodeDto>() {

			@Override
			public void onSelectionChanged(SelectionChangedEvent<AbstractNodeDto> event) {
				List<AbstractNodeDto> selection = event.getSelection();
				if(selection.isEmpty())
					return;

				AbstractNodeDto preNode = selection.get(0); 
				final AbstractNodeDto node = ManagerHelperTree.this.getStore().findModel(preNode);
				if(node != null){
					DelayedTask task = new DelayedTask() {
						
						@Override
						public void onExecute() {
							if(! ManagerHelperTree.this.isInDrag()){
								if(doubleClicked > 0)
									getSelectionModel().deselectAll();
								else {
									target.mask(BaseMessages.INSTANCE.loadingMsg());
									
									treeLoaderDao.loadFullViewNode(node, new RsAsyncCallback<AbstractNodeDto>(){
										
										@Override
										public void onSuccess(AbstractNodeDto fullNode) {
											target.unmask();
											
											updateHistoryOnSelection(fullNode);
											
											Collection<MainPanelViewProviderHook> viewProviders = hookHandler.getHookers(MainPanelViewProviderHook.class);
											final List<MainPanelView> views = new ArrayList<MainPanelView>();
											for(MainPanelViewProviderHook viewProvider : viewProviders){
												List<MainPanelView> newViews = viewProvider.mainPanelViewProviderHook_getView(fullNode);
												if(null != newViews)
													for(MainPanelView view : newViews)
														if(view.appliesTo(fullNode))
															views.add(view);
											}
											
											/* execute chain */
											TreePostSelectAsyncHook me = new TreePostSelectAsyncHook() {
												@Override
												public void postprocessNode(AbstractNodeDto selectedNode, List<TreePostSelectAsyncHook> next) {
													if(! views.isEmpty()){
														target.displayTreeSelection(views, selectedNode, ManagerHelperTree.this);
													}
												}
												@Override
												public boolean consumes(AbstractNodeDto node) {
													return true;
												}
											};
											List<TreePostSelectAsyncHook> postSelectHookers = hookHandler.getHookers(TreePostSelectAsyncHook.class);
											Iterator<TreePostSelectAsyncHook> it = postSelectHookers.iterator();
											while(it.hasNext()){
												if(!it.next().consumes(fullNode))
													it.remove();
											}
											postSelectHookers.add(me);
											
											postSelectHookers.get(0).postprocessNode(fullNode, postSelectHookers.subList(1, postSelectHookers.size()));
											
										}
	
									});
								}
								
							}
						}
					};
					
					task.delay(200);
				}
			}
		});
		
		setSelectionModel(sModel);
	}

	
	protected void updateHistoryOnSelection(
			AbstractNodeDto fullNode) {
		if(null != historyLocation){
			List<Long> pathList = new ArrayList(((AbstractNodeDtoDec)fullNode).getRootPath());
			Collections.reverse(pathList);
			
			String path = "";
			
			Iterator<Long> it = pathList.iterator();
			if(it.hasNext())
				path += String.valueOf(it.next());
			
			while(it.hasNext())
				path += "." + String.valueOf(it.next());
			
			if(! "".equals(path))
				path += ".";
			path += fullNode.getId();
			
			History.newItem(historyLocation + "/path:" + path, false);
		}
	}

	public void setHistoryLocation(String historyLocation) {
		this.historyLocation = historyLocation; 
	}
	
	public void addFolderTypes(Class<? extends Dto>... types){
		for(Class<? extends Dto> t : types )
			folderTypes.add(t);
	}
	
	@Override
	public boolean isLeaf(AbstractNodeDto model) {
		if(super.isLeaf(model)){
			for(Class<?> t : folderTypes)
				if(t.equals(model.getClass()))
					return false;
			return true;
		}
		return false;		
	}
	
}
