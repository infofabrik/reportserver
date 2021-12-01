package net.datenwerke.rs.dashboard.client.dashboard.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.DragEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.dom.AutoScrollSupport;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.fx.client.DragCancelEvent;
import com.sencha.gxt.fx.client.DragEndEvent;
import com.sencha.gxt.fx.client.DragHandler;
import com.sencha.gxt.fx.client.DragMoveEvent;
import com.sencha.gxt.fx.client.DragStartEvent;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer.PortalLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.ResizeEndEvent;
import com.sencha.gxt.widget.core.client.event.ResizeEndEvent.ResizeEndHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.drag.DwDraggable;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardViewGenericTargetIdentifier;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardContainer.ConfigType;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.WriteDto;

public class DashboardView extends DwFlowContainer {

	public interface EditSuccessCallback{
		void onSuccess(DashboardDto updatedDashboard, DadgetDto updatedDadget);
	}

	private final HookHandlerService hookHandler;
	private final Provider<DadgetPanel> dadgetPanelProvider;
	private final SecurityUIService securityService;

	private DashboardDto dashboard;
	private DashboardContainer mainComponent;
	private LayoutTypeDto dashboardLayout;
	private DragHandler dragListener;

	private HashMap<DadgetDto, DadgetPanel> panelMap = new HashMap<DadgetDto, DadgetPanel>();

	private int insertCol = -1, insertRow = -1;
	private XElement dummy;
	private AutoScrollSupport scrollSupport;
	private DadgetPanel active;
	private int startCol;
	private int startRow;
	private ArrayList<Integer> startColumns;

	private PortalLayoutAppearance app = GWT.<PortalLayoutAppearance> create(PortalLayoutAppearance.class);

	private HorizontalLayoutContainer centerContainer;
	private boolean isProtected = false;

	private DwContentPanel topPanel;
	private VerticalLayoutContainer topContainer;
	private int insertTop = -1;

	private DwContentPanel bottomPanel;
	private VerticalLayoutContainer bottomContainer;
	private int insertBottom = -1;
	
	private VerticalLayoutContainer scrollWrapper;
	private DwBorderContainer borderWrapper;
	private double lastFactor = 1;


	@Inject
	public DashboardView(
			HookHandlerService hookHandler,
			Provider<DadgetPanel> dadgetPanelProvider,
			SecurityUIService securityService
			){

		this.hookHandler = hookHandler;
		this.dadgetPanelProvider = dadgetPanelProvider;
		this.securityService = securityService;
		
		addStyleName("rs-dashboard-view");
	}

	protected void initializeUI(final DashboardDto dashboard) {
		setScrollMode(ScrollMode.AUTOY);
		
		scrollWrapper = new VerticalLayoutContainer();
		add(scrollWrapper);
		
		borderWrapper = new DwBorderContainer();
		scrollWrapper.add(borderWrapper, new VerticalLayoutData(1,1));
		
		topContainer = new VerticalLayoutContainer();
		topPanel = DwContentPanel.newInlineInstance(topContainer);
		BorderLayoutData topData = new BorderLayoutData(150); // will be adjusted later
		topData.setCollapseMini(false);
		topData.setCollapseHidden(true);
		topData.setCollapsed(true);
		topData.setSplit(false);
		topData.setMaxSize(Integer.MAX_VALUE);
		borderWrapper.setNorthWidget(topPanel, topData);
		borderWrapper.collapse(LayoutRegion.NORTH);

		centerContainer = new HorizontalLayoutContainer();

		BorderLayoutData centerData = new BorderLayoutData();
		borderWrapper.setCenterWidget(centerContainer, centerData);

		bottomContainer = new VerticalLayoutContainer();
		bottomPanel = DwContentPanel.newInlineInstance(bottomContainer);
		BorderLayoutData bottomData = new BorderLayoutData(150); // will be adjusted later
		bottomData.setCollapseMini(false);
		bottomData.setCollapseHidden(true);
		bottomData.setCollapsed(true);
		bottomData.setSplit(false);
		bottomData.setMaxSize(Integer.MAX_VALUE);
		borderWrapper.setSouthWidget(bottomPanel, bottomData);
		borderWrapper.collapse(LayoutRegion.SOUTH);

		dragListener = createDragListener();
	}

