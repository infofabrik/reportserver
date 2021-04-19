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
import net.datenwerke.security.client.security.dto.DeleteDto;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.dtogen.Dto2DeleteGenerator;

/**
 * Dto2PosoGenerator for Delete
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DeleteGenerator implements Dto2PosoGenerator<DeleteDto,Delete> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DeleteGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public Delete loadPoso(DeleteDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public Delete instantiatePoso()  {
		Delete poso = new Delete();
		return poso;
	}

	public Delete createPoso(DeleteDto dto)  throws ExpectedException {
		Delete poso = new Delete();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Delete createUnmanagedPoso(DeleteDto dto)  throws ExpectedException {
		Delete poso = new Delete();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(DeleteDto dto, final Delete poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DeleteDto dto, final Delete poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(DeleteDto dto, final Delete poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(DeleteDto dto, final Delete poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DeleteDto dto, final Delete poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(DeleteDto dto, final Delete poso)  throws ExpectedException {
	}

	public Delete loadAndMergePoso(DeleteDto dto)  throws ExpectedException {
		Delete poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DeleteDto dto, Delete poso)  {
	}


	public void postProcessCreateUnmanaged(DeleteDto dto, Delete poso)  {
	}


	public void postProcessLoad(DeleteDto dto, Delete poso)  {
	}


	public void postProcessMerge(DeleteDto dto, Delete poso)  {
	}


	public void postProcessInstantiate(Delete poso)  {
	}



}
