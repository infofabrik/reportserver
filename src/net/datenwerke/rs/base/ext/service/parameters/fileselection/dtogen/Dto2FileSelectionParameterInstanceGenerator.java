package net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
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
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.Dto2FileSelectionParameterInstanceGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FileSelectionParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FileSelectionParameterInstanceGenerator implements Dto2PosoGenerator<FileSelectionParameterInstanceDto,FileSelectionParameterInstance> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FileSelectionParameterInstanceGenerator(
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

	public FileSelectionParameterInstance loadPoso(FileSelectionParameterInstanceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		FileSelectionParameterInstance poso = entityManager.find(FileSelectionParameterInstance.class, id);
		return poso;
	}

	public FileSelectionParameterInstance instantiatePoso()  {
		FileSelectionParameterInstance poso = new FileSelectionParameterInstance();
		return poso;
	}

	public FileSelectionParameterInstance createPoso(FileSelectionParameterInstanceDto dto)  throws ExpectedException {
		FileSelectionParameterInstance poso = new FileSelectionParameterInstance();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FileSelectionParameterInstance createUnmanagedPoso(FileSelectionParameterInstanceDto dto)  throws ExpectedException {
		FileSelectionParameterInstance poso = new FileSelectionParameterInstance();

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

	public void mergePoso(FileSelectionParameterInstanceDto dto, final FileSelectionParameterInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FileSelectionParameterInstanceDto dto, final FileSelectionParameterInstance poso)  throws ExpectedException {
		/*  set definition */
		ParameterDefinitionDto tmpDto_definition = dto.getDefinition();
		if(null != tmpDto_definition && null != tmpDto_definition.getId()){
			if(null != poso.getDefinition() && null != poso.getDefinition().getId() && ! poso.getDefinition().getId().equals(tmpDto_definition.getId())){
				ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), newPropertyValue, "definition");
				poso.setDefinition(newPropertyValue);
			} else if(null == poso.getDefinition()){
				poso.setDefinition((ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition));
			}
		} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (definition)");
					poso.setDefinition((ParameterDefinition)refPoso);
				}
			});
		} else if(null == tmpDto_definition){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), null, "definition");
			poso.setDefinition(null);
		}

		/*  set selectedFiles */
		final List<SelectedParameterFile> col_selectedFiles = new ArrayList<SelectedParameterFile>();
		/* load new data from dto */
		Collection<SelectedParameterFileDto> tmpCol_selectedFiles = dto.getSelectedFiles();

		/* load current data from poso */
		if(null != poso.getSelectedFiles())
			col_selectedFiles.addAll(poso.getSelectedFiles());

		/* remove non existing data */
		List<SelectedParameterFile> remDto_selectedFiles = new ArrayList<SelectedParameterFile>();
		for(SelectedParameterFile ref : col_selectedFiles){
			boolean found = false;
			for(SelectedParameterFileDto refDto : tmpCol_selectedFiles){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_selectedFiles.add(ref);
		}
		for(SelectedParameterFile ref : remDto_selectedFiles)
			col_selectedFiles.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_selectedFiles, "selectedFiles");

		/* merge or add data */
		List<SelectedParameterFile> new_col_selectedFiles = new ArrayList<SelectedParameterFile>();
		for(SelectedParameterFileDto refDto : tmpCol_selectedFiles){
			boolean found = false;
			for(SelectedParameterFile ref : col_selectedFiles){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_selectedFiles.add((SelectedParameterFile) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_selectedFiles.add((SelectedParameterFile) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(selectedFiles) ");
		}
		poso.setSelectedFiles(new_col_selectedFiles);

		/*  set stillDefault */
		try{
			poso.setStillDefault(dto.isStillDefault() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(FileSelectionParameterInstanceDto dto, final FileSelectionParameterInstance poso)  throws ExpectedException {
		/*  set definition */
		if(dto.isDefinitionModified()){
			ParameterDefinitionDto tmpDto_definition = dto.getDefinition();
			if(null != tmpDto_definition && null != tmpDto_definition.getId()){
				if(null != poso.getDefinition() && null != poso.getDefinition().getId() && ! poso.getDefinition().getId().equals(tmpDto_definition.getId())){
					ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), newPropertyValue, "definition");
					poso.setDefinition(newPropertyValue);
				} else if(null == poso.getDefinition()){
					poso.setDefinition((ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition));
				}
			} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (definition)");
						poso.setDefinition((ParameterDefinition)refPoso);
					}
			});
			} else if(null == tmpDto_definition){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), null, "definition");
				poso.setDefinition(null);
			}
		}

		/*  set selectedFiles */
		if(dto.isSelectedFilesModified()){
			final List<SelectedParameterFile> col_selectedFiles = new ArrayList<SelectedParameterFile>();
			/* load new data from dto */
			Collection<SelectedParameterFileDto> tmpCol_selectedFiles = null;
			tmpCol_selectedFiles = dto.getSelectedFiles();

			/* load current data from poso */
			if(null != poso.getSelectedFiles())
				col_selectedFiles.addAll(poso.getSelectedFiles());

			/* remove non existing data */
			List<SelectedParameterFile> remDto_selectedFiles = new ArrayList<SelectedParameterFile>();
			for(SelectedParameterFile ref : col_selectedFiles){
				boolean found = false;
				for(SelectedParameterFileDto refDto : tmpCol_selectedFiles){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_selectedFiles.add(ref);
			}
			for(SelectedParameterFile ref : remDto_selectedFiles)
				col_selectedFiles.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_selectedFiles, "selectedFiles");

			/* merge or add data */
			List<SelectedParameterFile> new_col_selectedFiles = new ArrayList<SelectedParameterFile>();
			for(SelectedParameterFileDto refDto : tmpCol_selectedFiles){
				boolean found = false;
				for(SelectedParameterFile ref : col_selectedFiles){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_selectedFiles.add((SelectedParameterFile) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_selectedFiles.add((SelectedParameterFile) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(selectedFiles) ");
			}
			poso.setSelectedFiles(new_col_selectedFiles);
		}

		/*  set stillDefault */
		if(dto.isStillDefaultModified()){
			try{
				poso.setStillDefault(dto.isStillDefault() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(FileSelectionParameterInstanceDto dto, final FileSelectionParameterInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FileSelectionParameterInstanceDto dto, final FileSelectionParameterInstance poso)  throws ExpectedException {
		/*  set definition */
		ParameterDefinitionDto tmpDto_definition = dto.getDefinition();
		if(null != tmpDto_definition && null != tmpDto_definition.getId()){
			ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
			poso.setDefinition(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setDefinition((ParameterDefinition)refPoso);
				}
			});
		} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			final ParameterDefinitionDto tmpDto_definition_final = tmpDto_definition;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setDefinition((ParameterDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_definition_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setDefinition((ParameterDefinition)refPoso);
					}
				}
			});
		} else if(null == tmpDto_definition){
			poso.setDefinition(null);
		}

		/*  set selectedFiles */
		final List<SelectedParameterFile> col_selectedFiles = new ArrayList<SelectedParameterFile>();
		/* load new data from dto */
		Collection<SelectedParameterFileDto> tmpCol_selectedFiles = dto.getSelectedFiles();

		/* merge or add data */
		for(SelectedParameterFileDto refDto : tmpCol_selectedFiles){
			col_selectedFiles.add((SelectedParameterFile) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setSelectedFiles(col_selectedFiles);

		/*  set stillDefault */
		try{
			poso.setStillDefault(dto.isStillDefault() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(FileSelectionParameterInstanceDto dto, final FileSelectionParameterInstance poso)  throws ExpectedException {
		/*  set definition */
		if(dto.isDefinitionModified()){
			ParameterDefinitionDto tmpDto_definition = dto.getDefinition();
			if(null != tmpDto_definition && null != tmpDto_definition.getId()){
				ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
				poso.setDefinition(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setDefinition((ParameterDefinition)refPoso);
					}
			});
			} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
				final ParameterDefinitionDto tmpDto_definition_final = tmpDto_definition;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setDefinition((ParameterDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_definition_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setDefinition((ParameterDefinition)refPoso);
						}
					}
			});
			} else if(null == tmpDto_definition){
				poso.setDefinition(null);
			}
		}

		/*  set selectedFiles */
		if(dto.isSelectedFilesModified()){
			final List<SelectedParameterFile> col_selectedFiles = new ArrayList<SelectedParameterFile>();
			/* load new data from dto */
			Collection<SelectedParameterFileDto> tmpCol_selectedFiles = null;
			tmpCol_selectedFiles = dto.getSelectedFiles();

			/* merge or add data */
			for(SelectedParameterFileDto refDto : tmpCol_selectedFiles){
				col_selectedFiles.add((SelectedParameterFile) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setSelectedFiles(col_selectedFiles);
		}

		/*  set stillDefault */
		if(dto.isStillDefaultModified()){
			try{
				poso.setStillDefault(dto.isStillDefault() );
			} catch(NullPointerException e){
			}
		}

	}

	public FileSelectionParameterInstance loadAndMergePoso(FileSelectionParameterInstanceDto dto)  throws ExpectedException {
		FileSelectionParameterInstance poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FileSelectionParameterInstanceDto dto, FileSelectionParameterInstance poso)  {
	}


	public void postProcessCreateUnmanaged(FileSelectionParameterInstanceDto dto, FileSelectionParameterInstance poso)  {
	}


	public void postProcessLoad(FileSelectionParameterInstanceDto dto, FileSelectionParameterInstance poso)  {
	}


	public void postProcessMerge(FileSelectionParameterInstanceDto dto, FileSelectionParameterInstance poso)  {
	}


	public void postProcessInstantiate(FileSelectionParameterInstance poso)  {
	}



}
