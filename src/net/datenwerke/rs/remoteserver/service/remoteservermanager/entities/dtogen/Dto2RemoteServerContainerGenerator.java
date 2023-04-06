package net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.dtogen;

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
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerContainer;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.dtogen.Dto2RemoteServerContainerGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for RemoteServerContainer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2RemoteServerContainerGenerator implements Dto2PosoGenerator<RemoteServerContainerDto,RemoteServerContainer> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2RemoteServerContainerGenerator(
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

	public RemoteServerContainer loadPoso(RemoteServerContainerDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		RemoteServerContainer poso = entityManager.find(RemoteServerContainer.class, id);
		return poso;
	}

	public RemoteServerContainer instantiatePoso()  {
		RemoteServerContainer poso = new RemoteServerContainer();
		return poso;
	}

	public RemoteServerContainer createPoso(RemoteServerContainerDto dto)  throws ExpectedException {
		RemoteServerContainer poso = new RemoteServerContainer();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public RemoteServerContainer createUnmanagedPoso(RemoteServerContainerDto dto)  throws ExpectedException {
		RemoteServerContainer poso = new RemoteServerContainer();

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

	public void mergePoso(RemoteServerContainerDto dto, final RemoteServerContainer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(RemoteServerContainerDto dto, final RemoteServerContainer poso)  throws ExpectedException {
		/*  set remoteServer */
		RemoteServerDefinitionDto tmpDto_remoteServer = dto.getRemoteServer();
		if(null != tmpDto_remoteServer && null != tmpDto_remoteServer.getId()){
			if(null != poso.getRemoteServer() && null != poso.getRemoteServer().getId() && ! poso.getRemoteServer().getId().equals(tmpDto_remoteServer.getId())){
				RemoteServerDefinition newPropertyValue = (RemoteServerDefinition)dtoServiceProvider.get().loadPoso(tmpDto_remoteServer);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getRemoteServer(), newPropertyValue, "remoteServer");
				poso.setRemoteServer(newPropertyValue);
			} else if(null == poso.getRemoteServer()){
				poso.setRemoteServer((RemoteServerDefinition)dtoServiceProvider.get().loadPoso(tmpDto_remoteServer));
			}
		} else if(null != tmpDto_remoteServer && null == tmpDto_remoteServer.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_remoteServer, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (remoteServer)");
					poso.setRemoteServer((RemoteServerDefinition)refPoso);
				}
			});
		} else if(null == tmpDto_remoteServer){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getRemoteServer(), null, "remoteServer");
			poso.setRemoteServer(null);
		}

	}

	protected void mergeProxy2Poso(RemoteServerContainerDto dto, final RemoteServerContainer poso)  throws ExpectedException {
		/*  set remoteServer */
		if(dto.isRemoteServerModified()){
			RemoteServerDefinitionDto tmpDto_remoteServer = dto.getRemoteServer();
			if(null != tmpDto_remoteServer && null != tmpDto_remoteServer.getId()){
				if(null != poso.getRemoteServer() && null != poso.getRemoteServer().getId() && ! poso.getRemoteServer().getId().equals(tmpDto_remoteServer.getId())){
					RemoteServerDefinition newPropertyValue = (RemoteServerDefinition)dtoServiceProvider.get().loadPoso(tmpDto_remoteServer);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getRemoteServer(), newPropertyValue, "remoteServer");
					poso.setRemoteServer(newPropertyValue);
				} else if(null == poso.getRemoteServer()){
					poso.setRemoteServer((RemoteServerDefinition)dtoServiceProvider.get().loadPoso(tmpDto_remoteServer));
				}
			} else if(null != tmpDto_remoteServer && null == tmpDto_remoteServer.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_remoteServer, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (remoteServer)");
						poso.setRemoteServer((RemoteServerDefinition)refPoso);
					}
			});
			} else if(null == tmpDto_remoteServer){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getRemoteServer(), null, "remoteServer");
				poso.setRemoteServer(null);
			}
		}

	}

	public void mergeUnmanagedPoso(RemoteServerContainerDto dto, final RemoteServerContainer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(RemoteServerContainerDto dto, final RemoteServerContainer poso)  throws ExpectedException {
		/*  set remoteServer */
		RemoteServerDefinitionDto tmpDto_remoteServer = dto.getRemoteServer();
		if(null != tmpDto_remoteServer && null != tmpDto_remoteServer.getId()){
			RemoteServerDefinition newPropertyValue = (RemoteServerDefinition)dtoServiceProvider.get().loadPoso(tmpDto_remoteServer);
			poso.setRemoteServer(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_remoteServer, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setRemoteServer((RemoteServerDefinition)refPoso);
				}
			});
		} else if(null != tmpDto_remoteServer && null == tmpDto_remoteServer.getId()){
			final RemoteServerDefinitionDto tmpDto_remoteServer_final = tmpDto_remoteServer;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_remoteServer, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setRemoteServer((RemoteServerDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_remoteServer_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setRemoteServer((RemoteServerDefinition)refPoso);
					}
				}
			});
		} else if(null == tmpDto_remoteServer){
			poso.setRemoteServer(null);
		}

	}

	protected void mergeProxy2UnmanagedPoso(RemoteServerContainerDto dto, final RemoteServerContainer poso)  throws ExpectedException {
		/*  set remoteServer */
		if(dto.isRemoteServerModified()){
			RemoteServerDefinitionDto tmpDto_remoteServer = dto.getRemoteServer();
			if(null != tmpDto_remoteServer && null != tmpDto_remoteServer.getId()){
				RemoteServerDefinition newPropertyValue = (RemoteServerDefinition)dtoServiceProvider.get().loadPoso(tmpDto_remoteServer);
				poso.setRemoteServer(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_remoteServer, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setRemoteServer((RemoteServerDefinition)refPoso);
					}
			});
			} else if(null != tmpDto_remoteServer && null == tmpDto_remoteServer.getId()){
				final RemoteServerDefinitionDto tmpDto_remoteServer_final = tmpDto_remoteServer;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_remoteServer, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setRemoteServer((RemoteServerDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_remoteServer_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setRemoteServer((RemoteServerDefinition)refPoso);
						}
					}
			});
			} else if(null == tmpDto_remoteServer){
				poso.setRemoteServer(null);
			}
		}

	}

	public RemoteServerContainer loadAndMergePoso(RemoteServerContainerDto dto)  throws ExpectedException {
		RemoteServerContainer poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(RemoteServerContainerDto dto, RemoteServerContainer poso)  {
	}


	public void postProcessCreateUnmanaged(RemoteServerContainerDto dto, RemoteServerContainer poso)  {
	}


	public void postProcessLoad(RemoteServerContainerDto dto, RemoteServerContainer poso)  {
	}


	public void postProcessMerge(RemoteServerContainerDto dto, RemoteServerContainer poso)  {
	}


	public void postProcessInstantiate(RemoteServerContainer poso)  {
	}



}
