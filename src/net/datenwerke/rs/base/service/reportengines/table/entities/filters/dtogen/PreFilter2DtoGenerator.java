package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.PreFilterDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.PreFilter2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for PreFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class PreFilter2DtoGenerator implements Poso2DtoGenerator<PreFilter,PreFilterDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public PreFilter2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public PreFilterDtoDec instantiateDto(PreFilter poso)  {
		PreFilterDtoDec dto = new PreFilterDtoDec();
		return dto;
	}

	public PreFilterDtoDec createDto(PreFilter poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final PreFilterDtoDec dto = new PreFilterDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set rootBlock */
			Object tmpDtoFilterBlockDtogetRootBlock = dtoServiceProvider.get().createDto(poso.getRootBlock(), here, referenced);
			dto.setRootBlock((FilterBlockDto)tmpDtoFilterBlockDtogetRootBlock);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoFilterBlockDtogetRootBlock, poso.getRootBlock(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setRootBlock((FilterBlockDto)refDto);
				}
			});

			/*  set rootBlockType */
			Object tmpDtoBlockTypeDtogetRootBlockType = dtoServiceProvider.get().createDto(poso.getRootBlockType(), referenced, referenced);
			dto.setRootBlockType((BlockTypeDto)tmpDtoBlockTypeDtogetRootBlockType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoBlockTypeDtogetRootBlockType, poso.getRootBlockType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setRootBlockType((BlockTypeDto)refDto);
				}
			});

		}

		return dto;
	}


}
