package net.datenwerke.security.service.security.dtogen;

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
import net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.dtogen.Dto2SecurityServiceSecureeGenerator;

/**
 * Dto2PosoGenerator for SecurityServiceSecuree
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SecurityServiceSecureeGenerator implements Dto2PosoGenerator<SecurityServiceSecureeDto,SecurityServiceSecuree> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SecurityServiceSecureeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public SecurityServiceSecuree loadPoso(SecurityServiceSecureeDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public SecurityServiceSecuree instantiatePoso()  {
		SecurityServiceSecuree poso = new SecurityServiceSecuree();
		return poso;
	}

	public SecurityServiceSecuree createPoso(SecurityServiceSecureeDto dto)  throws ExpectedException {
		SecurityServiceSecuree poso = new SecurityServiceSecuree();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SecurityServiceSecuree createUnmanagedPoso(SecurityServiceSecureeDto dto)  throws ExpectedException {
		SecurityServiceSecuree poso = new SecurityServiceSecuree();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(SecurityServiceSecureeDto dto, final SecurityServiceSecuree poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SecurityServiceSecureeDto dto, final SecurityServiceSecuree poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(SecurityServiceSecureeDto dto, final SecurityServiceSecuree poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(SecurityServiceSecureeDto dto, final SecurityServiceSecuree poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SecurityServiceSecureeDto dto, final SecurityServiceSecuree poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(SecurityServiceSecureeDto dto, final SecurityServiceSecuree poso)  throws ExpectedException {
	}

	public SecurityServiceSecuree loadAndMergePoso(SecurityServiceSecureeDto dto)  throws ExpectedException {
		SecurityServiceSecuree poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SecurityServiceSecureeDto dto, SecurityServiceSecuree poso)  {
	}


	public void postProcessCreateUnmanaged(SecurityServiceSecureeDto dto, SecurityServiceSecuree poso)  {
	}


	public void postProcessLoad(SecurityServiceSecureeDto dto, SecurityServiceSecuree poso)  {
	}


	public void postProcessMerge(SecurityServiceSecureeDto dto, SecurityServiceSecuree poso)  {
	}


	public void postProcessInstantiate(SecurityServiceSecuree poso)  {
	}



}
