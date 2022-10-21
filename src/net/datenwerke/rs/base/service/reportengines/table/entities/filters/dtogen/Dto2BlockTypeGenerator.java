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
import net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2BlockTypeGenerator;

/**
 * Dto2PosoGenerator for BlockType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BlockTypeGenerator implements Dto2PosoGenerator<BlockTypeDto,BlockType> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2BlockTypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BlockType loadPoso(BlockTypeDto dto)  {
		return createPoso(dto);
	}

	public BlockType instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public BlockType createPoso(BlockTypeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case OR:
				return BlockType.OR;
			case AND:
				return BlockType.AND;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public BlockType createUnmanagedPoso(BlockTypeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(BlockTypeDto dto, BlockType poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(BlockTypeDto dto, BlockType poso)  {
		/* no merging for enums */
	}

	public BlockType loadAndMergePoso(BlockTypeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(BlockTypeDto dto, BlockType poso)  {
	}


	public void postProcessCreateUnmanaged(BlockTypeDto dto, BlockType poso)  {
	}


	public void postProcessLoad(BlockTypeDto dto, BlockType poso)  {
	}


	public void postProcessMerge(BlockTypeDto dto, BlockType poso)  {
	}


	public void postProcessInstantiate(BlockType poso)  {
	}



}
