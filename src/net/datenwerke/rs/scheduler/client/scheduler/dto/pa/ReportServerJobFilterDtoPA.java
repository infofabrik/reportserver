package net.datenwerke.rs.scheduler.client.scheduler.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.pa.JobFilterConfigurationDtoPA;
import net.datenwerke.security.client.usermanager.dto.UserDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter.class)
public interface ReportServerJobFilterDtoPA extends JobFilterConfigurationDtoPA {


	public static final ReportServerJobFilterDtoPA INSTANCE = GWT.create(ReportServerJobFilterDtoPA.class);


	/* Properties */
	public ValueProvider<ReportServerJobFilterDto,Boolean> fromCurrentUser();
	public ValueProvider<ReportServerJobFilterDto,UserDto> fromUser();
	public ValueProvider<ReportServerJobFilterDto,UserDto> owner();
	public ValueProvider<ReportServerJobFilterDto,String> reportId();
	public ValueProvider<ReportServerJobFilterDto,Set<Long>> reports();
	public ValueProvider<ReportServerJobFilterDto,UserDto> scheduledBy();
	public ValueProvider<ReportServerJobFilterDto,Boolean> toCurrentUser();
	public ValueProvider<ReportServerJobFilterDto,UserDto> toUser();


}
