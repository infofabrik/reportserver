package net.datenwerke.rs.base.service.datasources.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnectorConfig;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.definitions.dtogen.CsvDatasourceConfig2DtoGenerator;

/**
 * Poso2DtoGenerator for CsvDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CsvDatasourceConfig2DtoGenerator implements Poso2DtoGenerator<CsvDatasourceConfig,CsvDatasourceConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CsvDatasourceConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CsvDatasourceConfigDto instantiateDto(CsvDatasourceConfig poso)  {
		CsvDatasourceConfigDto dto = new CsvDatasourceConfigDto();
		return dto;
	}

	public CsvDatasourceConfigDto createDto(CsvDatasourceConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CsvDatasourceConfigDto dto = new CsvDatasourceConfigDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set connectorConfig */
			final List<DatasourceConnectorConfigDto> col_connectorConfig = new ArrayList<DatasourceConnectorConfigDto>();
			if( null != poso.getConnectorConfig()){
				for(DatasourceConnectorConfig refPoso : poso.getConnectorConfig()){
					final Object tmpDtoDatasourceConnectorConfigDtogetConnectorConfig = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_connectorConfig.add((DatasourceConnectorConfigDto) tmpDtoDatasourceConnectorConfigDtogetConnectorConfig);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceConnectorConfigDtogetConnectorConfig, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (connectorConfig)");
							int tmp_index = col_connectorConfig.indexOf(tmpDtoDatasourceConnectorConfigDtogetConnectorConfig);
							col_connectorConfig.set(tmp_index,(DatasourceConnectorConfigDto) dto);
						}
					});
				}
				dto.setConnectorConfig(col_connectorConfig);
			}

			/*  set queryWrapper */
			dto.setQueryWrapper(poso.getQueryWrapper() );

		}

		return dto;
	}


}
