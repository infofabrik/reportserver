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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2DaysGenerator;

/**
 * Dto2PosoGenerator for Days
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DaysGenerator implements Dto2PosoGenerator<DaysDto,Days> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2DaysGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public Days loadPoso(DaysDto dto)  {
		return createPoso(dto);
	}

	public Days instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public Days createPoso(DaysDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case MONDAY:
				return Days.MONDAY;
			case TUESDAY:
				return Days.TUESDAY;
			case WEDNESDAY:
				return Days.WEDNESDAY;
			case THURSDAY:
				return Days.THURSDAY;
			case FRIDAY:
				return Days.FRIDAY;
			case SATURDAY:
				return Days.SATURDAY;
			case SUNDAY:
				return Days.SUNDAY;
			case DAY:
				return Days.DAY;
			case WORKINGDAY:
				return Days.WORKINGDAY;
			case WEEKENDDAY:
				return Days.WEEKENDDAY;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public Days createUnmanagedPoso(DaysDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(DaysDto dto, Days poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(DaysDto dto, Days poso)  {
		/* no merging for enums */
	}

	public Days loadAndMergePoso(DaysDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(DaysDto dto, Days poso)  {
	}


	public void postProcessCreateUnmanaged(DaysDto dto, Days poso)  {
	}


	public void postProcessLoad(DaysDto dto, Days poso)  {
	}


	public void postProcessMerge(DaysDto dto, Days poso)  {
	}


	public void postProcessInstantiate(Days poso)  {
	}



}