	protected DragHandler createDragListener() {
		return new DragHandler() {

			@Override
			public void onDragCancel(DragCancelEvent event) {
				DashboardView.this.onDragCancel(event);
			}

			@Override
			public void onDragEnd(DragEndEvent event) {
				DashboardView.this.onDragEnd(event);
			}

			@Override
			public void onDragMove(DragMoveEvent event) {
				DashboardView.this.onDragMove(event);
			}

			@Override
			public void onDragStart(DragStartEvent event) {
				DashboardView.this.onDragStart(event);
			}
		};
	}

	public boolean isProtected(){
		return isProtected;
	}

	public void init(DashboardContainer mainComponent, DashboardDto dashboard) {
		boolean isProtected = ! securityService.hasRight(DashboardViewGenericTargetIdentifier.class, WriteDto.class);
		
		init(mainComponent, dashboard, isProtected);
	}

	public void init(DashboardContainer mainComponent, DashboardDto dashboard, boolean isProtected) {
		this.dashboard = dashboard;
		this.mainComponent = mainComponent;
		this.dashboardLayout = dashboard.getLayout();
		this.isProtected  = isProtected;

		/* init layout */
		if(null == centerContainer)
			initializeUI(dashboard);

		/* init columns */
		VerticalLayoutContainer column1 = new VerticalLayoutContainer();

		switch(dashboard.getLayout()){
		case ONE_COLUMN:
			centerContainer.add(column1, new HorizontalLayoutData(1, 1));
			break;
		case TWO_COLUMN_EQUI:
			centerContainer.add(column1, new HorizontalLayoutData(0.5, 1, new Margins(0,10,0,0)));

			VerticalLayoutContainer column2 = new VerticalLayoutContainer();
			centerContainer.add(column2, new HorizontalLayoutData(0.5, 1));
			break;
		case TWO_COLUMN_LEFT_LARGE:
			centerContainer.add(column1, new HorizontalLayoutData(0.66666, 1, new Margins(0,10,0,0)));

			column2 = new VerticalLayoutContainer();
			centerContainer.add(column2, new HorizontalLayoutData(0.333334, 1));
			break;
		case TWO_COLUMN_RIGHT_LARGE:
			centerContainer.add(column1, new HorizontalLayoutData(0.333334, 1, new Margins(0,10,0,0)));

			column2 = new VerticalLayoutContainer();
			centerContainer.add(column2, new HorizontalLayoutData(0.66666, 1));
			break;	
		case THREE_COLUMN:
			centerContainer.add(column1, new HorizontalLayoutData(0.333333, 1, new Margins(0,10,0,0)));

			column2 = new VerticalLayoutContainer();
			centerContainer.add(column2, new HorizontalLayoutData(0.333334, 1, new Margins(0,10,0,0)));

			VerticalLayoutContainer column3 = new VerticalLayoutContainer();
			centerContainer.add(column3, new HorizontalLayoutData(0.333333, 1));
			break;	
		}

		/* init dadgets */
		for(DadgetDto dadget : dashboard.getDadgets()){
			DadgetPanel panel = initDadgetPanel(dadget);

			double height = -1;

			switch(dadget.getContainer()){
			case NORTH:
				topContainer.add(panel, new VerticalLayoutData(1,height, new Margins(0,0,10,0)));
				borderWrapper.expand(LayoutRegion.NORTH);
				break;
			case SOUTH:
				bottomContainer.add(panel, new VerticalLayoutData(1,height, new Margins(0,0,10,0)));
				borderWrapper.expand(LayoutRegion.SOUTH);
				break;
			default:
				((VerticalLayoutContainer)centerContainer.getWidget(Math.max(0,Math.min(centerContainer.getWidgetCount()-1,dadget.getCol())))).add(panel, new VerticalLayoutData(1,height, new Margins(0,0,10,0)));
			}
		}

		adjustTopBottomContainers();
		updatePanelSize();
	}		

	public boolean isSinglePage(){
		return dashboard.isSinglePage();
	}

