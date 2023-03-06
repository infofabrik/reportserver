package net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.ComputedColumn;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.dtogen.ComputedColumn2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ComputedColumn
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ComputedColumn2DtoGenerator implements Poso2DtoGenerator<ComputedColumn,ComputedColumnDto> {

	private final net.datenwerke.rs.base.service.reportengines.table.entities.post.ColumnDtoPost postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ComputedColumn2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.base.service.reportengines.table.entities.post.ColumnDtoPost postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public ComputedColumnDto instantiateDto(ComputedColumn poso)  {
		ComputedColumnDto dto = new ComputedColumnDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public ComputedColumnDto createDto(ComputedColumn poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ComputedColumnDto dto = new ComputedColumnDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set type */
			dto.setType(poso.getType() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set aggregateFunction */
			Object tmpDtoAggregateFunctionDtogetAggregateFunction = dtoServiceProvider.get().createDto(poso.getAggregateFunction(), referenced, referenced);
			dto.setAggregateFunction((AggregateFunctionDto)tmpDtoAggregateFunctionDtogetAggregateFunction);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAggregateFunctionDtogetAggregateFunction, poso.getAggregateFunction(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setAggregateFunction((AggregateFunctionDto)refDto);
				}
			});

			/*  set alias */
			dto.setAlias(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAlias(),8192)));

			/*  set defaultAlias */
			dto.setDefaultAlias(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDefaultAlias(),8192)));

			/*  set defaultPreviewWidth */
			dto.setDefaultPreviewWidth(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDefaultPreviewWidth(),8192)));

			/*  set dimension */
			dto.setDimension(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDimension(),8192)));

			/*  set exportNullAsString */
			dto.setExportNullAsString(poso.isExportNullAsString() );

			/*  set expression */
			dto.setExpression(poso.getExpression() );

			/*  set filter */
			Object tmpDtoFilterDtogetFilter = dtoServiceProvider.get().createDto(poso.getFilter(), here, referenced);
			dto.setFilter((FilterDto)tmpDtoFilterDtogetFilter);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoFilterDtogetFilter, poso.getFilter(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setFilter((FilterDto)refDto);
				}
			});

			/*  set format */
			Object tmpDtoColumnFormatDtogetFormat = dtoServiceProvider.get().createDto(poso.getFormat(), here, referenced);
			dto.setFormat((ColumnFormatDto)tmpDtoColumnFormatDtogetFormat);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoColumnFormatDtogetFormat, poso.getFormat(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setFormat((ColumnFormatDto)refDto);
				}
			});

			/*  set hidden */
			dto.setHidden(poso.isHidden() );

			/*  set indexColumn */
			dto.setIndexColumn(poso.isIndexColumn() );

			/*  set nullHandling */
			Object tmpDtoNullHandlingDtogetNullHandling = dtoServiceProvider.get().createDto(poso.getNullHandling(), referenced, referenced);
			dto.setNullHandling((NullHandlingDto)tmpDtoNullHandlingDtogetNullHandling);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoNullHandlingDtogetNullHandling, poso.getNullHandling(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setNullHandling((NullHandlingDto)refDto);
				}
			});

			/*  set nullReplacementFormat */
			dto.setNullReplacementFormat(StringEscapeUtils.escapeXml(StringUtils.left(poso.getNullReplacementFormat(),8192)));

			/*  set order */
			Object tmpDtoOrderDtogetOrder = dtoServiceProvider.get().createDto(poso.getOrder(), referenced, referenced);
			dto.setOrder((OrderDto)tmpDtoOrderDtogetOrder);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOrderDtogetOrder, poso.getOrder(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setOrder((OrderDto)refDto);
				}
			});

			/*  set previewWidth */
			dto.setPreviewWidth(poso.getPreviewWidth() );

			/*  set semanticType */
			dto.setSemanticType(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSemanticType(),8192)));

			/*  set subtotalGroup */
			dto.setSubtotalGroup(poso.isSubtotalGroup() );

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
