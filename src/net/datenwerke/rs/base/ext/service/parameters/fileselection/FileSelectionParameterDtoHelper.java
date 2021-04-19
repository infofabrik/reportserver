package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.service.upload.FileUploadService;
import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;

import com.google.inject.Injector;
import com.google.inject.Provider;

/**
 * 
 *  This should be a dto post processor.. no idea why this is implemented in such an annoying way. 
 */
@Deprecated
public class FileSelectionParameterDtoHelper {
	
	
	private FileUploadService uploadService;
	private DtoService dtoService;
	private EntityClonerService entityCloner;
	private Provider<Injector> injectorProvider;

	@Inject
	public FileSelectionParameterDtoHelper(
			FileUploadService uploadService,
			DtoService dtoService,
			EntityClonerService entityCloner,
			Provider<Injector> injectorProvider
			) {
				this.uploadService = uploadService;
				this.dtoService = dtoService;
				this.entityCloner = entityCloner;
				this.injectorProvider = injectorProvider;
	}

	public void adaptPoso(ReportDto reportDto, Report report) {
		List<FileSelectionParameterDefinition> definitions = report.getParameterDefinitionsOfType(FileSelectionParameterDefinition.class);
		if(null == definitions || definitions.isEmpty())
			return;
		
		ReportDtoDec reportDec = (ReportDtoDec) reportDto;
		
		for(FileSelectionParameterDefinition def : definitions){
			String key = def.getKey();
			FileSelectionParameterDefinitionDto defDto = (FileSelectionParameterDefinitionDto) reportDec.getParameterDefinitionByKey(key);
			if(null == defDto)
				continue;
			
			FileSelectionParameterInstanceDtoDec instanceDto = (FileSelectionParameterInstanceDtoDec) reportDec.getParameterInstanceFor(defDto);
			if(null == instanceDto)
				continue;
			
			FileSelectionParameterInstance instance = (FileSelectionParameterInstance) report.getParameterInstanceFor(def);
			fixInstanceFromDto(instance, instanceDto);
		}		
	}

	public void fixInstanceFromDto(FileSelectionParameterInstance instance, FileSelectionParameterInstanceDtoDec instanceDto) {
		
		List<SelectedParameterFile> selectedFiles = new ArrayList<SelectedParameterFile>();

		ArrayList<SelectedFileWrapper> tempSelection = instanceDto.getTempSelection();
		if(null == tempSelection || !instanceDto.isTempSelectionModified())
			return;
		
		for(SelectedFileWrapper file : tempSelection){
			Dto originalDto = file.getOriginalDto();

			if(null != originalDto){
				Object loadPoso = dtoService.loadPoso(originalDto);
				
				if(loadPoso instanceof SelectedParameterFile){
					SelectedParameterFile selectedFile = (SelectedParameterFile) entityCloner.cloneEntity(loadPoso);
					selectedFile.setName(file.getName());
					
					selectedFiles.add(selectedFile);
				} else if(loadPoso instanceof AbstractTsDiskNode){
					AbstractTsDiskNode node = (AbstractTsDiskNode) loadPoso;
					
					SelectedParameterFile selectedFile = new SelectedParameterFile();
					selectedFile.setTeamSpaceFile(node);
					selectedFile.setName(file.getName());
					
					/* check access */
					if(! selectedFile.mayAccess(injectorProvider.get()))
						throw new ViolatedSecurityException();
					
					selectedFiles.add(selectedFile);
				} else if(loadPoso instanceof AbstractFileServerNode){
					AbstractFileServerNode node = (AbstractFileServerNode) loadPoso;
					
					SelectedParameterFile selectedFile = new SelectedParameterFile();
					selectedFile.setFileServerFile(node);
					selectedFile.setName(file.getName());
					
					/* check access */
					if(! selectedFile.mayAccess(injectorProvider.get()))
						throw new ViolatedSecurityException();
					
					selectedFiles.add(selectedFile);
				}
			} else {
				/* must be uploaded file */
				UploadedFile uploadedFile = uploadService.extractUploadedFileFrom(file, false);
				if(null == uploadedFile)
					continue;
				
				FileSelectionParameterDefinition definition = instance.getDefinition();
				long fileSize = definition.getFileSize();
				if(fileSize > 0 && uploadedFile.getFileBytes().length > fileSize)
					throw new IllegalArgumentException("File size too big. Allowed is at most " + definition.getFileSizeString());
				
				UploadedParameterFile uploadedParameterFile = new UploadedParameterFile();
				uploadedParameterFile.setContent(uploadedFile.getFileBytes());
				
				SelectedParameterFile selectedFile = new SelectedParameterFile();
				selectedFile.setUploadedFile(uploadedParameterFile);
				selectedFile.setName(file.getName());
				
				selectedFiles.add(selectedFile);
			}				
		}
		
		/* put files back */
		instance.getSelectedFiles().clear();
		if(null != selectedFiles)
			instance.setSelectedFiles(selectedFiles);
		
	}
}
