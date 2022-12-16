package net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.ScriptDatasourceConfig;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.dtogen.ScriptDatasourceConfig2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ScriptDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ScriptDatasourceConfig2DtoGenerator implements Poso2DtoGenerator<ScriptDatasourceConfig,ScriptDatasourceConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ScriptDatasourceConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ScriptDatasourceConfigDto instantiateDto(ScriptDatasourceConfig poso)  {
		ScriptDatasourceConfigDto dto = new ScriptDatasourceConfigDto();
		return dto;
	}

	public ScriptDatasourceConfigDto createDto(ScriptDatasourceConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ScriptDatasourceConfigDto dto = new ScriptDatasourceConfigDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set arguments */
			dto.setArguments(StringEscapeUtils.escapeXml(StringUtils.left(poso.getArguments(),8192)));

			/*  set queryWrapper */
			dto.setQueryWrapper(poso.getQueryWrapper() );

			/*  set script */
			dto.setScript(poso.getScript() );

		}

		return dto;
	}


}
