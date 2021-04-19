package net.datenwerke.scheduler.service.scheduler.entities.dtogen;

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
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.entities.dtogen.Dto2OutcomeGenerator;

/**
 * Dto2PosoGenerator for Outcome
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2OutcomeGenerator implements Dto2PosoGenerator<OutcomeDto,Outcome> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2OutcomeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public Outcome loadPoso(OutcomeDto dto)  {
		return createPoso(dto);
	}

	public Outcome instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public Outcome createPoso(OutcomeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case SUCCESS:
				return Outcome.SUCCESS;
			case FAILURE:
				return Outcome.FAILURE;
			case VETO:
				return Outcome.VETO;
			case EXECUTING:
				return Outcome.EXECUTING;
			case ACTION_VETO:
				return Outcome.ACTION_VETO;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public Outcome createUnmanagedPoso(OutcomeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(OutcomeDto dto, Outcome poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(OutcomeDto dto, Outcome poso)  {
		/* no merging for enums */
	}

	public Outcome loadAndMergePoso(OutcomeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(OutcomeDto dto, Outcome poso)  {
	}


	public void postProcessCreateUnmanaged(OutcomeDto dto, Outcome poso)  {
	}


	public void postProcessLoad(OutcomeDto dto, Outcome poso)  {
	}


	public void postProcessMerge(OutcomeDto dto, Outcome poso)  {
	}


	public void postProcessInstantiate(Outcome poso)  {
	}



}
