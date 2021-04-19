package net.datenwerke.security.service.security.rights.dtogen;

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
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.dtogen.Dto2ExecuteGenerator;

/**
 * Dto2PosoGenerator for Execute
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ExecuteGenerator implements Dto2PosoGenerator<ExecuteDto,Execute> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ExecuteGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public Execute loadPoso(ExecuteDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public Execute instantiatePoso()  {
		Execute poso = new Execute();
		return poso;
	}

	public Execute createPoso(ExecuteDto dto)  throws ExpectedException {
		Execute poso = new Execute();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Execute createUnmanagedPoso(ExecuteDto dto)  throws ExpectedException {
		Execute poso = new Execute();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(ExecuteDto dto, final Execute poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ExecuteDto dto, final Execute poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(ExecuteDto dto, final Execute poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(ExecuteDto dto, final Execute poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ExecuteDto dto, final Execute poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(ExecuteDto dto, final Execute poso)  throws ExpectedException {
	}

	public Execute loadAndMergePoso(ExecuteDto dto)  throws ExpectedException {
		Execute poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ExecuteDto dto, Execute poso)  {
	}


	public void postProcessCreateUnmanaged(ExecuteDto dto, Execute poso)  {
	}


	public void postProcessLoad(ExecuteDto dto, Execute poso)  {
	}


	public void postProcessMerge(ExecuteDto dto, Execute poso)  {
	}


	public void postProcessInstantiate(Execute poso)  {
	}



}
