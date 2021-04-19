package net.datenwerke.scheduler.service.scheduler.helper.dtogen;

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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.RetryTimeUnitDto;
import net.datenwerke.scheduler.service.scheduler.helper.RetryTimeUnit;
import net.datenwerke.scheduler.service.scheduler.helper.dtogen.Dto2RetryTimeUnitGenerator;

/**
 * Dto2PosoGenerator for RetryTimeUnit
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2RetryTimeUnitGenerator implements Dto2PosoGenerator<RetryTimeUnitDto,RetryTimeUnit> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2RetryTimeUnitGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public RetryTimeUnit loadPoso(RetryTimeUnitDto dto)  {
		return createPoso(dto);
	}

	public RetryTimeUnit instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public RetryTimeUnit createPoso(RetryTimeUnitDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case MINUTES:
				return RetryTimeUnit.MINUTES;
			case HOURS:
				return RetryTimeUnit.HOURS;
			case DAYS:
				return RetryTimeUnit.DAYS;
			case WEEKS:
				return RetryTimeUnit.WEEKS;
			case MONTHS:
				return RetryTimeUnit.MONTHS;
			case YEARS:
				return RetryTimeUnit.YEARS;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public RetryTimeUnit createUnmanagedPoso(RetryTimeUnitDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(RetryTimeUnitDto dto, RetryTimeUnit poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(RetryTimeUnitDto dto, RetryTimeUnit poso)  {
		/* no merging for enums */
	}

	public RetryTimeUnit loadAndMergePoso(RetryTimeUnitDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(RetryTimeUnitDto dto, RetryTimeUnit poso)  {
	}


	public void postProcessCreateUnmanaged(RetryTimeUnitDto dto, RetryTimeUnit poso)  {
	}


	public void postProcessLoad(RetryTimeUnitDto dto, RetryTimeUnit poso)  {
	}


	public void postProcessMerge(RetryTimeUnitDto dto, RetryTimeUnit poso)  {
	}


	public void postProcessInstantiate(RetryTimeUnit poso)  {
	}



}
