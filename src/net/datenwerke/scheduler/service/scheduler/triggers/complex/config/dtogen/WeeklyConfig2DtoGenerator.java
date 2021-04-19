package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.WeeklyConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.WeeklyConfig2DtoGenerator;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;

/**
 * Poso2DtoGenerator for WeeklyConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class WeeklyConfig2DtoGenerator implements Poso2DtoGenerator<WeeklyConfig,WeeklyConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public WeeklyConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public WeeklyConfigDto instantiateDto(WeeklyConfig poso)  {
		WeeklyConfigDto dto = new WeeklyConfigDto();
		return dto;
	}

	public WeeklyConfigDto createDto(WeeklyConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final WeeklyConfigDto dto = new WeeklyConfigDto();
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

			/*  set weeklyDays */
			final Set<DaysDto> col_weeklyDays = new HashSet<DaysDto>();
			if( null != poso.getWeeklyDays()){
				for(Days refPoso : poso.getWeeklyDays()){
					final Object tmpDtoDaysDtogetWeeklyDays = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
					col_weeklyDays.add((DaysDto) tmpDtoDaysDtogetWeeklyDays);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDaysDtogetWeeklyDays, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (weeklyDays)");
							col_weeklyDays.remove(tmpDtoDaysDtogetWeeklyDays);
							col_weeklyDays.add((DaysDto) dto);
						}
					});
				}
				dto.setWeeklyDays(col_weeklyDays);
			}

			/*  set weeklyN */
			dto.setWeeklyN(poso.getWeeklyN() );

		}

		return dto;
	}


}
