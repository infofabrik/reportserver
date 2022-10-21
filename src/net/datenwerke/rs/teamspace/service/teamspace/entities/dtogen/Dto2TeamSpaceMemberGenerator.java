package net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole;
import net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.Dto2TeamSpaceMemberGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

/**
 * Dto2PosoGenerator for TeamSpaceMember
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TeamSpaceMemberGenerator implements Dto2PosoGenerator<TeamSpaceMemberDto,TeamSpaceMember> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TeamSpaceMemberGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public TeamSpaceMember loadPoso(TeamSpaceMemberDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TeamSpaceMember poso = entityManager.find(TeamSpaceMember.class, id);
		return poso;
	}

	public TeamSpaceMember instantiatePoso()  {
		TeamSpaceMember poso = new TeamSpaceMember();
		return poso;
	}

	public TeamSpaceMember createPoso(TeamSpaceMemberDto dto)  throws ExpectedException {
		TeamSpaceMember poso = new TeamSpaceMember();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TeamSpaceMember createUnmanagedPoso(TeamSpaceMemberDto dto)  throws ExpectedException {
		TeamSpaceMember poso = new TeamSpaceMember();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(TeamSpaceMemberDto dto, final TeamSpaceMember poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TeamSpaceMemberDto dto, final TeamSpaceMember poso)  throws ExpectedException {
		/*  set folk */
		AbstractUserManagerNodeDto tmpDto_folk = dto.getFolk();
		if(null != tmpDto_folk && null != tmpDto_folk.getId()){
			if(null != poso.getFolk() && null != poso.getFolk().getId() && ! poso.getFolk().getId().equals(tmpDto_folk.getId())){
				AbstractUserManagerNode newPropertyValue = (AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFolk(), newPropertyValue, "folk");
				poso.setFolk(newPropertyValue);
			} else if(null == poso.getFolk()){
				poso.setFolk((AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk));
			}
		} else if(null != tmpDto_folk && null == tmpDto_folk.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (folk)");
					poso.setFolk((AbstractUserManagerNode)refPoso);
				}
			});
		} else if(null == tmpDto_folk){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFolk(), null, "folk");
			poso.setFolk(null);
		}

		/*  set role */
		TeamSpaceRoleDto tmpDto_role = dto.getRole();
		poso.setRole((TeamSpaceRole)dtoServiceProvider.get().createPoso(tmpDto_role));

	}

	protected void mergeProxy2Poso(TeamSpaceMemberDto dto, final TeamSpaceMember poso)  throws ExpectedException {
		/*  set folk */
		if(dto.isFolkModified()){
			AbstractUserManagerNodeDto tmpDto_folk = dto.getFolk();
			if(null != tmpDto_folk && null != tmpDto_folk.getId()){
				if(null != poso.getFolk() && null != poso.getFolk().getId() && ! poso.getFolk().getId().equals(tmpDto_folk.getId())){
					AbstractUserManagerNode newPropertyValue = (AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFolk(), newPropertyValue, "folk");
					poso.setFolk(newPropertyValue);
				} else if(null == poso.getFolk()){
					poso.setFolk((AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk));
				}
			} else if(null != tmpDto_folk && null == tmpDto_folk.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (folk)");
						poso.setFolk((AbstractUserManagerNode)refPoso);
					}
			});
			} else if(null == tmpDto_folk){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFolk(), null, "folk");
				poso.setFolk(null);
			}
		}

		/*  set role */
		if(dto.isRoleModified()){
			TeamSpaceRoleDto tmpDto_role = dto.getRole();
			poso.setRole((TeamSpaceRole)dtoServiceProvider.get().createPoso(tmpDto_role));
		}

	}

	public void mergeUnmanagedPoso(TeamSpaceMemberDto dto, final TeamSpaceMember poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TeamSpaceMemberDto dto, final TeamSpaceMember poso)  throws ExpectedException {
		/*  set folk */
		AbstractUserManagerNodeDto tmpDto_folk = dto.getFolk();
		if(null != tmpDto_folk && null != tmpDto_folk.getId()){
			AbstractUserManagerNode newPropertyValue = (AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk);
			poso.setFolk(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setFolk((AbstractUserManagerNode)refPoso);
				}
			});
		} else if(null != tmpDto_folk && null == tmpDto_folk.getId()){
			final AbstractUserManagerNodeDto tmpDto_folk_final = tmpDto_folk;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setFolk((AbstractUserManagerNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_folk_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setFolk((AbstractUserManagerNode)refPoso);
					}
				}
			});
		} else if(null == tmpDto_folk){
			poso.setFolk(null);
		}

		/*  set role */
		TeamSpaceRoleDto tmpDto_role = dto.getRole();
		poso.setRole((TeamSpaceRole)dtoServiceProvider.get().createPoso(tmpDto_role));

	}

	protected void mergeProxy2UnmanagedPoso(TeamSpaceMemberDto dto, final TeamSpaceMember poso)  throws ExpectedException {
		/*  set folk */
		if(dto.isFolkModified()){
			AbstractUserManagerNodeDto tmpDto_folk = dto.getFolk();
			if(null != tmpDto_folk && null != tmpDto_folk.getId()){
				AbstractUserManagerNode newPropertyValue = (AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk);
				poso.setFolk(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setFolk((AbstractUserManagerNode)refPoso);
					}
			});
			} else if(null != tmpDto_folk && null == tmpDto_folk.getId()){
				final AbstractUserManagerNodeDto tmpDto_folk_final = tmpDto_folk;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setFolk((AbstractUserManagerNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_folk_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setFolk((AbstractUserManagerNode)refPoso);
						}
					}
			});
			} else if(null == tmpDto_folk){
				poso.setFolk(null);
			}
		}

		/*  set role */
		if(dto.isRoleModified()){
			TeamSpaceRoleDto tmpDto_role = dto.getRole();
			poso.setRole((TeamSpaceRole)dtoServiceProvider.get().createPoso(tmpDto_role));
		}

	}

	public TeamSpaceMember loadAndMergePoso(TeamSpaceMemberDto dto)  throws ExpectedException {
		TeamSpaceMember poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TeamSpaceMemberDto dto, TeamSpaceMember poso)  {
	}


	public void postProcessCreateUnmanaged(TeamSpaceMemberDto dto, TeamSpaceMember poso)  {
	}


	public void postProcessLoad(TeamSpaceMemberDto dto, TeamSpaceMember poso)  {
	}


	public void postProcessMerge(TeamSpaceMemberDto dto, TeamSpaceMember poso)  {
	}


	public void postProcessInstantiate(TeamSpaceMember poso)  {
	}



}
