package net.datenwerke.scheduler.service.scheduler.entities.history.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory;
import net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.JobHistory2DtoGenerator;

/**
 * Poso2DtoGenerator for JobHistory
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JobHistory2DtoGenerator implements Poso2DtoGenerator<JobHistory,JobHistoryDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public JobHistory2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public JobHistoryDto instantiateDto(JobHistory poso)  {
		JobHistoryDto dto = new JobHistoryDto();
		return dto;
	}

	public JobHistoryDto createDto(JobHistory poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final JobHistoryDto dto = new JobHistoryDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set executionLogEntries */
			final List<ExecutionLogEntryDto> col_executionLogEntries = new ArrayList<ExecutionLogEntryDto>();
			if( null != poso.getExecutionLogEntries()){
				for(ExecutionLogEntry refPoso : poso.getExecutionLogEntries()){
					final Object tmpDtoExecutionLogEntryDtogetExecutionLogEntries = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_executionLogEntries.add((ExecutionLogEntryDto) tmpDtoExecutionLogEntryDtogetExecutionLogEntries);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoExecutionLogEntryDtogetExecutionLogEntries, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (executionLogEntries)");
							int tmp_index = col_executionLogEntries.indexOf(tmpDtoExecutionLogEntryDtogetExecutionLogEntries);
							col_executionLogEntries.set(tmp_index,(ExecutionLogEntryDto) dto);
						}
					});
				}
				dto.setExecutionLogEntries(col_executionLogEntries);
			}

		}

		return dto;
	}


}
