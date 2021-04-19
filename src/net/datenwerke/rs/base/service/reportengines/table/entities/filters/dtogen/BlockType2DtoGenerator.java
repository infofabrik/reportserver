package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.BlockType2DtoGenerator;

/**
 * Poso2DtoGenerator for BlockType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BlockType2DtoGenerator implements Poso2DtoGenerator<BlockType,BlockTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public BlockType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BlockTypeDto instantiateDto(BlockType poso)  {
		/* Simply return the first enum! */
		BlockTypeDto dto = BlockTypeDto.OR;
		return dto;
	}

	public BlockTypeDto createDto(BlockType poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case OR:
				return BlockTypeDto.OR;
			case AND:
				return BlockTypeDto.AND;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
