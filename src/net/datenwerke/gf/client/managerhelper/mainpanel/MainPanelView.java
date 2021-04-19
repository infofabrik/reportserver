package net.datenwerke.gf.client.managerhelper.mainpanel;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * The base class of a specific view for a node in a manager component.
 * 
 *
 */
public abstract class MainPanelView {

	public static final String ID_MAIN_PROPERTIES_VIEW = "MainPropertiesView";
	
	protected AbstractNodeDto selectedNode;
	protected UITree tree;
	protected TreeDbManagerDao treeManager;
	protected AbstractTreeMainPanel mainPanel;

	
	public void initializeView(AbstractNodeDto selectedNode, UITree tree, TreeDbManagerDao treeManager, AbstractTreeMainPanel mainPanel){
		this.selectedNode = selectedNode;
		this.tree = tree;
		this.treeManager = treeManager;
		this.mainPanel = mainPanel;
	}
	
	
	protected void mask(String msg) {
		mainPanel.mask(msg);
	}
	
	protected void enable() {
		mainPanel.enable();
	}
	
	protected void disable() {
		mainPanel.disable();
	}
	
	protected void unmask() {
		mainPanel.unmask();
	}
	
	public void reloadView(AbstractNodeDto selectedNode){
		mainPanel.reloadView(this, selectedNode);
	}
	
	public void reloadNodeAndView(){
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				treeManager.refreshNode(getSelectedNode(), new RsAsyncCallback<AbstractNodeDto>(){
					@Override
					public void onSuccess(AbstractNodeDto result) {
						unmask();
						reloadView(getSelectedNode());
					}
				});	
			}
		});
	}
	
	public void reloadNode(final AsyncCallback<AbstractNodeDto> callback){
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				treeManager.refreshNode(getSelectedNode(), callback);	
			}
		});
	}
	
	public String getViewId() {
		return MainPanelView.ID_MAIN_PROPERTIES_VIEW;
	}

	public boolean appliesTo(AbstractNodeDto item){
		return true;
	}
	
	public AbstractNodeDto getSelectedNode() {
		return selectedNode;
	}
	
	public UITree getTree() {
		return tree;
	}

	public void setTree(UITree tree) {
		this.tree = tree;
	}

	public TreeDbManagerDao getTreeManager() {
		return treeManager;
	}

	public void setTreeManager(TreeDbManagerDao treeManager) {
		this.treeManager = treeManager;
	}

	public abstract String getComponentHeader();
	
	public abstract Widget getViewComponent(AbstractNodeDto selectedNode);

	public void viewAdded(Container viewWrapper) {
	}

	public ImageResource getIcon() {
		return null;
	}
	
	/**
	 * Is the view sticky, i.e. once it is selected, should it try to be autoselected if the user clicks on another tree entry ?
	 */
	public boolean isSticky() {
		return false;
	}

}
