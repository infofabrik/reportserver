package net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.dtogen.ReportServerJobFilter2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ReportServerJobFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportServerJobFilter2DtoGenerator implements Poso2DtoGenerator<ReportServerJobFilter,ReportServerJobFilterDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ReportServerJobFilter2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ReportServerJobFilterDto instantiateDto(ReportServerJobFilter poso)  {
		ReportServerJobFilterDto dto = new ReportServerJobFilterDto();
		return dto;
	}

	public ReportServerJobFilterDto createDto(ReportServerJobFilter poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ReportServerJobFilterDto dto = new ReportServerJobFilterDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set active */
			dto.setActive(poso.isActive() );

			/*  set executionStatus */
			Object tmpDtoJobExecutionStatusDtogetExecutionStatus = dtoServiceProvider.get().createDto(poso.getExecutionStatus(), referenced, referenced);
			dto.setExecutionStatus((JobExecutionStatusDto)tmpDtoJobExecutionStatusDtogetExecutionStatus);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoJobExecutionStatusDtogetExecutionStatus, poso.getExecutionStatus(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setExecutionStatus((JobExecutionStatusDto)refDto);
				}
			});

			/*  set fromCurrentUser */
			dto.setFromCurrentUser(poso.isFromCurrentUser() );

			/*  set fromUser */
			Object tmpDtoUserDtogetFromUser = dtoServiceProvider.get().createDto(poso.getFromUser(), referenced, referenced);
			dto.setFromUser((UserDto)tmpDtoUserDtogetFromUser);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUserDtogetFromUser, poso.getFromUser(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setFromUser((UserDto)refDto);
				}
			});

			/*  set inActive */
			dto.setInActive(poso.isInActive() );

			/*  set jobId */
			dto.setJobId(StringEscapeUtils.escapeXml(StringUtils.left(poso.getJobId(),8192)));

			/*  set jobTitle */
			dto.setJobTitle(StringEscapeUtils.escapeXml(StringUtils.left(poso.getJobTitle(),8192)));

			/*  set lastOutcome */
			Object tmpDtoOutcomeDtogetLastOutcome = dtoServiceProvider.get().createDto(poso.getLastOutcome(), referenced, referenced);
			dto.setLastOutcome((OutcomeDto)tmpDtoOutcomeDtogetLastOutcome);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOutcomeDtogetLastOutcome, poso.getLastOutcome(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setLastOutcome((OutcomeDto)refDto);
				}
			});

			/*  set limit */
			dto.setLimit(poso.getLimit() );

			/*  set offset */
			dto.setOffset(poso.getOffset() );

			/*  set order */
			Object tmpDtoOrderDtogetOrder = dtoServiceProvider.get().createDto(poso.getOrder(), referenced, referenced);
			dto.setOrder((OrderDto)tmpDtoOrderDtogetOrder);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOrderDtogetOrder, poso.getOrder(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setOrder((OrderDto)refDto);
				}
			});

			/*  set owner */
			Object tmpDtoUserDtogetOwner = dtoServiceProvider.get().createDto(poso.getOwner(), referenced, referenced);
			dto.setOwner((UserDto)tmpDtoUserDtogetOwner);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUserDtogetOwner, poso.getOwner(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setOwner((UserDto)refDto);
				}
			});

			/*  set reportId */
			dto.setReportId(StringEscapeUtils.escapeXml(StringUtils.left(poso.getReportId(),8192)));

			/*  set reports */
			Set<Long> col_reports = new HashSet<Long>();
			if( null != poso.getReports()){
				for(Long obj : poso.getReports())
					col_reports.add((Long) obj);
				dto.setReports(col_reports);
			}

			/*  set scheduledBy */
			Object tmpDtoUserDtogetScheduledBy = dtoServiceProvider.get().createDto(poso.getScheduledBy(), referenced, referenced);
			dto.setScheduledBy((UserDto)tmpDtoUserDtogetScheduledBy);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUserDtogetScheduledBy, poso.getScheduledBy(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setScheduledBy((UserDto)refDto);
				}
			});

			/*  set sortField */
			dto.setSortField(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSortField(),8192)));

			/*  set toCurrentUser */
			dto.setToCurrentUser(poso.isToCurrentUser() );

			/*  set toUser */
			Object tmpDtoUserDtogetToUser = dtoServiceProvider.get().createDto(poso.getToUser(), referenced, referenced);
			dto.setToUser((UserDto)tmpDtoUserDtogetToUser);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUserDtogetToUser, poso.getToUser(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setToUser((UserDto)refDto);
				}
			});

		}

		return dto;
	}


}
