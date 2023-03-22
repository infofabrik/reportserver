package net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.dtogen.TableDatasink2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TableDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TableDatasink2DtoGenerator implements Poso2DtoGenerator<TableDatasink,TableDatasinkDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TableDatasink2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
	}

	public TableDatasinkDto instantiateDto(TableDatasink poso)  {
		TableDatasinkDto dto = new TableDatasinkDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		return dto;
	}

	public TableDatasinkDto createDto(TableDatasink poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TableDatasinkDto dto = new TableDatasinkDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set position */
			dto.setPosition(poso.getPosition() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set createdOn */
			dto.setCreatedOn(poso.getCreatedOn() );

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set batchSize */
			dto.setBatchSize(poso.getBatchSize() );

			/*  set copyPrimaryKeys */
			dto.setCopyPrimaryKeys(poso.isCopyPrimaryKeys() );

			/*  set datasourceContainer */
			Object tmpDtoDatasourceContainerDtogetDatasourceContainer = dtoServiceProvider.get().createDto(poso.getDatasourceContainer(), here, referenced);
			dto.setDatasourceContainer((DatasourceContainerDto)tmpDtoDatasourceContainerDtogetDatasourceContainer);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceContainerDtogetDatasourceContainer, poso.getDatasourceContainer(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDatasourceContainer((DatasourceContainerDto)refDto);
				}
			});

			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set primaryKeys */
			dto.setPrimaryKeys(StringEscapeUtils.escapeXml(StringUtils.left(poso.getPrimaryKeys(),8192)));

			/*  set tableName */
			dto.setTableName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTableName(),8192)));

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);

		return dto;
	}


}
