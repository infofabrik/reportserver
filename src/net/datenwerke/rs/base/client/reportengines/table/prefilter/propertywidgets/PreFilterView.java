package net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreAddEvent.StoreAddHandler;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent.StoreRemoveHandler;
import com.sencha.gxt.data.shared.event.TreeStoreRemoveEvent;
import com.sencha.gxt.dnd.core.client.DND.Operation;
import com.sencha.gxt.dnd.core.client.DND.TreeSource;
import com.sencha.gxt.dnd.core.client.DndDragEnterEvent;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.TreeGridDragSource;
import com.sencha.gxt.dnd.core.client.TreeGridDropTarget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent.BeforeShowContextMenuHandler;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.grid.DwTreeGrid;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardCopyProcessor;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardDtoPasteProcessor;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterSpecDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.hooks.PreFilterConfiguratorHook;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.locale.PreFilterMessages;
import net.datenwerke.rs.core.client.contexthelp.ContextHelpUiService;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class PreFilterView extends ReportExecutorMainPanelView {

	public static final String VIEW_ID = "prefilter";

	public interface InstantiatePreFilterCallback{
		void filterInstantiated(FilterSpecDto filter);
	}
	
	public interface EditPreFilterCallback{
		void editDone();
	}
	
	private static final PreFilterMessages messages = GWT.create(PreFilterMessages.class);
	
	private final ClipboardUiService clipboardService; 
	private final HookHandlerService hookHandler;
	private final ToolbarService toolbarService;
	private final ContextHelpUiService contextHelpService;
	
	private List<PreFilterConfiguratorHook> preFilterConfigs;
	
	private TableReportDto report;
	
	private TreeStore<RsDto> store;
	
	private ToolBar toolbar;
	private TreeGrid<RsDto> treegrid;
	
	private DwNorthSouthContainer mainPanel;

	private boolean initialized;


	
	@Inject
	public PreFilterView(
		ClipboardUiService clipboardService,
		HookHandlerService hookHandler,
		ContextHelpUiService contextHelpService,
		ToolbarService toolbarService
		){
		
		/* store objects */
		this.clipboardService = clipboardService;
		this.hookHandler = hookHandler;
		this.contextHelpService = contextHelpService;
		this.toolbarService = toolbarService;
	}
	
	@Override
	public String getViewId(){
		return VIEW_ID;
	}
	
	@Override
	public String getComponentHeader() {
		return messages.preFilterHeading(); 
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.FILTER.toImageResource(); 
	}
	
	@Override
	public boolean allowsDropOf(Object m) {
		return false;
	}
	
	public void setReport(TableReportDto report) {
		this.report = report;
	}
	
	@Override
	public Component getViewComponent() {
		/* init */
		initialize();

		/* main panel */
		mainPanel = new DwNorthSouthContainer();
		mainPanel.setNorthWidget(toolbar);
		mainPanel.setWidget(treegrid);
		
		initClipboard();
		
		return mainPanel;
	}
	
	private void initialize() {
		if(initialized)
			return;
		
		/* filter configs */
		preFilterConfigs = hookHandler.getHookers(PreFilterConfiguratorHook.class);
	
		initStore();
		createToolbar();
		createTableGrid();
		initialized = true;
	}

	protected void initClipboard() {
		clipboardService.registerCopyHandler(treegrid, new ClipboardCopyProcessor() {
			@Override
			public ClipboardItem getItem() {
				return createClipboardItemFromSelected();
			}
		});
		
		clipboardService.registerPasteHandler(treegrid, new ClipboardDtoPasteProcessor(FilterBlockDto.class) {
			@Override
			protected void doPaste(ClipboardDtoItem dtoItem) {
				handlePasteBlock(dtoItem);
			}
		});
		
		clipboardService.registerPasteHandler(treegrid, new ClipboardDtoPasteProcessor(FilterSpecDto.class) {
			@Override
			protected void doPaste(ClipboardDtoItem dtoItem) {
				handlePasteFilter(dtoItem);
			}
		});
	}

	protected void handlePasteFilter(ClipboardDtoItem dtoItem) {
		if(! report.getParentNodeId().equals(dtoItem.getAdditionalInfo()))
			return;
		
		importFilter((FilterSpecDto)dtoItem.getDto());
	}

	protected void handlePasteBlock(ClipboardDtoItem dtoItem) {
		if(! report.getParentNodeId().equals(dtoItem.getAdditionalInfo()))
			return;
		
		importBlock((FilterBlockDto)dtoItem.getDto());
	}

	protected ClipboardItem createClipboardItemFromSelected() {
		Object item = treegrid.getSelectionModel().getSelectedItem();
		if(null != item)
			return new ClipboardDtoItem((Dto) item, report.getParentNodeId());
		return null;
	}

	protected void importBlock(FilterBlockDto subBlock) {
		RsDto selectedItem = treegrid.getSelectionModel().getSelectedItem();
		if(null == selectedItem || !( selectedItem instanceof FilterBlockDto))
			return;
		FilterBlockDto block = (FilterBlockDto) selectedItem;
		
		/* insert */
		addToStore(block, ((FilterBlockDtoDec)subBlock).cloneFilterBlock());
	}

	protected void importFilter(FilterSpecDto filter) {
		RsDto selectedItem = treegrid.getSelectionModel().getSelectedItem();
		if(null == selectedItem || !( selectedItem instanceof FilterBlockDto))
			return;
		FilterBlockDto block = (FilterBlockDto) selectedItem;
		
		/* insert */
		store.add(block, ((FilterSpecDtoDec)filter).cloneFilter());
	}

	private void initStore() {
	    /* crete store */
		store = new TreeStore<RsDto>(new DtoIdModelKeyProvider());
		store.setAutoCommit(true);
		
		store.addStoreAddHandler(new StoreAddHandler<RsDto>() {

			@Override
			public void onAdd(StoreAddEvent<RsDto> event) {
				for(RsDto item : event.getItems()){
					RsDto parentBlockModel = store.getParent(item);
					
					if(null == parentBlockModel && store.getRootItems().size() > 0 && ! store.getRootItems().get(0).equals(item))
						throw new IllegalStateException("Can only have one root");
					else if (null == parentBlockModel)
						return;
					
					if(! (parentBlockModel instanceof FilterBlockDto))
						throw new IllegalStateException("Expected FilterBlockDto");
	
					FilterBlockDtoDec parentBlock = (FilterBlockDtoDec) parentBlockModel;
					
					if(item instanceof FilterBlockDto){
						parentBlock.addChildBlock((FilterBlockDto)item);
						
						/* update mode */
						if(BlockTypeDto.AND == parentBlock.getBlockType())
							((FilterBlockDtoDec)item).setBlockType(BlockTypeDto.OR);
						else
							((FilterBlockDtoDec)item).setBlockType(BlockTypeDto.AND);
					} else {
						parentBlock.addFilter((FilterSpecDto)item);
					}
				}
			}
		});
		
		store.addStoreRemoveHandler(new StoreRemoveHandler<RsDto>() {
			@Override
			public void onRemove(StoreRemoveEvent<RsDto> event) {
				TreeStoreRemoveEvent<RsDto> tevent = (TreeStoreRemoveEvent<RsDto>) event;
				
				RsDto item = event.getItem();
				RsDto parentBlockModel = tevent.getParent();
				
				if(! (parentBlockModel instanceof FilterBlockDto))
					throw new IllegalStateException("Expected FilterBlockDto");
				
				FilterBlockDtoDec parentBlock = (FilterBlockDtoDec) parentBlockModel;

				RsDto toBeRemovedModel = item;
				if(toBeRemovedModel instanceof FilterBlockDto )
					parentBlock.removeChildBlock((FilterBlockDto)toBeRemovedModel);
				else
					parentBlock.removeChildFilter((FilterSpecDto)toBeRemovedModel);
			}
		});
		
		addToStore(null, report.getPreFilter().getRootBlock());
		
		report.addInstanceChangedHandler(new ObjectChangedEventHandler<Dto>() {
			
			@Override
			public void onObjectChangedEvent(ObjectChangedEvent<Dto> event) {
				if(!initialized)
					return;

				store.clear();
				addToStore(null, report.getPreFilter().getRootBlock());
			}
		});
		
		store.addSortInfo(new StoreSortInfo<RsDto>(new Comparator<RsDto>(){
			@Override
			public int compare(RsDto m1, RsDto m2) {
				if(m1 instanceof FilterBlockDto && m2 instanceof FilterSpecDto)
					return 1;
				if(m2 instanceof FilterBlockDto && m1 instanceof FilterSpecDto)
					return -1;
				
				return 0;
			}
		}, SortDir.ASC));
	}

	private void addToStore(FilterBlockDto parent, FilterBlockDto block) {
		if(null == parent)
			store.add(block);
		else
			store.add(parent, block);
		for(FilterSpecDto filter : block.getFilters())
			store.add(block, filter);
		for(FilterBlockDto subBlock : block.getChildBlocks())
			addToStore(block, subBlock);
	}

	private void createTableGrid() {
		/* create columns */
		ColumnConfig<RsDto, RsDto> name = new ColumnConfig<RsDto,RsDto>(new IdentityValueProvider<RsDto>("__name"), 200, BaseMessages.INSTANCE.name());
		name.setCell(new AbstractCell<RsDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					RsDto model, SafeHtmlBuilder sb) {
				if(model instanceof FilterBlockDto) {
					switch(((FilterBlockDtoDec)model).getBlockType()){
					case AND:
						sb.appendEscaped(messages.andBlockName());
						break;
					default:
						sb.appendEscaped(messages.orBlockName());
					}
				} else if (model instanceof FilterSpecDto) {
					sb.appendEscaped(((FilterSpecDto)model).toDisplayTitle());
				} else
					throw new IllegalArgumentException("Expected FilterBlock or FilterSpec");
			}
		});
		name.setSortable(false);
		name.setMenuDisabled(true);
		
		ColumnConfig<RsDto, String> description = new ColumnConfig<RsDto,String>(new ValueProvider<RsDto, String>() {
			@Override
			public String getValue(RsDto model) {
				if(model instanceof FilterBlockDto && null != ((FilterBlockDto) model).getDescription())
					return ((FilterBlockDto) model).getDescription();
				else if (model instanceof FilterSpecDto && null != ((FilterSpecDto) model).getDescription()) 
					return ((FilterSpecDto) model).getDescription();
				else return "";
			}

			@Override
			public void setValue(RsDto model, String value) {
				if(model instanceof FilterBlockDto)
					((FilterBlockDto) model).setDescription(value);
				else if (model instanceof FilterSpecDto) 
					((FilterSpecDto) model).setDescription(value);
				else
					throw new IllegalArgumentException("Expected FilterBlock or FilterSpec");
			}

			@Override
			public String getPath() {
				return "description";
			}
		}, 300, BaseMessages.INSTANCE.description());
		description.setSortable(false);
		description.setMenuDisabled(true);
		
		
		List<ColumnConfig<RsDto, ?>> cols = new ArrayList<ColumnConfig<RsDto, ?>>();
		cols.add(name);
		cols.add(description);
		
	    /* create grid */
		treegrid = new DwTreeGrid<RsDto>(store, new ColumnModel<RsDto>(cols), name);
		treegrid.setAutoExpand(true);
		treegrid.getView().setAutoExpandColumn(description);  
		
		/* edit description */
		GridEditing<RsDto> editing = new GridInlineEditing<RsDto>(treegrid);
		editing.addEditor(description, new TextField());
		
		/* icons */
		treegrid.setIconProvider(new IconProvider<RsDto>() {
			
			@Override
			public ImageResource getIcon(RsDto model) {
				if(model instanceof FilterBlockDto) {
					switch(((FilterBlockDtoDec)model).getBlockType()){
					case AND:
						return BaseIcon.LOGICAL_AND.toImageResource();
					default:
						return BaseIcon.LOGICAL_OR.toImageResource();
					}
				} else if (model instanceof FilterSpecDto) {
					return null;
				} else
					throw new IllegalArgumentException("Expected FilterBlock or FilterSpec");
			}
		});
		treegrid.getStyle().setLeafIcon(null);
		
		/* selection */
		treegrid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		treegrid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				displayEditFilterDialog();
			}
		});
		
		/* drag drop */
		TreeGridDragSource<RsDto> source = new TreeGridDragSource<RsDto>(treegrid){
			protected void onDragStart(com.sencha.gxt.dnd.core.client.DndDragStartEvent event) {
				TreeNode<RsDto> node = treegrid.findNode(event.getDragStartEvent().getStartElement());
				if(null == node){
					event.setCancelled(true);  
					event.getStatusProxy().setStatus(false);  
					return;
				}

				RsDto source = (RsDto) node.getModel();
				if(null == source || store.getRootItems().get(0).equals(source)){
					event.setCancelled(true);  
					event.getStatusProxy().setStatus(false);  
					return;
				}

				/* call super class */
				super.onDragStart(event);
			};
		};
        TreeGridDropTarget<RsDto> target = new TreeGridDropTarget<RsDto>(treegrid){
        	
        	@Override
        	protected void onDragMove(DndDragMoveEvent event) {
        		try{
        			super.onDragMove(event);
        			
        			final TreeNode<RsDto> targetNode = treegrid.findNode(
        			        event.getDragMoveEvent().getNativeEvent().getEventTarget().<Element> cast());
        			
        			if(!handleTheDragEvent(targetNode)){
        				event.setCancelled(true);  
 				        event.getStatusProxy().setStatus(false);
        			}
        		}catch(Exception ex){
        			ex.printStackTrace();
        		}
        	}
        	
        	@Override
        	protected void onDragEnter(DndDragEnterEvent event) {
        		try{
        			super.onDragEnter(event);
        			
        			final TreeNode<RsDto> targetNode = treegrid.findNode(
        			        event.getDragEnterEvent().getNativeEvent().getEventTarget().<Element> cast());
        			
        			if(!handleTheDragEvent(targetNode)){
        				event.setCancelled(true);  
 				        event.getStatusProxy().setStatus(false);
        			}
        		}catch(Exception ex){
        			ex.printStackTrace();
        		}
        	}
        	
        	private boolean handleTheDragEvent(TreeNode<RsDto> targetNode) {
        		if(null != targetNode){
        			RsDto targetDto = (RsDto) targetNode.getModel();
        			if(targetDto instanceof FilterSpecDto){
        				return false;
        			} 
        		} else {
        			return false;
        		}
        		return true;
			}
        	
        };

        target.setAllowSelfAsSource(true);
        target.setAllowDropOnLeaf(true);
        target.setOperation(Operation.MOVE);
        source.setTreeGridSource(TreeSource.BOTH);
        
        /* menu */
        Menu ctxMen = new DwMenu();
		
		final MenuItem copyItem = new DwMenuItem(BaseMessages.INSTANCE.copy());
		copyItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ClipboardItem clipboardItem = createClipboardItemFromSelected();
				clipboardService.setClipboardItem(clipboardItem);
			}
		});
		ctxMen.add(copyItem);
		
		final MenuItem pasteItem = new DwMenuItem(BaseMessages.INSTANCE.paste());
		pasteItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ClipboardItem clipboardItem = clipboardService.getClipboardItem();
				
				if(null == clipboardItem || ! (clipboardItem instanceof ClipboardDtoItem))
					return;
							
				Dto dto = ((ClipboardDtoItem) clipboardItem).getDto();
				
				if(dto instanceof FilterSpecDto)
					handlePasteFilter((ClipboardDtoItem) clipboardItem);
				else if(dto instanceof FilterBlockDto)
					handlePasteBlock((ClipboardDtoItem) clipboardItem);
			}
		});
		ctxMen.add(pasteItem);
		
		
		treegrid.setContextMenu(ctxMen);

		treegrid.addBeforeShowContextMenuHandler(new BeforeShowContextMenuHandler() {
			@Override
			public void onBeforeShowContextMenu(BeforeShowContextMenuEvent event) {
				/* clipboard */
				pasteItem.setEnabled(true);
				ClipboardItem clipboardItem = clipboardService.getClipboardItem();
				if(null == clipboardItem || ! (clipboardItem instanceof ClipboardDtoItem))
					pasteItem.setEnabled(false);
				else {
					Dto item = ((ClipboardDtoItem) clipboardItem).getDto();
					if(! (item instanceof FilterSpecDto) && ! (item instanceof FilterBlockDto))
						pasteItem.setEnabled(false);
				}
				
				copyItem.setEnabled(true);
				RsDto selected = treegrid.getSelectionModel().getSelectedItem();
				if(null == selected)
					copyItem.setEnabled(false);

			}
		});
	}

	private void createToolbar() {
		toolbar = new DwToolBar();
		
		/* add block */
		DwTextButton addBlockButton = toolbarService.createSmallButtonLeft(messages.addBlockLabel(), BaseIcon.PLUS_SQUARE);
		addBlockButton.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				addBlock();
			}
		});
		toolbar.add(addBlockButton);
	
		/* add filter */
		DwTextButton addFilterButton = toolbarService.createSmallButtonLeft(messages.addFilterLabel(), BaseIcon.FILTER_ADD);
		toolbar.add(addFilterButton);
		
		/* create filter menu */
		Menu filterMenu = new DwMenu();
		addFilterButton.setMenu(filterMenu);
		addFilterButton.setArrowAlign(ButtonArrowAlign.RIGHT);
		
		for(final PreFilterConfiguratorHook filterConfig : preFilterConfigs){
			MenuItem item = new DwMenuItem(filterConfig.getHeadline(), filterConfig.getIcon());
			filterMenu.add(item);
			
			item.addSelectionHandler(new SelectionHandler<Item>() {
				
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					addFilter(filterConfig);
				}
			});
		}
		
		toolbar.add(new SeparatorToolItem());
		
		DwSplitButton toggleAndOrButton = new DwSplitButton(PreFilterMessages.INSTANCE.toggleAndOrBtnLabel());
		toggleAndOrButton.setIcon(BaseIcon.TOGGLE_ON);
		toggleAndOrButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toggleAndOr();
			}
		});
		toolbar.add(toggleAndOrButton);
		
		toolbar.add(new SeparatorToolItem());
		
		
		/* remove */
		DwSplitButton deleteColumnButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
		deleteColumnButton.setIcon(BaseIcon.DELETE);
		deleteColumnButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				removeSelected();
			}
		});
		Menu deleteMenu = new DwMenu();
		deleteColumnButton.setMenu(deleteMenu);
		toolbar.add(deleteColumnButton);
		
		MenuItem deleteAllColumnsItem = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		deleteMenu.add(deleteAllColumnsItem);
		deleteAllColumnsItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ConfirmMessageBox cmb = new DwConfirmMessageBox(messages.removeAllConfirmHeading(), messages.removeAllConfirmText());
				cmb.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton() == PredefinedButton.YES)
							removeAll();
					}
				});
				cmb.show();
			}
		});
		
		toolbar.add(new FillToolItem());
		
		/* info */
		if(contextHelpService.isEnabled()){
			ContextHelpInfo contextHelpInfo = new ContextHelpInfo("dynamiclist/prefilter");
			DwTextButton btn = contextHelpService.createDwTextButton(contextHelpInfo);
			toolbar.add(btn);
		}

	}

	protected void toggleAndOr() {
		List<RsDto> roots = store.getRootItems();
		if(null == roots || roots.size() != 1 || !(roots.get(0) instanceof FilterBlockDto))
			throw new IllegalArgumentException("Expected one root of type FilterBlockDto");

		FilterBlockDtoDec root = (FilterBlockDtoDec)roots.get(0);
		report.getPreFilter().setRootBlockType(root.getBlockType() == BlockTypeDto.OR ? BlockTypeDto.AND : BlockTypeDto.OR);
		
		toggleAndOr(root);
	}

	protected void toggleAndOr(FilterBlockDtoDec block) {
		switch(block.getBlockType()){
		case OR:
			block.setBlockType(BlockTypeDto.AND);
			break;
		default:
			block.setBlockType(BlockTypeDto.OR);
			break;
		}
		store.update(block);
		for(FilterBlockDto child : block.getChildBlocks())
			toggleAndOr((FilterBlockDtoDec) child);
	}

	protected void removeSelected() {
		final RsDto item = treegrid.getSelectionModel().getSelectedItem();
		if(null == item)
			return;
		
		if(store.getRootItems().contains(item))
			return;
		
		if(store.hasChildren(item)){
			ConfirmMessageBox cmb = new DwConfirmMessageBox(messages.removeSelectedConfirmHeading(), messages.removeSelectedConfirmText());
			cmb.addDialogHideHandler(new DialogHideHandler() {
				
				@Override
				public void onDialogHide(DialogHideEvent event) {
					if (event.getHideButton() == PredefinedButton.YES)
						doRemove(item);	
				}
			});
			cmb.show();
		} else 
			doRemove(item);
	}

	protected void doRemove(RsDto item) {
		store.remove(item);
	}

	protected void addBlock() {
		RsDto item = treegrid.getSelectionModel().getSelectedItem();
		if(null == item)
			item = store.getRootItems().get(0);
		
		if(! isBlock(item))
			item = store.getParent(item);

		if(! isBlock(item)) {
			displayNoBlockSelectedError();
			return;
		}
		FilterBlockDtoDec currentBlock = (FilterBlockDtoDec) item;
		
		/* create block */
		FilterBlockDtoDec newBlock = new FilterBlockDtoDec();
		newBlock.setBlockType(currentBlock.getBlockType() == BlockTypeDto.AND ? BlockTypeDto.OR : BlockTypeDto.AND);
		
		/* insert */
		store.add(item, newBlock);
		
		/* expand parent */
		treegrid.setExpanded(item,true);
	}
	
	private void displayNoBlockSelectedError() {
		new DwAlertMessageBox(BaseMessages.INSTANCE.error(), messages.noBlockSelectedText()).show();
	}

	protected boolean isBlock(RsDto item) {
		return null != item && (item instanceof FilterBlockDto);
	}
	
	protected boolean isFilter(RsDto item) {
		return null != item && (item instanceof FilterSpecDto);
	}

	protected void addFilter(final PreFilterConfiguratorHook filterConfig) {
		RsDto prelimItem = treegrid.getSelectionModel().getSelectedItem();
		if(null == prelimItem)
			prelimItem = store.getRootItems().get(0);
		
		if(! isBlock(prelimItem))
			prelimItem = store.getParent(prelimItem);
			
		if(! isBlock(prelimItem)) {
			displayNoBlockSelectedError();
			return;
		}
		
		final RsDto item = prelimItem;
		
		/* create filter */
		mainPanel.mask();
		filterConfig.instantiateFilter(report, getExecuteReportToken(), new InstantiatePreFilterCallback(){

			@Override
			public void filterInstantiated(FilterSpecDto filter) {
				if(null == filter){
					mainPanel.unmask();
					return;
				}
				
				/* insert */
				store.add(item, filter);
				
				/* expand parent */
				treegrid.setExpanded(item,true);
				
				/* display after instantiated */
				filterConfig.filterInstantiated(report, (FilterSpecDto)filter, getExecuteReportToken(), new EditPreFilterCallback(){
					@Override
					public void editDone() {
						mainPanel.unmask();
						store.update(item);
					}
				});
			}
			
		});
	}


	protected void removeAll() {
		store.clear();
		((PreFilterDtoDec)report.getPreFilter()).clearFilter();
		addToStore(null, report.getPreFilter().getRootBlock());
	}
	
	protected void displayEditFilterDialog() {
		final RsDto item = treegrid.getSelectionModel().getSelectedItem();
		if(! isFilter(item))
			return;
		
		for(PreFilterConfiguratorHook filterConfig : preFilterConfigs){
			if(filterConfig.consumes((FilterSpecDto)item)){
				mainPanel.mask();
				filterConfig.displayFilter(report, (FilterSpecDto)item, getExecuteReportToken(), new EditPreFilterCallback(){
					@Override
					public void editDone() {
						mainPanel.unmask();
						store.update(item);
					}
				});
				break;
			}
		}
	}

}
