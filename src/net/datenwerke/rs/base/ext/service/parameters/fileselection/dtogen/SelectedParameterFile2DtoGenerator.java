package net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.SelectedParameterFile2DtoGenerator;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for SelectedParameterFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SelectedParameterFile2DtoGenerator implements Poso2DtoGenerator<SelectedParameterFile,SelectedParameterFileDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public SelectedParameterFile2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public SelectedParameterFileDto instantiateDto(SelectedParameterFile poso)  {
		SelectedParameterFileDto dto = new SelectedParameterFileDto();
		return dto;
	}

	public SelectedParameterFileDto createDto(SelectedParameterFile poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final SelectedParameterFileDto dto = new SelectedParameterFileDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set fileServerFile */
			Object tmpDtoAbstractFileServerNodeDtogetFileServerFile = dtoServiceProvider.get().createDto(poso.getFileServerFile(), referenced, referenced);
			dto.setFileServerFile((AbstractFileServerNodeDto)tmpDtoAbstractFileServerNodeDtogetFileServerFile);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAbstractFileServerNodeDtogetFileServerFile, poso.getFileServerFile(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setFileServerFile((AbstractFileServerNodeDto)refDto);
				}
			});

			/*  set teamSpaceFile */
			Object tmpDtoAbstractTsDiskNodeDtogetTeamSpaceFile = dtoServiceProvider.get().createDto(poso.getTeamSpaceFile(), referenced, referenced);
			dto.setTeamSpaceFile((AbstractTsDiskNodeDto)tmpDtoAbstractTsDiskNodeDtogetTeamSpaceFile);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAbstractTsDiskNodeDtogetTeamSpaceFile, poso.getTeamSpaceFile(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setTeamSpaceFile((AbstractTsDiskNodeDto)refDto);
				}
			});

			/*  set uploadedFile */
			Object tmpDtoUploadedParameterFileDtogetUploadedFile = dtoServiceProvider.get().createDto(poso.getUploadedFile(), here, referenced);
			dto.setUploadedFile((UploadedParameterFileDto)tmpDtoUploadedParameterFileDtogetUploadedFile);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUploadedParameterFileDtogetUploadedFile, poso.getUploadedFile(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setUploadedFile((UploadedParameterFileDto)refDto);
				}
			});

		}

		return dto;
	}


}
