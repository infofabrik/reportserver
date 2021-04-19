package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Dto2TimeGenerator;

/**
 * Dto2PosoGenerator for Time
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TimeGenerator implements Dto2PosoGenerator<TimeDto,Time> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TimeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public Time loadPoso(TimeDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public Time instantiatePoso()  {
		Time poso = new Time();
		return poso;
	}

	public Time createPoso(TimeDto dto)  throws ExpectedException {
		Time poso = new Time();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Time createUnmanagedPoso(TimeDto dto)  throws ExpectedException {
		Time poso = new Time();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(TimeDto dto, final Time poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TimeDto dto, final Time poso)  throws ExpectedException {
		/*  set hour */
		poso.setHour(dto.getHour() );

		/*  set minutes */
		poso.setMinutes(dto.getMinutes() );

	}

	protected void mergeProxy2Poso(TimeDto dto, final Time poso)  throws ExpectedException {
		/*  set hour */
		if(dto.isHourModified()){
			poso.setHour(dto.getHour() );
		}

		/*  set minutes */
		if(dto.isMinutesModified()){
			poso.setMinutes(dto.getMinutes() );
		}

	}

	public void mergeUnmanagedPoso(TimeDto dto, final Time poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TimeDto dto, final Time poso)  throws ExpectedException {
		/*  set hour */
		poso.setHour(dto.getHour() );

		/*  set minutes */
		poso.setMinutes(dto.getMinutes() );

	}

	protected void mergeProxy2UnmanagedPoso(TimeDto dto, final Time poso)  throws ExpectedException {
		/*  set hour */
		if(dto.isHourModified()){
			poso.setHour(dto.getHour() );
		}

		/*  set minutes */
		if(dto.isMinutesModified()){
			poso.setMinutes(dto.getMinutes() );
		}

	}

	public Time loadAndMergePoso(TimeDto dto)  throws ExpectedException {
		Time poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TimeDto dto, Time poso)  {
	}


	public void postProcessCreateUnmanaged(TimeDto dto, Time poso)  {
	}


	public void postProcessLoad(TimeDto dto, Time poso)  {
	}


	public void postProcessMerge(TimeDto dto, Time poso)  {
	}


	public void postProcessInstantiate(Time poso)  {
	}



}
