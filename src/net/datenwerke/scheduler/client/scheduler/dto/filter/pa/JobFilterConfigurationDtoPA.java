package net.datenwerke.scheduler.client.scheduler.dto.filter.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration.class)
public interface JobFilterConfigurationDtoPA extends PropertyAccess<JobFilterConfigurationDto> {


	public static final JobFilterConfigurationDtoPA INSTANCE = GWT.create(JobFilterConfigurationDtoPA.class);


	/* Properties */
	public ValueProvider<JobFilterConfigurationDto,Boolean> active();
	public ValueProvider<JobFilterConfigurationDto,JobExecutionStatusDto> executionStatus();
	public ValueProvider<JobFilterConfigurationDto,Boolean> inActive();
	public ValueProvider<JobFilterConfigurationDto,String> jobId();
	public ValueProvider<JobFilterConfigurationDto,String> jobTitle();
	public ValueProvider<JobFilterConfigurationDto,OutcomeDto> lastOutcome();
	public ValueProvider<JobFilterConfigurationDto,Integer> limit();
	public ValueProvider<JobFilterConfigurationDto,Integer> offset();
	public ValueProvider<JobFilterConfigurationDto,OrderDto> order();
	public ValueProvider<JobFilterConfigurationDto,String> sortField();


}
