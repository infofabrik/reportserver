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
import net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledHTMLBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.dtogen.Dto2CompiledHTMLBirtReportGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CompiledHTMLBirtReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CompiledHTMLBirtReportGenerator implements Dto2PosoGenerator<CompiledHTMLBirtReportDto,CompiledHTMLBirtReport> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CompiledHTMLBirtReportGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CompiledHTMLBirtReport loadPoso(CompiledHTMLBirtReportDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CompiledHTMLBirtReport instantiatePoso()  {
		CompiledHTMLBirtReport poso = new CompiledHTMLBirtReport();
		return poso;
	}

	public CompiledHTMLBirtReport createPoso(CompiledHTMLBirtReportDto dto)  throws ExpectedException {
		CompiledHTMLBirtReport poso = new CompiledHTMLBirtReport();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CompiledHTMLBirtReport createUnmanagedPoso(CompiledHTMLBirtReportDto dto)  throws ExpectedException {
		CompiledHTMLBirtReport poso = new CompiledHTMLBirtReport();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CompiledHTMLBirtReportDto dto, final CompiledHTMLBirtReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CompiledHTMLBirtReportDto dto, final CompiledHTMLBirtReport poso)  throws ExpectedException {
		/*  set report */
		poso.setReport(dto.getReport() );

	}

	protected void mergeProxy2Poso(CompiledHTMLBirtReportDto dto, final CompiledHTMLBirtReport poso)  throws ExpectedException {
		/*  set report */
		if(dto.isReportModified()){
			poso.setReport(dto.getReport() );
		}

	}

	public void mergeUnmanagedPoso(CompiledHTMLBirtReportDto dto, final CompiledHTMLBirtReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CompiledHTMLBirtReportDto dto, final CompiledHTMLBirtReport poso)  throws ExpectedException {
		/*  set report */
		poso.setReport(dto.getReport() );

	}

	protected void mergeProxy2UnmanagedPoso(CompiledHTMLBirtReportDto dto, final CompiledHTMLBirtReport poso)  throws ExpectedException {
		/*  set report */
		if(dto.isReportModified()){
			poso.setReport(dto.getReport() );
		}

	}

	public CompiledHTMLBirtReport loadAndMergePoso(CompiledHTMLBirtReportDto dto)  throws ExpectedException {
		CompiledHTMLBirtReport poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CompiledHTMLBirtReportDto dto, CompiledHTMLBirtReport poso)  {
	}


	public void postProcessCreateUnmanaged(CompiledHTMLBirtReportDto dto, CompiledHTMLBirtReport poso)  {
	}


	public void postProcessLoad(CompiledHTMLBirtReportDto dto, CompiledHTMLBirtReport poso)  {
	}


	public void postProcessMerge(CompiledHTMLBirtReportDto dto, CompiledHTMLBirtReport poso)  {
	}


	public void postProcessInstantiate(CompiledHTMLBirtReport poso)  {
	}



}
