
package net.datenwerke.rs.core.client.reportexecutor.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.Insert;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import com.sencha.gxt.widget.core.client.tree.TreeSelectionModel;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationModelData;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwHookableToolbar;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ExecuteReportConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.events.ExecutorEventHandler;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.core.client.reportexecutor.module.ReportExecuteAreaModule;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.CloseableAware;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.DeselectionAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.EventHandlerAware;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.MainPanelAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.SelectionAwareView;
import net.datenwerke.rs.core.client.reportexecutor.variantstorer.VariantStorerHook;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * The main Panel of the reportExecutor. 
 * It hosts the tabs to configure the report
 * 
 *
 */
public class ReportExecutorMainPanel extends DwContentPanel implements CloseableAware {

	private static final String REPORT_EXECUTOR_MAIN_PANEL_TOOLBAR_NAME = "reportexecutor:main:toolbar";

	@Inject
	private static HookHandlerService hookHandler;
	
	@Inject
	private static Provider<ReportExecutorUIService> reportExecutorUIService;
		
	@Inject
	private static Provider<DwHookableToolbar> toolbarProvider;
	
	@Inject
	private static Provider<ReportManagerUIService> reportManagerUIService;
	
	private final ExecuteReportConfiguration config;
	private final ExecutorEventHandler eventHandler;
	private final ReportDto report;
	private final Collection<ReportViewConfiguration> viewConfigs;
	
	private final String executeReportToken;
	
	private ReportExecutorMainPanelView selectedView = null;

	private DwContentPanel navPanel;
	
	private DwHookableToolbar toolbar;

	private List<ReportExecutorViewToolbarHook> toolbarHookers;

	private int reportViewCount = 0;

	private DwContentPanel mainWrapper;
	private CardLayoutContainer cardLayout = new CardLayoutContainer();
	
	private boolean markedChange;

	private HandlerRegistration markChangeHandler;

	private List<ReportExecutorMainPanelView> views = new ArrayList<ReportExecutorMainPanelView>();


	public ReportExecutorMainPanel(
		ReportDto report,
		ExecutorEventHandler eventHandler,
		String executeReportToken,
		ExecuteReportConfiguration config,
		ReportViewConfiguration... viewConfigs
		) {
		
		/* store objects */
		this.report = report;
		this.eventHandler = eventHandler;
		this.executeReportToken = executeReportToken;
		this.config = config;
		this.viewConfigs = new ArrayList<ReportViewConfiguration>(Arrays.asList(viewConfigs));
		
		/* init */
		initializeUI();
	}

