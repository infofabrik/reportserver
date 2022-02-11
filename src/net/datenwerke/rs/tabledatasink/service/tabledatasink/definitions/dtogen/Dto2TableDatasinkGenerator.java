package net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.dtogen;

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
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.dtogen.Dto2TableDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TableDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TableDatasinkGenerator implements Dto2PosoGenerator<TableDatasinkDto,TableDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TableDatasinkGenerator(
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

	public TableDatasink loadPoso(TableDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TableDatasink poso = entityManager.find(TableDatasink.class, id);
		return poso;
	}

	public TableDatasink instantiatePoso()  {
		TableDatasink poso = new TableDatasink();
		return poso;
	}

	public TableDatasink createPoso(TableDatasinkDto dto)  throws ExpectedException {
		TableDatasink poso = new TableDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TableDatasink createUnmanagedPoso(TableDatasinkDto dto)  throws ExpectedException {
		TableDatasink poso = new TableDatasink();

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

	public void mergePoso(TableDatasinkDto dto, final TableDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TableDatasinkDto dto, final TableDatasink poso)  throws ExpectedException {
		/*  set datasourceContainer */
		DatasourceContainerDto tmpDto_datasourceContainer = dto.getDatasourceContainer();
		if(null != tmpDto_datasourceContainer && null != tmpDto_datasourceContainer.getId()){
			if(null != poso.getDatasourceContainer() && null != poso.getDatasourceContainer().getId() && poso.getDatasourceContainer().getId().equals(tmpDto_datasourceContainer.getId()))
				poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().loadAndMergePoso(tmpDto_datasourceContainer));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (datasourceContainer)");
		} else if(null != poso.getDatasourceContainer()){
			DatasourceContainer newPropertyValue = (DatasourceContainer)dtoServiceProvider.get().createPoso(tmpDto_datasourceContainer);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDatasourceContainer(), newPropertyValue, "datasourceContainer");
			poso.setDatasourceContainer(newPropertyValue);
		} else {
			poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().createPoso(tmpDto_datasourceContainer));
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set tableName */
		poso.setTableName(dto.getTableName() );

	}

	protected void mergeProxy2Poso(TableDatasinkDto dto, final TableDatasink poso)  throws ExpectedException {
		/*  set datasourceContainer */
		if(dto.isDatasourceContainerModified()){
			DatasourceContainerDto tmpDto_datasourceContainer = dto.getDatasourceContainer();
			if(null != tmpDto_datasourceContainer && null != tmpDto_datasourceContainer.getId()){
				if(null != poso.getDatasourceContainer() && null != poso.getDatasourceContainer().getId() && poso.getDatasourceContainer().getId().equals(tmpDto_datasourceContainer.getId()))
					poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().loadAndMergePoso(tmpDto_datasourceContainer));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (datasourceContainer)");
			} else if(null != poso.getDatasourceContainer()){
				DatasourceContainer newPropertyValue = (DatasourceContainer)dtoServiceProvider.get().createPoso(tmpDto_datasourceContainer);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDatasourceContainer(), newPropertyValue, "datasourceContainer");
				poso.setDatasourceContainer(newPropertyValue);
			} else {
				poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().createPoso(tmpDto_datasourceContainer));
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

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set tableName */
		if(dto.isTableNameModified()){
			poso.setTableName(dto.getTableName() );
		}

	}

	public void mergeUnmanagedPoso(TableDatasinkDto dto, final TableDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TableDatasinkDto dto, final TableDatasink poso)  throws ExpectedException {
		/*  set datasourceContainer */
		DatasourceContainerDto tmpDto_datasourceContainer = dto.getDatasourceContainer();
		poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasourceContainer));

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set tableName */
		poso.setTableName(dto.getTableName() );

	}

	protected void mergeProxy2UnmanagedPoso(TableDatasinkDto dto, final TableDatasink poso)  throws ExpectedException {
		/*  set datasourceContainer */
		if(dto.isDatasourceContainerModified()){
			DatasourceContainerDto tmpDto_datasourceContainer = dto.getDatasourceContainer();
			poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasourceContainer));
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

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set tableName */
		if(dto.isTableNameModified()){
			poso.setTableName(dto.getTableName() );
		}

	}

	public TableDatasink loadAndMergePoso(TableDatasinkDto dto)  throws ExpectedException {
		TableDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TableDatasinkDto dto, TableDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(TableDatasinkDto dto, TableDatasink poso)  {
	}


	public void postProcessLoad(TableDatasinkDto dto, TableDatasink poso)  {
	}


	public void postProcessMerge(TableDatasinkDto dto, TableDatasink poso)  {
	}


	public void postProcessInstantiate(TableDatasink poso)  {
	}



}
