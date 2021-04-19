package net.datenwerke.gf.service.juel.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gf.client.juel.dto.JuelResultTypeDto;
import net.datenwerke.gf.service.juel.JuelResultType;
import net.datenwerke.gf.service.juel.dtogen.JuelResultType2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;

/**
 * Poso2DtoGenerator for JuelResultType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JuelResultType2DtoGenerator implements Poso2DtoGenerator<JuelResultType,JuelResultTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public JuelResultType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public JuelResultTypeDto instantiateDto(JuelResultType poso)  {
		/* Simply return the first enum! */
		JuelResultTypeDto dto = JuelResultTypeDto.NULL;
		return dto;
	}

	public JuelResultTypeDto createDto(JuelResultType poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case NULL:
				return JuelResultTypeDto.NULL;
			case STRING:
				return JuelResultTypeDto.STRING;
			case INTEGER:
				return JuelResultTypeDto.INTEGER;
			case LONG:
				return JuelResultTypeDto.LONG;
			case DECIMAL:
				return JuelResultTypeDto.DECIMAL;
			case DOUBLE:
				return JuelResultTypeDto.DOUBLE;
			case FLOAT:
				return JuelResultTypeDto.FLOAT;
			case DATE:
				return JuelResultTypeDto.DATE;
			case BOOLEAN:
				return JuelResultTypeDto.BOOLEAN;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
