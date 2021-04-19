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
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayOfWeekConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2MonthlyNthDayOfWeekConfigGenerator;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Nth;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits;

/**
 * Dto2PosoGenerator for MonthlyNthDayOfWeekConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2MonthlyNthDayOfWeekConfigGenerator implements Dto2PosoGenerator<MonthlyNthDayOfWeekConfigDto,MonthlyNthDayOfWeekConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2MonthlyNthDayOfWeekConfigGenerator(
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

	public MonthlyNthDayOfWeekConfig loadPoso(MonthlyNthDayOfWeekConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		MonthlyNthDayOfWeekConfig poso = entityManager.find(MonthlyNthDayOfWeekConfig.class, id);
		return poso;
	}

	public MonthlyNthDayOfWeekConfig instantiatePoso()  {
		MonthlyNthDayOfWeekConfig poso = new MonthlyNthDayOfWeekConfig();
		return poso;
	}

	public MonthlyNthDayOfWeekConfig createPoso(MonthlyNthDayOfWeekConfigDto dto)  throws ExpectedException {
		MonthlyNthDayOfWeekConfig poso = new MonthlyNthDayOfWeekConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public MonthlyNthDayOfWeekConfig createUnmanagedPoso(MonthlyNthDayOfWeekConfigDto dto)  throws ExpectedException {
		MonthlyNthDayOfWeekConfig poso = new MonthlyNthDayOfWeekConfig();

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

	public void mergePoso(MonthlyNthDayOfWeekConfigDto dto, final MonthlyNthDayOfWeekConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(MonthlyNthDayOfWeekConfigDto dto, final MonthlyNthDayOfWeekConfig poso)  throws ExpectedException {
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

		/*  set month */
		poso.setMonth(dto.getMonth() );

		/*  set monthlyDay */
		DaysDto tmpDto_monthlyDay = dto.getMonthlyDay();
		poso.setMonthlyDay((Days)dtoServiceProvider.get().createPoso(tmpDto_monthlyDay));

		/*  set monthlyNth */
		NthDto tmpDto_monthlyNth = dto.getMonthlyNth();
		poso.setMonthlyNth((Nth)dtoServiceProvider.get().createPoso(tmpDto_monthlyNth));

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

	}

	protected void mergeProxy2Poso(MonthlyNthDayOfWeekConfigDto dto, final MonthlyNthDayOfWeekConfig poso)  throws ExpectedException {
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

		/*  set month */
		if(dto.isMonthModified()){
			poso.setMonth(dto.getMonth() );
		}

		/*  set monthlyDay */
		if(dto.isMonthlyDayModified()){
			DaysDto tmpDto_monthlyDay = dto.getMonthlyDay();
			poso.setMonthlyDay((Days)dtoServiceProvider.get().createPoso(tmpDto_monthlyDay));
		}

		/*  set monthlyNth */
		if(dto.isMonthlyNthModified()){
			NthDto tmpDto_monthlyNth = dto.getMonthlyNth();
			poso.setMonthlyNth((Nth)dtoServiceProvider.get().createPoso(tmpDto_monthlyNth));
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

	}

	public void mergeUnmanagedPoso(MonthlyNthDayOfWeekConfigDto dto, final MonthlyNthDayOfWeekConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(MonthlyNthDayOfWeekConfigDto dto, final MonthlyNthDayOfWeekConfig poso)  throws ExpectedException {
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

		/*  set month */
		poso.setMonth(dto.getMonth() );

		/*  set monthlyDay */
		DaysDto tmpDto_monthlyDay = dto.getMonthlyDay();
		poso.setMonthlyDay((Days)dtoServiceProvider.get().createPoso(tmpDto_monthlyDay));

		/*  set monthlyNth */
		NthDto tmpDto_monthlyNth = dto.getMonthlyNth();
		poso.setMonthlyNth((Nth)dtoServiceProvider.get().createPoso(tmpDto_monthlyNth));

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

	}

	protected void mergeProxy2UnmanagedPoso(MonthlyNthDayOfWeekConfigDto dto, final MonthlyNthDayOfWeekConfig poso)  throws ExpectedException {
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

		/*  set month */
		if(dto.isMonthModified()){
			poso.setMonth(dto.getMonth() );
		}

		/*  set monthlyDay */
		if(dto.isMonthlyDayModified()){
			DaysDto tmpDto_monthlyDay = dto.getMonthlyDay();
			poso.setMonthlyDay((Days)dtoServiceProvider.get().createPoso(tmpDto_monthlyDay));
		}

		/*  set monthlyNth */
		if(dto.isMonthlyNthModified()){
			NthDto tmpDto_monthlyNth = dto.getMonthlyNth();
			poso.setMonthlyNth((Nth)dtoServiceProvider.get().createPoso(tmpDto_monthlyNth));
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

	}

	public MonthlyNthDayOfWeekConfig loadAndMergePoso(MonthlyNthDayOfWeekConfigDto dto)  throws ExpectedException {
		MonthlyNthDayOfWeekConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(MonthlyNthDayOfWeekConfigDto dto, MonthlyNthDayOfWeekConfig poso)  {
	}


	public void postProcessCreateUnmanaged(MonthlyNthDayOfWeekConfigDto dto, MonthlyNthDayOfWeekConfig poso)  {
	}


	public void postProcessLoad(MonthlyNthDayOfWeekConfigDto dto, MonthlyNthDayOfWeekConfig poso)  {
	}


	public void postProcessMerge(MonthlyNthDayOfWeekConfigDto dto, MonthlyNthDayOfWeekConfig poso)  {
	}


	public void postProcessInstantiate(MonthlyNthDayOfWeekConfig poso)  {
	}



}
