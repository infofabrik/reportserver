package net.datenwerke.rs.grideditor.service.grideditor.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto;
import net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReportVariant;
import net.datenwerke.rs.grideditor.service.grideditor.entities.dtogen.Dto2GridEditorReportVariantGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for GridEditorReportVariant
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2GridEditorReportVariantGenerator implements Dto2PosoGenerator<GridEditorReportVariantDto,GridEditorReportVariant> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.rs.core.service.reportmanager.entities.reports.post.Dto2ReportPostProcessor postProcessor_1;

	private final net.datenwerke.rs.core.service.reportmanager.entities.reports.supervisor.Dto2ReportSupervisor dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2GridEditorReportVariantGenerator(
		net.datenwerke.rs.core.service.reportmanager.entities.reports.supervisor.Dto2ReportSupervisor dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		net.datenwerke.rs.core.service.reportmanager.entities.reports.post.Dto2ReportPostProcessor postProcessor_1,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.postProcessor_1 = postProcessor_1;
		this.reflectionService = reflectionService;
	}

	public GridEditorReportVariant loadPoso(GridEditorReportVariantDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		GridEditorReportVariant poso = entityManager.find(GridEditorReportVariant.class, id);
		return poso;
	}

	public GridEditorReportVariant instantiatePoso()  {
		GridEditorReportVariant poso = new GridEditorReportVariant();
		return poso;
	}

	public GridEditorReportVariant createPoso(GridEditorReportVariantDto dto)  throws ExpectedException {
		GridEditorReportVariant poso = new GridEditorReportVariant();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public GridEditorReportVariant createUnmanagedPoso(GridEditorReportVariantDto dto)  throws ExpectedException {
		GridEditorReportVariant poso = new GridEditorReportVariant();

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

	public void mergePoso(GridEditorReportVariantDto dto, final GridEditorReportVariant poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(GridEditorReportVariantDto dto, final GridEditorReportVariant poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set parameterInstances */
		final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
		/* load new data from dto */
		Collection<ParameterInstanceDto> tmpCol_parameterInstances = dto.getParameterInstances();

		/* load current data from poso */
		if(null != poso.getParameterInstances())
			col_parameterInstances.addAll(poso.getParameterInstances());

		/* remove non existing data */
		Set<ParameterInstance> remDto_parameterInstances = new HashSet<ParameterInstance>();
		for(ParameterInstance ref : col_parameterInstances){
			boolean found = false;
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_parameterInstances.add(ref);
		}
		for(ParameterInstance ref : remDto_parameterInstances)
			col_parameterInstances.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_parameterInstances, "parameterInstances");

		/* merge or add data */
		Set<ParameterInstance> new_col_parameterInstances = new HashSet<ParameterInstance>();
		for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
			boolean found = false;
			for(ParameterInstance ref : col_parameterInstances){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(parameterInstances) ");
		}
		poso.setParameterInstances(new_col_parameterInstances);

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

	}

	protected void mergeProxy2Poso(GridEditorReportVariantDto dto, final GridEditorReportVariant poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

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

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set parameterInstances */
		if(dto.isParameterInstancesModified()){
			final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
			/* load new data from dto */
			Collection<ParameterInstanceDto> tmpCol_parameterInstances = null;
			tmpCol_parameterInstances = dto.getParameterInstances();

			/* load current data from poso */
			if(null != poso.getParameterInstances())
				col_parameterInstances.addAll(poso.getParameterInstances());

			/* remove non existing data */
			Set<ParameterInstance> remDto_parameterInstances = new HashSet<ParameterInstance>();
			for(ParameterInstance ref : col_parameterInstances){
				boolean found = false;
				for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_parameterInstances.add(ref);
			}
			for(ParameterInstance ref : remDto_parameterInstances)
				col_parameterInstances.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_parameterInstances, "parameterInstances");

			/* merge or add data */
			Set<ParameterInstance> new_col_parameterInstances = new HashSet<ParameterInstance>();
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				boolean found = false;
				for(ParameterInstance ref : col_parameterInstances){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(parameterInstances) ");
			}
			poso.setParameterInstances(new_col_parameterInstances);
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

	}

	public void mergeUnmanagedPoso(GridEditorReportVariantDto dto, final GridEditorReportVariant poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(GridEditorReportVariantDto dto, final GridEditorReportVariant poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set parameterInstances */
		final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
		/* load new data from dto */
		Collection<ParameterInstanceDto> tmpCol_parameterInstances = dto.getParameterInstances();

		/* merge or add data */
		for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
			col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setParameterInstances(col_parameterInstances);

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

	}

	protected void mergeProxy2UnmanagedPoso(GridEditorReportVariantDto dto, final GridEditorReportVariant poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

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

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set parameterInstances */
		if(dto.isParameterInstancesModified()){
			final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
			/* load new data from dto */
			Collection<ParameterInstanceDto> tmpCol_parameterInstances = null;
			tmpCol_parameterInstances = dto.getParameterInstances();

			/* merge or add data */
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setParameterInstances(col_parameterInstances);
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

	}

	public GridEditorReportVariant loadAndMergePoso(GridEditorReportVariantDto dto)  throws ExpectedException {
		GridEditorReportVariant poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(GridEditorReportVariantDto dto, GridEditorReportVariant poso)  {

		this.postProcessor_1.posoCreated(dto, poso);
	}


	public void postProcessCreateUnmanaged(GridEditorReportVariantDto dto, GridEditorReportVariant poso)  {

		this.postProcessor_1.posoCreatedUnmanaged(dto, poso);
	}


	public void postProcessLoad(GridEditorReportVariantDto dto, GridEditorReportVariant poso)  {

		this.postProcessor_1.posoLoaded(dto, poso);
	}


	public void postProcessMerge(GridEditorReportVariantDto dto, GridEditorReportVariant poso)  {

		this.postProcessor_1.posoMerged(dto, poso);
	}


	public void postProcessInstantiate(GridEditorReportVariant poso)  {

		this.postProcessor_1.posoInstantiated(poso);
	}


	public boolean validateKeyProperty(GridEditorReportVariantDto dto, GridEditorReportVariant poso)  throws ExpectedException {
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
