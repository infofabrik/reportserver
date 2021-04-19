package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.AutocompleteResultDtoDec;
import net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.AutocompleteResult2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for AutocompleteResult
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AutocompleteResult2DtoGenerator implements Poso2DtoGenerator<AutocompleteResult,AutocompleteResultDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AutocompleteResult2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AutocompleteResultDtoDec instantiateDto(AutocompleteResult poso)  {
		AutocompleteResultDtoDec dto = new AutocompleteResultDtoDec();
		return dto;
	}

	public AutocompleteResultDtoDec createDto(AutocompleteResult poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final AutocompleteResultDtoDec dto = new AutocompleteResultDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set cmdEntries */
			Object tmpDtoCommandResultListDtogetCmdEntries = dtoServiceProvider.get().createDto(poso.getCmdEntries(), here, referenced);
			dto.setCmdEntries((CommandResultListDto)tmpDtoCommandResultListDtogetCmdEntries);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoCommandResultListDtogetCmdEntries, poso.getCmdEntries(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setCmdEntries((CommandResultListDto)refDto);
				}
			});

			/*  set completeStump */
			dto.setCompleteStump(StringEscapeUtils.escapeXml(StringUtils.left(poso.getCompleteStump(),8192)));

			/*  set entries */
			Object tmpDtoCommandResultListDtogetEntries = dtoServiceProvider.get().createDto(poso.getEntries(), here, referenced);
			dto.setEntries((CommandResultListDto)tmpDtoCommandResultListDtogetEntries);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoCommandResultListDtogetEntries, poso.getEntries(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setEntries((CommandResultListDto)refDto);
				}
			});

			/*  set objectEntries */
			Object tmpDtoCommandResultListDtogetObjectEntries = dtoServiceProvider.get().createDto(poso.getObjectEntries(), here, referenced);
			dto.setObjectEntries((CommandResultListDto)tmpDtoCommandResultListDtogetObjectEntries);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoCommandResultListDtogetObjectEntries, poso.getObjectEntries(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setObjectEntries((CommandResultListDto)refDto);
				}
			});

		}

		return dto;
	}


}
