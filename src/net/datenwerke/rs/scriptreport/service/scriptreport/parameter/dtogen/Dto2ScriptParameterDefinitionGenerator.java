package net.datenwerke.rs.scriptreport.service.scriptreport.parameter.dtogen;

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
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto;
import net.datenwerke.rs.scriptreport.service.scriptreport.parameter.ScriptParameterDefinition;
import net.datenwerke.rs.scriptreport.service.scriptreport.parameter.dtogen.Dto2ScriptParameterDefinitionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ScriptParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ScriptParameterDefinitionGenerator implements Dto2PosoGenerator<ScriptParameterDefinitionDto,ScriptParameterDefinition> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ScriptParameterDefinitionGenerator(
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

	public ScriptParameterDefinition loadPoso(ScriptParameterDefinitionDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ScriptParameterDefinition poso = entityManager.find(ScriptParameterDefinition.class, id);
		return poso;
	}

	public ScriptParameterDefinition instantiatePoso()  {
		ScriptParameterDefinition poso = new ScriptParameterDefinition();
		return poso;
	}

	public ScriptParameterDefinition createPoso(ScriptParameterDefinitionDto dto)  throws ExpectedException {
		ScriptParameterDefinition poso = new ScriptParameterDefinition();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ScriptParameterDefinition createUnmanagedPoso(ScriptParameterDefinitionDto dto)  throws ExpectedException {
		ScriptParameterDefinition poso = new ScriptParameterDefinition();

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

	public void mergePoso(ScriptParameterDefinitionDto dto, final ScriptParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ScriptParameterDefinitionDto dto, final ScriptParameterDefinition poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set defaultValue */
		poso.setDefaultValue(dto.getDefaultValue() );

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

		/*  set height */
		poso.setHeight(dto.getHeight() );

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

		/*  set script */
		FileServerFileDto tmpDto_script = dto.getScript();
		if(null != tmpDto_script && null != tmpDto_script.getId()){
			if(null != poso.getScript() && null != poso.getScript().getId() && ! poso.getScript().getId().equals(tmpDto_script.getId())){
				FileServerFile newPropertyValue = (FileServerFile)dtoServiceProvider.get().loadPoso(tmpDto_script);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getScript(), newPropertyValue, "script");
				poso.setScript(newPropertyValue);
			} else if(null == poso.getScript()){
				poso.setScript((FileServerFile)dtoServiceProvider.get().loadPoso(tmpDto_script));
			}
		} else if(null != tmpDto_script && null == tmpDto_script.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_script, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (script)");
					poso.setScript((FileServerFile)refPoso);
				}
			});
		} else if(null == tmpDto_script){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getScript(), null, "script");
			poso.setScript(null);
		}

		/*  set width */
		poso.setWidth(dto.getWidth() );

	}

	protected void mergeProxy2Poso(ScriptParameterDefinitionDto dto, final ScriptParameterDefinition poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

		/*  set defaultValue */
		if(dto.isDefaultValueModified()){
			poso.setDefaultValue(dto.getDefaultValue() );
		}

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

		/*  set height */
		if(dto.isHeightModified()){
			poso.setHeight(dto.getHeight() );
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

		/*  set script */
		if(dto.isScriptModified()){
			FileServerFileDto tmpDto_script = dto.getScript();
			if(null != tmpDto_script && null != tmpDto_script.getId()){
				if(null != poso.getScript() && null != poso.getScript().getId() && ! poso.getScript().getId().equals(tmpDto_script.getId())){
					FileServerFile newPropertyValue = (FileServerFile)dtoServiceProvider.get().loadPoso(tmpDto_script);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getScript(), newPropertyValue, "script");
					poso.setScript(newPropertyValue);
				} else if(null == poso.getScript()){
					poso.setScript((FileServerFile)dtoServiceProvider.get().loadPoso(tmpDto_script));
				}
			} else if(null != tmpDto_script && null == tmpDto_script.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_script, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (script)");
						poso.setScript((FileServerFile)refPoso);
					}
			});
			} else if(null == tmpDto_script){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getScript(), null, "script");
				poso.setScript(null);
			}
		}

		/*  set width */
		if(dto.isWidthModified()){
			poso.setWidth(dto.getWidth() );
		}

	}

	public void mergeUnmanagedPoso(ScriptParameterDefinitionDto dto, final ScriptParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ScriptParameterDefinitionDto dto, final ScriptParameterDefinition poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set defaultValue */
		poso.setDefaultValue(dto.getDefaultValue() );

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

		/*  set height */
		poso.setHeight(dto.getHeight() );

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

		/*  set script */
		FileServerFileDto tmpDto_script = dto.getScript();
		if(null != tmpDto_script && null != tmpDto_script.getId()){
			FileServerFile newPropertyValue = (FileServerFile)dtoServiceProvider.get().loadPoso(tmpDto_script);
			poso.setScript(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_script, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setScript((FileServerFile)refPoso);
				}
			});
		} else if(null != tmpDto_script && null == tmpDto_script.getId()){
			final FileServerFileDto tmpDto_script_final = tmpDto_script;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_script, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setScript((FileServerFile)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_script_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setScript((FileServerFile)refPoso);
					}
				}
			});
		} else if(null == tmpDto_script){
			poso.setScript(null);
		}

		/*  set width */
		poso.setWidth(dto.getWidth() );

	}

	protected void mergeProxy2UnmanagedPoso(ScriptParameterDefinitionDto dto, final ScriptParameterDefinition poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

		/*  set defaultValue */
		if(dto.isDefaultValueModified()){
			poso.setDefaultValue(dto.getDefaultValue() );
		}

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

		/*  set height */
		if(dto.isHeightModified()){
			poso.setHeight(dto.getHeight() );
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

		/*  set script */
		if(dto.isScriptModified()){
			FileServerFileDto tmpDto_script = dto.getScript();
			if(null != tmpDto_script && null != tmpDto_script.getId()){
				FileServerFile newPropertyValue = (FileServerFile)dtoServiceProvider.get().loadPoso(tmpDto_script);
				poso.setScript(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_script, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setScript((FileServerFile)refPoso);
					}
			});
			} else if(null != tmpDto_script && null == tmpDto_script.getId()){
				final FileServerFileDto tmpDto_script_final = tmpDto_script;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_script, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setScript((FileServerFile)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_script_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setScript((FileServerFile)refPoso);
						}
					}
			});
			} else if(null == tmpDto_script){
				poso.setScript(null);
			}
		}

		/*  set width */
		if(dto.isWidthModified()){
			poso.setWidth(dto.getWidth() );
		}

	}

	public ScriptParameterDefinition loadAndMergePoso(ScriptParameterDefinitionDto dto)  throws ExpectedException {
		ScriptParameterDefinition poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ScriptParameterDefinitionDto dto, ScriptParameterDefinition poso)  {
	}


	public void postProcessCreateUnmanaged(ScriptParameterDefinitionDto dto, ScriptParameterDefinition poso)  {
	}


	public void postProcessLoad(ScriptParameterDefinitionDto dto, ScriptParameterDefinition poso)  {
	}


	public void postProcessMerge(ScriptParameterDefinitionDto dto, ScriptParameterDefinition poso)  {
	}


	public void postProcessInstantiate(ScriptParameterDefinition poso)  {
	}


	public boolean validateKeyProperty(ScriptParameterDefinitionDto dto, ScriptParameterDefinition poso)  throws ExpectedException {
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
