package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
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
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.Dto2TsDiskRootGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TsDiskRoot
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TsDiskRootGenerator implements Dto2PosoGenerator<TsDiskRootDto,TsDiskRoot> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TsDiskRootGenerator(
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

	public TsDiskRoot loadPoso(TsDiskRootDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TsDiskRoot poso = entityManager.find(TsDiskRoot.class, id);
		return poso;
	}

	public TsDiskRoot instantiatePoso()  {
		TsDiskRoot poso = new TsDiskRoot();
		return poso;
	}

	public TsDiskRoot createPoso(TsDiskRootDto dto)  throws ExpectedException {
		TsDiskRoot poso = new TsDiskRoot();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TsDiskRoot createUnmanagedPoso(TsDiskRootDto dto)  throws ExpectedException {
		TsDiskRoot poso = new TsDiskRoot();

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

	public void mergePoso(TsDiskRootDto dto, final TsDiskRoot poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TsDiskRootDto dto, final TsDiskRoot poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set teamSpace */
		TeamSpaceDto tmpDto_teamSpace = dto.getTeamSpace();
		if(null != tmpDto_teamSpace && null != tmpDto_teamSpace.getId()){
			if(null != poso.getTeamSpace() && null != poso.getTeamSpace().getId() && ! poso.getTeamSpace().getId().equals(tmpDto_teamSpace.getId())){
				TeamSpace newPropertyValue = (TeamSpace)dtoServiceProvider.get().loadPoso(tmpDto_teamSpace);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getTeamSpace(), newPropertyValue, "teamSpace");
				poso.setTeamSpace(newPropertyValue);
			} else if(null == poso.getTeamSpace()){
				poso.setTeamSpace((TeamSpace)dtoServiceProvider.get().loadPoso(tmpDto_teamSpace));
			}
		} else if(null != tmpDto_teamSpace && null == tmpDto_teamSpace.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpace, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (teamSpace)");
					poso.setTeamSpace((TeamSpace)refPoso);
				}
			});
		} else if(null == tmpDto_teamSpace){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getTeamSpace(), null, "teamSpace");
			poso.setTeamSpace(null);
		}

	}

	protected void mergeProxy2Poso(TsDiskRootDto dto, final TsDiskRoot poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set teamSpace */
		if(dto.isTeamSpaceModified()){
			TeamSpaceDto tmpDto_teamSpace = dto.getTeamSpace();
			if(null != tmpDto_teamSpace && null != tmpDto_teamSpace.getId()){
				if(null != poso.getTeamSpace() && null != poso.getTeamSpace().getId() && ! poso.getTeamSpace().getId().equals(tmpDto_teamSpace.getId())){
					TeamSpace newPropertyValue = (TeamSpace)dtoServiceProvider.get().loadPoso(tmpDto_teamSpace);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getTeamSpace(), newPropertyValue, "teamSpace");
					poso.setTeamSpace(newPropertyValue);
				} else if(null == poso.getTeamSpace()){
					poso.setTeamSpace((TeamSpace)dtoServiceProvider.get().loadPoso(tmpDto_teamSpace));
				}
			} else if(null != tmpDto_teamSpace && null == tmpDto_teamSpace.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpace, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (teamSpace)");
						poso.setTeamSpace((TeamSpace)refPoso);
					}
			});
			} else if(null == tmpDto_teamSpace){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getTeamSpace(), null, "teamSpace");
				poso.setTeamSpace(null);
			}
		}

	}

	public void mergeUnmanagedPoso(TsDiskRootDto dto, final TsDiskRoot poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TsDiskRootDto dto, final TsDiskRoot poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set teamSpace */
		TeamSpaceDto tmpDto_teamSpace = dto.getTeamSpace();
		if(null != tmpDto_teamSpace && null != tmpDto_teamSpace.getId()){
			TeamSpace newPropertyValue = (TeamSpace)dtoServiceProvider.get().loadPoso(tmpDto_teamSpace);
			poso.setTeamSpace(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpace, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setTeamSpace((TeamSpace)refPoso);
				}
			});
		} else if(null != tmpDto_teamSpace && null == tmpDto_teamSpace.getId()){
			final TeamSpaceDto tmpDto_teamSpace_final = tmpDto_teamSpace;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpace, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setTeamSpace((TeamSpace)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_teamSpace_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setTeamSpace((TeamSpace)refPoso);
					}
				}
			});
		} else if(null == tmpDto_teamSpace){
			poso.setTeamSpace(null);
		}

	}

	protected void mergeProxy2UnmanagedPoso(TsDiskRootDto dto, final TsDiskRoot poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set teamSpace */
		if(dto.isTeamSpaceModified()){
			TeamSpaceDto tmpDto_teamSpace = dto.getTeamSpace();
			if(null != tmpDto_teamSpace && null != tmpDto_teamSpace.getId()){
				TeamSpace newPropertyValue = (TeamSpace)dtoServiceProvider.get().loadPoso(tmpDto_teamSpace);
				poso.setTeamSpace(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpace, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setTeamSpace((TeamSpace)refPoso);
					}
			});
			} else if(null != tmpDto_teamSpace && null == tmpDto_teamSpace.getId()){
				final TeamSpaceDto tmpDto_teamSpace_final = tmpDto_teamSpace;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_teamSpace, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setTeamSpace((TeamSpace)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_teamSpace_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setTeamSpace((TeamSpace)refPoso);
						}
					}
			});
			} else if(null == tmpDto_teamSpace){
				poso.setTeamSpace(null);
			}
		}

	}

	public TsDiskRoot loadAndMergePoso(TsDiskRootDto dto)  throws ExpectedException {
		TsDiskRoot poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TsDiskRootDto dto, TsDiskRoot poso)  {
	}


	public void postProcessCreateUnmanaged(TsDiskRootDto dto, TsDiskRoot poso)  {
	}


	public void postProcessLoad(TsDiskRootDto dto, TsDiskRoot poso)  {
	}


	public void postProcessMerge(TsDiskRootDto dto, TsDiskRoot poso)  {
	}


	public void postProcessInstantiate(TsDiskRoot poso)  {
	}



}
