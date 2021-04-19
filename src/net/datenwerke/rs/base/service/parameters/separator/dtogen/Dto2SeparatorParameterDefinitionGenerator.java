package net.datenwerke.rs.base.service.parameters.separator.dtogen;

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
import net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto;
import net.datenwerke.rs.base.service.parameters.separator.SeparatorParameterDefinition;
import net.datenwerke.rs.base.service.parameters.separator.dtogen.Dto2SeparatorParameterDefinitionGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for SeparatorParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SeparatorParameterDefinitionGenerator implements Dto2PosoGenerator<SeparatorParameterDefinitionDto,SeparatorParameterDefinition> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SeparatorParameterDefinitionGenerator(
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

	public SeparatorParameterDefinition loadPoso(SeparatorParameterDefinitionDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		SeparatorParameterDefinition poso = entityManager.find(SeparatorParameterDefinition.class, id);
		return poso;
	}

	public SeparatorParameterDefinition instantiatePoso()  {
		SeparatorParameterDefinition poso = new SeparatorParameterDefinition();
		return poso;
	}

	public SeparatorParameterDefinition createPoso(SeparatorParameterDefinitionDto dto)  throws ExpectedException {
		SeparatorParameterDefinition poso = new SeparatorParameterDefinition();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SeparatorParameterDefinition createUnmanagedPoso(SeparatorParameterDefinitionDto dto)  throws ExpectedException {
		SeparatorParameterDefinition poso = new SeparatorParameterDefinition();

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

	public void mergePoso(SeparatorParameterDefinitionDto dto, final SeparatorParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SeparatorParameterDefinitionDto dto, final SeparatorParameterDefinition poso)  throws ExpectedException {
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

	}

	protected void mergeProxy2Poso(SeparatorParameterDefinitionDto dto, final SeparatorParameterDefinition poso)  throws ExpectedException {
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

	}

	public void mergeUnmanagedPoso(SeparatorParameterDefinitionDto dto, final SeparatorParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SeparatorParameterDefinitionDto dto, final SeparatorParameterDefinition poso)  throws ExpectedException {
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

	}

	protected void mergeProxy2UnmanagedPoso(SeparatorParameterDefinitionDto dto, final SeparatorParameterDefinition poso)  throws ExpectedException {
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

	}

	public SeparatorParameterDefinition loadAndMergePoso(SeparatorParameterDefinitionDto dto)  throws ExpectedException {
		SeparatorParameterDefinition poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SeparatorParameterDefinitionDto dto, SeparatorParameterDefinition poso)  {
	}


	public void postProcessCreateUnmanaged(SeparatorParameterDefinitionDto dto, SeparatorParameterDefinition poso)  {
	}


	public void postProcessLoad(SeparatorParameterDefinitionDto dto, SeparatorParameterDefinition poso)  {
	}


	public void postProcessMerge(SeparatorParameterDefinitionDto dto, SeparatorParameterDefinition poso)  {
	}


	public void postProcessInstantiate(SeparatorParameterDefinition poso)  {
	}


	public boolean validateKeyProperty(SeparatorParameterDefinitionDto dto, SeparatorParameterDefinition poso)  throws ExpectedException {
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
