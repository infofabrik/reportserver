package net.datenwerke.rs.core.service.parameters.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.service.parameters.entities.Datatype;
import net.datenwerke.rs.core.service.parameters.entities.dtogen.Datatype2DtoGenerator;

/**
 * Poso2DtoGenerator for Datatype
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Datatype2DtoGenerator implements Poso2DtoGenerator<Datatype,DatatypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Datatype2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatatypeDto instantiateDto(Datatype poso)  {
		/* Simply return the first enum! */
		DatatypeDto dto = DatatypeDto.String;
		return dto;
	}

	public DatatypeDto createDto(Datatype poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case String:
				return DatatypeDto.String;
			case Integer:
				return DatatypeDto.Integer;
			case Long:
				return DatatypeDto.Long;
			case BigDecimal:
				return DatatypeDto.BigDecimal;
			case Float:
				return DatatypeDto.Float;
			case Double:
				return DatatypeDto.Double;
			case Date:
				return DatatypeDto.Date;
			case Boolean:
				return DatatypeDto.Boolean;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
