package net.datenwerke.scheduler.client.scheduler.dto.history.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry.class)
public interface JobEntryDtoPA extends PropertyAccess<JobEntryDto> {


	public static final JobEntryDtoPA INSTANCE = GWT.create(JobEntryDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<JobEntryDto> dtoId();

	/* Properties */
	public ValueProvider<JobEntryDto,String> errorDescription();
	public ValueProvider<JobEntryDto,Set<HistoryEntryPropertyDto>> historyProperties();
	public ValueProvider<JobEntryDto,Long> id();
	public ValueProvider<JobEntryDto,OutcomeDto> outcome();


}
