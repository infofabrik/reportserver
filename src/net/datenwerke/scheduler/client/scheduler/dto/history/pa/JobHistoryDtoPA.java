package net.datenwerke.scheduler.client.scheduler.dto.history.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory.class)
public interface JobHistoryDtoPA extends PropertyAccess<JobHistoryDto> {


	public static final JobHistoryDtoPA INSTANCE = GWT.create(JobHistoryDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<JobHistoryDto> dtoId();

	/* Properties */
	public ValueProvider<JobHistoryDto,List<ExecutionLogEntryDto>> executionLogEntries();
	public ValueProvider<JobHistoryDto,Long> id();


}
