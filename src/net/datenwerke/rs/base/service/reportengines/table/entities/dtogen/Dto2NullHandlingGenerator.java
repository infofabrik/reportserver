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
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.NullHandling;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2NullHandlingGenerator;

/**
 * Dto2PosoGenerator for NullHandling
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2NullHandlingGenerator implements Dto2PosoGenerator<NullHandlingDto,NullHandling> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2NullHandlingGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public NullHandling loadPoso(NullHandlingDto dto)  {
		return createPoso(dto);
	}

	public NullHandling instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public NullHandling createPoso(NullHandlingDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case Include:
				return NullHandling.Include;
			case Exlude:
				return NullHandling.Exlude;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public NullHandling createUnmanagedPoso(NullHandlingDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(NullHandlingDto dto, NullHandling poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(NullHandlingDto dto, NullHandling poso)  {
		/* no merging for enums */
	}

	public NullHandling loadAndMergePoso(NullHandlingDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(NullHandlingDto dto, NullHandling poso)  {
	}


	public void postProcessCreateUnmanaged(NullHandlingDto dto, NullHandling poso)  {
	}


	public void postProcessLoad(NullHandlingDto dto, NullHandling poso)  {
	}


	public void postProcessMerge(NullHandlingDto dto, NullHandling poso)  {
	}


	public void postProcessInstantiate(NullHandling poso)  {
	}



}
