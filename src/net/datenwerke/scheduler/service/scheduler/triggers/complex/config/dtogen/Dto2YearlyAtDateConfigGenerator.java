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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyAtDateConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2YearlyAtDateConfigGenerator;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits;

/**
 * Dto2PosoGenerator for YearlyAtDateConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2YearlyAtDateConfigGenerator implements Dto2PosoGenerator<YearlyAtDateConfigDto,YearlyAtDateConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2YearlyAtDateConfigGenerator(
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

	public YearlyAtDateConfig loadPoso(YearlyAtDateConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		YearlyAtDateConfig poso = entityManager.find(YearlyAtDateConfig.class, id);
		return poso;
	}

	public YearlyAtDateConfig instantiatePoso()  {
		YearlyAtDateConfig poso = new YearlyAtDateConfig();
		return poso;
	}

	public YearlyAtDateConfig createPoso(YearlyAtDateConfigDto dto)  throws ExpectedException {
		YearlyAtDateConfig poso = new YearlyAtDateConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public YearlyAtDateConfig createUnmanagedPoso(YearlyAtDateConfigDto dto)  throws ExpectedException {
		YearlyAtDateConfig poso = new YearlyAtDateConfig();

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

	public void mergePoso(YearlyAtDateConfigDto dto, final YearlyAtDateConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(YearlyAtDateConfigDto dto, final YearlyAtDateConfig poso)  throws ExpectedException {
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

		/*  set yearlyMonth */
		MonthsDto tmpDto_yearlyMonth = dto.getYearlyMonth();
		poso.setYearlyMonth((Months)dtoServiceProvider.get().createPoso(tmpDto_yearlyMonth));

		/*  set yearlyNDay */
		poso.setYearlyNDay(dto.getYearlyNDay() );

	}

	protected void mergeProxy2Poso(YearlyAtDateConfigDto dto, final YearlyAtDateConfig poso)  throws ExpectedException {
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

		/*  set yearlyMonth */
		if(dto.isYearlyMonthModified()){
			MonthsDto tmpDto_yearlyMonth = dto.getYearlyMonth();
			poso.setYearlyMonth((Months)dtoServiceProvider.get().createPoso(tmpDto_yearlyMonth));
		}

		/*  set yearlyNDay */
		if(dto.isYearlyNDayModified()){
			poso.setYearlyNDay(dto.getYearlyNDay() );
		}

	}

	public void mergeUnmanagedPoso(YearlyAtDateConfigDto dto, final YearlyAtDateConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(YearlyAtDateConfigDto dto, final YearlyAtDateConfig poso)  throws ExpectedException {
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

		/*  set yearlyMonth */
		MonthsDto tmpDto_yearlyMonth = dto.getYearlyMonth();
		poso.setYearlyMonth((Months)dtoServiceProvider.get().createPoso(tmpDto_yearlyMonth));

		/*  set yearlyNDay */
		poso.setYearlyNDay(dto.getYearlyNDay() );

	}

	protected void mergeProxy2UnmanagedPoso(YearlyAtDateConfigDto dto, final YearlyAtDateConfig poso)  throws ExpectedException {
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

		/*  set yearlyMonth */
		if(dto.isYearlyMonthModified()){
			MonthsDto tmpDto_yearlyMonth = dto.getYearlyMonth();
			poso.setYearlyMonth((Months)dtoServiceProvider.get().createPoso(tmpDto_yearlyMonth));
		}

		/*  set yearlyNDay */
		if(dto.isYearlyNDayModified()){
			poso.setYearlyNDay(dto.getYearlyNDay() );
		}

	}

	public YearlyAtDateConfig loadAndMergePoso(YearlyAtDateConfigDto dto)  throws ExpectedException {
		YearlyAtDateConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(YearlyAtDateConfigDto dto, YearlyAtDateConfig poso)  {
	}


	public void postProcessCreateUnmanaged(YearlyAtDateConfigDto dto, YearlyAtDateConfig poso)  {
	}


	public void postProcessLoad(YearlyAtDateConfigDto dto, YearlyAtDateConfig poso)  {
	}


	public void postProcessMerge(YearlyAtDateConfigDto dto, YearlyAtDateConfig poso)  {
	}


	public void postProcessInstantiate(YearlyAtDateConfig poso)  {
	}



}
