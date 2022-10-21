package net.datenwerke.rs.incubator.service.scriptdatasource.entities.dtogen;

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
import net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceConfigDto;
import net.datenwerke.rs.incubator.service.scriptdatasource.entities.ScriptDatasourceConfig;
import net.datenwerke.rs.incubator.service.scriptdatasource.entities.dtogen.Dto2ScriptDatasourceConfigGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ScriptDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ScriptDatasourceConfigGenerator implements Dto2PosoGenerator<ScriptDatasourceConfigDto,ScriptDatasourceConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ScriptDatasourceConfigGenerator(
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

	public ScriptDatasourceConfig loadPoso(ScriptDatasourceConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ScriptDatasourceConfig poso = entityManager.find(ScriptDatasourceConfig.class, id);
		return poso;
	}

	public ScriptDatasourceConfig instantiatePoso()  {
		ScriptDatasourceConfig poso = new ScriptDatasourceConfig();
		return poso;
	}

	public ScriptDatasourceConfig createPoso(ScriptDatasourceConfigDto dto)  throws ExpectedException {
		ScriptDatasourceConfig poso = new ScriptDatasourceConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ScriptDatasourceConfig createUnmanagedPoso(ScriptDatasourceConfigDto dto)  throws ExpectedException {
		ScriptDatasourceConfig poso = new ScriptDatasourceConfig();

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

	public void mergePoso(ScriptDatasourceConfigDto dto, final ScriptDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ScriptDatasourceConfigDto dto, final ScriptDatasourceConfig poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set queryWrapper */
		poso.setQueryWrapper(dto.getQueryWrapper() );

		/*  set script */
		poso.setScript(dto.getScript() );

	}

	protected void mergeProxy2Poso(ScriptDatasourceConfigDto dto, final ScriptDatasourceConfig poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

		/*  set queryWrapper */
		if(dto.isQueryWrapperModified()){
			poso.setQueryWrapper(dto.getQueryWrapper() );
		}

		/*  set script */
		if(dto.isScriptModified()){
			poso.setScript(dto.getScript() );
		}

	}

	public void mergeUnmanagedPoso(ScriptDatasourceConfigDto dto, final ScriptDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ScriptDatasourceConfigDto dto, final ScriptDatasourceConfig poso)  throws ExpectedException {
		/*  set arguments */
		poso.setArguments(dto.getArguments() );

		/*  set queryWrapper */
		poso.setQueryWrapper(dto.getQueryWrapper() );

		/*  set script */
		poso.setScript(dto.getScript() );

	}

	protected void mergeProxy2UnmanagedPoso(ScriptDatasourceConfigDto dto, final ScriptDatasourceConfig poso)  throws ExpectedException {
		/*  set arguments */
		if(dto.isArgumentsModified()){
			poso.setArguments(dto.getArguments() );
		}

		/*  set queryWrapper */
		if(dto.isQueryWrapperModified()){
			poso.setQueryWrapper(dto.getQueryWrapper() );
		}

		/*  set script */
		if(dto.isScriptModified()){
			poso.setScript(dto.getScript() );
		}

	}

	public ScriptDatasourceConfig loadAndMergePoso(ScriptDatasourceConfigDto dto)  throws ExpectedException {
		ScriptDatasourceConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ScriptDatasourceConfigDto dto, ScriptDatasourceConfig poso)  {
	}


	public void postProcessCreateUnmanaged(ScriptDatasourceConfigDto dto, ScriptDatasourceConfig poso)  {
	}


	public void postProcessLoad(ScriptDatasourceConfigDto dto, ScriptDatasourceConfig poso)  {
	}


	public void postProcessMerge(ScriptDatasourceConfigDto dto, ScriptDatasourceConfig poso)  {
	}


	public void postProcessInstantiate(ScriptDatasourceConfig poso)  {
	}



}
