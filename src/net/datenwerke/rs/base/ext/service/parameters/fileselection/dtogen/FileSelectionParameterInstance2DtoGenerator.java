package net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.FileSelectionParameterInstance2DtoGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;

/**
 * Poso2DtoGenerator for FileSelectionParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FileSelectionParameterInstance2DtoGenerator implements Poso2DtoGenerator<FileSelectionParameterInstance,FileSelectionParameterInstanceDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FileSelectionParameterInstance2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FileSelectionParameterInstanceDtoDec instantiateDto(FileSelectionParameterInstance poso)  {
		FileSelectionParameterInstanceDtoDec dto = new FileSelectionParameterInstanceDtoDec();
		return dto;
	}

	public FileSelectionParameterInstanceDtoDec createDto(FileSelectionParameterInstance poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FileSelectionParameterInstanceDtoDec dto = new FileSelectionParameterInstanceDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set definition */
			Object tmpDtoParameterDefinitionDtogetDefinition = dtoServiceProvider.get().createDto(poso.getDefinition(), referenced, referenced);
			dto.setDefinition((ParameterDefinitionDto)tmpDtoParameterDefinitionDtogetDefinition);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoParameterDefinitionDtogetDefinition, poso.getDefinition(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDefinition((ParameterDefinitionDto)refDto);
				}
			});

			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set selectedFiles */
			final List<SelectedParameterFileDto> col_selectedFiles = new ArrayList<SelectedParameterFileDto>();
			if( null != poso.getSelectedFiles()){
				for(SelectedParameterFile refPoso : poso.getSelectedFiles()){
					final Object tmpDtoSelectedParameterFileDtogetSelectedFiles = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_selectedFiles.add((SelectedParameterFileDto) tmpDtoSelectedParameterFileDtogetSelectedFiles);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoSelectedParameterFileDtogetSelectedFiles, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (selectedFiles)");
							int tmp_index = col_selectedFiles.indexOf(tmpDtoSelectedParameterFileDtogetSelectedFiles);
							col_selectedFiles.set(tmp_index,(SelectedParameterFileDto) dto);
						}
					});
				}
				dto.setSelectedFiles(col_selectedFiles);
			}

			/*  set stillDefault */
			dto.setStillDefault(poso.isStillDefault() );

		}

		return dto;
	}


}
