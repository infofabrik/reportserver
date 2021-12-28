package net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.fileselector;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.download.dto.DownloadProperties;
import net.datenwerke.gf.client.fileselection.FileSelectionWidget;
import net.datenwerke.gf.client.fileselection.FileSelectorSourceImpl;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.treedb.selection.SelectionFilter;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCTreeNodeSelectionFilter;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.forms.selection.SingleSelectionField;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform.SFFCTsTeamSpaceSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TeamSpaceFileSelectorSource extends FileSelectorSourceImpl {

	public static final String TEAMSPACE_FILE_TYPE = "teamspace_disk_type";
	
	private boolean enableNameEditing = true;
	private boolean enableDownload = true;
	private SelectionFilter selectionFilter = SelectionFilter.DUMMY_FILTER;

	private HandlerRegistration addAttachHandler;
	
	@Override
	public void configureToolbar(FileSelectionWidget fileSelectionWidget,
			ToolBar toolbar) {
		DwTextButton btn = new DwTextButton(TsFavoriteMessages.INSTANCE.selectFromTeamSpaceText(), BaseIcon.NEWSPAPER_O);
		toolbar.add(btn);
		btn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				displayTeamSpaceFileSelection();
			}
		});
	}

	@Override
	public void configureGrid(FileSelectionWidget fileSelectionWidget,
			Grid<SelectedFileWrapper> grid) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean consumes(SelectedFileWrapper value) {
		return null != value && value.getOriginalDto() instanceof AbstractTsDiskNodeDto;
	}

	@Override
	public ImageResource getIconFor(SelectedFileWrapper value) {
		return BaseIcon.FILE.toImageResource();
	}

	@Override
	public boolean isEditNameEnabled(SelectedFileWrapper selectedItem) {
		return false;
	}

	@Override
	public boolean isDownloadEnabled(SelectedFileWrapper item) {
		return false;
	}

	@Override
	public DownloadProperties getDownloadPropertiesFor(
			SelectedFileWrapper selectedItem) {
		return null;
	}

	@Override
	public String getTypeDescription(SelectedFileWrapper value) {
		if(null != value.getOriginalDto()){
			if(value.getOriginalDto() instanceof TsDiskFolderDto)
				return TsFavoriteMessages.INSTANCE.folderDescription();
			if(value.getOriginalDto() instanceof TsDiskReportReferenceDto)
				return TsFavoriteMessages.INSTANCE.reportDescription();
			if(value.getOriginalDto() instanceof TsDiskGeneralReferenceDto)
				return TsFavoriteMessages.INSTANCE.referenceDescription();
		}
		return "";
	}

	public boolean isEnableNameEditing() {
		return enableNameEditing;
	}

	public void setEnableNameEditing(boolean enableNameEditing) {
		this.enableNameEditing = enableNameEditing;
	}

	public boolean isEnableDownload() {
		return enableDownload;
	}

	public void setEnableDownload(boolean enableDownload) {
		this.enableDownload = enableDownload;
	}

	protected void displayTeamSpaceFileSelection() {
		if(fileSelectionWidget.getRemainingFileSpace() < 1){
			fileSelectionWidget.displayMaxFilesReachedMessage();
			return;
		}
		
		final DwWindow selectionDialog = new DwWindow();
		selectionDialog.setHeading(TsFavoriteMessages.INSTANCE.selectFromTeamSpaceText());
		selectionDialog.setWidth(400);
		selectionDialog.setHeight(200);
		selectionDialog.setModal(true);
		selectionDialog.getButtonBar().clear();

		final SimpleForm form = SimpleForm.getInlineInstance();
		
		final String teamSpaceKey = form.addField(TeamSpaceDto.class, TsFavoriteMessages.INSTANCE.selectTeamspaceLabel());
		
		final String fileKey = form.addField(AbstractTsDiskNodeDto.class, TsFavoriteMessages.INSTANCE.selectFileFromTeamSpace(), new SFFCTsTeamSpaceSelector(){
			@Override
			public TeamSpaceDto getTeamSpace() {
				return (TeamSpaceDto) form.getValue(teamSpaceKey);
			}
		}, new SFFCTreeNodeSelectionFilter(){
			@Override
			public SelectionFilter getFilter() {
				return selectionFilter;
			}
		});
		
		form.loadFields();
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		selectionDialog.add(container, new MarginData(15));
		container.add(form, new VerticalLayoutData(1,-1));
		selectionDialog.add(container);
		
		
		addAttachHandler = form.addAttachHandler(new Handler() {
			
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				addAttachHandler.removeHandler();
				
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					
					@Override
					public void execute() {
						Widget field = form.getField(teamSpaceKey);
						if(field instanceof SingleSelectionField){
							((SingleSelectionField)field).displaySelectionPopup();
							((SingleSelectionField) field).addValueChangeHandler(new ValueChangeHandler<AbstractNodeDto>() {
								@Override
								public void onValueChange(
										ValueChangeEvent<AbstractNodeDto> event) {
									form.setValue(fileKey, null);
									Widget f = form.getField(fileKey);
									if(f instanceof SingleTreeSelectionField){
										((SingleTreeSelectionField)f).fireEvent(new TriggerClickEvent());
									}
								}
							});
						}
					}
				});
			}
		});
		
		/* Cancel */
		DwTextButton cnlButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
		cnlButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				selectionDialog.hide();
			}
		});
		selectionDialog.addButton(cnlButton);
		
		/* ok Button */
		DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.submit());
		okButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				AbstractTsDiskNodeDto file = (AbstractTsDiskNodeDto) form.getValue(fileKey);
				if(null != file){
					add(file);
					
					selectionDialog.hide();
				} 
			}
		});
		selectionDialog.addButton(okButton);
		
		selectionDialog.show();
	}

	protected void add(AbstractTsDiskNodeDto file) {
		SelectedFileWrapper sfw = new SelectedFileWrapper();
		sfw.setId(String.valueOf(file.getId()));
		sfw.setName(file.toDisplayTitle());
		sfw.setType(TEAMSPACE_FILE_TYPE);
		sfw.setOriginalDto(file);
		
		fileSelectionWidget.add(sfw);
	}
	
	public SelectionFilter getSelectionFilter() {
		return selectionFilter;
	}
	
	public void setSelectionFilter(SelectionFilter selectionFilter) {
		this.selectionFilter = selectionFilter;
	}
	
}
