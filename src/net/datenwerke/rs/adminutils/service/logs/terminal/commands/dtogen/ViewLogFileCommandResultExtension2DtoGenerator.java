package net.datenwerke.rs.adminutils.service.logs.terminal.commands.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto;
import net.datenwerke.rs.adminutils.service.logs.terminal.commands.ViewLogFileCommandResultExtension;
import net.datenwerke.rs.adminutils.service.logs.terminal.commands.dtogen.ViewLogFileCommandResultExtension2DtoGenerator;

/**
 * Poso2DtoGenerator for ViewLogFileCommandResultExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ViewLogFileCommandResultExtension2DtoGenerator implements Poso2DtoGenerator<ViewLogFileCommandResultExtension,ViewLogFileCommandResultExtensionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ViewLogFileCommandResultExtension2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ViewLogFileCommandResultExtensionDto instantiateDto(ViewLogFileCommandResultExtension poso)  {
		ViewLogFileCommandResultExtensionDto dto = new ViewLogFileCommandResultExtensionDto();
		return dto;
	}

	public ViewLogFileCommandResultExtensionDto createDto(ViewLogFileCommandResultExtension poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ViewLogFileCommandResultExtensionDto dto = new ViewLogFileCommandResultExtensionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set data */
			List<String> col_data = new ArrayList<String>();
			if( null != poso.getData()){
				for(String obj : poso.getData())
					col_data.add((String) obj);
				dto.setData(col_data);
			}

			/*  set filename */
			dto.setFilename(poso.getFilename() );

		}

		return dto;
	}


}
