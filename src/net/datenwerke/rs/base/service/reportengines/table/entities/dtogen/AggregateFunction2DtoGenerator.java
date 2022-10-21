package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.AggregateFunction2DtoGenerator;

/**
 * Poso2DtoGenerator for AggregateFunction
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AggregateFunction2DtoGenerator implements Poso2DtoGenerator<AggregateFunction,AggregateFunctionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AggregateFunction2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AggregateFunctionDto instantiateDto(AggregateFunction poso)  {
		/* Simply return the first enum! */
		AggregateFunctionDto dto = AggregateFunctionDto.AVG;
		return dto;
	}

	public AggregateFunctionDto createDto(AggregateFunction poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case AVG:
				return AggregateFunctionDto.AVG;
			case COUNT:
				return AggregateFunctionDto.COUNT;
			case MAX:
				return AggregateFunctionDto.MAX;
			case MIN:
				return AggregateFunctionDto.MIN;
			case SUM:
				return AggregateFunctionDto.SUM;
			case VARIANCE:
				return AggregateFunctionDto.VARIANCE;
			case COUNT_DISTINCT:
				return AggregateFunctionDto.COUNT_DISTINCT;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
