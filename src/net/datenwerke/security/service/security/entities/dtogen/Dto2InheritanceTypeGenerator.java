package net.datenwerke.security.service.security.entities.dtogen;

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
import net.datenwerke.security.client.security.dto.InheritanceTypeDto;
import net.datenwerke.security.service.security.entities.InheritanceType;
import net.datenwerke.security.service.security.entities.dtogen.Dto2InheritanceTypeGenerator;

/**
 * Dto2PosoGenerator for InheritanceType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2InheritanceTypeGenerator implements Dto2PosoGenerator<InheritanceTypeDto,InheritanceType> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2InheritanceTypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public InheritanceType loadPoso(InheritanceTypeDto dto)  {
		return createPoso(dto);
	}

	public InheritanceType instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public InheritanceType createPoso(InheritanceTypeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case HERE:
				return InheritanceType.HERE;
			case INHERITED:
				return InheritanceType.INHERITED;
			case BOTH:
				return InheritanceType.BOTH;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public InheritanceType createUnmanagedPoso(InheritanceTypeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(InheritanceTypeDto dto, InheritanceType poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(InheritanceTypeDto dto, InheritanceType poso)  {
		/* no merging for enums */
	}

	public InheritanceType loadAndMergePoso(InheritanceTypeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(InheritanceTypeDto dto, InheritanceType poso)  {
	}


	public void postProcessCreateUnmanaged(InheritanceTypeDto dto, InheritanceType poso)  {
	}


	public void postProcessLoad(InheritanceTypeDto dto, InheritanceType poso)  {
	}


	public void postProcessMerge(InheritanceTypeDto dto, InheritanceType poso)  {
	}


	public void postProcessInstantiate(InheritanceType poso)  {
	}



}
