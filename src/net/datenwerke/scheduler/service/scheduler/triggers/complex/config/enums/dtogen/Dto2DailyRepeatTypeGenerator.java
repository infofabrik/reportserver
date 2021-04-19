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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Dto2DailyRepeatTypeGenerator;

/**
 * Dto2PosoGenerator for DailyRepeatType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DailyRepeatTypeGenerator implements Dto2PosoGenerator<DailyRepeatTypeDto,DailyRepeatType> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2DailyRepeatTypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DailyRepeatType loadPoso(DailyRepeatTypeDto dto)  {
		return createPoso(dto);
	}

	public DailyRepeatType instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public DailyRepeatType createPoso(DailyRepeatTypeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case ONCE:
				return DailyRepeatType.ONCE;
			case BOUNDED_INTERVAL:
				return DailyRepeatType.BOUNDED_INTERVAL;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public DailyRepeatType createUnmanagedPoso(DailyRepeatTypeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(DailyRepeatTypeDto dto, DailyRepeatType poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(DailyRepeatTypeDto dto, DailyRepeatType poso)  {
		/* no merging for enums */
	}

	public DailyRepeatType loadAndMergePoso(DailyRepeatTypeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(DailyRepeatTypeDto dto, DailyRepeatType poso)  {
	}


	public void postProcessCreateUnmanaged(DailyRepeatTypeDto dto, DailyRepeatType poso)  {
	}


	public void postProcessLoad(DailyRepeatTypeDto dto, DailyRepeatType poso)  {
	}


	public void postProcessMerge(DailyRepeatTypeDto dto, DailyRepeatType poso)  {
	}


	public void postProcessInstantiate(DailyRepeatType poso)  {
	}



}
