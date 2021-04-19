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
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledCSVJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.Dto2CompiledCSVJasperReportGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CompiledCSVJasperReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CompiledCSVJasperReportGenerator implements Dto2PosoGenerator<CompiledCSVJasperReportDto,CompiledCSVJasperReport> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CompiledCSVJasperReportGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CompiledCSVJasperReport loadPoso(CompiledCSVJasperReportDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CompiledCSVJasperReport instantiatePoso()  {
		CompiledCSVJasperReport poso = new CompiledCSVJasperReport();
		return poso;
	}

	public CompiledCSVJasperReport createPoso(CompiledCSVJasperReportDto dto)  throws ExpectedException {
		CompiledCSVJasperReport poso = new CompiledCSVJasperReport();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CompiledCSVJasperReport createUnmanagedPoso(CompiledCSVJasperReportDto dto)  throws ExpectedException {
		CompiledCSVJasperReport poso = new CompiledCSVJasperReport();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CompiledCSVJasperReportDto dto, final CompiledCSVJasperReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CompiledCSVJasperReportDto dto, final CompiledCSVJasperReport poso)  throws ExpectedException {
		/*  set report */
		poso.setReport(dto.getReport() );

	}

	protected void mergeProxy2Poso(CompiledCSVJasperReportDto dto, final CompiledCSVJasperReport poso)  throws ExpectedException {
		/*  set report */
		if(dto.isReportModified()){
			poso.setReport(dto.getReport() );
		}

	}

	public void mergeUnmanagedPoso(CompiledCSVJasperReportDto dto, final CompiledCSVJasperReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CompiledCSVJasperReportDto dto, final CompiledCSVJasperReport poso)  throws ExpectedException {
		/*  set report */
		poso.setReport(dto.getReport() );

	}

	protected void mergeProxy2UnmanagedPoso(CompiledCSVJasperReportDto dto, final CompiledCSVJasperReport poso)  throws ExpectedException {
		/*  set report */
		if(dto.isReportModified()){
			poso.setReport(dto.getReport() );
		}

	}

	public CompiledCSVJasperReport loadAndMergePoso(CompiledCSVJasperReportDto dto)  throws ExpectedException {
		CompiledCSVJasperReport poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CompiledCSVJasperReportDto dto, CompiledCSVJasperReport poso)  {
	}


	public void postProcessCreateUnmanaged(CompiledCSVJasperReportDto dto, CompiledCSVJasperReport poso)  {
	}


	public void postProcessLoad(CompiledCSVJasperReportDto dto, CompiledCSVJasperReport poso)  {
	}


	public void postProcessMerge(CompiledCSVJasperReportDto dto, CompiledCSVJasperReport poso)  {
	}


	public void postProcessInstantiate(CompiledCSVJasperReport poso)  {
	}



}
