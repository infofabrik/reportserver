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
import net.datenwerke.security.client.security.dto.GrantAccessDto;
import net.datenwerke.security.service.security.rights.GrantAccess;
import net.datenwerke.security.service.security.rights.dtogen.Dto2GrantAccessGenerator;

/**
 * Dto2PosoGenerator for GrantAccess
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2GrantAccessGenerator implements Dto2PosoGenerator<GrantAccessDto,GrantAccess> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2GrantAccessGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public GrantAccess loadPoso(GrantAccessDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public GrantAccess instantiatePoso()  {
		GrantAccess poso = new GrantAccess();
		return poso;
	}

	public GrantAccess createPoso(GrantAccessDto dto)  throws ExpectedException {
		GrantAccess poso = new GrantAccess();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public GrantAccess createUnmanagedPoso(GrantAccessDto dto)  throws ExpectedException {
		GrantAccess poso = new GrantAccess();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(GrantAccessDto dto, final GrantAccess poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(GrantAccessDto dto, final GrantAccess poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(GrantAccessDto dto, final GrantAccess poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(GrantAccessDto dto, final GrantAccess poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(GrantAccessDto dto, final GrantAccess poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(GrantAccessDto dto, final GrantAccess poso)  throws ExpectedException {
	}

	public GrantAccess loadAndMergePoso(GrantAccessDto dto)  throws ExpectedException {
		GrantAccess poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(GrantAccessDto dto, GrantAccess poso)  {
	}


	public void postProcessCreateUnmanaged(GrantAccessDto dto, GrantAccess poso)  {
	}


	public void postProcessLoad(GrantAccessDto dto, GrantAccess poso)  {
	}


	public void postProcessMerge(GrantAccessDto dto, GrantAccess poso)  {
	}


	public void postProcessInstantiate(GrantAccess poso)  {
	}



}
