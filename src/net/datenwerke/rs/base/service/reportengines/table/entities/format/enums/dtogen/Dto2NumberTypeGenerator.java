package net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.NumberType;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen.Dto2NumberTypeGenerator;

/**
 * Dto2PosoGenerator for NumberType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2NumberTypeGenerator implements Dto2PosoGenerator<NumberTypeDto,NumberType> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2NumberTypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public NumberType loadPoso(NumberTypeDto dto)  {
		return createPoso(dto);
	}

	public NumberType instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public NumberType createPoso(NumberTypeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case DEFAULT:
				return NumberType.DEFAULT;
			case PERCENT:
				return NumberType.PERCENT;
			case SCIENTIFIC:
				return NumberType.SCIENTIFIC;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public NumberType createUnmanagedPoso(NumberTypeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(NumberTypeDto dto, NumberType poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(NumberTypeDto dto, NumberType poso)  {
		/* no merging for enums */
	}

	public NumberType loadAndMergePoso(NumberTypeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(NumberTypeDto dto, NumberType poso)  {
	}


	public void postProcessCreateUnmanaged(NumberTypeDto dto, NumberType poso)  {
	}


	public void postProcessLoad(NumberTypeDto dto, NumberType poso)  {
	}


	public void postProcessMerge(NumberTypeDto dto, NumberType poso)  {
	}


	public void postProcessInstantiate(NumberType poso)  {
	}



}
