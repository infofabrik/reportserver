package net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledHTMLJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.Dto2CompiledHTMLJasperReportGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CompiledHTMLJasperReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CompiledHTMLJasperReportGenerator implements Dto2PosoGenerator<CompiledHTMLJasperReportDto,CompiledHTMLJasperReport> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CompiledHTMLJasperReportGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CompiledHTMLJasperReport loadPoso(CompiledHTMLJasperReportDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CompiledHTMLJasperReport instantiatePoso()  {
		CompiledHTMLJasperReport poso = new CompiledHTMLJasperReport();
		return poso;
	}

	public CompiledHTMLJasperReport createPoso(CompiledHTMLJasperReportDto dto)  throws ExpectedException {
		CompiledHTMLJasperReport poso = new CompiledHTMLJasperReport();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CompiledHTMLJasperReport createUnmanagedPoso(CompiledHTMLJasperReportDto dto)  throws ExpectedException {
		CompiledHTMLJasperReport poso = new CompiledHTMLJasperReport();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CompiledHTMLJasperReportDto dto, final CompiledHTMLJasperReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CompiledHTMLJasperReportDto dto, final CompiledHTMLJasperReport poso)  throws ExpectedException {
		/*  set report */
		poso.setReport(dto.getReport() );

	}

	protected void mergeProxy2Poso(CompiledHTMLJasperReportDto dto, final CompiledHTMLJasperReport poso)  throws ExpectedException {
		/*  set report */
		if(dto.isReportModified()){
			poso.setReport(dto.getReport() );
		}

	}

	public void mergeUnmanagedPoso(CompiledHTMLJasperReportDto dto, final CompiledHTMLJasperReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CompiledHTMLJasperReportDto dto, final CompiledHTMLJasperReport poso)  throws ExpectedException {
		/*  set report */
		poso.setReport(dto.getReport() );

	}

	protected void mergeProxy2UnmanagedPoso(CompiledHTMLJasperReportDto dto, final CompiledHTMLJasperReport poso)  throws ExpectedException {
		/*  set report */
		if(dto.isReportModified()){
			poso.setReport(dto.getReport() );
		}

	}

	public CompiledHTMLJasperReport loadAndMergePoso(CompiledHTMLJasperReportDto dto)  throws ExpectedException {
		CompiledHTMLJasperReport poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CompiledHTMLJasperReportDto dto, CompiledHTMLJasperReport poso)  {
	}


	public void postProcessCreateUnmanaged(CompiledHTMLJasperReportDto dto, CompiledHTMLJasperReport poso)  {
	}


	public void postProcessLoad(CompiledHTMLJasperReportDto dto, CompiledHTMLJasperReport poso)  {
	}


	public void postProcessMerge(CompiledHTMLJasperReportDto dto, CompiledHTMLJasperReport poso)  {
	}


	public void postProcessInstantiate(CompiledHTMLJasperReport poso)  {
	}



}
