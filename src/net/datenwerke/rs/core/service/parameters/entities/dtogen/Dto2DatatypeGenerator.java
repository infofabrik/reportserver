package net.datenwerke.rs.core.service.parameters.entities.dtogen;

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
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.service.parameters.entities.Datatype;
import net.datenwerke.rs.core.service.parameters.entities.dtogen.Dto2DatatypeGenerator;

/**
 * Dto2PosoGenerator for Datatype
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatatypeGenerator implements Dto2PosoGenerator<DatatypeDto,Datatype> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2DatatypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public Datatype loadPoso(DatatypeDto dto)  {
		return createPoso(dto);
	}

	public Datatype instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public Datatype createPoso(DatatypeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case String:
				return Datatype.String;
			case Integer:
				return Datatype.Integer;
			case Long:
				return Datatype.Long;
			case BigDecimal:
				return Datatype.BigDecimal;
			case Float:
				return Datatype.Float;
			case Double:
				return Datatype.Double;
			case Date:
				return Datatype.Date;
			case Boolean:
				return Datatype.Boolean;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public Datatype createUnmanagedPoso(DatatypeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(DatatypeDto dto, Datatype poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(DatatypeDto dto, Datatype poso)  {
		/* no merging for enums */
	}

	public Datatype loadAndMergePoso(DatatypeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(DatatypeDto dto, Datatype poso)  {
	}


	public void postProcessCreateUnmanaged(DatatypeDto dto, Datatype poso)  {
	}


	public void postProcessLoad(DatatypeDto dto, Datatype poso)  {
	}


	public void postProcessMerge(DatatypeDto dto, Datatype poso)  {
	}


	public void postProcessInstantiate(Datatype poso)  {
	}



}
