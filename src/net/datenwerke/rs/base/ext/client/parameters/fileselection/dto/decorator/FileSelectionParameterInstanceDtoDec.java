package net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator;

import java.util.ArrayList;

import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;

/**
 * Dto Decorator for {@link FileSelectionParameterInstanceDto}
 *
 */
public class FileSelectionParameterInstanceDtoDec extends FileSelectionParameterInstanceDto {


	private static final long serialVersionUID = 1L;

	private ArrayList<SelectedFileWrapper> tempSelection = new ArrayList<SelectedFileWrapper>();
	private boolean tempSelection_m = false;
	
	public FileSelectionParameterInstanceDtoDec() {
		super();
	}

	public void setTempSelection(ArrayList<SelectedFileWrapper> tempSelection) {
		this.tempSelection = tempSelection;
		this.tempSelection_m = true;
	}
	
	public ArrayList<SelectedFileWrapper> getTempSelection() {
		return tempSelection;
	}

	public boolean isTempSelectionModified(){
		return tempSelection_m;
	}
	
	public SelectedParameterFileDto getSelectedFileById(Long id) {
		for(SelectedParameterFileDto file : getSelectedFiles())
			if(id.equals(file.getId()))
				return file;
		return null;
	}

	@Override
	public void clearModified() {
		super.clearModified();
		this.tempSelection_m = false;
	}
	
	@Override
	public boolean isModified() {
		return super.isIdModified() || this.tempSelection_m;
	}

}
