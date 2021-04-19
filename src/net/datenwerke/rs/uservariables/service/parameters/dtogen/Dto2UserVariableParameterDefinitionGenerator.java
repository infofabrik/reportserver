package net.datenwerke.rs.uservariables.service.parameters.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition;
import net.datenwerke.rs.uservariables.service.parameters.dtogen.Dto2UserVariableParameterDefinitionGenerator;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for UserVariableParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2UserVariableParameterDefinitionGenerator implements Dto2PosoGenerator<UserVariableParameterDefinitionDto,UserVariableParameterDefinition> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2UserVariableParameterDefinitionGenerator(
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

	public UserVariableParameterDefinition loadPoso(UserVariableParameterDefinitionDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		UserVariableParameterDefinition poso = entityManager.find(UserVariableParameterDefinition.class, id);
		return poso;
	}

	public UserVariableParameterDefinition instantiatePoso()  {
		UserVariableParameterDefinition poso = new UserVariableParameterDefinition();
		return poso;
	}

	public UserVariableParameterDefinition createPoso(UserVariableParameterDefinitionDto dto)  throws ExpectedException {
		UserVariableParameterDefinition poso = new UserVariableParameterDefinition();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public UserVariableParameterDefinition createUnmanagedPoso(UserVariableParameterDefinitionDto dto)  throws ExpectedException {
		UserVariableParameterDefinition poso = new UserVariableParameterDefinition();

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

	public void mergePoso(UserVariableParameterDefinitionDto dto, final UserVariableParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(UserVariableParameterDefinitionDto dto, final UserVariableParameterDefinition poso)  throws ExpectedException {
		/*  set dependsOn */
		final List<ParameterDefinition> col_dependsOn = new ArrayList<ParameterDefinition>();
		/* load new data from dto */
		Collection<ParameterDefinitionDto> tmpCol_dependsOn = dto.getDependsOn();

		for(ParameterDefinitionDto refDto : tmpCol_dependsOn){
			if(null != refDto && null != refDto.getId())
				col_dependsOn.add((ParameterDefinition) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (dependsOn)");
						col_dependsOn.add((ParameterDefinition) poso);
					}
					});
			}
		}
		poso.setDependsOn(col_dependsOn);

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set displayInline */
		poso.setDisplayInline(dto.isDisplayInline() );

		/*  set editable */
		poso.setEditable(dto.isEditable() );

		/*  set hidden */
		poso.setHidden(dto.isHidden() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set labelWidth */
		poso.setLabelWidth(dto.getLabelWidth() );

		/*  set mandatory */
		try{
			poso.setMandatory(dto.isMandatory() );
		} catch(NullPointerException e){
		}

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set userVariableDefinition */
		UserVariableDefinitionDto tmpDto_userVariableDefinition = dto.getUserVariableDefinition();
		if(null != tmpDto_userVariableDefinition && null != tmpDto_userVariableDefinition.getId()){
			if(null != poso.getUserVariableDefinition() && null != poso.getUserVariableDefinition().getId() && ! poso.getUserVariableDefinition().getId().equals(tmpDto_userVariableDefinition.getId())){
				UserVariableDefinition newPropertyValue = (UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_userVariableDefinition);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getUserVariableDefinition(), newPropertyValue, "userVariableDefinition");
				poso.setUserVariableDefinition(newPropertyValue);
			} else if(null == poso.getUserVariableDefinition()){
				poso.setUserVariableDefinition((UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_userVariableDefinition));
			}
		} else if(null != tmpDto_userVariableDefinition && null == tmpDto_userVariableDefinition.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_userVariableDefinition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (userVariableDefinition)");
					poso.setUserVariableDefinition((UserVariableDefinition)refPoso);
				}
			});
		} else if(null == tmpDto_userVariableDefinition){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getUserVariableDefinition(), null, "userVariableDefinition");
			poso.setUserVariableDefinition(null);
		}

	}

	protected void mergeProxy2Poso(UserVariableParameterDefinitionDto dto, final UserVariableParameterDefinition poso)  throws ExpectedException {
		/*  set dependsOn */
		if(dto.isDependsOnModified()){
			final List<ParameterDefinition> col_dependsOn = new ArrayList<ParameterDefinition>();
			/* load new data from dto */
			Collection<ParameterDefinitionDto> tmpCol_dependsOn = null;
			tmpCol_dependsOn = dto.getDependsOn();

			for(ParameterDefinitionDto refDto : tmpCol_dependsOn){
				if(null != refDto && null != refDto.getId())
					col_dependsOn.add((ParameterDefinition) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (dependsOn)");
							col_dependsOn.add((ParameterDefinition) poso);
						}
					});
				}
			}
			poso.setDependsOn(col_dependsOn);
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set displayInline */
		if(dto.isDisplayInlineModified()){
			poso.setDisplayInline(dto.isDisplayInline() );
		}

		/*  set editable */
		if(dto.isEditableModified()){
			poso.setEditable(dto.isEditable() );
		}

		/*  set hidden */
		if(dto.isHiddenModified()){
			poso.setHidden(dto.isHidden() );
		}

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set labelWidth */
		if(dto.isLabelWidthModified()){
			poso.setLabelWidth(dto.getLabelWidth() );
		}

		/*  set mandatory */
		if(dto.isMandatoryModified()){
			try{
				poso.setMandatory(dto.isMandatory() );
			} catch(NullPointerException e){
			}
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set userVariableDefinition */
		if(dto.isUserVariableDefinitionModified()){
			UserVariableDefinitionDto tmpDto_userVariableDefinition = dto.getUserVariableDefinition();
			if(null != tmpDto_userVariableDefinition && null != tmpDto_userVariableDefinition.getId()){
				if(null != poso.getUserVariableDefinition() && null != poso.getUserVariableDefinition().getId() && ! poso.getUserVariableDefinition().getId().equals(tmpDto_userVariableDefinition.getId())){
					UserVariableDefinition newPropertyValue = (UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_userVariableDefinition);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getUserVariableDefinition(), newPropertyValue, "userVariableDefinition");
					poso.setUserVariableDefinition(newPropertyValue);
				} else if(null == poso.getUserVariableDefinition()){
					poso.setUserVariableDefinition((UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_userVariableDefinition));
				}
			} else if(null != tmpDto_userVariableDefinition && null == tmpDto_userVariableDefinition.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_userVariableDefinition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (userVariableDefinition)");
						poso.setUserVariableDefinition((UserVariableDefinition)refPoso);
					}
			});
			} else if(null == tmpDto_userVariableDefinition){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getUserVariableDefinition(), null, "userVariableDefinition");
				poso.setUserVariableDefinition(null);
			}
		}

	}

	public void mergeUnmanagedPoso(UserVariableParameterDefinitionDto dto, final UserVariableParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(UserVariableParameterDefinitionDto dto, final UserVariableParameterDefinition poso)  throws ExpectedException {
		/*  set dependsOn */
		final List<ParameterDefinition> col_dependsOn = new ArrayList<ParameterDefinition>();
		/* load new data from dto */
		Collection<ParameterDefinitionDto> tmpCol_dependsOn = dto.getDependsOn();

		for(ParameterDefinitionDto refDto : tmpCol_dependsOn){
			if(null != refDto && null != refDto.getId())
				col_dependsOn.add((ParameterDefinition) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (dependsOn)");
						col_dependsOn.add((ParameterDefinition) poso);
					}
					});
			}
		}
		poso.setDependsOn(col_dependsOn);

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set displayInline */
		poso.setDisplayInline(dto.isDisplayInline() );

		/*  set editable */
		poso.setEditable(dto.isEditable() );

		/*  set hidden */
		poso.setHidden(dto.isHidden() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set labelWidth */
		poso.setLabelWidth(dto.getLabelWidth() );

		/*  set mandatory */
		try{
			poso.setMandatory(dto.isMandatory() );
		} catch(NullPointerException e){
		}

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set userVariableDefinition */
		UserVariableDefinitionDto tmpDto_userVariableDefinition = dto.getUserVariableDefinition();
		if(null != tmpDto_userVariableDefinition && null != tmpDto_userVariableDefinition.getId()){
			UserVariableDefinition newPropertyValue = (UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_userVariableDefinition);
			poso.setUserVariableDefinition(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_userVariableDefinition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setUserVariableDefinition((UserVariableDefinition)refPoso);
				}
			});
		} else if(null != tmpDto_userVariableDefinition && null == tmpDto_userVariableDefinition.getId()){
			final UserVariableDefinitionDto tmpDto_userVariableDefinition_final = tmpDto_userVariableDefinition;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_userVariableDefinition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setUserVariableDefinition((UserVariableDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_userVariableDefinition_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setUserVariableDefinition((UserVariableDefinition)refPoso);
					}
				}
			});
		} else if(null == tmpDto_userVariableDefinition){
			poso.setUserVariableDefinition(null);
		}

	}

	protected void mergeProxy2UnmanagedPoso(UserVariableParameterDefinitionDto dto, final UserVariableParameterDefinition poso)  throws ExpectedException {
		/*  set dependsOn */
		if(dto.isDependsOnModified()){
			final List<ParameterDefinition> col_dependsOn = new ArrayList<ParameterDefinition>();
			/* load new data from dto */
			Collection<ParameterDefinitionDto> tmpCol_dependsOn = null;
			tmpCol_dependsOn = dto.getDependsOn();

			for(ParameterDefinitionDto refDto : tmpCol_dependsOn){
				if(null != refDto && null != refDto.getId())
					col_dependsOn.add((ParameterDefinition) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (dependsOn)");
							col_dependsOn.add((ParameterDefinition) poso);
						}
					});
				}
			}
			poso.setDependsOn(col_dependsOn);
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set displayInline */
		if(dto.isDisplayInlineModified()){
			poso.setDisplayInline(dto.isDisplayInline() );
		}

		/*  set editable */
		if(dto.isEditableModified()){
			poso.setEditable(dto.isEditable() );
		}

		/*  set hidden */
		if(dto.isHiddenModified()){
			poso.setHidden(dto.isHidden() );
		}

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set labelWidth */
		if(dto.isLabelWidthModified()){
			poso.setLabelWidth(dto.getLabelWidth() );
		}

		/*  set mandatory */
		if(dto.isMandatoryModified()){
			try{
				poso.setMandatory(dto.isMandatory() );
			} catch(NullPointerException e){
			}
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set userVariableDefinition */
		if(dto.isUserVariableDefinitionModified()){
			UserVariableDefinitionDto tmpDto_userVariableDefinition = dto.getUserVariableDefinition();
			if(null != tmpDto_userVariableDefinition && null != tmpDto_userVariableDefinition.getId()){
				UserVariableDefinition newPropertyValue = (UserVariableDefinition)dtoServiceProvider.get().loadPoso(tmpDto_userVariableDefinition);
				poso.setUserVariableDefinition(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_userVariableDefinition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setUserVariableDefinition((UserVariableDefinition)refPoso);
					}
			});
			} else if(null != tmpDto_userVariableDefinition && null == tmpDto_userVariableDefinition.getId()){
				final UserVariableDefinitionDto tmpDto_userVariableDefinition_final = tmpDto_userVariableDefinition;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_userVariableDefinition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setUserVariableDefinition((UserVariableDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_userVariableDefinition_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setUserVariableDefinition((UserVariableDefinition)refPoso);
						}
					}
			});
			} else if(null == tmpDto_userVariableDefinition){
				poso.setUserVariableDefinition(null);
			}
		}

	}

	public UserVariableParameterDefinition loadAndMergePoso(UserVariableParameterDefinitionDto dto)  throws ExpectedException {
		UserVariableParameterDefinition poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(UserVariableParameterDefinitionDto dto, UserVariableParameterDefinition poso)  {
	}


	public void postProcessCreateUnmanaged(UserVariableParameterDefinitionDto dto, UserVariableParameterDefinition poso)  {
	}


	public void postProcessLoad(UserVariableParameterDefinitionDto dto, UserVariableParameterDefinition poso)  {
	}


	public void postProcessMerge(UserVariableParameterDefinitionDto dto, UserVariableParameterDefinition poso)  {
	}


	public void postProcessInstantiate(UserVariableParameterDefinition poso)  {
	}


	public boolean validateKeyProperty(UserVariableParameterDefinitionDto dto, UserVariableParameterDefinition poso)  throws ExpectedException {
		Object propertyValue = dto.getKey();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for key", "expected a String");

		if(! ((String)propertyValue).matches("^[a-zA-Z0-9_\\-]*$"))
			throw new ValidationFailedException("String validation failed for key", " Regex test failed.");

		/* all went well */
		return true;
	}


}
