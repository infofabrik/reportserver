package net.datenwerke.gf.client.fileselection;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.event.StoreHandlers;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent.BeforeShowContextMenuHandler;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent.BeforeStartEditHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.download.FileDownloadUiService;
import net.datenwerke.gf.client.download.dto.DownloadProperties;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapperPA;
import net.datenwerke.gf.client.fileselection.locale.FileSelectionMessages;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoTypeAwareIdProvider;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.CssIconImageResource;

public class FileSelectionWidget extends VerticalLayoutContainer {

	public static interface SubmitHandler{
		void onSuccess(FileSelectionWidget fileSelectionWidget);
		void onFailure(FileSelectionWidget fileSelectionWidget, Throwable t);
	}
	
	protected class FileSelectionWrapperKeyProvider implements ModelKeyProvider<SelectedFileWrapper>{
		
		private DtoTypeAwareIdProvider provider = new DtoTypeAwareIdProvider();
		
		@Override
		public String getKey(SelectedFileWrapper item) {
			return provider.getKey(item);
		}
	}
	
	private final FileSelectionDao fileSelectionDao;
	private final FileDownloadUiService downloadService;
	
	private Grid<SelectedFileWrapper> grid;
	private ToolBar toolbar;
	private ListStore<SelectedFileWrapper> fileStore;
	private List<FileSelectorSource> sources = new ArrayList<FileSelectorSource>();
	

	private int minFiles = 0;
	private int maxFiles = 0;
	
	private int gridHeight = 150;

	private FileSelectionConfig config;

	private Menu contextMenu;


	@Inject
	public FileSelectionWidget(
		FileSelectionDao fileSelectionDao,
		FileDownloadUiService downloadService
		) {
		super();
		
		this.fileSelectionDao = fileSelectionDao;
		this.downloadService = downloadService;
		
		setBorders(true);
		addStyleName("rs-fileselector");
	}
	
	public void build(FileSelectionConfig config){
		this.config = config;
		
		for(FileSelectorSource source : sources)
			source.init(this);
		
		buildToolbar();
		buildGrid();
	}

