package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.RuntimeException;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2AggregateFunctionGenerator;

/**
 * Dto2PosoGenerator for AggregateFunction
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2AggregateFunctionGenerator implements Dto2PosoGenerator<AggregateFunctionDto,AggregateFunction> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2AggregateFunctionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AggregateFunction loadPoso(AggregateFunctionDto dto)  {
		return createPoso(dto);
	}

	public AggregateFunction instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public AggregateFunction createPoso(AggregateFunctionDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case AVG:
				return AggregateFunction.AVG;
			case COUNT:
				return AggregateFunction.COUNT;
			case MAX:
				return AggregateFunction.MAX;
			case MIN:
				return AggregateFunction.MIN;
			case SUM:
				return AggregateFunction.SUM;
			case VARIANCE:
				return AggregateFunction.VARIANCE;
			case COUNT_DISTINCT:
				return AggregateFunction.COUNT_DISTINCT;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public AggregateFunction createUnmanagedPoso(AggregateFunctionDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(AggregateFunctionDto dto, AggregateFunction poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(AggregateFunctionDto dto, AggregateFunction poso)  {
		/* no merging for enums */
	}

	public AggregateFunction loadAndMergePoso(AggregateFunctionDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(AggregateFunctionDto dto, AggregateFunction poso)  {
	}


	public void postProcessCreateUnmanaged(AggregateFunctionDto dto, AggregateFunction poso)  {
	}


	public void postProcessLoad(AggregateFunctionDto dto, AggregateFunction poso)  {
	}


	public void postProcessMerge(AggregateFunctionDto dto, AggregateFunction poso)  {
	}


	public void postProcessInstantiate(AggregateFunction poso)  {
	}



}
