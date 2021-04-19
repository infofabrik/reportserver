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
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.ExecutionLogEntry2DtoGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ExecutionLogEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ExecutionLogEntry2DtoGenerator implements Poso2DtoGenerator<ExecutionLogEntry,ExecutionLogEntryDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ExecutionLogEntry2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ExecutionLogEntryDto instantiateDto(ExecutionLogEntry poso)  {
		ExecutionLogEntryDto dto = new ExecutionLogEntryDto();
		return dto;
	}

	public ExecutionLogEntryDto createDto(ExecutionLogEntry poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ExecutionLogEntryDto dto = new ExecutionLogEntryDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set end */
			dto.setEnd(poso.getEnd() );

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

			/*  set scheduledStart */
			dto.setScheduledStart(poso.getScheduledStart() );

			/*  set start */
			dto.setStart(poso.getStart() );

			/*  set vetoExplanation */
			dto.setVetoExplanation(StringEscapeUtils.escapeXml(StringUtils.left(poso.getVetoExplanation(),8192)));

			/*  set vetoMode */
			Object tmpDtoVetoJobExecutionModeDtogetVetoMode = dtoServiceProvider.get().createDto(poso.getVetoMode(), referenced, referenced);
			dto.setVetoMode((VetoJobExecutionModeDto)tmpDtoVetoJobExecutionModeDtogetVetoMode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoVetoJobExecutionModeDtogetVetoMode, poso.getVetoMode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setVetoMode((VetoJobExecutionModeDto)refDto);
				}
			});

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set actionEntries */
			final List<ActionEntryDto> col_actionEntries = new ArrayList<ActionEntryDto>();
			if( null != poso.getActionEntries()){
				for(ActionEntry refPoso : poso.getActionEntries()){
					final Object tmpDtoActionEntryDtogetActionEntries = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_actionEntries.add((ActionEntryDto) tmpDtoActionEntryDtogetActionEntries);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoActionEntryDtogetActionEntries, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (actionEntries)");
							int tmp_index = col_actionEntries.indexOf(tmpDtoActionEntryDtogetActionEntries);
							col_actionEntries.set(tmp_index,(ActionEntryDto) dto);
						}
					});
				}
				dto.setActionEntries(col_actionEntries);
			}

			/*  set badErrorDescription */
			dto.setBadErrorDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getBadErrorDescription(),8192)));

			/*  set jobEntry */
			Object tmpDtoJobEntryDtogetJobEntry = dtoServiceProvider.get().createDto(poso.getJobEntry(), here, referenced);
			dto.setJobEntry((JobEntryDto)tmpDtoJobEntryDtogetJobEntry);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoJobEntryDtogetJobEntry, poso.getJobEntry(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setJobEntry((JobEntryDto)refDto);
				}
			});

		}

		return dto;
	}


}
