package net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.UploadedParameterFile;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.UploadedParameterFile2DtoGenerator;

/**
 * Poso2DtoGenerator for UploadedParameterFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class UploadedParameterFile2DtoGenerator implements Poso2DtoGenerator<UploadedParameterFile,UploadedParameterFileDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public UploadedParameterFile2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public UploadedParameterFileDto instantiateDto(UploadedParameterFile poso)  {
		UploadedParameterFileDto dto = new UploadedParameterFileDto();
		return dto;
	}

	public UploadedParameterFileDto createDto(UploadedParameterFile poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final UploadedParameterFileDto dto = new UploadedParameterFileDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}

		return dto;
	}


}
