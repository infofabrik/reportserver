package net.datenwerke.scheduler.service.scheduler.entities.history.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;
import net.datenwerke.scheduler.service.scheduler.entities.history.HistoryEntryProperty;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.JobEntry2DtoGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for JobEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JobEntry2DtoGenerator implements Poso2DtoGenerator<JobEntry,JobEntryDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public JobEntry2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public JobEntryDto instantiateDto(JobEntry poso)  {
		JobEntryDto dto = new JobEntryDto();
		return dto;
	}

	public JobEntryDto createDto(JobEntry poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final JobEntryDto dto = new JobEntryDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set errorDescription */
			dto.setErrorDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getErrorDescription(),8192)));

			/*  set historyProperties */
			final Set<HistoryEntryPropertyDto> col_historyProperties = new HashSet<HistoryEntryPropertyDto>();
			if( null != poso.getHistoryProperties()){
				for(HistoryEntryProperty refPoso : poso.getHistoryProperties()){
					final Object tmpDtoHistoryEntryPropertyDtogetHistoryProperties = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_historyProperties.add((HistoryEntryPropertyDto) tmpDtoHistoryEntryPropertyDtogetHistoryProperties);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoHistoryEntryPropertyDtogetHistoryProperties, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (historyProperties)");
							col_historyProperties.remove(tmpDtoHistoryEntryPropertyDtogetHistoryProperties);
							col_historyProperties.add((HistoryEntryPropertyDto) dto);
						}
					});
				}
				dto.setHistoryProperties(col_historyProperties);
			}

			/*  set outcome */
			Object tmpDtoOutcomeDtogetOutcome = dtoServiceProvider.get().createDto(poso.getOutcome(), referenced, referenced);
			dto.setOutcome((OutcomeDto)tmpDtoOutcomeDtogetOutcome);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOutcomeDtogetOutcome, poso.getOutcome(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setOutcome((OutcomeDto)refDto);
				}
			});

		}

		return dto;
	}


}
