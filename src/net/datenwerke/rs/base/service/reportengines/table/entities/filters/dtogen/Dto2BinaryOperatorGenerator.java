package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryOperator;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2BinaryOperatorGenerator;

/**
 * Dto2PosoGenerator for BinaryOperator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BinaryOperatorGenerator implements Dto2PosoGenerator<BinaryOperatorDto,BinaryOperator> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2BinaryOperatorGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BinaryOperator loadPoso(BinaryOperatorDto dto)  {
		return createPoso(dto);
	}

	public BinaryOperator instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public BinaryOperator createPoso(BinaryOperatorDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case LESS:
				return BinaryOperator.LESS;
			case LESS_OR_EQUALS:
				return BinaryOperator.LESS_OR_EQUALS;
			case EQUALS:
				return BinaryOperator.EQUALS;
			case NOT_EQUALS:
				return BinaryOperator.NOT_EQUALS;
			case GREATER:
				return BinaryOperator.GREATER;
			case GREATER_OR_EQUALS:
				return BinaryOperator.GREATER_OR_EQUALS;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public BinaryOperator createUnmanagedPoso(BinaryOperatorDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(BinaryOperatorDto dto, BinaryOperator poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(BinaryOperatorDto dto, BinaryOperator poso)  {
		/* no merging for enums */
	}

	public BinaryOperator loadAndMergePoso(BinaryOperatorDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(BinaryOperatorDto dto, BinaryOperator poso)  {
	}


	public void postProcessCreateUnmanaged(BinaryOperatorDto dto, BinaryOperator poso)  {
	}


	public void postProcessLoad(BinaryOperatorDto dto, BinaryOperator poso)  {
	}


	public void postProcessMerge(BinaryOperatorDto dto, BinaryOperator poso)  {
	}


	public void postProcessInstantiate(BinaryOperator poso)  {
	}



}