	protected DadgetPanel initDadgetPanel(final DadgetDto dadget) {
		final DadgetProcessorHook processor = getProcessor(dadget);

		final DadgetPanel panel = dadgetPanelProvider.get();
		panel.init(this, dadget);

		/* height */
		if(dadget.getHeight() < 1)
			dadget.setHeight(250);
		panel.setHeight((int)dadget.getHeight());

		processor.draw(dadget, panel);

		if(! isProtected){
			Resizable r = new Resizable(panel, Resizable.Dir.S);
			r.addResizeEndHandler(new ResizeEndHandler() {

				@Override
				public void onResizeEnd(ResizeEndEvent event) {
					int newHeight = panel.getOffsetHeight();
					onDadgetResize(panel, newHeight);
				}
			});

			Draggable d = new DwDraggable(panel, panel.getHeader());
			d.setUseProxy(true);
			d.addDragHandler(dragListener);
			d.setMoveAfterProxyDrag(false);
			d.setSizeProxyToSource(true);
			d.setEnabled(true);
		}

		panelMap.put(dadget, panel);

		return panel;
	}

	protected void onDadgetResize(DadgetPanel panel, int newHeight) {
		/* tell main component */
		if(panel.getDadget().getHeight() == newHeight)
			return;
	
		if(isSinglePage())
			newHeight = (int)(newHeight/lastFactor);
		
		/* tell main component */
		mainComponent.resizeDadget(dashboard, panel.getDadget(), newHeight);
		
		updatePanelSize();
	}

