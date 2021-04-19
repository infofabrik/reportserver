package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyNthDayOfWeekConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2YearlyNthDayOfWeekConfigGenerator;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Nth;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits;

/**
 * Dto2PosoGenerator for YearlyNthDayOfWeekConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2YearlyNthDayOfWeekConfigGenerator implements Dto2PosoGenerator<YearlyNthDayOfWeekConfigDto,YearlyNthDayOfWeekConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2YearlyNthDayOfWeekConfigGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public YearlyNthDayOfWeekConfig loadPoso(YearlyNthDayOfWeekConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		YearlyNthDayOfWeekConfig poso = entityManager.find(YearlyNthDayOfWeekConfig.class, id);
		return poso;
	}

	public YearlyNthDayOfWeekConfig instantiatePoso()  {
		YearlyNthDayOfWeekConfig poso = new YearlyNthDayOfWeekConfig();
		return poso;
	}

	public YearlyNthDayOfWeekConfig createPoso(YearlyNthDayOfWeekConfigDto dto)  throws ExpectedException {
		YearlyNthDayOfWeekConfig poso = new YearlyNthDayOfWeekConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public YearlyNthDayOfWeekConfig createUnmanagedPoso(YearlyNthDayOfWeekConfigDto dto)  throws ExpectedException {
		YearlyNthDayOfWeekConfig poso = new YearlyNthDayOfWeekConfig();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(YearlyNthDayOfWeekConfigDto dto, final YearlyNthDayOfWeekConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(YearlyNthDayOfWeekConfigDto dto, final YearlyNthDayOfWeekConfig poso)  throws ExpectedException {
		/*  set atTime */
		TimeDto tmpDto_atTime = dto.getAtTime();
		poso.setAtTime((Time)dtoServiceProvider.get().createPoso(tmpDto_atTime));

		/*  set dailyRepeatType */
		DailyRepeatTypeDto tmpDto_dailyRepeatType = dto.getDailyRepeatType();
		poso.setDailyRepeatType((DailyRepeatType)dtoServiceProvider.get().createPoso(tmpDto_dailyRepeatType));

		/*  set endType */
		EndTypesDto tmpDto_endType = dto.getEndType();
		poso.setEndType((EndTypes)dtoServiceProvider.get().createPoso(tmpDto_endType));

		/*  set firstExecution */
		poso.setFirstExecution(dto.getFirstExecution() );

		/*  set lastExecution */
		poso.setLastExecution(dto.getLastExecution() );

		/*  set numberOfExecutions */
		poso.setNumberOfExecutions(dto.getNumberOfExecutions() );

		/*  set timeRangeEnd */
		TimeDto tmpDto_timeRangeEnd = dto.getTimeRangeEnd();
		poso.setTimeRangeEnd((Time)dtoServiceProvider.get().createPoso(tmpDto_timeRangeEnd));

		/*  set timeRangeInterval */
		poso.setTimeRangeInterval(dto.getTimeRangeInterval() );

		/*  set timeRangeStart */
		TimeDto tmpDto_timeRangeStart = dto.getTimeRangeStart();
		poso.setTimeRangeStart((Time)dtoServiceProvider.get().createPoso(tmpDto_timeRangeStart));

		/*  set timeRangeUnit */
		TimeUnitsDto tmpDto_timeRangeUnit = dto.getTimeRangeUnit();
		poso.setTimeRangeUnit((TimeUnits)dtoServiceProvider.get().createPoso(tmpDto_timeRangeUnit));

		/*  set yearlyDay */
		DaysDto tmpDto_yearlyDay = dto.getYearlyDay();
		poso.setYearlyDay((Days)dtoServiceProvider.get().createPoso(tmpDto_yearlyDay));

		/*  set yearlyMonth */
		MonthsDto tmpDto_yearlyMonth = dto.getYearlyMonth();
		poso.setYearlyMonth((Months)dtoServiceProvider.get().createPoso(tmpDto_yearlyMonth));

		/*  set yearlyNth */
		NthDto tmpDto_yearlyNth = dto.getYearlyNth();
		poso.setYearlyNth((Nth)dtoServiceProvider.get().createPoso(tmpDto_yearlyNth));

	}

	protected void mergeProxy2Poso(YearlyNthDayOfWeekConfigDto dto, final YearlyNthDayOfWeekConfig poso)  throws ExpectedException {
		/*  set atTime */
		if(dto.isAtTimeModified()){
			TimeDto tmpDto_atTime = dto.getAtTime();
			poso.setAtTime((Time)dtoServiceProvider.get().createPoso(tmpDto_atTime));
		}

		/*  set dailyRepeatType */
		if(dto.isDailyRepeatTypeModified()){
			DailyRepeatTypeDto tmpDto_dailyRepeatType = dto.getDailyRepeatType();
			poso.setDailyRepeatType((DailyRepeatType)dtoServiceProvider.get().createPoso(tmpDto_dailyRepeatType));
		}

		/*  set endType */
		if(dto.isEndTypeModified()){
			EndTypesDto tmpDto_endType = dto.getEndType();
			poso.setEndType((EndTypes)dtoServiceProvider.get().createPoso(tmpDto_endType));
		}

		/*  set firstExecution */
		if(dto.isFirstExecutionModified()){
			poso.setFirstExecution(dto.getFirstExecution() );
		}

		/*  set lastExecution */
		if(dto.isLastExecutionModified()){
			poso.setLastExecution(dto.getLastExecution() );
		}

		/*  set numberOfExecutions */
		if(dto.isNumberOfExecutionsModified()){
			poso.setNumberOfExecutions(dto.getNumberOfExecutions() );
		}

		/*  set timeRangeEnd */
		if(dto.isTimeRangeEndModified()){
			TimeDto tmpDto_timeRangeEnd = dto.getTimeRangeEnd();
			poso.setTimeRangeEnd((Time)dtoServiceProvider.get().createPoso(tmpDto_timeRangeEnd));
		}

		/*  set timeRangeInterval */
		if(dto.isTimeRangeIntervalModified()){
			poso.setTimeRangeInterval(dto.getTimeRangeInterval() );
		}

		/*  set timeRangeStart */
		if(dto.isTimeRangeStartModified()){
			TimeDto tmpDto_timeRangeStart = dto.getTimeRangeStart();
			poso.setTimeRangeStart((Time)dtoServiceProvider.get().createPoso(tmpDto_timeRangeStart));
		}

		/*  set timeRangeUnit */
		if(dto.isTimeRangeUnitModified()){
			TimeUnitsDto tmpDto_timeRangeUnit = dto.getTimeRangeUnit();
			poso.setTimeRangeUnit((TimeUnits)dtoServiceProvider.get().createPoso(tmpDto_timeRangeUnit));
		}

		/*  set yearlyDay */
		if(dto.isYearlyDayModified()){
			DaysDto tmpDto_yearlyDay = dto.getYearlyDay();
			poso.setYearlyDay((Days)dtoServiceProvider.get().createPoso(tmpDto_yearlyDay));
		}

		/*  set yearlyMonth */
		if(dto.isYearlyMonthModified()){
			MonthsDto tmpDto_yearlyMonth = dto.getYearlyMonth();
			poso.setYearlyMonth((Months)dtoServiceProvider.get().createPoso(tmpDto_yearlyMonth));
		}

		/*  set yearlyNth */
		if(dto.isYearlyNthModified()){
			NthDto tmpDto_yearlyNth = dto.getYearlyNth();
			poso.setYearlyNth((Nth)dtoServiceProvider.get().createPoso(tmpDto_yearlyNth));
		}

	}

	public void mergeUnmanagedPoso(YearlyNthDayOfWeekConfigDto dto, final YearlyNthDayOfWeekConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(YearlyNthDayOfWeekConfigDto dto, final YearlyNthDayOfWeekConfig poso)  throws ExpectedException {
		/*  set atTime */
		TimeDto tmpDto_atTime = dto.getAtTime();
		poso.setAtTime((Time)dtoServiceProvider.get().createPoso(tmpDto_atTime));

		/*  set dailyRepeatType */
		DailyRepeatTypeDto tmpDto_dailyRepeatType = dto.getDailyRepeatType();
		poso.setDailyRepeatType((DailyRepeatType)dtoServiceProvider.get().createPoso(tmpDto_dailyRepeatType));

		/*  set endType */
		EndTypesDto tmpDto_endType = dto.getEndType();
		poso.setEndType((EndTypes)dtoServiceProvider.get().createPoso(tmpDto_endType));

		/*  set firstExecution */
		poso.setFirstExecution(dto.getFirstExecution() );

		/*  set lastExecution */
		poso.setLastExecution(dto.getLastExecution() );

		/*  set numberOfExecutions */
		poso.setNumberOfExecutions(dto.getNumberOfExecutions() );

		/*  set timeRangeEnd */
		TimeDto tmpDto_timeRangeEnd = dto.getTimeRangeEnd();
		poso.setTimeRangeEnd((Time)dtoServiceProvider.get().createPoso(tmpDto_timeRangeEnd));

		/*  set timeRangeInterval */
		poso.setTimeRangeInterval(dto.getTimeRangeInterval() );

		/*  set timeRangeStart */
		TimeDto tmpDto_timeRangeStart = dto.getTimeRangeStart();
		poso.setTimeRangeStart((Time)dtoServiceProvider.get().createPoso(tmpDto_timeRangeStart));

		/*  set timeRangeUnit */
		TimeUnitsDto tmpDto_timeRangeUnit = dto.getTimeRangeUnit();
		poso.setTimeRangeUnit((TimeUnits)dtoServiceProvider.get().createPoso(tmpDto_timeRangeUnit));

		/*  set yearlyDay */
		DaysDto tmpDto_yearlyDay = dto.getYearlyDay();
		poso.setYearlyDay((Days)dtoServiceProvider.get().createPoso(tmpDto_yearlyDay));

		/*  set yearlyMonth */
		MonthsDto tmpDto_yearlyMonth = dto.getYearlyMonth();
		poso.setYearlyMonth((Months)dtoServiceProvider.get().createPoso(tmpDto_yearlyMonth));

		/*  set yearlyNth */
		NthDto tmpDto_yearlyNth = dto.getYearlyNth();
		poso.setYearlyNth((Nth)dtoServiceProvider.get().createPoso(tmpDto_yearlyNth));

	}

	protected void mergeProxy2UnmanagedPoso(YearlyNthDayOfWeekConfigDto dto, final YearlyNthDayOfWeekConfig poso)  throws ExpectedException {
		/*  set atTime */
		if(dto.isAtTimeModified()){
			TimeDto tmpDto_atTime = dto.getAtTime();
			poso.setAtTime((Time)dtoServiceProvider.get().createPoso(tmpDto_atTime));
		}

		/*  set dailyRepeatType */
		if(dto.isDailyRepeatTypeModified()){
			DailyRepeatTypeDto tmpDto_dailyRepeatType = dto.getDailyRepeatType();
			poso.setDailyRepeatType((DailyRepeatType)dtoServiceProvider.get().createPoso(tmpDto_dailyRepeatType));
		}

		/*  set endType */
		if(dto.isEndTypeModified()){
			EndTypesDto tmpDto_endType = dto.getEndType();
			poso.setEndType((EndTypes)dtoServiceProvider.get().createPoso(tmpDto_endType));
		}

		/*  set firstExecution */
		if(dto.isFirstExecutionModified()){
			poso.setFirstExecution(dto.getFirstExecution() );
		}

		/*  set lastExecution */
		if(dto.isLastExecutionModified()){
			poso.setLastExecution(dto.getLastExecution() );
		}

		/*  set numberOfExecutions */
		if(dto.isNumberOfExecutionsModified()){
			poso.setNumberOfExecutions(dto.getNumberOfExecutions() );
		}

		/*  set timeRangeEnd */
		if(dto.isTimeRangeEndModified()){
			TimeDto tmpDto_timeRangeEnd = dto.getTimeRangeEnd();
			poso.setTimeRangeEnd((Time)dtoServiceProvider.get().createPoso(tmpDto_timeRangeEnd));
		}

		/*  set timeRangeInterval */
		if(dto.isTimeRangeIntervalModified()){
			poso.setTimeRangeInterval(dto.getTimeRangeInterval() );
		}

		/*  set timeRangeStart */
		if(dto.isTimeRangeStartModified()){
			TimeDto tmpDto_timeRangeStart = dto.getTimeRangeStart();
			poso.setTimeRangeStart((Time)dtoServiceProvider.get().createPoso(tmpDto_timeRangeStart));
		}

		/*  set timeRangeUnit */
		if(dto.isTimeRangeUnitModified()){
			TimeUnitsDto tmpDto_timeRangeUnit = dto.getTimeRangeUnit();
			poso.setTimeRangeUnit((TimeUnits)dtoServiceProvider.get().createPoso(tmpDto_timeRangeUnit));
		}

		/*  set yearlyDay */
		if(dto.isYearlyDayModified()){
			DaysDto tmpDto_yearlyDay = dto.getYearlyDay();
			poso.setYearlyDay((Days)dtoServiceProvider.get().createPoso(tmpDto_yearlyDay));
		}

		/*  set yearlyMonth */
		if(dto.isYearlyMonthModified()){
			MonthsDto tmpDto_yearlyMonth = dto.getYearlyMonth();
			poso.setYearlyMonth((Months)dtoServiceProvider.get().createPoso(tmpDto_yearlyMonth));
		}

		/*  set yearlyNth */
		if(dto.isYearlyNthModified()){
			NthDto tmpDto_yearlyNth = dto.getYearlyNth();
			poso.setYearlyNth((Nth)dtoServiceProvider.get().createPoso(tmpDto_yearlyNth));
		}

	}

	public YearlyNthDayOfWeekConfig loadAndMergePoso(YearlyNthDayOfWeekConfigDto dto)  throws ExpectedException {
		YearlyNthDayOfWeekConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(YearlyNthDayOfWeekConfigDto dto, YearlyNthDayOfWeekConfig poso)  {
	}


	public void postProcessCreateUnmanaged(YearlyNthDayOfWeekConfigDto dto, YearlyNthDayOfWeekConfig poso)  {
	}


	public void postProcessLoad(YearlyNthDayOfWeekConfigDto dto, YearlyNthDayOfWeekConfig poso)  {
	}


	public void postProcessMerge(YearlyNthDayOfWeekConfigDto dto, YearlyNthDayOfWeekConfig poso)  {
	}


	public void postProcessInstantiate(YearlyNthDayOfWeekConfig poso)  {
	}



}
