package net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.dtogen;

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
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.ScriptDatasource;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.dtogen.Dto2ScriptDatasourceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ScriptDatasource
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ScriptDatasourceGenerator implements Dto2PosoGenerator<ScriptDatasourceDto,ScriptDatasource> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ScriptDatasourceGenerator(
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

	public ScriptDatasource loadPoso(ScriptDatasourceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ScriptDatasource poso = entityManager.find(ScriptDatasource.class, id);
		return poso;
	}

	public ScriptDatasource instantiatePoso()  {
		ScriptDatasource poso = new ScriptDatasource();
		return poso;
	}

	public ScriptDatasource createPoso(ScriptDatasourceDto dto)  throws ExpectedException {
		ScriptDatasource poso = new ScriptDatasource();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ScriptDatasource createUnmanagedPoso(ScriptDatasourceDto dto)  throws ExpectedException {
		ScriptDatasource poso = new ScriptDatasource();

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

	public void mergePoso(ScriptDatasourceDto dto, final ScriptDatasource poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ScriptDatasourceDto dto, final ScriptDatasource poso)  throws ExpectedException {
		/*  set databaseCache */
		try{
			poso.setDatabaseCache(dto.getDatabaseCache() );
		} catch(NullPointerException e){
		}

		/*  set defineAtTarget */
		try{
			poso.setDefineAtTarget(dto.isDefineAtTarget() );
		} catch(NullPointerException e){
		}

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

	protected void mergeProxy2Poso(ScriptDatasourceDto dto, final ScriptDatasource poso)  throws ExpectedException {
		/*  set databaseCache */
		if(dto.isDatabaseCacheModified()){
			try{
				poso.setDatabaseCache(dto.getDatabaseCache() );
			} catch(NullPointerException e){
			}
		}

		/*  set defineAtTarget */
		if(dto.isDefineAtTargetModified()){
			try{
				poso.setDefineAtTarget(dto.isDefineAtTarget() );
			} catch(NullPointerException e){
			}
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

	public void mergeUnmanagedPoso(ScriptDatasourceDto dto, final ScriptDatasource poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ScriptDatasourceDto dto, final ScriptDatasource poso)  throws ExpectedException {
		/*  set databaseCache */
		try{
			poso.setDatabaseCache(dto.getDatabaseCache() );
		} catch(NullPointerException e){
		}

		/*  set defineAtTarget */
		try{
			poso.setDefineAtTarget(dto.isDefineAtTarget() );
		} catch(NullPointerException e){
		}

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

	protected void mergeProxy2UnmanagedPoso(ScriptDatasourceDto dto, final ScriptDatasource poso)  throws ExpectedException {
		/*  set databaseCache */
		if(dto.isDatabaseCacheModified()){
			try{
				poso.setDatabaseCache(dto.getDatabaseCache() );
			} catch(NullPointerException e){
			}
		}

		/*  set defineAtTarget */
		if(dto.isDefineAtTargetModified()){
			try{
				poso.setDefineAtTarget(dto.isDefineAtTarget() );
			} catch(NullPointerException e){
			}
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

	public ScriptDatasource loadAndMergePoso(ScriptDatasourceDto dto)  throws ExpectedException {
		ScriptDatasource poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ScriptDatasourceDto dto, ScriptDatasource poso)  {
	}


	public void postProcessCreateUnmanaged(ScriptDatasourceDto dto, ScriptDatasource poso)  {
	}


	public void postProcessLoad(ScriptDatasourceDto dto, ScriptDatasource poso)  {
	}


	public void postProcessMerge(ScriptDatasourceDto dto, ScriptDatasource poso)  {
	}


	public void postProcessInstantiate(ScriptDatasource poso)  {
	}


	public boolean validateKeyProperty(ScriptDatasourceDto dto, ScriptDatasource poso)  throws ExpectedException {
		Object propertyValue = dto.getKey();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for key", "expected a String");

		if(! ((String)propertyValue).matches("^[a-zA-Z0-9_\\-]+$"))
			throw new ValidationFailedException("String validation failed for key", " Regex test failed.");

		/* all went well */
		return true;
	}


}
