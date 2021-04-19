package net.datenwerke.rs.birt.service.reportengine.output.object.dtogen;

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
import net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledPNGBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.dtogen.Dto2CompiledPNGBirtReportGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CompiledPNGBirtReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CompiledPNGBirtReportGenerator implements Dto2PosoGenerator<CompiledPNGBirtReportDto,CompiledPNGBirtReport> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CompiledPNGBirtReportGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CompiledPNGBirtReport loadPoso(CompiledPNGBirtReportDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CompiledPNGBirtReport instantiatePoso()  {
		CompiledPNGBirtReport poso = new CompiledPNGBirtReport();
		return poso;
	}

	public CompiledPNGBirtReport createPoso(CompiledPNGBirtReportDto dto)  throws ExpectedException {
		CompiledPNGBirtReport poso = new CompiledPNGBirtReport();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CompiledPNGBirtReport createUnmanagedPoso(CompiledPNGBirtReportDto dto)  throws ExpectedException {
		CompiledPNGBirtReport poso = new CompiledPNGBirtReport();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CompiledPNGBirtReportDto dto, final CompiledPNGBirtReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CompiledPNGBirtReportDto dto, final CompiledPNGBirtReport poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(CompiledPNGBirtReportDto dto, final CompiledPNGBirtReport poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(CompiledPNGBirtReportDto dto, final CompiledPNGBirtReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CompiledPNGBirtReportDto dto, final CompiledPNGBirtReport poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(CompiledPNGBirtReportDto dto, final CompiledPNGBirtReport poso)  throws ExpectedException {
	}

	public CompiledPNGBirtReport loadAndMergePoso(CompiledPNGBirtReportDto dto)  throws ExpectedException {
		CompiledPNGBirtReport poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CompiledPNGBirtReportDto dto, CompiledPNGBirtReport poso)  {
	}


	public void postProcessCreateUnmanaged(CompiledPNGBirtReportDto dto, CompiledPNGBirtReport poso)  {
	}


	public void postProcessLoad(CompiledPNGBirtReportDto dto, CompiledPNGBirtReport poso)  {
	}


	public void postProcessMerge(CompiledPNGBirtReportDto dto, CompiledPNGBirtReport poso)  {
	}


	public void postProcessInstantiate(CompiledPNGBirtReport poso)  {
	}



}
