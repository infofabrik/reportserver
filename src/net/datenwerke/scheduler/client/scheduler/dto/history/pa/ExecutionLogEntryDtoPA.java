package net.datenwerke.scheduler.client.scheduler.dto.history.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry.class)
public interface ExecutionLogEntryDtoPA extends PropertyAccess<ExecutionLogEntryDto> {


	public static final ExecutionLogEntryDtoPA INSTANCE = GWT.create(ExecutionLogEntryDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<ExecutionLogEntryDto> dtoId();

	/* Properties */
	public ValueProvider<ExecutionLogEntryDto,List<ActionEntryDto>> actionEntries();
	public ValueProvider<ExecutionLogEntryDto,String> badErrorDescription();
	public ValueProvider<ExecutionLogEntryDto,Date> end();
	public ValueProvider<ExecutionLogEntryDto,Long> id();
	public ValueProvider<ExecutionLogEntryDto,JobEntryDto> jobEntry();
	public ValueProvider<ExecutionLogEntryDto,OutcomeDto> outcome();
	public ValueProvider<ExecutionLogEntryDto,Date> scheduledStart();
	public ValueProvider<ExecutionLogEntryDto,Date> start();
	public ValueProvider<ExecutionLogEntryDto,String> vetoExplanation();
	public ValueProvider<ExecutionLogEntryDto,VetoJobExecutionModeDto> vetoMode();


}
