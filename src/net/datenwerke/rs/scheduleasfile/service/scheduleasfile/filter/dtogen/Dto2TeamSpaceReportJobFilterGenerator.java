package net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.dtogen;

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
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.TeamSpaceReportJobFilter;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.dtogen.Dto2TeamSpaceReportJobFilterGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TeamSpaceReportJobFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TeamSpaceReportJobFilterGenerator implements Dto2PosoGenerator<TeamSpaceReportJobFilterDto,TeamSpaceReportJobFilter> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TeamSpaceReportJobFilterGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public TeamSpaceReportJobFilter loadPoso(TeamSpaceReportJobFilterDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public TeamSpaceReportJobFilter instantiatePoso()  {
		TeamSpaceReportJobFilter poso = new TeamSpaceReportJobFilter();
		return poso;
	}

	public TeamSpaceReportJobFilter createPoso(TeamSpaceReportJobFilterDto dto)  throws ExpectedException {
		TeamSpaceReportJobFilter poso = new TeamSpaceReportJobFilter();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TeamSpaceReportJobFilter createUnmanagedPoso(TeamSpaceReportJobFilterDto dto)  throws ExpectedException {
		TeamSpaceReportJobFilter poso = new TeamSpaceReportJobFilter();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(TeamSpaceReportJobFilterDto dto, final TeamSpaceReportJobFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TeamSpaceReportJobFilterDto dto, final TeamSpaceReportJobFilter poso)  throws ExpectedException {
		/*  set teamspaceId */
		poso.setTeamspaceId(dto.getTeamspaceId() );

	}

	protected void mergeProxy2Poso(TeamSpaceReportJobFilterDto dto, final TeamSpaceReportJobFilter poso)  throws ExpectedException {
		/*  set teamspaceId */
		if(dto.isTeamspaceIdModified()){
			poso.setTeamspaceId(dto.getTeamspaceId() );
		}

	}

	public void mergeUnmanagedPoso(TeamSpaceReportJobFilterDto dto, final TeamSpaceReportJobFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TeamSpaceReportJobFilterDto dto, final TeamSpaceReportJobFilter poso)  throws ExpectedException {
		/*  set teamspaceId */
		poso.setTeamspaceId(dto.getTeamspaceId() );

	}

	protected void mergeProxy2UnmanagedPoso(TeamSpaceReportJobFilterDto dto, final TeamSpaceReportJobFilter poso)  throws ExpectedException {
		/*  set teamspaceId */
		if(dto.isTeamspaceIdModified()){
			poso.setTeamspaceId(dto.getTeamspaceId() );
		}

	}

	public TeamSpaceReportJobFilter loadAndMergePoso(TeamSpaceReportJobFilterDto dto)  throws ExpectedException {
		TeamSpaceReportJobFilter poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TeamSpaceReportJobFilterDto dto, TeamSpaceReportJobFilter poso)  {
	}


	public void postProcessCreateUnmanaged(TeamSpaceReportJobFilterDto dto, TeamSpaceReportJobFilter poso)  {
	}


	public void postProcessLoad(TeamSpaceReportJobFilterDto dto, TeamSpaceReportJobFilter poso)  {
	}


	public void postProcessMerge(TeamSpaceReportJobFilterDto dto, TeamSpaceReportJobFilter poso)  {
	}


	public void postProcessInstantiate(TeamSpaceReportJobFilter poso)  {
	}



}
