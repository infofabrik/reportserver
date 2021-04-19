package net.datenwerke.gf.client.fileselection;

import net.datenwerke.gf.client.download.dto.DownloadProperties;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public abstract class FileSelectorSourceImpl implements FileSelectorSource {

	protected FileSelectionWidget fileSelectionWidget;

	@Override
	public void configureToolbar(FileSelectionWidget fileSelectionWidget,
			ToolBar toolbar) {
	}

	@Override
	public void configureGrid(FileSelectionWidget fileSelectionWidget,
			Grid<SelectedFileWrapper> grid) {
	}

	@Override
	public void init(FileSelectionWidget fileSelectionWidget) {
		this.fileSelectionWidget = fileSelectionWidget;
	}

	@Override
	public boolean consumes(SelectedFileWrapper value) {
		return false;
	}

	@Override
	public ImageResource getIconFor(SelectedFileWrapper value) {
		return BaseIcon.FILE_O.toImageResource();
	}

	@Override
	public boolean isEditNameEnabled(SelectedFileWrapper selectedItem) {
		return true;
	}

	@Override
	public boolean isDownloadEnabled(SelectedFileWrapper item) {
		return true;
	}
	
	@Override
	public DownloadProperties getDownloadPropertiesFor(
			SelectedFileWrapper selectedItem) {
		return null;
	}
	
	@Override
	public String getTypeDescription(SelectedFileWrapper value) {
		return "";
	}
	
	@Override
	public boolean isValid() {
		return true;
	}

}
