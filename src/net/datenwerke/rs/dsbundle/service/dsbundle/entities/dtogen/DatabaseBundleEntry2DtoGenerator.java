package net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundleEntry;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen.DatabaseBundleEntry2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for DatabaseBundleEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseBundleEntry2DtoGenerator implements Poso2DtoGenerator<DatabaseBundleEntry,DatabaseBundleEntryDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatabaseBundleEntry2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatabaseBundleEntryDto instantiateDto(DatabaseBundleEntry poso)  {
		DatabaseBundleEntryDto dto = new DatabaseBundleEntryDto();
		return dto;
	}

	public DatabaseBundleEntryDto createDto(DatabaseBundleEntry poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatabaseBundleEntryDto dto = new DatabaseBundleEntryDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set database */
			Object tmpDtoAbstractDatasourceManagerNodeDtogetDatabase = dtoServiceProvider.get().createDto(poso.getDatabase(), referenced, referenced);
			dto.setDatabase((AbstractDatasourceManagerNodeDto)tmpDtoAbstractDatasourceManagerNodeDtogetDatabase);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAbstractDatasourceManagerNodeDtogetDatabase, poso.getDatabase(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDatabase((AbstractDatasourceManagerNodeDto)refDto);
				}
			});

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

		}

		return dto;
	}


}
