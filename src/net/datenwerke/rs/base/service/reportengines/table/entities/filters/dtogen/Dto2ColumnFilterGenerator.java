package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2ColumnFilterGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ColumnFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ColumnFilterGenerator implements Dto2PosoGenerator<ColumnFilterDto,ColumnFilter> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ColumnFilterGenerator(
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

	public ColumnFilter loadPoso(ColumnFilterDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ColumnFilter poso = entityManager.find(ColumnFilter.class, id);
		return poso;
	}

	public ColumnFilter instantiatePoso()  {
		ColumnFilter poso = new ColumnFilter();
		return poso;
	}

	public ColumnFilter createPoso(ColumnFilterDto dto)  throws ExpectedException {
		ColumnFilter poso = new ColumnFilter();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ColumnFilter createUnmanagedPoso(ColumnFilterDto dto)  throws ExpectedException {
		ColumnFilter poso = new ColumnFilter();

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

	public void mergePoso(ColumnFilterDto dto, final ColumnFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ColumnFilterDto dto, final ColumnFilter poso)  throws ExpectedException {
		/*  set column */
		ColumnDto tmpDto_column = dto.getColumn();
		if(null != tmpDto_column && null != tmpDto_column.getId()){
			if(null != poso.getColumn() && null != poso.getColumn().getId() && poso.getColumn().getId().equals(tmpDto_column.getId()))
				poso.setColumn((Column)dtoServiceProvider.get().loadAndMergePoso(tmpDto_column));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (column)");
		} else if(null != poso.getColumn()){
			Column newPropertyValue = (Column)dtoServiceProvider.get().createPoso(tmpDto_column);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getColumn(), newPropertyValue, "column");
			poso.setColumn(newPropertyValue);
		} else {
			poso.setColumn((Column)dtoServiceProvider.get().createPoso(tmpDto_column));
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

	}

	protected void mergeProxy2Poso(ColumnFilterDto dto, final ColumnFilter poso)  throws ExpectedException {
		/*  set column */
		if(dto.isColumnModified()){
			ColumnDto tmpDto_column = dto.getColumn();
			if(null != tmpDto_column && null != tmpDto_column.getId()){
				if(null != poso.getColumn() && null != poso.getColumn().getId() && poso.getColumn().getId().equals(tmpDto_column.getId()))
					poso.setColumn((Column)dtoServiceProvider.get().loadAndMergePoso(tmpDto_column));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (column)");
			} else if(null != poso.getColumn()){
				Column newPropertyValue = (Column)dtoServiceProvider.get().createPoso(tmpDto_column);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getColumn(), newPropertyValue, "column");
				poso.setColumn(newPropertyValue);
			} else {
				poso.setColumn((Column)dtoServiceProvider.get().createPoso(tmpDto_column));
			}
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

	}

	public void mergeUnmanagedPoso(ColumnFilterDto dto, final ColumnFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ColumnFilterDto dto, final ColumnFilter poso)  throws ExpectedException {
		/*  set column */
		ColumnDto tmpDto_column = dto.getColumn();
		poso.setColumn((Column)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_column));

		/*  set description */
		poso.setDescription(dto.getDescription() );

	}

	protected void mergeProxy2UnmanagedPoso(ColumnFilterDto dto, final ColumnFilter poso)  throws ExpectedException {
		/*  set column */
		if(dto.isColumnModified()){
			ColumnDto tmpDto_column = dto.getColumn();
			poso.setColumn((Column)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_column));
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

	}

	public ColumnFilter loadAndMergePoso(ColumnFilterDto dto)  throws ExpectedException {
		ColumnFilter poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ColumnFilterDto dto, ColumnFilter poso)  {
	}


	public void postProcessCreateUnmanaged(ColumnFilterDto dto, ColumnFilter poso)  {
	}


	public void postProcessLoad(ColumnFilterDto dto, ColumnFilter poso)  {
	}


	public void postProcessMerge(ColumnFilterDto dto, ColumnFilter poso)  {
	}


	public void postProcessInstantiate(ColumnFilter poso)  {
	}



}
