package net.datenwerke.rs.saiku.service.saiku.reportengine.config.dtogen;

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
import net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto;
import net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart;
import net.datenwerke.rs.saiku.service.saiku.reportengine.config.dtogen.Dto2RECSaikuChartGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for RECSaikuChart
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2RECSaikuChartGenerator implements Dto2PosoGenerator<RECSaikuChartDto,RECSaikuChart> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2RECSaikuChartGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public RECSaikuChart loadPoso(RECSaikuChartDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public RECSaikuChart instantiatePoso()  {
		RECSaikuChart poso = new RECSaikuChart();
		return poso;
	}

	public RECSaikuChart createPoso(RECSaikuChartDto dto)  throws ExpectedException {
		RECSaikuChart poso = new RECSaikuChart();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public RECSaikuChart createUnmanagedPoso(RECSaikuChartDto dto)  throws ExpectedException {
		RECSaikuChart poso = new RECSaikuChart();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(RECSaikuChartDto dto, final RECSaikuChart poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(RECSaikuChartDto dto, final RECSaikuChart poso)  throws ExpectedException {
		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2Poso(RECSaikuChartDto dto, final RECSaikuChart poso)  throws ExpectedException {
		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public void mergeUnmanagedPoso(RECSaikuChartDto dto, final RECSaikuChart poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(RECSaikuChartDto dto, final RECSaikuChart poso)  throws ExpectedException {
		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2UnmanagedPoso(RECSaikuChartDto dto, final RECSaikuChart poso)  throws ExpectedException {
		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public RECSaikuChart loadAndMergePoso(RECSaikuChartDto dto)  throws ExpectedException {
		RECSaikuChart poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(RECSaikuChartDto dto, RECSaikuChart poso)  {
	}


	public void postProcessCreateUnmanaged(RECSaikuChartDto dto, RECSaikuChart poso)  {
	}


	public void postProcessLoad(RECSaikuChartDto dto, RECSaikuChart poso)  {
	}


	public void postProcessMerge(RECSaikuChartDto dto, RECSaikuChart poso)  {
	}


	public void postProcessInstantiate(RECSaikuChart poso)  {
	}



}
