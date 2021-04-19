package net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen;

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
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.LayoutType;
import net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2LayoutTypeGenerator;

/**
 * Dto2PosoGenerator for LayoutType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2LayoutTypeGenerator implements Dto2PosoGenerator<LayoutTypeDto,LayoutType> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2LayoutTypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public LayoutType loadPoso(LayoutTypeDto dto)  {
		return createPoso(dto);
	}

	public LayoutType instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public LayoutType createPoso(LayoutTypeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case ONE_COLUMN:
				return LayoutType.ONE_COLUMN;
			case TWO_COLUMN_EQUI:
				return LayoutType.TWO_COLUMN_EQUI;
			case TWO_COLUMN_LEFT_LARGE:
				return LayoutType.TWO_COLUMN_LEFT_LARGE;
			case TWO_COLUMN_RIGHT_LARGE:
				return LayoutType.TWO_COLUMN_RIGHT_LARGE;
			case THREE_COLUMN:
				return LayoutType.THREE_COLUMN;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public LayoutType createUnmanagedPoso(LayoutTypeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(LayoutTypeDto dto, LayoutType poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(LayoutTypeDto dto, LayoutType poso)  {
		/* no merging for enums */
	}

	public LayoutType loadAndMergePoso(LayoutTypeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(LayoutTypeDto dto, LayoutType poso)  {
	}


	public void postProcessCreateUnmanaged(LayoutTypeDto dto, LayoutType poso)  {
	}


	public void postProcessLoad(LayoutTypeDto dto, LayoutType poso)  {
	}


	public void postProcessMerge(LayoutTypeDto dto, LayoutType poso)  {
	}


	public void postProcessInstantiate(LayoutType poso)  {
	}



}
