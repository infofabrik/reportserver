package net.datenwerke.security.service.usermanager.entities.dtogen;

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
import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.service.usermanager.entities.Sex;
import net.datenwerke.security.service.usermanager.entities.dtogen.Dto2SexGenerator;

/**
 * Dto2PosoGenerator for Sex
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SexGenerator implements Dto2PosoGenerator<SexDto,Sex> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2SexGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public Sex loadPoso(SexDto dto)  {
		return createPoso(dto);
	}

	public Sex instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public Sex createPoso(SexDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case Male:
				return Sex.Male;
			case Female:
				return Sex.Female;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public Sex createUnmanagedPoso(SexDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(SexDto dto, Sex poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(SexDto dto, Sex poso)  {
		/* no merging for enums */
	}

	public Sex loadAndMergePoso(SexDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(SexDto dto, Sex poso)  {
	}


	public void postProcessCreateUnmanaged(SexDto dto, Sex poso)  {
	}


	public void postProcessLoad(SexDto dto, Sex poso)  {
	}


	public void postProcessMerge(SexDto dto, Sex poso)  {
	}


	public void postProcessInstantiate(Sex poso)  {
	}



}
