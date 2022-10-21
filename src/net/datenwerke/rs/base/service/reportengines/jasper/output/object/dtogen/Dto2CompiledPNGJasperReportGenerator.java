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
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledPNGJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.Dto2CompiledPNGJasperReportGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CompiledPNGJasperReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CompiledPNGJasperReportGenerator implements Dto2PosoGenerator<CompiledPNGJasperReportDto,CompiledPNGJasperReport> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CompiledPNGJasperReportGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CompiledPNGJasperReport loadPoso(CompiledPNGJasperReportDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CompiledPNGJasperReport instantiatePoso()  {
		CompiledPNGJasperReport poso = new CompiledPNGJasperReport();
		return poso;
	}

	public CompiledPNGJasperReport createPoso(CompiledPNGJasperReportDto dto)  throws ExpectedException {
		CompiledPNGJasperReport poso = new CompiledPNGJasperReport();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CompiledPNGJasperReport createUnmanagedPoso(CompiledPNGJasperReportDto dto)  throws ExpectedException {
		CompiledPNGJasperReport poso = new CompiledPNGJasperReport();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CompiledPNGJasperReportDto dto, final CompiledPNGJasperReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CompiledPNGJasperReportDto dto, final CompiledPNGJasperReport poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(CompiledPNGJasperReportDto dto, final CompiledPNGJasperReport poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(CompiledPNGJasperReportDto dto, final CompiledPNGJasperReport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CompiledPNGJasperReportDto dto, final CompiledPNGJasperReport poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(CompiledPNGJasperReportDto dto, final CompiledPNGJasperReport poso)  throws ExpectedException {
	}

	public CompiledPNGJasperReport loadAndMergePoso(CompiledPNGJasperReportDto dto)  throws ExpectedException {
		CompiledPNGJasperReport poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CompiledPNGJasperReportDto dto, CompiledPNGJasperReport poso)  {
	}


	public void postProcessCreateUnmanaged(CompiledPNGJasperReportDto dto, CompiledPNGJasperReport poso)  {
	}


	public void postProcessLoad(CompiledPNGJasperReportDto dto, CompiledPNGJasperReport poso)  {
	}


	public void postProcessMerge(CompiledPNGJasperReportDto dto, CompiledPNGJasperReport poso)  {
	}


	public void postProcessInstantiate(CompiledPNGJasperReport poso)  {
	}



}
