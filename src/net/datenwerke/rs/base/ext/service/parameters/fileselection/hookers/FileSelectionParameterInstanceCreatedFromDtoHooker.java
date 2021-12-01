package net.datenwerke.rs.base.ext.service.parameters.fileselection.hookers;

import javax.inject.Inject;

import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterDtoHelper;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterInstanceCreatedFromDtoHook;

public class FileSelectionParameterInstanceCreatedFromDtoHooker implements ParameterInstanceCreatedFromDtoHook {

	private FileSelectionParameterDtoHelper fileSelectionParameterDtoHelper;

	@Inject
	public FileSelectionParameterInstanceCreatedFromDtoHooker(FileSelectionParameterDtoHelper fileSelectionParameterDtoHelper) {
		this.fileSelectionParameterDtoHelper = fileSelectionParameterDtoHelper;
	}
	
	@Override
	public boolean consumes(ParameterInstanceDto parameterInstanceDto) {
		return (parameterInstanceDto instanceof FileSelectionParameterInstanceDto);
	}

	@Override
	public void posoCreated(ParameterInstanceDto parameterInstanceDto, ParameterInstance parameterInstance) {
		
		fileSelectionParameterDtoHelper.fixInstanceFromDto((FileSelectionParameterInstance)parameterInstance, (FileSelectionParameterInstanceDtoDec)parameterInstanceDto);
	}

}
