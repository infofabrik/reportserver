package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.FilterBlock2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for FilterBlock
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FilterBlock2DtoGenerator implements Poso2DtoGenerator<FilterBlock,FilterBlockDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FilterBlock2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FilterBlockDtoDec instantiateDto(FilterBlock poso)  {
		FilterBlockDtoDec dto = new FilterBlockDtoDec();
		return dto;
	}

	public FilterBlockDtoDec createDto(FilterBlock poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FilterBlockDtoDec dto = new FilterBlockDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set childBlocks */
			final Set<FilterBlockDto> col_childBlocks = new HashSet<FilterBlockDto>();
			if( null != poso.getChildBlocks()){
				for(FilterBlock refPoso : poso.getChildBlocks()){
					final Object tmpDtoFilterBlockDtogetChildBlocks = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_childBlocks.add((FilterBlockDto) tmpDtoFilterBlockDtogetChildBlocks);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoFilterBlockDtogetChildBlocks, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (childBlocks)");
							col_childBlocks.remove(tmpDtoFilterBlockDtogetChildBlocks);
							col_childBlocks.add((FilterBlockDto) dto);
						}
					});
				}
				dto.setChildBlocks(col_childBlocks);
			}

			/*  set filters */
			final Set<FilterSpecDto> col_filters = new HashSet<FilterSpecDto>();
			if( null != poso.getFilters()){
				for(FilterSpec refPoso : poso.getFilters()){
					final Object tmpDtoFilterSpecDtogetFilters = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_filters.add((FilterSpecDto) tmpDtoFilterSpecDtogetFilters);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoFilterSpecDtogetFilters, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (filters)");
							col_filters.remove(tmpDtoFilterSpecDtogetFilters);
							col_filters.add((FilterSpecDto) dto);
						}
					});
				}
				dto.setFilters(col_filters);
			}

		}

		return dto;
	}


}