	protected void updatePanelSize() {
		if(isSinglePage()){
			updatePanelSizeSinglePage();
			return;
		}

		int height = 0;
		
		for(Widget w : topContainer){
			height += ((DadgetPanel)w).getDadget().getHeight() + 10;
			((DadgetPanel)w).setHeight((int)((DadgetPanel)w).getDadget().getHeight()); // needed when swithching from scaled to unscaled
		}
		
		((BorderLayoutData)topPanel.getLayoutData()).setSize(height);// needed when swithching from scaled to unscaled
		
		int bHeight = 0;
		for(Widget w : bottomContainer){
			bHeight += ((DadgetPanel)w).getDadget().getHeight() + 10;
			((DadgetPanel)w).setHeight((int)((DadgetPanel)w).getDadget().getHeight());
		}
		
		((BorderLayoutData)bottomPanel.getLayoutData()).setSize(bHeight);
		
		height += bHeight;
		
		/* get max in center */
		int centerMax = 0;
		for(Widget cont : centerContainer){
			int iMax = 0;
			for(Widget w : (Container)cont){
				iMax += ((DadgetPanel)w).getDadget().getHeight() + 10;
				((DadgetPanel)w).setHeight((int)((DadgetPanel)w).getDadget().getHeight()); // needed when swithching from scaled to unscaled
			}
			
			if(iMax > centerMax)
				centerMax = iMax;
		}
		
		height += centerMax;
		
		scrollWrapper.setHeight(Math.max(getOffsetHeight()-50,height));
		
		centerContainer.forceLayout();
		scrollWrapper.forceLayout();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				centerContainer.forceLayout();
				scrollWrapper.forceLayout();
			}
		});
	}
	
	@Override
	protected void onResize(int width, int height) {
		super.onResize(width, height);
		if(isSinglePage()){
			scrollWrapper.setWidth(width);
			scrollWrapper.setHeight(height);
		}
		adjustTopBottomContainers();
		updatePanelSize();
	}
	
	protected void updatePanelSizeSinglePage() {
		int maxHeight = getOffsetHeight();
		scrollWrapper.setHeight(maxHeight);
		
		/* get Height of all panels and compute number of longest path */
		int path = 0;
		int combinedHeight = 0;
		for(Widget w : topContainer){
			combinedHeight += ((DadgetPanel)w).getDadget().getHeight();
			path++;
		}
		for(Widget w : bottomContainer){
			combinedHeight += ((DadgetPanel)w).getDadget().getHeight();
			path++;
		}
		int centerMaxPath = 0;
		int centerMax = 0;
		for(Widget cont : centerContainer){
			int iMax = 0;
			int iMaxPath = 0;
			for(Widget w : (Container)cont){
				iMax += ((DadgetPanel)w).getDadget().getHeight();
				iMaxPath++;
			}
			
			if(iMax > centerMax){
				centerMax = iMax;
				centerMaxPath = iMaxPath;
			}
		}
		path += centerMaxPath;
		combinedHeight += (centerMax);
		
		/* what now */
		lastFactor = (maxHeight - 10*path) / (double) (combinedHeight);
		
		/* adjust all panels */
		for(DadgetPanel p : getAllDagetPanels())
			p.setHeight((int) (p.getDadget().getHeight() * lastFactor));
		
		/* adjust top and bottom */
		int topheight = 0;
		for(Widget w : topContainer)
			topheight += ((DadgetPanel)w).getOffsetHeight() + 10;
		
		((BorderLayoutData)topPanel.getLayoutData()).setSize(topheight);
		
		int bHeight = 0;
		for(Widget w : bottomContainer)
			bHeight += ((DadgetPanel)w).getOffsetHeight() + 10;
		
		((BorderLayoutData)bottomPanel.getLayoutData()).setSize(bHeight);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				centerContainer.forceLayout();
				scrollWrapper.forceLayout();
			}
		});
	}

	public List<DadgetPanel> getAllDagetPanels() {
		List<DadgetPanel> panels = new ArrayList<DadgetPanel>();
		for(Widget w : topContainer)
			panels.add((DadgetPanel)w);
		for(Widget w : bottomContainer)
			panels.add((DadgetPanel)w);
		for(Widget cont : centerContainer)
			for(Widget w : (Container)cont)
				panels.add((DadgetPanel)w);
		return panels;
	}

	protected DadgetProcessorHook getProcessor(DadgetDto dadget) {
		for(DadgetProcessorHook processor : hookHandler.getHookers(DadgetProcessorHook.class))
			if(processor.consumes(dadget))
				return processor;
		throw new IllegalArgumentException("Could not find dadgetprocessor for " + dadget.getClass());
	}

	public void dashboardChanged(DashboardDto dashboard) {
		if(this.dashboard.getLayout() != dashboardLayout || dashboard.isSinglePage() ^ isSinglePage())
			mainComponent.reload(dashboard);
		else if(! dashboard.getDadgets().isEmpty()){
			DadgetDto newFirst = dashboard.getDadgets().get(0);
			if(!panelMap.containsKey(newFirst)){
				DadgetPanel panel = initDadgetPanel(newFirst);

				getFirstColumn().insert(panel, 0, new VerticalLayoutData(-1,-1, new Margins(0, 0, 10, 0)));
				scrollWrapper.forceLayout();
				getFirstColumn().forceLayout();
			}
		} 
		
		this.dashboard = dashboard;
		updatePanelSize();
	}

	public void removeDadget(DadgetPanel panel) {
		mainComponent.remove(dashboard, panel.getDadget());
		panel.removeFromParent();
		adjustTopBottomContainers();
	}

	public void dadgetConfigured(final DadgetPanel panel, ConfigType type) {
		mainComponent.dadgetConfigured(dashboard, panel.getDadget(), type, new EditSuccessCallback(){
			@Override
			public void onSuccess(DashboardDto updatedDashboard, DadgetDto updatedDadget) {
				panel.clear();
				DadgetDto oldDadget = panel.getDadget();
				panel.updateDadget(updatedDadget);
				
				/* update also dashboard */
				DashboardDto dashboard = panel.getView().getDashboard();
				if(null != dashboard)
					((DashboardDtoDec)dashboard).updateDadget(oldDadget, updatedDadget);
				
				onDadgetResize(panel,updatedDadget.getHeight());
				getProcessor(updatedDadget).draw(updatedDadget, panel);
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						borderWrapper.doLayout();
					}
				});
			}
		});
	}
	
	public DadgetPanel getPanel(DadgetDto dadget){
		return panelMap.get(dadget);
	}

	public VerticalLayoutContainer getFirstColumn(){
		return (VerticalLayoutContainer) (centerContainer.getWidget(0));
	}

	/**
	 * Returns the scroll support instance.
	 * 
	 * @return the scroll support
	 */
	public AutoScrollSupport getAutoScrollSupport() {
		if (scrollSupport == null) {
			scrollSupport = new AutoScrollSupport(this.getElement());
			scrollSupport.setScrollRepeatDelay(100);
			scrollSupport.setScrollDelay(200);
		}
		return scrollSupport;
	}

	protected void onDragCancel(DragCancelEvent event) {
		active.setVisible(true);
		active = null;
		insertCol = -1;
		insertRow = -1;
		insertTop = -1;
		insertBottom = -1;
		dummy.removeFromParent();
		getAutoScrollSupport().stop();
	}

	protected void onDragEnd(DragEndEvent event) {
		dummy.removeFromParent();

		VerticalLayoutContainer containerToAdjust  = null;
		if (insertCol != -1 && insertRow != -1) {
			if (startCol == insertCol && insertRow > startRow) {
				insertRow--;
			}
			active.setVisible(true);
			active.removeFromParent();
			((VerticalLayoutContainer)centerContainer.getWidget(insertCol)).insert(active, insertRow);
			((VerticalLayoutContainer)centerContainer.getWidget(insertCol)).forceLayout();

			active.getDadget().setCol(insertCol);
			active.getDadget().setContainer(DadgetContainerDto.CENTER);

			containerToAdjust = ((VerticalLayoutContainer)centerContainer.getWidget(insertCol));

			/* fix n */
			int n = 0;
			for(Widget w : containerToAdjust)
				((DadgetPanel)w).getDadget().setN(n++);
			
			mainComponent.dadgetConfigured(dashboard, active.getDadget(), ConfigType.MISC, new EditSuccessCallback() {
				@Override
				public void onSuccess(DashboardDto updatedDashboard, DadgetDto updatedDadget) {
					getPanel(updatedDadget).onMove();
				}
			});
		} else if(insertTop != -1 || insertBottom != -1){
			VerticalLayoutContainer container = insertTop != -1 ? topContainer : bottomContainer;
			int pos = insertTop != -1 ? insertTop : insertBottom;

			if(active.getParent() == container){
				int i = 0;
				for(Widget w : container){
					if(w== active)
						break;
					i++;
				}
				if((i >= pos || pos >= container.getWidgetCount() )&& pos > 0)
					pos--;
			}

			active.getDadget().setContainer( insertTop != -1 ? DadgetContainerDto.NORTH : DadgetContainerDto.SOUTH);

			active.removeFromParent();

			/* adjust width and margins */
			((VerticalLayoutData)active.getLayoutData()).setWidth(1);
			((VerticalLayoutData)active.getLayoutData()).setMargins(new Margins(0,0,10,0));

			container.insert(active, pos);
			containerToAdjust = container;
			
			/* fix n */
			int n = 0;
			for(Widget w : container)
				((DadgetPanel)w).getDadget().setN(n++);
			
			mainComponent.dadgetConfigured(dashboard, active.getDadget(), ConfigType.MISC, new EditSuccessCallback() {
				@Override
				public void onSuccess(DashboardDto updatedDashboard, DadgetDto updatedDadget) {
					getPanel(updatedDadget).onMove();
				}
			});
		} 

		active.setVisible(true);
		active = null;
		insertCol = -1;
		insertRow = -1;
		insertTop = -1;
		insertBottom = -1;

		adjustTopBottomContainers();

		getAutoScrollSupport().stop();
		
		updatePanelSize();

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				scrollWrapper.forceLayout();
				borderWrapper.doLayout();
			}
		});
	}

	protected void onDragLeave(DragEvent de) {
		getAutoScrollSupport().stop();
	}

	protected void onDragMove(DragMoveEvent event) {
		int col = getColumn(event.getNativeEvent().getClientX());
		int row = getRowPosition(col, event.getNativeEvent().getClientY());
		int adjustRow = row;
		if (startCol == col && row > startRow) {
			adjustRow--;
		}

		if(isInPanel(event, topPanel)){
			int pos = getRowPosition(event.getNativeEvent().getClientY(), topContainer);
			addInsertTopDummy(pos);
		} else if(isInPanel(event, bottomPanel)){
			int pos = getRowPosition(event.getNativeEvent().getClientY(), bottomContainer);
			addInsertBottomDummy(pos);
		} else if (col != insertCol || adjustRow != insertRow) 
			addInsertDummy(col, adjustRow);
	}

	protected void onDragStart(DragStartEvent event) {
		active = (DadgetPanel) event.getTarget();

		showTopBottomForDrag();

		if (dummy == null) {
			SafeHtmlBuilder sb = new SafeHtmlBuilder();
			app.renderInsert(sb);
			dummy = XDOM.create(sb.toSafeHtml()).cast();
			dummy.addClassName("rs-dummy");
		}

		dummy.getStyle().setProperty("padding", active.getElement().getStyle().getPadding());

		int h = active.getElement().getOffsetHeight() - active.getElement().getFrameWidth(Side.TOP, Side.BOTTOM);
		dummy.getFirstChildElement().<XElement> cast().setHeight(h);

		startColumns = new ArrayList<Integer>();
		for (int i = 0; i < getNumberOfColumns(); i++) {
			VerticalLayoutContainer con = (VerticalLayoutContainer) centerContainer.getWidget(i);
			int x = con.getAbsoluteLeft();
			startColumns.add(x);
		}
		startCol = getColumn(event.getNativeEvent().getClientX());
		startRow = getRow(startCol, event.getY());
		active.setVisible(false);
		addInsertDummy(startCol, startRow);

		getAutoScrollSupport().start();
	}

	private void showTopBottomForDrag() {
		if(isSinglePage()){
			showTopBottomSinglePage();
			return;
		}
			
		int addHeight = 0;
		if(0 == topContainer.getWidgetCount()) {
			((BorderLayoutData)topPanel.getLayoutData()).setSize(150);
			addHeight += 150;
		} 
		if(0 == bottomContainer.getWidgetCount()){
			((BorderLayoutData)bottomPanel.getLayoutData()).setSize(150);
			addHeight += 150;
		}
		boolean hasCenter = false;
		for(Widget c : centerContainer)
			hasCenter |= ((Container)c).getWidgetCount() > 0;

		if(! hasCenter)
			addHeight += 400;
		
		if(addHeight > 0)
			scrollWrapper.setHeight(scrollWrapper.getOffsetHeight()+addHeight);
		
		borderWrapper.expand(LayoutRegion.NORTH);
		borderWrapper.expand(LayoutRegion.SOUTH);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				centerContainer.forceLayout();
				scrollWrapper.forceLayout();
			}
		});
	}

	private void showTopBottomSinglePage() {
		((BorderLayoutData)topPanel.getLayoutData()).setSize(150);
		((BorderLayoutData)bottomPanel.getLayoutData()).setSize(150);
		
		/* adapt sizes 
		 * for this we take the number of dadgets on the longest path (number of dadgets not height) and distribute
		 * everything equally */
		List<DadgetPanel> panels = new ArrayList<DadgetPanel>();
		for(Widget w : topContainer)
			panels.add((DadgetPanel) w);
		for(Widget w : bottomContainer)
			panels.add((DadgetPanel) w);
		
		int maxContainer = 0;
		int maxCnt = 0;
		for(int i = 0 ; i < centerContainer.getWidgetCount(); i++){
			if(((Container)centerContainer.getWidget(i)).getWidgetCount() > maxCnt){
				maxCnt = ((Container)centerContainer.getWidget(i)).getWidgetCount();
				maxContainer = i;
			}
		}
		for(Widget w : (Container)centerContainer.getWidget(maxContainer))
			panels.add((DadgetPanel) w);
		
		/* adapt all dadgets (remove 150 for good measure) */
		int newHeight = (getOffsetHeight()-150) / panels.size();
		for(DadgetPanel p : getAllDagetPanels())
			p.setHeight(newHeight);
		
		/* adapt top bottom container if it contains widgets */
		if(topContainer.getWidgetCount() > 0)
			((BorderLayoutData)topPanel.getLayoutData()).setSize((newHeight+5)*topContainer.getWidgetCount());
		if(bottomContainer.getWidgetCount() > 0)
			((BorderLayoutData)bottomPanel.getLayoutData()).setSize((newHeight+5)*bottomContainer.getWidgetCount());
			
		
		borderWrapper.expand(LayoutRegion.NORTH);
		borderWrapper.expand(LayoutRegion.SOUTH);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				centerContainer.forceLayout();
				scrollWrapper.forceLayout();
			}
		});
	}

	private void adjustTopBottomContainers() {
		if(topContainer.getWidgetCount() == 0)
			borderWrapper.collapse(LayoutRegion.NORTH);
		if(bottomContainer.getWidgetCount() == 0)
			borderWrapper.collapse(LayoutRegion.SOUTH);
	}

	private void addInsertTopDummy(int pos) {
		insertTop  = pos;

		insertBottom = -1;
		insertCol = -1;
		insertRow = -1;

		dummy.removeFromParent();
		topContainer.getElement().insertChild(dummy, pos);
	}

	private void addInsertBottomDummy(int pos) {
		insertBottom = pos;

		insertTop = -1;
		insertCol = -1;
		insertRow = -1;

		dummy.removeFromParent();
		bottomContainer.getElement().insertFirst(dummy);
	}

	private void addInsertDummy(int col, int row) {
		insertTop = -1;
		insertBottom = -1;

		insertCol = col;
		insertRow = row;

		VerticalLayoutContainer lc = (VerticalLayoutContainer) centerContainer.getWidget(insertCol);

		dummy.removeFromParent();
		lc.getElement().insertChild(dummy, row);
	}
	
	private int getNumberOfColumns() {
		switch (dashboardLayout) {
		case ONE_COLUMN:
			return 1;
		case THREE_COLUMN:
			return 3;
		default:
			return 2;
		}
	}

	private boolean isInPanel(DragMoveEvent event, DwContentPanel panel) {
		int top = panel.getAbsoluteTop();
		int bottom = panel.getAbsoluteTop() + panel.getOffsetHeight();

		int y = event.getNativeEvent().getClientY();

		return y > top && y < bottom;
	}


	private int getColumn(int x) {
		x += XDOM.getBodyScrollLeft();
		for (int i = startColumns.size() - 1; i >= 0; i--) {
			if (x > startColumns.get(i)) {
				return i;
			}
		}
		return 0;
	}

	private int getRow(int col, int y) {
		VerticalLayoutContainer con = (VerticalLayoutContainer) centerContainer.getWidget(col);
		return getRow(y, con);
	}

	private int getRow(int y, VerticalLayoutContainer con){
		y += XDOM.getBodyScrollTop();
		int count = con.getWidgetCount();

		for (int i = 0; i < count; i++) {
			Widget c = con.getWidget(i);
			int b = c.getAbsoluteTop();
			int t = b + c.getOffsetHeight();

			if (y < t) {
				return i;
			}
		}

		return 0;
	}

	private int getRowPosition(int col, int y) {
		VerticalLayoutContainer con = (VerticalLayoutContainer) centerContainer.getWidget(col);
		return getRowPosition(y, con);
	}

	private int getRowPosition(int y, VerticalLayoutContainer con) {
		y += XDOM.getBodyScrollTop();
		List<Widget> list = new ArrayList<Widget>();
		for (int i = 0; i < con.getWidgetCount(); i++) {
			list.add(con.getWidget(i));
		}
		int count = list.size();

		for (int i = 0; i < count; i++) {
			Widget c = list.get(i);

			int b = c.getAbsoluteTop();
			int t = b + c.getOffsetHeight();
			int m = b + (c.getOffsetHeight() / 2);
			if (y < t) {
				if (y < m) {
					return i;
				} else {
					return i + 1;
				}
			}
		}
		return count;
	}
	
	public DashboardDto getDashboard() {
		return dashboard;
	}

	public void makeAwareOfSelection() {
		for(DadgetPanel panel : getAllDagetPanels())
			panel.makeAwareOfSelection();
	}

	public void doForceLayout() {
		if(null != scrollWrapper)
			scrollWrapper.onResize();
		
		adjustTopBottomContainers();
		updatePanelSize();
	}

}
