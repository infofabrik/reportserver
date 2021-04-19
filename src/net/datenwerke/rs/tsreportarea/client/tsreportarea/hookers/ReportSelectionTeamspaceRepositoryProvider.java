package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import java.util.List;
import java.util.Optional;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent.BeforeShowContextMenuHandler;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.ReportSelectionCardConfig;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.RepositoryProviderConfig;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportSelectionRepositoryProviderHook;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIService;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform.SFFCTsTeamSpaceSelector;

public class ReportSelectionTeamspaceRepositoryProvider implements ReportSelectionRepositoryProviderHook {

	private final TsDiskDao diskDao;
	private final TsDiskUIService diskService;
	private final ToolbarService toolbarService;
	
	@Inject
	public ReportSelectionTeamspaceRepositoryProvider(TsDiskDao diskDao,
			TsDiskUIService diskService, ToolbarService toolbarService) {
		this.diskDao = diskDao;
		this.diskService = diskService;
		this.toolbarService = toolbarService;
	}

	@Override
	public void addCards(final ReportSelectionDialog dialog, Optional<ReportDto> selectedReport, RepositoryProviderConfig[] configs) {
		diskDao.getTeamSpacesWithFavoriteApp(new RsAsyncCallback<List<TeamSpaceDto>>(){
			@Override
			public void onSuccess(List<TeamSpaceDto> result) {
			   result.forEach(teamspace -> addTeamSpace(dialog, teamspace));
			}
		});
	}

	protected void addTeamSpace(final ReportSelectionDialog dialog, final TeamSpaceDto teamSpace) {
		final ListStore<TsDiskReportReferenceDto> store = new ListStore<TsDiskReportReferenceDto>(new DtoIdModelKeyProvider()); 
		
		/* create grid */
		final Grid<TsDiskReportReferenceDto> grid = new Grid<TsDiskReportReferenceDto>(store, new ColumnModel<TsDiskReportReferenceDto>(diskService.createGridColumnConfig(TsDiskReportReferenceDto.class)));
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		/* fill store */
		grid.addAttachHandler(new Handler() {
			private boolean workDone = false;
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				if(workDone)
					return;
				workDone = true;
				
				diskDao.getReferencesInApp(teamSpace, new RsAsyncCallback<List<TsDiskReportReferenceDto>>(){
					@Override
					public void onSuccess(List<TsDiskReportReferenceDto> result) {
						store.addAll(result);
					}
				});
			}
		});
		
		grid.setContextMenu(new DwMenu());
	
		grid.addBeforeShowContextMenuHandler(new BeforeShowContextMenuHandler() {
			@Override
			public void onBeforeShowContextMenu(BeforeShowContextMenuEvent event) {
				TsDiskReportReferenceDtoDec dto = (TsDiskReportReferenceDtoDec) grid.getSelectionModel().getSelectedItem();
				if(null == dto){
					event.setCancelled(true);
					return;
				}
			
				Menu menu =  dialog.getContextMenuFor(dto, ReportSelectionTeamspaceRepositoryProvider.this, teamSpace);
				if(null == menu){
					event.setCancelled(true);
					return;
				}
				grid.setContextMenu(menu);
			}
		});
		
		/* show context menue on doubleclick */
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				TsDiskReportReferenceDtoDec dto = (TsDiskReportReferenceDtoDec) grid.getStore().get(event.getRowIndex());
                if(null != dto)
                	dialog.handleDoubleClick(dto, ReportSelectionTeamspaceRepositoryProvider.this, event.getEvent(), teamSpace);
            }
        });
		
		
		/* create wrapper */
		DwNorthSouthContainer wrapper = new DwNorthSouthContainer();
		wrapper.add(grid);
		
		ToolBar tb = new DwToolBar();
		wrapper.setNorthWidget(tb);
		
		final StoreFilterField<TsDiskReportReferenceDto> textFilter = new StoreFilterField<TsDiskReportReferenceDto>(){
			
			@Override
			protected boolean doSelect(Store<TsDiskReportReferenceDto> store,
					TsDiskReportReferenceDto parent,
					TsDiskReportReferenceDto item, String filter) {
				String title = item.getName();
				if(null != title && title.toLowerCase().contains(filter.toLowerCase()))
					return true;
				
				return false;
			}
		   
	    };  
	    textFilter.bind(store);
	    
	    /* folder selector */
	    ValueBaseField<TsDiskFolderDto> folderSelector = (ValueBaseField<TsDiskFolderDto>) SimpleForm.createFormlessField(TsDiskFolderDto.class, new SFFCTsTeamSpaceSelector() {
			@Override
			public TeamSpaceDto getTeamSpace() {
				return teamSpace;
			} 
		});
	    folderSelector.addValueChangeHandler(new ValueChangeHandler<TsDiskFolderDto>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<TsDiskFolderDto> event) {
				textFilter.clear();
				AbstractTsDiskNodeDto folder = event.getValue();
				loadIn(teamSpace, folder instanceof TsDiskFolderDto ? (TsDiskFolderDto) folder : null, grid, store);
			}
		});

	    /* add items to toolbar */
	    toolbarService.addPlainToolbarItem(tb, BaseIcon.SEARCH);
	    tb.add(textFilter);
	    toolbarService.addPlainToolbarItem(tb, BaseIcon.FOLDER_OPEN_O);
	    tb.add(folderSelector);
	    tb.add(new FillToolItem());
		
		dialog.addCard(
			teamSpace.getName(), 
			BaseIcon.GROUP_EDIT, 
			wrapper,
			new ReportSelectionCardConfig(){
				@Override
				public void cardSelected() {
				}

				@Override
				public ReportContainerDto getSelectedReport() {
					return (TsDiskReportReferenceDtoDec)grid.getSelectionModel().getSelectedItem();
				}
				
			}
		);
	}

	protected void loadIn(TeamSpaceDto teamSpace, TsDiskFolderDto folder,
			final Grid<TsDiskReportReferenceDto> grid,
			final ListStore<TsDiskReportReferenceDto> store) {
		grid.mask(BaseMessages.INSTANCE.loadingMsg());
		diskDao.getReferencesInApp(teamSpace, folder, new RsAsyncCallback<List<TsDiskReportReferenceDto>>(){
			@Override
			public void onSuccess(List<TsDiskReportReferenceDto> result) {
				store.removeFilters();
				store.clear();
				store.addAll(result);
				grid.unmask();
			}
		});
	}

}
