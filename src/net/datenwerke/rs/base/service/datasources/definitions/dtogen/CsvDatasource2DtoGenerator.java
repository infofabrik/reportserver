package net.datenwerke.rs.base.service.datasources.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.dtogen.CsvDatasource2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CsvDatasource
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CsvDatasource2DtoGenerator implements Poso2DtoGenerator<CsvDatasource,CsvDatasourceDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CsvDatasource2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
	}

	public CsvDatasourceDto instantiateDto(CsvDatasource poso)  {
		CsvDatasourceDto dto = new CsvDatasourceDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		return dto;
	}

	public CsvDatasourceDto createDto(CsvDatasource poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CsvDatasourceDto dto = new CsvDatasourceDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set connector */
			Object tmpDtoDatasourceConnectorDtogetConnector = dtoServiceProvider.get().createDto(poso.getConnector(), here, referenced);
			dto.setConnector((DatasourceConnectorDto)tmpDtoDatasourceConnectorDtogetConnector);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceConnectorDtogetConnector, poso.getConnector(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setConnector((DatasourceConnectorDto)refDto);
				}
			});

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

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set databaseCache */
			dto.setDatabaseCache(poso.getDatabaseCache() );

			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set quote */
			dto.setQuote(StringEscapeUtils.escapeXml(StringUtils.left(poso.getQuote(),8192)));

			/*  set separator */
			dto.setSeparator(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSeparator(),8192)));

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);

		return dto;
	}


}
