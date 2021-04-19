package net.datenwerke.rs.teamspace.service.teamspace.security.rights.dtogen;

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
import net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto;
import net.datenwerke.rs.teamspace.service.teamspace.security.rights.TeamSpaceAdministrator;
import net.datenwerke.rs.teamspace.service.teamspace.security.rights.dtogen.Dto2TeamSpaceAdministratorGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TeamSpaceAdministrator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TeamSpaceAdministratorGenerator implements Dto2PosoGenerator<TeamSpaceAdministratorDto,TeamSpaceAdministrator> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TeamSpaceAdministratorGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public TeamSpaceAdministrator loadPoso(TeamSpaceAdministratorDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public TeamSpaceAdministrator instantiatePoso()  {
		TeamSpaceAdministrator poso = new TeamSpaceAdministrator();
		return poso;
	}

	public TeamSpaceAdministrator createPoso(TeamSpaceAdministratorDto dto)  throws ExpectedException {
		TeamSpaceAdministrator poso = new TeamSpaceAdministrator();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TeamSpaceAdministrator createUnmanagedPoso(TeamSpaceAdministratorDto dto)  throws ExpectedException {
		TeamSpaceAdministrator poso = new TeamSpaceAdministrator();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(TeamSpaceAdministratorDto dto, final TeamSpaceAdministrator poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TeamSpaceAdministratorDto dto, final TeamSpaceAdministrator poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(TeamSpaceAdministratorDto dto, final TeamSpaceAdministrator poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(TeamSpaceAdministratorDto dto, final TeamSpaceAdministrator poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TeamSpaceAdministratorDto dto, final TeamSpaceAdministrator poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(TeamSpaceAdministratorDto dto, final TeamSpaceAdministrator poso)  throws ExpectedException {
	}

	public TeamSpaceAdministrator loadAndMergePoso(TeamSpaceAdministratorDto dto)  throws ExpectedException {
		TeamSpaceAdministrator poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TeamSpaceAdministratorDto dto, TeamSpaceAdministrator poso)  {
	}


	public void postProcessCreateUnmanaged(TeamSpaceAdministratorDto dto, TeamSpaceAdministrator poso)  {
	}


	public void postProcessLoad(TeamSpaceAdministratorDto dto, TeamSpaceAdministrator poso)  {
	}


	public void postProcessMerge(TeamSpaceAdministratorDto dto, TeamSpaceAdministrator poso)  {
	}


	public void postProcessInstantiate(TeamSpaceAdministrator poso)  {
	}



}
