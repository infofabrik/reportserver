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
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryOperator;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2BinaryColumnFilterGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for BinaryColumnFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BinaryColumnFilterGenerator implements Dto2PosoGenerator<BinaryColumnFilterDto,BinaryColumnFilter> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2BinaryColumnFilterGenerator(
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

	public BinaryColumnFilter loadPoso(BinaryColumnFilterDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		BinaryColumnFilter poso = entityManager.find(BinaryColumnFilter.class, id);
		return poso;
	}

	public BinaryColumnFilter instantiatePoso()  {
		BinaryColumnFilter poso = new BinaryColumnFilter();
		return poso;
	}

	public BinaryColumnFilter createPoso(BinaryColumnFilterDto dto)  throws ExpectedException {
		BinaryColumnFilter poso = new BinaryColumnFilter();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public BinaryColumnFilter createUnmanagedPoso(BinaryColumnFilterDto dto)  throws ExpectedException {
		BinaryColumnFilter poso = new BinaryColumnFilter();

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

	public void mergePoso(BinaryColumnFilterDto dto, final BinaryColumnFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(BinaryColumnFilterDto dto, final BinaryColumnFilter poso)  throws ExpectedException {
		/*  set columnA */
		ColumnDto tmpDto_columnA = dto.getColumnA();
		if(null != tmpDto_columnA && null != tmpDto_columnA.getId()){
			if(null != poso.getColumnA() && null != poso.getColumnA().getId() && poso.getColumnA().getId().equals(tmpDto_columnA.getId()))
				poso.setColumnA((Column)dtoServiceProvider.get().loadAndMergePoso(tmpDto_columnA));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (columnA)");
		} else if(null != poso.getColumnA()){
			Column newPropertyValue = (Column)dtoServiceProvider.get().createPoso(tmpDto_columnA);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getColumnA(), newPropertyValue, "columnA");
			poso.setColumnA(newPropertyValue);
		} else {
			poso.setColumnA((Column)dtoServiceProvider.get().createPoso(tmpDto_columnA));
		}

		/*  set columnB */
		ColumnDto tmpDto_columnB = dto.getColumnB();
		if(null != tmpDto_columnB && null != tmpDto_columnB.getId()){
			if(null != poso.getColumnB() && null != poso.getColumnB().getId() && poso.getColumnB().getId().equals(tmpDto_columnB.getId()))
				poso.setColumnB((Column)dtoServiceProvider.get().loadAndMergePoso(tmpDto_columnB));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (columnB)");
		} else if(null != poso.getColumnB()){
			Column newPropertyValue = (Column)dtoServiceProvider.get().createPoso(tmpDto_columnB);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getColumnB(), newPropertyValue, "columnB");
			poso.setColumnB(newPropertyValue);
		} else {
			poso.setColumnB((Column)dtoServiceProvider.get().createPoso(tmpDto_columnB));
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set operator */
		BinaryOperatorDto tmpDto_operator = dto.getOperator();
		poso.setOperator((BinaryOperator)dtoServiceProvider.get().createPoso(tmpDto_operator));

	}

	protected void mergeProxy2Poso(BinaryColumnFilterDto dto, final BinaryColumnFilter poso)  throws ExpectedException {
		/*  set columnA */
		if(dto.isColumnAModified()){
			ColumnDto tmpDto_columnA = dto.getColumnA();
			if(null != tmpDto_columnA && null != tmpDto_columnA.getId()){
				if(null != poso.getColumnA() && null != poso.getColumnA().getId() && poso.getColumnA().getId().equals(tmpDto_columnA.getId()))
					poso.setColumnA((Column)dtoServiceProvider.get().loadAndMergePoso(tmpDto_columnA));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (columnA)");
			} else if(null != poso.getColumnA()){
				Column newPropertyValue = (Column)dtoServiceProvider.get().createPoso(tmpDto_columnA);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getColumnA(), newPropertyValue, "columnA");
				poso.setColumnA(newPropertyValue);
			} else {
				poso.setColumnA((Column)dtoServiceProvider.get().createPoso(tmpDto_columnA));
			}
		}

		/*  set columnB */
		if(dto.isColumnBModified()){
			ColumnDto tmpDto_columnB = dto.getColumnB();
			if(null != tmpDto_columnB && null != tmpDto_columnB.getId()){
				if(null != poso.getColumnB() && null != poso.getColumnB().getId() && poso.getColumnB().getId().equals(tmpDto_columnB.getId()))
					poso.setColumnB((Column)dtoServiceProvider.get().loadAndMergePoso(tmpDto_columnB));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (columnB)");
			} else if(null != poso.getColumnB()){
				Column newPropertyValue = (Column)dtoServiceProvider.get().createPoso(tmpDto_columnB);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getColumnB(), newPropertyValue, "columnB");
				poso.setColumnB(newPropertyValue);
			} else {
				poso.setColumnB((Column)dtoServiceProvider.get().createPoso(tmpDto_columnB));
			}
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set operator */
		if(dto.isOperatorModified()){
			BinaryOperatorDto tmpDto_operator = dto.getOperator();
			poso.setOperator((BinaryOperator)dtoServiceProvider.get().createPoso(tmpDto_operator));
		}

	}

	public void mergeUnmanagedPoso(BinaryColumnFilterDto dto, final BinaryColumnFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(BinaryColumnFilterDto dto, final BinaryColumnFilter poso)  throws ExpectedException {
		/*  set columnA */
		ColumnDto tmpDto_columnA = dto.getColumnA();
		poso.setColumnA((Column)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_columnA));

		/*  set columnB */
		ColumnDto tmpDto_columnB = dto.getColumnB();
		poso.setColumnB((Column)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_columnB));

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set operator */
		BinaryOperatorDto tmpDto_operator = dto.getOperator();
		poso.setOperator((BinaryOperator)dtoServiceProvider.get().createPoso(tmpDto_operator));

	}

	protected void mergeProxy2UnmanagedPoso(BinaryColumnFilterDto dto, final BinaryColumnFilter poso)  throws ExpectedException {
		/*  set columnA */
		if(dto.isColumnAModified()){
			ColumnDto tmpDto_columnA = dto.getColumnA();
			poso.setColumnA((Column)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_columnA));
		}

		/*  set columnB */
		if(dto.isColumnBModified()){
			ColumnDto tmpDto_columnB = dto.getColumnB();
			poso.setColumnB((Column)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_columnB));
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set operator */
		if(dto.isOperatorModified()){
			BinaryOperatorDto tmpDto_operator = dto.getOperator();
			poso.setOperator((BinaryOperator)dtoServiceProvider.get().createPoso(tmpDto_operator));
		}

	}

	public BinaryColumnFilter loadAndMergePoso(BinaryColumnFilterDto dto)  throws ExpectedException {
		BinaryColumnFilter poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(BinaryColumnFilterDto dto, BinaryColumnFilter poso)  {
	}


	public void postProcessCreateUnmanaged(BinaryColumnFilterDto dto, BinaryColumnFilter poso)  {
	}


	public void postProcessLoad(BinaryColumnFilterDto dto, BinaryColumnFilter poso)  {
	}


	public void postProcessMerge(BinaryColumnFilterDto dto, BinaryColumnFilter poso)  {
	}


	public void postProcessInstantiate(BinaryColumnFilter poso)  {
	}



}
