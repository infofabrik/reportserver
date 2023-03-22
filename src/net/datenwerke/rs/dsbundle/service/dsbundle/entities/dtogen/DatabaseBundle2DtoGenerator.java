package net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundleEntry;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen.DatabaseBundle2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for DatabaseBundle
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseBundle2DtoGenerator implements Poso2DtoGenerator<DatabaseBundle,DatabaseBundleDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final net.datenwerke.rs.base.service.datasources.definitions.dtogen.DatabaseDatasource2DtoPostProcessor postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatabaseBundle2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2,
		net.datenwerke.rs.base.service.datasources.definitions.dtogen.DatabaseDatasource2DtoPostProcessor postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public DatabaseBundleDto instantiateDto(DatabaseBundle poso)  {
		DatabaseBundleDto dto = new DatabaseBundleDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public DatabaseBundleDto createDto(DatabaseBundle poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatabaseBundleDto dto = new DatabaseBundleDto();
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
			/*  set bundleEntries */
			final Set<DatabaseBundleEntryDto> col_bundleEntries = new HashSet<DatabaseBundleEntryDto>();
			if( null != poso.getBundleEntries()){
				for(DatabaseBundleEntry refPoso : poso.getBundleEntries()){
					final Object tmpDtoDatabaseBundleEntryDtogetBundleEntries = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_bundleEntries.add((DatabaseBundleEntryDto) tmpDtoDatabaseBundleEntryDtogetBundleEntries);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatabaseBundleEntryDtogetBundleEntries, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (bundleEntries)");
							col_bundleEntries.remove(tmpDtoDatabaseBundleEntryDtogetBundleEntries);
							col_bundleEntries.add((DatabaseBundleEntryDto) dto);
						}
					});
				}
				dto.setBundleEntries(col_bundleEntries);
			}

			/*  set databaseDescriptor */
			dto.setDatabaseDescriptor(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDatabaseDescriptor(),8192)));

			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set jdbcProperties */
			dto.setJdbcProperties(poso.getJdbcProperties() );

			/*  set keySource */
			dto.setKeySource(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKeySource(),8192)));

			/*  set keySourceParamName */
			dto.setKeySourceParamName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKeySourceParamName(),8192)));

			/*  set mappingSource */
			dto.setMappingSource(StringEscapeUtils.escapeXml(StringUtils.left(poso.getMappingSource(),8192)));

			/*  set url */
			dto.setUrl(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUrl(),8192)));

			/*  set username */
			dto.setUsername(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUsername(),8192)));

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);

		return dto;
	}


}
