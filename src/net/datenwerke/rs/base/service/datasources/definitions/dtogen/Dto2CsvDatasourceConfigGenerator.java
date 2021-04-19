package net.datenwerke.rs.base.service.datasources.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
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
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnectorConfig;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.definitions.dtogen.Dto2CsvDatasourceConfigGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CsvDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CsvDatasourceConfigGenerator implements Dto2PosoGenerator<CsvDatasourceConfigDto,CsvDatasourceConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CsvDatasourceConfigGenerator(
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

	public CsvDatasourceConfig loadPoso(CsvDatasourceConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		CsvDatasourceConfig poso = entityManager.find(CsvDatasourceConfig.class, id);
		return poso;
	}

	public CsvDatasourceConfig instantiatePoso()  {
		CsvDatasourceConfig poso = new CsvDatasourceConfig();
		return poso;
	}

	public CsvDatasourceConfig createPoso(CsvDatasourceConfigDto dto)  throws ExpectedException {
		CsvDatasourceConfig poso = new CsvDatasourceConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CsvDatasourceConfig createUnmanagedPoso(CsvDatasourceConfigDto dto)  throws ExpectedException {
		CsvDatasourceConfig poso = new CsvDatasourceConfig();

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

	public void mergePoso(CsvDatasourceConfigDto dto, final CsvDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CsvDatasourceConfigDto dto, final CsvDatasourceConfig poso)  throws ExpectedException {
		/*  set connectorConfig */
		final List<DatasourceConnectorConfig> col_connectorConfig = new ArrayList<DatasourceConnectorConfig>();
		/* load new data from dto */
		Collection<DatasourceConnectorConfigDto> tmpCol_connectorConfig = dto.getConnectorConfig();

		/* load current data from poso */
		if(null != poso.getConnectorConfig())
			col_connectorConfig.addAll(poso.getConnectorConfig());

		/* remove non existing data */
		List<DatasourceConnectorConfig> remDto_connectorConfig = new ArrayList<DatasourceConnectorConfig>();
		for(DatasourceConnectorConfig ref : col_connectorConfig){
			boolean found = false;
			for(DatasourceConnectorConfigDto refDto : tmpCol_connectorConfig){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_connectorConfig.add(ref);
		}
		for(DatasourceConnectorConfig ref : remDto_connectorConfig)
			col_connectorConfig.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_connectorConfig, "connectorConfig");

		/* merge or add data */
		List<DatasourceConnectorConfig> new_col_connectorConfig = new ArrayList<DatasourceConnectorConfig>();
		for(DatasourceConnectorConfigDto refDto : tmpCol_connectorConfig){
			boolean found = false;
			for(DatasourceConnectorConfig ref : col_connectorConfig){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_connectorConfig.add((DatasourceConnectorConfig) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_connectorConfig.add((DatasourceConnectorConfig) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(connectorConfig) ");
		}
		poso.setConnectorConfig(new_col_connectorConfig);

		/*  set queryWrapper */
		poso.setQueryWrapper(dto.getQueryWrapper() );

	}

	protected void mergeProxy2Poso(CsvDatasourceConfigDto dto, final CsvDatasourceConfig poso)  throws ExpectedException {
		/*  set connectorConfig */
		if(dto.isConnectorConfigModified()){
			final List<DatasourceConnectorConfig> col_connectorConfig = new ArrayList<DatasourceConnectorConfig>();
			/* load new data from dto */
			Collection<DatasourceConnectorConfigDto> tmpCol_connectorConfig = null;
			tmpCol_connectorConfig = dto.getConnectorConfig();

			/* load current data from poso */
			if(null != poso.getConnectorConfig())
				col_connectorConfig.addAll(poso.getConnectorConfig());

			/* remove non existing data */
			List<DatasourceConnectorConfig> remDto_connectorConfig = new ArrayList<DatasourceConnectorConfig>();
			for(DatasourceConnectorConfig ref : col_connectorConfig){
				boolean found = false;
				for(DatasourceConnectorConfigDto refDto : tmpCol_connectorConfig){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_connectorConfig.add(ref);
			}
			for(DatasourceConnectorConfig ref : remDto_connectorConfig)
				col_connectorConfig.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_connectorConfig, "connectorConfig");

			/* merge or add data */
			List<DatasourceConnectorConfig> new_col_connectorConfig = new ArrayList<DatasourceConnectorConfig>();
			for(DatasourceConnectorConfigDto refDto : tmpCol_connectorConfig){
				boolean found = false;
				for(DatasourceConnectorConfig ref : col_connectorConfig){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_connectorConfig.add((DatasourceConnectorConfig) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_connectorConfig.add((DatasourceConnectorConfig) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(connectorConfig) ");
			}
			poso.setConnectorConfig(new_col_connectorConfig);
		}

		/*  set queryWrapper */
		if(dto.isQueryWrapperModified()){
			poso.setQueryWrapper(dto.getQueryWrapper() );
		}

	}

	public void mergeUnmanagedPoso(CsvDatasourceConfigDto dto, final CsvDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CsvDatasourceConfigDto dto, final CsvDatasourceConfig poso)  throws ExpectedException {
		/*  set connectorConfig */
		final List<DatasourceConnectorConfig> col_connectorConfig = new ArrayList<DatasourceConnectorConfig>();
		/* load new data from dto */
		Collection<DatasourceConnectorConfigDto> tmpCol_connectorConfig = dto.getConnectorConfig();

		/* merge or add data */
		for(DatasourceConnectorConfigDto refDto : tmpCol_connectorConfig){
			col_connectorConfig.add((DatasourceConnectorConfig) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setConnectorConfig(col_connectorConfig);

		/*  set queryWrapper */
		poso.setQueryWrapper(dto.getQueryWrapper() );

	}

	protected void mergeProxy2UnmanagedPoso(CsvDatasourceConfigDto dto, final CsvDatasourceConfig poso)  throws ExpectedException {
		/*  set connectorConfig */
		if(dto.isConnectorConfigModified()){
			final List<DatasourceConnectorConfig> col_connectorConfig = new ArrayList<DatasourceConnectorConfig>();
			/* load new data from dto */
			Collection<DatasourceConnectorConfigDto> tmpCol_connectorConfig = null;
			tmpCol_connectorConfig = dto.getConnectorConfig();

			/* merge or add data */
			for(DatasourceConnectorConfigDto refDto : tmpCol_connectorConfig){
				col_connectorConfig.add((DatasourceConnectorConfig) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setConnectorConfig(col_connectorConfig);
		}

		/*  set queryWrapper */
		if(dto.isQueryWrapperModified()){
			poso.setQueryWrapper(dto.getQueryWrapper() );
		}

	}

	public CsvDatasourceConfig loadAndMergePoso(CsvDatasourceConfigDto dto)  throws ExpectedException {
		CsvDatasourceConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CsvDatasourceConfigDto dto, CsvDatasourceConfig poso)  {
	}


	public void postProcessCreateUnmanaged(CsvDatasourceConfigDto dto, CsvDatasourceConfig poso)  {
	}


	public void postProcessLoad(CsvDatasourceConfigDto dto, CsvDatasourceConfig poso)  {
	}


	public void postProcessMerge(CsvDatasourceConfigDto dto, CsvDatasourceConfig poso)  {
	}


	public void postProcessInstantiate(CsvDatasourceConfig poso)  {
	}



}
