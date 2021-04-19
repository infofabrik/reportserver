package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen;

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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2TimeUnitsGenerator;

/**
 * Dto2PosoGenerator for TimeUnits
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TimeUnitsGenerator implements Dto2PosoGenerator<TimeUnitsDto,TimeUnits> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2TimeUnitsGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TimeUnits loadPoso(TimeUnitsDto dto)  {
		return createPoso(dto);
	}

	public TimeUnits instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public TimeUnits createPoso(TimeUnitsDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case HOURS:
				return TimeUnits.HOURS;
			case MINUTES:
				return TimeUnits.MINUTES;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public TimeUnits createUnmanagedPoso(TimeUnitsDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(TimeUnitsDto dto, TimeUnits poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(TimeUnitsDto dto, TimeUnits poso)  {
		/* no merging for enums */
	}

	public TimeUnits loadAndMergePoso(TimeUnitsDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(TimeUnitsDto dto, TimeUnits poso)  {
	}


	public void postProcessCreateUnmanaged(TimeUnitsDto dto, TimeUnits poso)  {
	}


	public void postProcessLoad(TimeUnitsDto dto, TimeUnits poso)  {
	}


	public void postProcessMerge(TimeUnitsDto dto, TimeUnits poso)  {
	}


	public void postProcessInstantiate(TimeUnits poso)  {
	}



}
