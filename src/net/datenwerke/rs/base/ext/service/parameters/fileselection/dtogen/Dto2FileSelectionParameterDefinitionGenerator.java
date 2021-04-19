package net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen;

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
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterDefinition;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.Dto2FileSelectionParameterDefinitionGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FileSelectionParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FileSelectionParameterDefinitionGenerator implements Dto2PosoGenerator<FileSelectionParameterDefinitionDto,FileSelectionParameterDefinition> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FileSelectionParameterDefinitionGenerator(
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

	public FileSelectionParameterDefinition loadPoso(FileSelectionParameterDefinitionDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		FileSelectionParameterDefinition poso = entityManager.find(FileSelectionParameterDefinition.class, id);
		return poso;
	}

	public FileSelectionParameterDefinition instantiatePoso()  {
		FileSelectionParameterDefinition poso = new FileSelectionParameterDefinition();
		return poso;
	}

	public FileSelectionParameterDefinition createPoso(FileSelectionParameterDefinitionDto dto)  throws ExpectedException {
		FileSelectionParameterDefinition poso = new FileSelectionParameterDefinition();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FileSelectionParameterDefinition createUnmanagedPoso(FileSelectionParameterDefinitionDto dto)  throws ExpectedException {
		FileSelectionParameterDefinition poso = new FileSelectionParameterDefinition();

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

	public void mergePoso(FileSelectionParameterDefinitionDto dto, final FileSelectionParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FileSelectionParameterDefinitionDto dto, final FileSelectionParameterDefinition poso)  throws ExpectedException {
		/*  set allowDownload */
		try{
			poso.setAllowDownload(dto.isAllowDownload() );
		} catch(NullPointerException e){
		}

		/*  set allowFileServerSelection */
		try{
			poso.setAllowFileServerSelection(dto.isAllowFileServerSelection() );
		} catch(NullPointerException e){
		}

		/*  set allowFileUpload */
		try{
			poso.setAllowFileUpload(dto.isAllowFileUpload() );
		} catch(NullPointerException e){
		}

		/*  set allowTeamSpaceSelection */
		try{
			poso.setAllowTeamSpaceSelection(dto.isAllowTeamSpaceSelection() );
		} catch(NullPointerException e){
		}

		/*  set allowedFileExtensions */
		poso.setAllowedFileExtensions(dto.getAllowedFileExtensions() );

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

		/*  set fileSizeString */
		poso.setFileSizeString(dto.getFileSizeString() );

		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

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

		/*  set maxNumberOfFiles */
		poso.setMaxNumberOfFiles(dto.getMaxNumberOfFiles() );

		/*  set minNumberOfFiles */
		poso.setMinNumberOfFiles(dto.getMinNumberOfFiles() );

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set width */
		try{
			poso.setWidth(dto.getWidth() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(FileSelectionParameterDefinitionDto dto, final FileSelectionParameterDefinition poso)  throws ExpectedException {
		/*  set allowDownload */
		if(dto.isAllowDownloadModified()){
			try{
				poso.setAllowDownload(dto.isAllowDownload() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowFileServerSelection */
		if(dto.isAllowFileServerSelectionModified()){
			try{
				poso.setAllowFileServerSelection(dto.isAllowFileServerSelection() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowFileUpload */
		if(dto.isAllowFileUploadModified()){
			try{
				poso.setAllowFileUpload(dto.isAllowFileUpload() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowTeamSpaceSelection */
		if(dto.isAllowTeamSpaceSelectionModified()){
			try{
				poso.setAllowTeamSpaceSelection(dto.isAllowTeamSpaceSelection() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowedFileExtensions */
		if(dto.isAllowedFileExtensionsModified()){
			poso.setAllowedFileExtensions(dto.getAllowedFileExtensions() );
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

		/*  set fileSizeString */
		if(dto.isFileSizeStringModified()){
			poso.setFileSizeString(dto.getFileSizeString() );
		}

		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
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

		/*  set maxNumberOfFiles */
		if(dto.isMaxNumberOfFilesModified()){
			poso.setMaxNumberOfFiles(dto.getMaxNumberOfFiles() );
		}

		/*  set minNumberOfFiles */
		if(dto.isMinNumberOfFilesModified()){
			poso.setMinNumberOfFiles(dto.getMinNumberOfFiles() );
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

		/*  set width */
		if(dto.isWidthModified()){
			try{
				poso.setWidth(dto.getWidth() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(FileSelectionParameterDefinitionDto dto, final FileSelectionParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FileSelectionParameterDefinitionDto dto, final FileSelectionParameterDefinition poso)  throws ExpectedException {
		/*  set allowDownload */
		try{
			poso.setAllowDownload(dto.isAllowDownload() );
		} catch(NullPointerException e){
		}

		/*  set allowFileServerSelection */
		try{
			poso.setAllowFileServerSelection(dto.isAllowFileServerSelection() );
		} catch(NullPointerException e){
		}

		/*  set allowFileUpload */
		try{
			poso.setAllowFileUpload(dto.isAllowFileUpload() );
		} catch(NullPointerException e){
		}

		/*  set allowTeamSpaceSelection */
		try{
			poso.setAllowTeamSpaceSelection(dto.isAllowTeamSpaceSelection() );
		} catch(NullPointerException e){
		}

		/*  set allowedFileExtensions */
		poso.setAllowedFileExtensions(dto.getAllowedFileExtensions() );

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

		/*  set fileSizeString */
		poso.setFileSizeString(dto.getFileSizeString() );

		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

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

		/*  set maxNumberOfFiles */
		poso.setMaxNumberOfFiles(dto.getMaxNumberOfFiles() );

		/*  set minNumberOfFiles */
		poso.setMinNumberOfFiles(dto.getMinNumberOfFiles() );

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set width */
		try{
			poso.setWidth(dto.getWidth() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(FileSelectionParameterDefinitionDto dto, final FileSelectionParameterDefinition poso)  throws ExpectedException {
		/*  set allowDownload */
		if(dto.isAllowDownloadModified()){
			try{
				poso.setAllowDownload(dto.isAllowDownload() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowFileServerSelection */
		if(dto.isAllowFileServerSelectionModified()){
			try{
				poso.setAllowFileServerSelection(dto.isAllowFileServerSelection() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowFileUpload */
		if(dto.isAllowFileUploadModified()){
			try{
				poso.setAllowFileUpload(dto.isAllowFileUpload() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowTeamSpaceSelection */
		if(dto.isAllowTeamSpaceSelectionModified()){
			try{
				poso.setAllowTeamSpaceSelection(dto.isAllowTeamSpaceSelection() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowedFileExtensions */
		if(dto.isAllowedFileExtensionsModified()){
			poso.setAllowedFileExtensions(dto.getAllowedFileExtensions() );
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

		/*  set fileSizeString */
		if(dto.isFileSizeStringModified()){
			poso.setFileSizeString(dto.getFileSizeString() );
		}

		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
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

		/*  set maxNumberOfFiles */
		if(dto.isMaxNumberOfFilesModified()){
			poso.setMaxNumberOfFiles(dto.getMaxNumberOfFiles() );
		}

		/*  set minNumberOfFiles */
		if(dto.isMinNumberOfFilesModified()){
			poso.setMinNumberOfFiles(dto.getMinNumberOfFiles() );
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

		/*  set width */
		if(dto.isWidthModified()){
			try{
				poso.setWidth(dto.getWidth() );
			} catch(NullPointerException e){
			}
		}

	}

	public FileSelectionParameterDefinition loadAndMergePoso(FileSelectionParameterDefinitionDto dto)  throws ExpectedException {
		FileSelectionParameterDefinition poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FileSelectionParameterDefinitionDto dto, FileSelectionParameterDefinition poso)  {
	}


	public void postProcessCreateUnmanaged(FileSelectionParameterDefinitionDto dto, FileSelectionParameterDefinition poso)  {
	}


	public void postProcessLoad(FileSelectionParameterDefinitionDto dto, FileSelectionParameterDefinition poso)  {
	}


	public void postProcessMerge(FileSelectionParameterDefinitionDto dto, FileSelectionParameterDefinition poso)  {
	}


	public void postProcessInstantiate(FileSelectionParameterDefinition poso)  {
	}


	public boolean validateKeyProperty(FileSelectionParameterDefinitionDto dto, FileSelectionParameterDefinition poso)  throws ExpectedException {
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
