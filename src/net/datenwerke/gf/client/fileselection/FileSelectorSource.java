package net.datenwerke.gf.client.fileselection;

import net.datenwerke.gf.client.download.dto.DownloadProperties;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public interface FileSelectorSource {

	void configureToolbar(FileSelectionWidget fileSelectionWidget,
			ToolBar toolbar);

	void configureGrid(FileSelectionWidget fileSelectionWidget, Grid<SelectedFileWrapper> grid);

	void init(FileSelectionWidget fileSelectionWidget);

	boolean consumes(SelectedFileWrapper value);

	ImageResource getIconFor(SelectedFileWrapper value);

	boolean isEditNameEnabled(SelectedFileWrapper selectedItem);

	boolean isDownloadEnabled(SelectedFileWrapper item);

	DownloadProperties getDownloadPropertiesFor(SelectedFileWrapper selectedItem);

	String getTypeDescription(SelectedFileWrapper value);

	boolean isValid();

}
