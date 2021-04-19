package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayOfWeekConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.MonthlyNthDayOfWeekConfig2DtoGenerator;

/**
 * Poso2DtoGenerator for MonthlyNthDayOfWeekConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MonthlyNthDayOfWeekConfig2DtoGenerator implements Poso2DtoGenerator<MonthlyNthDayOfWeekConfig,MonthlyNthDayOfWeekConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MonthlyNthDayOfWeekConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MonthlyNthDayOfWeekConfigDto instantiateDto(MonthlyNthDayOfWeekConfig poso)  {
		MonthlyNthDayOfWeekConfigDto dto = new MonthlyNthDayOfWeekConfigDto();
		return dto;
	}

	public MonthlyNthDayOfWeekConfigDto createDto(MonthlyNthDayOfWeekConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final MonthlyNthDayOfWeekConfigDto dto = new MonthlyNthDayOfWeekConfigDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set atTime */
			Object tmpDtoTimeDtogetAtTime = dtoServiceProvider.get().createDto(poso.getAtTime(), here, referenced);
			dto.setAtTime((TimeDto)tmpDtoTimeDtogetAtTime);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTimeDtogetAtTime, poso.getAtTime(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setAtTime((TimeDto)refDto);
				}
			});

			/*  set dailyRepeatType */
			Object tmpDtoDailyRepeatTypeDtogetDailyRepeatType = dtoServiceProvider.get().createDto(poso.getDailyRepeatType(), referenced, referenced);
			dto.setDailyRepeatType((DailyRepeatTypeDto)tmpDtoDailyRepeatTypeDtogetDailyRepeatType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDailyRepeatTypeDtogetDailyRepeatType, poso.getDailyRepeatType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDailyRepeatType((DailyRepeatTypeDto)refDto);
				}
			});

			/*  set endType */
			Object tmpDtoEndTypesDtogetEndType = dtoServiceProvider.get().createDto(poso.getEndType(), here, referenced);
			dto.setEndType((EndTypesDto)tmpDtoEndTypesDtogetEndType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoEndTypesDtogetEndType, poso.getEndType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setEndType((EndTypesDto)refDto);
				}
			});

			/*  set firstExecution */
			dto.setFirstExecution(poso.getFirstExecution() );

			/*  set lastExecution */
			dto.setLastExecution(poso.getLastExecution() );

			/*  set month */
			dto.setMonth(poso.getMonth() );

			/*  set monthlyDay */
			Object tmpDtoDaysDtogetMonthlyDay = dtoServiceProvider.get().createDto(poso.getMonthlyDay(), referenced, referenced);
			dto.setMonthlyDay((DaysDto)tmpDtoDaysDtogetMonthlyDay);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDaysDtogetMonthlyDay, poso.getMonthlyDay(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setMonthlyDay((DaysDto)refDto);
				}
			});

			/*  set monthlyNth */
			Object tmpDtoNthDtogetMonthlyNth = dtoServiceProvider.get().createDto(poso.getMonthlyNth(), referenced, referenced);
			dto.setMonthlyNth((NthDto)tmpDtoNthDtogetMonthlyNth);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoNthDtogetMonthlyNth, poso.getMonthlyNth(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setMonthlyNth((NthDto)refDto);
				}
			});

			/*  set numberOfExecutions */
			dto.setNumberOfExecutions(poso.getNumberOfExecutions() );

			/*  set timeRangeEnd */
			Object tmpDtoTimeDtogetTimeRangeEnd = dtoServiceProvider.get().createDto(poso.getTimeRangeEnd(), here, referenced);
			dto.setTimeRangeEnd((TimeDto)tmpDtoTimeDtogetTimeRangeEnd);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTimeDtogetTimeRangeEnd, poso.getTimeRangeEnd(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setTimeRangeEnd((TimeDto)refDto);
				}
			});

			/*  set timeRangeInterval */
			dto.setTimeRangeInterval(poso.getTimeRangeInterval() );

			/*  set timeRangeStart */
			Object tmpDtoTimeDtogetTimeRangeStart = dtoServiceProvider.get().createDto(poso.getTimeRangeStart(), here, referenced);
			dto.setTimeRangeStart((TimeDto)tmpDtoTimeDtogetTimeRangeStart);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTimeDtogetTimeRangeStart, poso.getTimeRangeStart(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setTimeRangeStart((TimeDto)refDto);
				}
			});

			/*  set timeRangeUnit */
			Object tmpDtoTimeUnitsDtogetTimeRangeUnit = dtoServiceProvider.get().createDto(poso.getTimeRangeUnit(), referenced, referenced);
			dto.setTimeRangeUnit((TimeUnitsDto)tmpDtoTimeUnitsDtogetTimeRangeUnit);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTimeUnitsDtogetTimeRangeUnit, poso.getTimeRangeUnit(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setTimeRangeUnit((TimeUnitsDto)refDto);
				}
			});

		}

		return dto;
	}


}
