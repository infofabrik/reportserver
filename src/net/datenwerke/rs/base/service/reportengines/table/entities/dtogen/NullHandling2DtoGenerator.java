package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.NullHandling;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.NullHandling2DtoGenerator;

/**
 * Poso2DtoGenerator for NullHandling
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class NullHandling2DtoGenerator implements Poso2DtoGenerator<NullHandling,NullHandlingDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public NullHandling2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public NullHandlingDto instantiateDto(NullHandling poso)  {
		/* Simply return the first enum! */
		NullHandlingDto dto = NullHandlingDto.Include;
		return dto;
	}

	public NullHandlingDto createDto(NullHandling poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case Include:
				return NullHandlingDto.Include;
			case Exlude:
				return NullHandlingDto.Exlude;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