	protected void buildGrid() {
		/* create store */
		fileStore = new ListStore<SelectedFileWrapper>(new FileSelectionWrapperKeyProvider());
		fileStore.setAutoCommit(true);
		
		/* configure columns */ 
		List<ColumnConfig<SelectedFileWrapper,?>> columns = new ArrayList<ColumnConfig<SelectedFileWrapper,?>>();
		
		ColumnConfig<SelectedFileWrapper,SelectedFileWrapper> iconConfig = new ColumnConfig<SelectedFileWrapper,SelectedFileWrapper>(new IdentityValueProvider<SelectedFileWrapper>("__file_icon"), 30); 
		iconConfig.setMenuDisabled(true);
		iconConfig.setCell(new AbstractCell<SelectedFileWrapper>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					SelectedFileWrapper value, SafeHtmlBuilder sb) {
				FileSelectorSource source = getSourceFor(value);
				if(null != source){
					ImageResource icon = source.getIconFor(value);
					if(icon instanceof CssIconImageResource)
						sb.append(((CssIconImageResource)icon).getCssIcon());
					else
						sb.append(AbstractImagePrototype.create(icon).getSafeHtml());
				}
			}
		});
		columns.add(iconConfig);
		
		/* Name column */
		ColumnConfig<SelectedFileWrapper,String> nameConfig = new ColumnConfig<SelectedFileWrapper,String>(SelectedFileWrapperPA.INSTANCE.name(), 250, BaseMessages.INSTANCE.name());
		nameConfig.setMenuDisabled(true);
		columns.add(nameConfig);
		
		/* Type column */
		ColumnConfig<SelectedFileWrapper,SelectedFileWrapper> typeConfig = new ColumnConfig<SelectedFileWrapper,SelectedFileWrapper>(new IdentityValueProvider<SelectedFileWrapper>("__file_type"), 200, BaseMessages.INSTANCE.type()); 
		typeConfig.setMenuDisabled(true);
		typeConfig.setCell(new AbstractCell<SelectedFileWrapper>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					SelectedFileWrapper value, SafeHtmlBuilder sb) {
				FileSelectorSource source = getSourceFor(value);
				if(null != source){
					String typeDescription = source.getTypeDescription(value);
					sb.appendEscaped(typeDescription);
				}
			}
		});
		columns.add(typeConfig);
		
		/* create grid */
		grid = new Grid<SelectedFileWrapper>(fileStore, new ColumnModel<SelectedFileWrapper>(columns));
		
		GridEditing<SelectedFileWrapper> editing = new GridInlineEditing<SelectedFileWrapper>(grid);
		editing.addEditor(nameConfig, new TextField());
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<SelectedFileWrapper>() {
			@Override
			public void onBeforeStartEdit(
					BeforeStartEditEvent<SelectedFileWrapper> event) {
				SelectedFileWrapper selectedItem = grid.getSelectionModel().getSelectedItem();
				event.setCancelled(true);
				
				FileSelectorSource source = getSourceFor(selectedItem);
				if(null != source && source.isEditNameEnabled(selectedItem))
					event.setCancelled(false);
			}
		});
		
		grid.getView().setAutoExpandColumn(nameConfig);
		grid.setSelectionModel(new GridSelectionModel<SelectedFileWrapper>());
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		grid.getView().setShowDirtyCells(false);
		grid.setHeight(gridHeight);

		grid.getView().setAutoExpandColumn(nameConfig);
		grid.setColumnReordering(false);
		
		/* context menu */
		contextMenu = new DwMenu();
		grid.setContextMenu(contextMenu);
		
		grid.addBeforeShowContextMenuHandler(new BeforeShowContextMenuHandler() {
			@Override
			public void onBeforeShowContextMenu(BeforeShowContextMenuEvent event) {
				contextMenu.clear();
				
				final SelectedFileWrapper selectedItem = grid.getSelectionModel().getSelectedItem();
				if(null == selectedItem)
					return;
				
				/* add download menu */
				final FileSelectorSource source = getSourceFor(selectedItem);
				if(null != source && source.isDownloadEnabled(selectedItem)){
					MenuItem download = new DwMenuItem(BaseMessages.INSTANCE.download());
					contextMenu.add(download);
					download.addSelectionHandler(new SelectionHandler<Item>() {
						@Override
						public void onSelection(SelectionEvent<Item> event) {
							DownloadProperties properties = source.getDownloadPropertiesFor(selectedItem);
							if(null != properties){
								downloadService.triggerDownload(properties);
							}
						}
					});
				}
			}
		});
		
		for(FileSelectorSource source : sources)
			source.configureGrid(this, grid);
		
		/* enhance with menu */
		
		/* make grid main widget */
		add(grid, new VerticalLayoutData(1,-1));
	}
	
	public ListStore<SelectedFileWrapper> getFileStore() {
		return fileStore;
	}
	
	public void addStoreHandler(StoreHandlers<SelectedFileWrapper> handlers){
		fileStore.addStoreHandlers(handlers);
	}

	protected FileSelectorSource getSourceFor(SelectedFileWrapper file) {
		if(null == file)
			return null;
		
		for(FileSelectorSource source : sources)
			if(source.consumes(file))
				return source;
		return null;
	}

	protected void buildToolbar() {
		toolbar = new DwToolBar();

		for(FileSelectorSource source : sources)
			source.configureToolbar(this, toolbar);
		
		toolbar.add(new SeparatorToolItem());
		
		DwSplitButton removeButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
		removeButton.setIcon(BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				deleteSelection();
			}
		});
		toolbar.add(removeButton);
		
		MenuItem removeAllButton = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				deleteAll();				
			}
		});
		Menu menu = new DwMenu();
		menu.add(removeAllButton);
		removeButton.setMenu(menu);
		
		add(toolbar, new VerticalLayoutData(1,-1));
	}
	
	protected void deleteAll() {
		ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.removeAll(), BaseMessages.INSTANCE.confirmDeleteMsg(""));
		cmb.addDialogHideHandler(new DialogHideHandler() {
			@Override
			public void onDialogHide(DialogHideEvent event) {
				if (event.getHideButton() == PredefinedButton.YES) 
					fileStore.clear();	
			}
		});
		cmb.show();
	}

	protected void deleteSelection() {
		List<SelectedFileWrapper> items = grid.getSelectionModel().getSelectedItems();

		for(SelectedFileWrapper model : items)
			fileStore.remove(model);	
	}

	public void addSource(FileSelectorSource... sources){
		if(null == sources || sources.length == 0)
			return;
		
		for(FileSelectorSource source : sources)
			this.sources.add(source);
	}
	
	public void submit(final SubmitHandler handler) {
		ArrayList<SelectedFileWrapper> data = new ArrayList<SelectedFileWrapper>(fileStore.getAll());
		
		fileSelectionDao.submit(data, config, new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				handler.onSuccess(FileSelectionWidget.this);
			}
			@Override
			public void onFailure(Throwable caught) {
				handler.onFailure(FileSelectionWidget.this,caught);
			}
		});
	}

	
	public void setGridHeight(int gridHeight){
		this.gridHeight = gridHeight;
	}
	
	public Grid<SelectedFileWrapper> getGrid() {
		return grid;
	}

	public void add(SelectedFileWrapper sfw) {
		fileStore.add(sfw);
	}

	public int getMinFiles() {
		return minFiles;
	}

	public void setMinFiles(int minFiles) {
		this.minFiles = Math.max(minFiles,0);
	}

	public int getMaxFiles() {
		return maxFiles;
	}

	public void setMaxFiles(int maxFiles) {
		this.maxFiles = Math.max(0,maxFiles);
	}

	public boolean isValid(){
		for(FileSelectorSource source : sources)
			if(! source.isValid())
				return false;
		
		List<SelectedFileWrapper> all = fileStore.getAll();
		return (all.size() <= maxFiles || 0 == maxFiles) && (all.size() >= minFiles || 0 == minFiles);
	}

	public int getRemainingFileSpace(){
		if(maxFiles < 1)
			return Integer.MAX_VALUE;
		int remaining =  maxFiles - fileStore.size();
		return remaining;
	}

	public void displayMaxFilesReachedMessage() {
		new DwAlertMessageBox(FileSelectionMessages.INSTANCE.maxFilesReachedTitle(), FileSelectionMessages.INSTANCE.maxFilesReachedMessage(maxFiles)).show();
	}
}
