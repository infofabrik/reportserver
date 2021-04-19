package net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen;

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
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableInstance;
import net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen.Dto2ListUserVariableInstanceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

/**
 * Dto2PosoGenerator for ListUserVariableInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ListUserVariableInstanceGenerator implements Dto2PosoGenerator<ListUserVariableInstanceDto,ListUserVariableInstance> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ListUserVariableInstanceGenerator(
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

	public ListUserVariableInstance loadPoso(ListUserVariableInstanceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ListUserVariableInstance poso = entityManager.find(ListUserVariableInstance.class, id);
		return poso;
	}

	public ListUserVariableInstance instantiatePoso()  {
		ListUserVariableInstance poso = new ListUserVariableInstance();
		return poso;
	}

	public ListUserVariableInstance createPoso(ListUserVariableInstanceDto dto)  throws ExpectedException {
		ListUserVariableInstance poso = new ListUserVariableInstance();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ListUserVariableInstance createUnmanagedPoso(ListUserVariableInstanceDto dto)  throws ExpectedException {
		ListUserVariableInstance poso = new ListUserVariableInstance();

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

	public void mergePoso(ListUserVariableInstanceDto dto, final ListUserVariableInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ListUserVariableInstanceDto dto, final ListUserVariableInstance poso)  throws ExpectedException {
		/*  set definition */
		UserVariableDefinitionDto tmpDto_definition = dto.getDefinition();
		if(null != tmpDto_definition && null != tmpDto_definition.getId()){
			if(null != poso.getDefinition() && null != poso.getDefinition().getId() && ! poso.getDefinition().getId().equals(tmpDto_definition.getId())){
				UserVariableDefinition newPropertyValue = (UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), newPropertyValue, "definition");
				poso.setDefinition(newPropertyValue);
			} else if(null == poso.getDefinition()){
				poso.setDefinition((UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition));
			}
		} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (definition)");
					poso.setDefinition((UserVariableDefinition)refPoso);
				}
			});
		} else if(null == tmpDto_definition){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), null, "definition");
			poso.setDefinition(null);
		}

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

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2Poso(ListUserVariableInstanceDto dto, final ListUserVariableInstance poso)  throws ExpectedException {
		/*  set definition */
		if(dto.isDefinitionModified()){
			UserVariableDefinitionDto tmpDto_definition = dto.getDefinition();
			if(null != tmpDto_definition && null != tmpDto_definition.getId()){
				if(null != poso.getDefinition() && null != poso.getDefinition().getId() && ! poso.getDefinition().getId().equals(tmpDto_definition.getId())){
					UserVariableDefinition newPropertyValue = (UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), newPropertyValue, "definition");
					poso.setDefinition(newPropertyValue);
				} else if(null == poso.getDefinition()){
					poso.setDefinition((UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition));
				}
			} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (definition)");
						poso.setDefinition((UserVariableDefinition)refPoso);
					}
			});
			} else if(null == tmpDto_definition){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), null, "definition");
				poso.setDefinition(null);
			}
		}

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

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public void mergeUnmanagedPoso(ListUserVariableInstanceDto dto, final ListUserVariableInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ListUserVariableInstanceDto dto, final ListUserVariableInstance poso)  throws ExpectedException {
		/*  set definition */
		UserVariableDefinitionDto tmpDto_definition = dto.getDefinition();
		if(null != tmpDto_definition && null != tmpDto_definition.getId()){
			UserVariableDefinition newPropertyValue = (UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
			poso.setDefinition(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setDefinition((UserVariableDefinition)refPoso);
				}
			});
		} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			final UserVariableDefinitionDto tmpDto_definition_final = tmpDto_definition;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setDefinition((UserVariableDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_definition_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setDefinition((UserVariableDefinition)refPoso);
					}
				}
			});
		} else if(null == tmpDto_definition){
			poso.setDefinition(null);
		}

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

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2UnmanagedPoso(ListUserVariableInstanceDto dto, final ListUserVariableInstance poso)  throws ExpectedException {
		/*  set definition */
		if(dto.isDefinitionModified()){
			UserVariableDefinitionDto tmpDto_definition = dto.getDefinition();
			if(null != tmpDto_definition && null != tmpDto_definition.getId()){
				UserVariableDefinition newPropertyValue = (UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
				poso.setDefinition(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setDefinition((UserVariableDefinition)refPoso);
					}
			});
			} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
				final UserVariableDefinitionDto tmpDto_definition_final = tmpDto_definition;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setDefinition((UserVariableDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_definition_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setDefinition((UserVariableDefinition)refPoso);
						}
					}
			});
			} else if(null == tmpDto_definition){
				poso.setDefinition(null);
			}
		}

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

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public ListUserVariableInstance loadAndMergePoso(ListUserVariableInstanceDto dto)  throws ExpectedException {
		ListUserVariableInstance poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ListUserVariableInstanceDto dto, ListUserVariableInstance poso)  {
	}


	public void postProcessCreateUnmanaged(ListUserVariableInstanceDto dto, ListUserVariableInstance poso)  {
	}


	public void postProcessLoad(ListUserVariableInstanceDto dto, ListUserVariableInstance poso)  {
	}


	public void postProcessMerge(ListUserVariableInstanceDto dto, ListUserVariableInstance poso)  {
	}


	public void postProcessInstantiate(ListUserVariableInstance poso)  {
	}



}
