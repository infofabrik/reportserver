package net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen;

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
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableInstance;
import net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen.Dto2StringUserVariableInstanceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

/**
 * Dto2PosoGenerator for StringUserVariableInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2StringUserVariableInstanceGenerator implements Dto2PosoGenerator<StringUserVariableInstanceDto,StringUserVariableInstance> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2StringUserVariableInstanceGenerator(
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

	public StringUserVariableInstance loadPoso(StringUserVariableInstanceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		StringUserVariableInstance poso = entityManager.find(StringUserVariableInstance.class, id);
		return poso;
	}

	public StringUserVariableInstance instantiatePoso()  {
		StringUserVariableInstance poso = new StringUserVariableInstance();
		return poso;
	}

	public StringUserVariableInstance createPoso(StringUserVariableInstanceDto dto)  throws ExpectedException {
		StringUserVariableInstance poso = new StringUserVariableInstance();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public StringUserVariableInstance createUnmanagedPoso(StringUserVariableInstanceDto dto)  throws ExpectedException {
		StringUserVariableInstance poso = new StringUserVariableInstance();

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

	public void mergePoso(StringUserVariableInstanceDto dto, final StringUserVariableInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(StringUserVariableInstanceDto dto, final StringUserVariableInstance poso)  throws ExpectedException {
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

	protected void mergeProxy2Poso(StringUserVariableInstanceDto dto, final StringUserVariableInstance poso)  throws ExpectedException {
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

	public void mergeUnmanagedPoso(StringUserVariableInstanceDto dto, final StringUserVariableInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(StringUserVariableInstanceDto dto, final StringUserVariableInstance poso)  throws ExpectedException {
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

	protected void mergeProxy2UnmanagedPoso(StringUserVariableInstanceDto dto, final StringUserVariableInstance poso)  throws ExpectedException {
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

	public StringUserVariableInstance loadAndMergePoso(StringUserVariableInstanceDto dto)  throws ExpectedException {
		StringUserVariableInstance poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(StringUserVariableInstanceDto dto, StringUserVariableInstance poso)  {
	}


	public void postProcessCreateUnmanaged(StringUserVariableInstanceDto dto, StringUserVariableInstance poso)  {
	}


	public void postProcessLoad(StringUserVariableInstanceDto dto, StringUserVariableInstance poso)  {
	}


	public void postProcessMerge(StringUserVariableInstanceDto dto, StringUserVariableInstance poso)  {
	}


	public void postProcessInstantiate(StringUserVariableInstance poso)  {
	}



}