	private void initializeUI() {
		setHeading(getReportName() + " (" + report.getId() + ")");
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		setWidget(container);
		
		/* toolbar */
		toolbar = initToolbar();
		
		/* create panels */
		navPanel = createNavPanel();
		
		/* add toolbar */
		container.add(toolbar, new VerticalLayoutData(1, -1));
		
		/* border layout */
		BorderLayoutContainer mainBorderContainer = new BorderLayoutContainer();
		container.add(mainBorderContainer, new VerticalLayoutData(1, 1));

		/* add nav panel */
		if(reportViewCount > 1){
			BorderLayoutData westData = new BorderLayoutData(200);
			westData.setMargins(new Margins(0,0,0,0));
			westData.setSplit(true);
			westData.setCollapsible(true);
			mainBorderContainer.setWestWidget(navPanel, westData);
		}
		
		/* add main */
		mainWrapper = new DwContentPanel();
		mainWrapper.setHeaderVisible(false);
		mainWrapper.setWidget(cardLayout);

		/* clear tools */
		for(Widget w : new ArrayList<Widget>(getHeader().getTools()))
			getHeader().removeTool(w);
		
		/* add close tool */
		ToolButton closeBtn = new ToolButton(ToolButton.CLOSE);
		addTool(closeBtn);
		closeBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				reportExecutorUIService.get().getActiveReportExecuteAreaModule().closeCurrent();
			}
		});
		
		markChangeHandler = report.addInstanceChangedHandler(new ObjectChangedEventHandler<Dto>() {
			
			@Override
			public void onObjectChangedEvent(ObjectChangedEvent<Dto> event) {
				if(! markedChange){
					setHeading("*" + getReportName() + " (" + report.getId() + ")");
					ReportExecuteAreaModule module = reportExecutorUIService.get().getActiveReportExecuteAreaModule();
					if(null != module)
						module.markCurrentChanged();
					markedChange = true;
				}
			}
		});
		
		DwContentPanel wrap = DwContentPanel.newInlineInstance(mainWrapper);
		wrap.addClassName("rs-rep-view-main");
		
		mainBorderContainer.setCenterWidget(wrap);
	}
	
	public void reload(){
		markChangeHandler.removeHandler();
		getWidget().removeFromParent();
		initializeUI();
	}
	
	public String getExecuteReportToken() {
		return executeReportToken;
	}
	
	@Override
	protected void onDetach() {
		super.onDetach();

		if(null != markChangeHandler){
			markChangeHandler.removeHandler();
			markChangeHandler = null;
		}
	}

	protected DwHookableToolbar initToolbar() {
		DwHookableToolbar toolbar = toolbarProvider.get();
		
		/* configure toolbar */
		toolbar.setContainerName(REPORT_EXECUTOR_MAIN_PANEL_TOOLBAR_NAME);
		toolbar.getHookConfig().put("id", String.valueOf(report.getId()));
		toolbar.getHookConfig().put("key", String.valueOf(report.getKey()));
		toolbar.getHookConfig().put("classname", report.getClass().getName());
		
		toolbarHookers = hookHandler.getHookers(ReportExecutorViewToolbarHook.class);
		
		/* add variant storer */
		if(config.getVariantStorerConfig().displayVariantStorer() && ! report.isWriteProtected() && reportManagerUIService.get().supportsVariants(report)){
			try{
				assert hookHandler.getHookers(VariantStorerHook.class).size() == 1 : "There should be exactly one Variant Storer";
				
				/* get storer add it to list and configure it */
				VariantStorerHook variantStorer = hookHandler.getHookers(VariantStorerHook.class).get(0);
				variantStorer.setEventHandler(eventHandler);
				variantStorer.setConfig(config.getVariantStorerConfig());
				toolbarHookers.add(variantStorer);
			} catch(IndexOutOfBoundsException e){}
		}
		
		/* fill left */
		Iterator<ReportExecutorViewToolbarHook> it = toolbarHookers.iterator();
		while(it.hasNext()){
			ReportExecutorViewToolbarHook hooker = it.next();
			boolean sep = hooker.reportPreviewViewToolbarHook_addLeft(toolbar, getReport(), createInfo(), this);
			if(sep && it.hasNext())
				toolbar.add(new SeparatorToolItem());
		}
		
		/* base hookers */
		toolbar.addBaseHookersLeft();
		
		/* fill */
		toolbar.add(new FillToolItem());
		
		/* base hookers */
		toolbar.addBaseHookersRight();
		
		/* fill right */
		it = toolbarHookers.iterator();
		while(it.hasNext()){
			ReportExecutorViewToolbarHook hooker = it.next();
			boolean sep = hooker.reportPreviewViewToolbarHook_addRight(toolbar, getReport(), createInfo(), this);
			if(sep && it.hasNext())
				toolbar.add(new SeparatorToolItem());
		}
		return toolbar;
	}
	
	protected ReportExecutorInformation createInfo(){
		return new ReportExecutorInformation(){

			@Override
			public String getExecuteReportToken() {
				return executeReportToken;
			}
			
		};
	}

	protected void makeViewAware(ReportExecutorMainPanelView view){
		view.setExecuteReportToken(executeReportToken);
		if(view instanceof MainPanelAwareView)
			((MainPanelAwareView)view).makeAwareOfMainPanel(this);
		if(view instanceof EventHandlerAware)
			((EventHandlerAware)view).makeAwareOfEventHandler(eventHandler);
	}
	
	protected String getReportName() {
		if(report instanceof ReportVariantDto)
			return ((ReportVariantDto) report).getBaseReport().getName() + ": " + report.getName();
		return report.getName();
	}

	protected DwContentPanel createNavPanel(){
		DwContentPanel navigationPanel = new DwContentPanel();
		navigationPanel.setHeaderVisible(false);
		navigationPanel.addClassName("rs-rep-view-nav");
		
		/* store to store views */
		final TreeStore<NavigationModelData<ReportExecutorMainPanelView>> store = new TreeStore<NavigationModelData<ReportExecutorMainPanelView>>(new BasicObjectModelKeyProvider<NavigationModelData<ReportExecutorMainPanelView>>());
		
		/* load panels */
		for(ReportViewHook hooker : hookHandler.getHookers(ReportViewHook.class)){
			ReportViewFactory fact = hooker.getViewFactory();
			if(fact.consumes(report) && config.acceptView(fact.getViewId())){
				reportViewCount++;
				
				ReportExecutorMainPanelView view = fact.newInstance(report, viewConfigs);
				
				/* make view aware of things */
				makeViewAware(view);
				
				/* add view */
				store.add(new NavigationModelData<ReportExecutorMainPanelView>(view.getComponentHeader(), view.getIcon(), view));
				views.add(view);
			}
		}
		
		/* build tree */
		final Tree<NavigationModelData<ReportExecutorMainPanelView>, String> tree = new Tree<NavigationModelData<ReportExecutorMainPanelView>, String>(store, new ValueProvider<NavigationModelData<ReportExecutorMainPanelView>, String>() {
			@Override
			public String getValue(
					NavigationModelData<ReportExecutorMainPanelView> object) {
				return object.getName();
			}

			@Override
			public void setValue(
					NavigationModelData<ReportExecutorMainPanelView> object,
					String value) {
				
			}

			@Override
			public String getPath() {
				return "name";
			}
		//TODO: Theme
		//}, (TreeAppearance) GWT.create(RepserTreeMenuLightAppearance.class));
		});
		
		/* selection model */
		final TreeSelectionModel<NavigationModelData<ReportExecutorMainPanelView>> sModel = new TreeSelectionModel<NavigationModelData<ReportExecutorMainPanelView>>();
		sModel.setSelectionMode(SelectionMode.SINGLE);
		sModel.addSelectionChangedHandler(new SelectionChangedHandler<NavigationModelData<ReportExecutorMainPanelView>>() {
			private Map<ReportExecutorMainPanelView, Widget> componentMap = new HashMap<ReportExecutorMainPanelView, Widget>();
			private NavigationModelData<ReportExecutorMainPanelView> selectedModelData;
			private boolean inDeselect;

			@Override
			public void onSelectionChanged(
					SelectionChangedEvent<NavigationModelData<ReportExecutorMainPanelView>> event) {
				if(inDeselect || null == event.getSelection() || event.getSelection().isEmpty())
					return;
				
				/* if same thing */
				if(selectedModelData == event.getSelection().get(0))
					return;
				
				/* validate current view */
				if(null != selectedView){
					List<String> errorMsgs = selectedView.validateView();
					if(null != errorMsgs && ! errorMsgs.isEmpty()){
						String errorMsg = "";
						boolean first = true;
						for(String msg : errorMsgs){
							if(first)
								first = false;
							else
								errorMsg += "<br/>";
							errorMsg += msg;
						}
						
						new DwAlertMessageBox(BaseMessages.INSTANCE.error(), errorMsg).show();
						sModel.select(false, selectedModelData);
						return;
					}
				}
				
				/* deselect all and select current (only single selection does not seem to do the trick */
				boolean oldInDes = inDeselect;
				inDeselect = true;
				sModel.select(event.getSelection().get(0),false);
				inDeselect = oldInDes;
				
				
				/* throw deselctionevent */
				if(null != selectedView)
					if(selectedView instanceof DeselectionAwareView)
						((DeselectionAwareView)selectedView).makeAwareOfDeselection();
				
				/* remove focus so that for example field bindings are activated */
				tree.focus();
				
				/* add new view */
				selectedModelData = event.getSelection().get(0);
				selectedView = event.getSelection().get(0).getModel();
				if(! componentMap.containsKey(selectedView)) {
					componentMap.put(selectedView, selectedView.getViewComponent());
					cardLayout.add(componentMap.get(selectedView));
				}
				cardLayout.setActiveWidget(componentMap.get(selectedView));
				
				/* tell new view about selection */
				if(selectedView instanceof SelectionAwareView)
					((SelectionAwareView)selectedView).makeAwareOfSelection();
				
				forceLayout();
			}
		});
		tree.setSelectionModel(sModel);
		
		tree.setAllowTextSelection(false);
		
		/* icons for naviagtion */
		tree.setIconProvider(new IconProvider<NavigationModelData<ReportExecutorMainPanelView>>() {
			@Override
			public ImageResource getIcon(
					NavigationModelData<ReportExecutorMainPanelView> model) {
				return model.getIcon();
			}
		});
		
		/* add tree to navigation panel */
		navigationPanel.setWidget(tree);
		
		/* on render ask config which to select */
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				String id = config.getViewId();
				NavigationModelData<ReportExecutorMainPanelView> defaultView = null;
				
				for(NavigationModelData<ReportExecutorMainPanelView> item : store.getRootItems()){
					if(item.getModel().getViewId().equals(id)){
						tree.getSelectionModel().select(item,false);
						return;
					}
					
					if(null == defaultView && item.getModel().wantsToBeDefault())
						defaultView = item;
				}
				
				/* default : select first */
				tree.getSelectionModel().deselectAll();
				if(null != defaultView)
					tree.getSelectionModel().select(defaultView,false);
				else
					tree.getSelectionModel().select(store.getRootItems().get(0),false);
			}
		});
		
		/* allow drop */
		TreeDropTarget<NavigationModelData<ReportExecutorMainPanelView>> dropTarget = new TreeDropTarget<NavigationModelData<ReportExecutorMainPanelView>>(tree){
			
			@Override
			protected void showFeedback(DndDragMoveEvent event) {
				 final TreeNode<NavigationModelData<ReportExecutorMainPanelView>> node = getWidget().findNode(
					        event.getDragMoveEvent().getNativeEvent().getEventTarget().<Element> cast());
				
				if (node == null && activeItem != null) 
				      clearStyle(activeItem);
				
				if(null == node){
					Insert.get().hide();
			          event.getStatusProxy().setStatus(false);
					return;
				}
				
				NavigationModelData<ReportExecutorMainPanelView> modeldata = (NavigationModelData<ReportExecutorMainPanelView>) node.getModel();
				ReportExecutorMainPanelView view = modeldata.getModel();
				List list = (List) event.getData();
				if(list.size() != 1){
					Insert.get().hide();
			          event.getStatusProxy().setStatus(false);
					return;
				} else {
					Object m = list.get(0);
		
					if(! view.allowsDropOf(m)){
						Insert.get().hide();
				          event.getStatusProxy().setStatus(false);
						return;
					}
				} 
				
				Insert.get().hide();
				event.getStatusProxy().setStatus(true);
				
			    activeItem = node;
			    if (activeItem != null) 
			    	getWidget().getView().onDropChange(activeItem, true);
			}
			
			@Override
			protected void onDragDrop(DndDropEvent event) {
				event.getStatusProxy().setStatus(false);
				
				final TreeNode<NavigationModelData<ReportExecutorMainPanelView>> node = getWidget().findNode(
				        event.getDragEndEvent().getNativeEvent().getEventTarget().<Element> cast());
				if(null == node)
					return;
				
				tree.getView().onDropChange(node, false);
				
				NavigationModelData<ReportExecutorMainPanelView> modeldata = (NavigationModelData<ReportExecutorMainPanelView>) node.getModel();
				ReportExecutorMainPanelView view = modeldata.getModel();
				List list = (List) event.getData();
				if(list.size() == 1){
					Object m = list.get(0);
		
					if(view.allowsDropOf(m))
						view.objectDropped(m);
				}
			}
		};
		dropTarget.setAllowDropOnLeaf(true);
		dropTarget.setAutoExpand(false);
		
		return navigationPanel;
	}
	
	public ReportDto getReport() {
		return report;
	}

	public Collection<ReportViewConfiguration> getViewConfigs() {
		return viewConfigs;
	}

	public void cleanup() {
		for(ReportExecutorMainPanelView v : views){
			v.cleanup();
		}
		
	}

	public List<ReportExecutorMainPanelView> getViews() {
		return views;
	}
	
	@Override
	public boolean needToConfirmClose() {
		for(ReportExecutorMainPanelView view : views)
			if(view instanceof CloseableAware)
				if(((CloseableAware)view).needToConfirmClose())
					return true;
		return false;
	}

	public boolean isViewSelected(ReportExecutorMainPanelView view) {
		return selectedView == view;
	}
}
