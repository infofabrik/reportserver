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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2MonthsGenerator;

/**
 * Dto2PosoGenerator for Months
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2MonthsGenerator implements Dto2PosoGenerator<MonthsDto,Months> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2MonthsGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public Months loadPoso(MonthsDto dto)  {
		return createPoso(dto);
	}

	public Months instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public Months createPoso(MonthsDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case JANUARY:
				return Months.JANUARY;
			case FEBRUARY:
				return Months.FEBRUARY;
			case MARCH:
				return Months.MARCH;
			case APRIL:
				return Months.APRIL;
			case MAY:
				return Months.MAY;
			case JUNE:
				return Months.JUNE;
			case JULY:
				return Months.JULY;
			case AUGUST:
				return Months.AUGUST;
			case SEPTEMBER:
				return Months.SEPTEMBER;
			case OCTOBER:
				return Months.OCTOBER;
			case NOVEMBER:
				return Months.NOVEMBER;
			case DECEMBER:
				return Months.DECEMBER;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public Months createUnmanagedPoso(MonthsDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(MonthsDto dto, Months poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(MonthsDto dto, Months poso)  {
		/* no merging for enums */
	}

	public Months loadAndMergePoso(MonthsDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(MonthsDto dto, Months poso)  {
	}


	public void postProcessCreateUnmanaged(MonthsDto dto, Months poso)  {
	}


	public void postProcessLoad(MonthsDto dto, Months poso)  {
	}


	public void postProcessMerge(MonthsDto dto, Months poso)  {
	}


	public void postProcessInstantiate(Months poso)  {
	}



}
