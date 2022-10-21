package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.EditCommandResultExtension;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.dtogen.EditCommandResultExtension2DtoGenerator;

/**
 * Poso2DtoGenerator for EditCommandResultExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class EditCommandResultExtension2DtoGenerator implements Poso2DtoGenerator<EditCommandResultExtension,EditCommandResultExtensionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public EditCommandResultExtension2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public EditCommandResultExtensionDto instantiateDto(EditCommandResultExtension poso)  {
		EditCommandResultExtensionDto dto = new EditCommandResultExtensionDto();
		return dto;
	}

	public EditCommandResultExtensionDto createDto(EditCommandResultExtension poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final EditCommandResultExtensionDto dto = new EditCommandResultExtensionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set data */
			dto.setData(poso.getData() );

			/*  set file */
			Object tmpDtoFileServerFileDtogetFile = dtoServiceProvider.get().createDto(poso.getFile(), here, referenced);
			dto.setFile((FileServerFileDto)tmpDtoFileServerFileDtogetFile);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoFileServerFileDtogetFile, poso.getFile(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setFile((FileServerFileDto)refDto);
				}
			});

		}

		return dto;
	}


}
