package net.datenwerke.rs.base.service.dbhelper.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.dtogen.DatabaseHelper2DtoGenerator;

/**
 * Poso2DtoGenerator for DatabaseHelper
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseHelper2DtoGenerator implements Poso2DtoGenerator<DatabaseHelper,DatabaseHelperDto> {

	private final net.datenwerke.rs.base.service.dbhelper.dtogen.post.DatabaseHelper2DtoPostProcessor postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatabaseHelper2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.base.service.dbhelper.dtogen.post.DatabaseHelper2DtoPostProcessor postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public DatabaseHelperDto instantiateDto(DatabaseHelper poso)  {
		DatabaseHelperDto dto = new DatabaseHelperDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public DatabaseHelperDto createDto(DatabaseHelper poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatabaseHelperDto dto = new DatabaseHelperDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set Descriptor */
			dto.setDescriptor(poso.getDescriptor() );

			/*  set Driver */
			dto.setDriver(poso.getDriver() );

			/*  set Name */
			dto.setName(poso.getName() );

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
