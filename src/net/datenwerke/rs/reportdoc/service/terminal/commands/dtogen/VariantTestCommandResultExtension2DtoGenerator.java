package net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto;
import net.datenwerke.rs.reportdoc.service.terminal.commands.VariantTestCommandResultExtension;
import net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen.VariantTestCommandResultExtension2DtoGenerator;

/**
 * Poso2DtoGenerator for VariantTestCommandResultExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class VariantTestCommandResultExtension2DtoGenerator implements Poso2DtoGenerator<VariantTestCommandResultExtension,VariantTestCommandResultExtensionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public VariantTestCommandResultExtension2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public VariantTestCommandResultExtensionDto instantiateDto(VariantTestCommandResultExtension poso)  {
		VariantTestCommandResultExtensionDto dto = new VariantTestCommandResultExtensionDto();
		return dto;
	}

	public VariantTestCommandResultExtensionDto createDto(VariantTestCommandResultExtension poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final VariantTestCommandResultExtensionDto dto = new VariantTestCommandResultExtensionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set datasources */
			final List<DatasourceDefinitionDto> col_datasources = new ArrayList<DatasourceDefinitionDto>();
			if( null != poso.getDatasources()){
				for(DatasourceDefinition refPoso : poso.getDatasources()){
					final Object tmpDtoDatasourceDefinitionDtogetDatasources = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
					col_datasources.add((DatasourceDefinitionDto) tmpDtoDatasourceDefinitionDtogetDatasources);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceDefinitionDtogetDatasources, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (datasources)");
							int tmp_index = col_datasources.indexOf(tmpDtoDatasourceDefinitionDtogetDatasources);
							col_datasources.set(tmp_index,(DatasourceDefinitionDto) dto);
						}
					});
				}
				dto.setDatasources(col_datasources);
			}

			/*  set report */
			Object tmpDtoReportDtogetReport = dtoServiceProvider.get().createDto(poso.getReport(), referenced, referenced);
			dto.setReport((ReportDto)tmpDtoReportDtogetReport);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoReportDtogetReport, poso.getReport(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setReport((ReportDto)refDto);
				}
			});

		}

		return dto;
	}


}
