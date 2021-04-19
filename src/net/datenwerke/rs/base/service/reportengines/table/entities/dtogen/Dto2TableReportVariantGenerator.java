package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2TableReportVariantGenerator;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TableReportVariant
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TableReportVariantGenerator implements Dto2PosoGenerator<TableReportVariantDto,TableReportVariant> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.rs.core.service.reportmanager.entities.reports.post.Dto2ReportPostProcessor postProcessor_1;

	private final net.datenwerke.rs.base.service.reportengines.table.entities.post.Dto2TableReportPost postProcessor_2;

	private final net.datenwerke.rs.base.service.reportengines.table.entities.supervisor.Dto2TableReportSupervisor dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TableReportVariantGenerator(
		net.datenwerke.rs.base.service.reportengines.table.entities.supervisor.Dto2TableReportSupervisor dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		net.datenwerke.rs.core.service.reportmanager.entities.reports.post.Dto2ReportPostProcessor postProcessor_1,
		net.datenwerke.rs.base.service.reportengines.table.entities.post.Dto2TableReportPost postProcessor_2,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.reflectionService = reflectionService;
	}

	public TableReportVariant loadPoso(TableReportVariantDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TableReportVariant poso = entityManager.find(TableReportVariant.class, id);
		return poso;
	}

	public TableReportVariant instantiatePoso()  {
		TableReportVariant poso = new TableReportVariant();
		return poso;
	}

	public TableReportVariant createPoso(TableReportVariantDto dto)  throws ExpectedException {
		TableReportVariant poso = new TableReportVariant();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TableReportVariant createUnmanagedPoso(TableReportVariantDto dto)  throws ExpectedException {
		TableReportVariant poso = new TableReportVariant();

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

	public void mergePoso(TableReportVariantDto dto, final TableReportVariant poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TableReportVariantDto dto, final TableReportVariant poso)  throws ExpectedException {
		/*  set additionalColumns */
		final List<AdditionalColumnSpec> col_additionalColumns = new ArrayList<AdditionalColumnSpec>();
		/* load new data from dto */
		Collection<AdditionalColumnSpecDto> tmpCol_additionalColumns = dto.getAdditionalColumns();

		/* load current data from poso */
		if(null != poso.getAdditionalColumns())
			col_additionalColumns.addAll(poso.getAdditionalColumns());

		/* remove non existing data */
		List<AdditionalColumnSpec> remDto_additionalColumns = new ArrayList<AdditionalColumnSpec>();
		for(AdditionalColumnSpec ref : col_additionalColumns){
			boolean found = false;
			for(AdditionalColumnSpecDto refDto : tmpCol_additionalColumns){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_additionalColumns.add(ref);
		}
		for(AdditionalColumnSpec ref : remDto_additionalColumns)
			col_additionalColumns.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_additionalColumns, "additionalColumns");

		/* merge or add data */
		List<AdditionalColumnSpec> new_col_additionalColumns = new ArrayList<AdditionalColumnSpec>();
		for(AdditionalColumnSpecDto refDto : tmpCol_additionalColumns){
			boolean found = false;
			for(AdditionalColumnSpec ref : col_additionalColumns){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_additionalColumns.add((AdditionalColumnSpec) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_additionalColumns.add((AdditionalColumnSpec) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(additionalColumns) ");
		}
		poso.setAdditionalColumns(new_col_additionalColumns);

		/*  set allowCubification */
		try{
			poso.setAllowCubification(dto.isAllowCubification() );
		} catch(NullPointerException e){
		}

		/*  set allowMdx */
		try{
			poso.setAllowMdx(dto.isAllowMdx() );
		} catch(NullPointerException e){
		}

		/*  set columns */
		final List<Column> col_columns = new ArrayList<Column>();
		/* load new data from dto */
		Collection<ColumnDto> tmpCol_columns = dto.getColumns();

		/* load current data from poso */
		if(null != poso.getColumns())
			col_columns.addAll(poso.getColumns());

		/* remove non existing data */
		List<Column> remDto_columns = new ArrayList<Column>();
		for(Column ref : col_columns){
			boolean found = false;
			for(ColumnDto refDto : tmpCol_columns){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_columns.add(ref);
		}
		for(Column ref : remDto_columns)
			col_columns.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_columns, "columns");

		/* merge or add data */
		List<Column> new_col_columns = new ArrayList<Column>();
		for(ColumnDto refDto : tmpCol_columns){
			boolean found = false;
			for(Column ref : col_columns){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_columns.add((Column) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_columns.add((Column) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(columns) ");
		}
		poso.setColumns(new_col_columns);

		/*  set cubeFlag */
		try{
			poso.setCubeFlag(dto.isCubeFlag() );
		} catch(NullPointerException e){
		}

		/*  set cubeXml */
		poso.setCubeXml(dto.getCubeXml() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set distinctFlag */
		poso.setDistinctFlag(dto.isDistinctFlag() );

		/*  set enableSubtotals */
		try{
			poso.setEnableSubtotals(dto.isEnableSubtotals() );
		} catch(NullPointerException e){
		}

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set hideParents */
		try{
			poso.setHideParents(dto.isHideParents() );
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

		/*  set preFilter */
		PreFilterDto tmpDto_preFilter = dto.getPreFilter();
		if(null != tmpDto_preFilter && null != tmpDto_preFilter.getId()){
			if(null != poso.getPreFilter() && null != poso.getPreFilter().getId() && poso.getPreFilter().getId().equals(tmpDto_preFilter.getId()))
				poso.setPreFilter((PreFilter)dtoServiceProvider.get().loadAndMergePoso(tmpDto_preFilter));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (preFilter)");
		} else if(null != poso.getPreFilter()){
			PreFilter newPropertyValue = (PreFilter)dtoServiceProvider.get().createPoso(tmpDto_preFilter);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getPreFilter(), newPropertyValue, "preFilter");
			poso.setPreFilter(newPropertyValue);
		} else {
			poso.setPreFilter((PreFilter)dtoServiceProvider.get().createPoso(tmpDto_preFilter));
		}

	}

	protected void mergeProxy2Poso(TableReportVariantDto dto, final TableReportVariant poso)  throws ExpectedException {
		/*  set additionalColumns */
		if(dto.isAdditionalColumnsModified()){
			final List<AdditionalColumnSpec> col_additionalColumns = new ArrayList<AdditionalColumnSpec>();
			/* load new data from dto */
			Collection<AdditionalColumnSpecDto> tmpCol_additionalColumns = null;
			tmpCol_additionalColumns = dto.getAdditionalColumns();

			/* load current data from poso */
			if(null != poso.getAdditionalColumns())
				col_additionalColumns.addAll(poso.getAdditionalColumns());

			/* remove non existing data */
			List<AdditionalColumnSpec> remDto_additionalColumns = new ArrayList<AdditionalColumnSpec>();
			for(AdditionalColumnSpec ref : col_additionalColumns){
				boolean found = false;
				for(AdditionalColumnSpecDto refDto : tmpCol_additionalColumns){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_additionalColumns.add(ref);
			}
			for(AdditionalColumnSpec ref : remDto_additionalColumns)
				col_additionalColumns.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_additionalColumns, "additionalColumns");

			/* merge or add data */
			List<AdditionalColumnSpec> new_col_additionalColumns = new ArrayList<AdditionalColumnSpec>();
			for(AdditionalColumnSpecDto refDto : tmpCol_additionalColumns){
				boolean found = false;
				for(AdditionalColumnSpec ref : col_additionalColumns){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_additionalColumns.add((AdditionalColumnSpec) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_additionalColumns.add((AdditionalColumnSpec) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(additionalColumns) ");
			}
			poso.setAdditionalColumns(new_col_additionalColumns);
		}

		/*  set allowCubification */
		if(dto.isAllowCubificationModified()){
			try{
				poso.setAllowCubification(dto.isAllowCubification() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowMdx */
		if(dto.isAllowMdxModified()){
			try{
				poso.setAllowMdx(dto.isAllowMdx() );
			} catch(NullPointerException e){
			}
		}

		/*  set columns */
		if(dto.isColumnsModified()){
			final List<Column> col_columns = new ArrayList<Column>();
			/* load new data from dto */
			Collection<ColumnDto> tmpCol_columns = null;
			tmpCol_columns = dto.getColumns();

			/* load current data from poso */
			if(null != poso.getColumns())
				col_columns.addAll(poso.getColumns());

			/* remove non existing data */
			List<Column> remDto_columns = new ArrayList<Column>();
			for(Column ref : col_columns){
				boolean found = false;
				for(ColumnDto refDto : tmpCol_columns){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_columns.add(ref);
			}
			for(Column ref : remDto_columns)
				col_columns.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_columns, "columns");

			/* merge or add data */
			List<Column> new_col_columns = new ArrayList<Column>();
			for(ColumnDto refDto : tmpCol_columns){
				boolean found = false;
				for(Column ref : col_columns){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_columns.add((Column) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_columns.add((Column) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(columns) ");
			}
			poso.setColumns(new_col_columns);
		}

		/*  set cubeFlag */
		if(dto.isCubeFlagModified()){
			try{
				poso.setCubeFlag(dto.isCubeFlag() );
			} catch(NullPointerException e){
			}
		}

		/*  set cubeXml */
		if(dto.isCubeXmlModified()){
			poso.setCubeXml(dto.getCubeXml() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set distinctFlag */
		if(dto.isDistinctFlagModified()){
			poso.setDistinctFlag(dto.isDistinctFlag() );
		}

		/*  set enableSubtotals */
		if(dto.isEnableSubtotalsModified()){
			try{
				poso.setEnableSubtotals(dto.isEnableSubtotals() );
			} catch(NullPointerException e){
			}
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set hideParents */
		if(dto.isHideParentsModified()){
			try{
				poso.setHideParents(dto.isHideParents() );
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

		/*  set preFilter */
		if(dto.isPreFilterModified()){
			PreFilterDto tmpDto_preFilter = dto.getPreFilter();
			if(null != tmpDto_preFilter && null != tmpDto_preFilter.getId()){
				if(null != poso.getPreFilter() && null != poso.getPreFilter().getId() && poso.getPreFilter().getId().equals(tmpDto_preFilter.getId()))
					poso.setPreFilter((PreFilter)dtoServiceProvider.get().loadAndMergePoso(tmpDto_preFilter));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (preFilter)");
			} else if(null != poso.getPreFilter()){
				PreFilter newPropertyValue = (PreFilter)dtoServiceProvider.get().createPoso(tmpDto_preFilter);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getPreFilter(), newPropertyValue, "preFilter");
				poso.setPreFilter(newPropertyValue);
			} else {
				poso.setPreFilter((PreFilter)dtoServiceProvider.get().createPoso(tmpDto_preFilter));
			}
		}

	}

	public void mergeUnmanagedPoso(TableReportVariantDto dto, final TableReportVariant poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TableReportVariantDto dto, final TableReportVariant poso)  throws ExpectedException {
		/*  set additionalColumns */
		final List<AdditionalColumnSpec> col_additionalColumns = new ArrayList<AdditionalColumnSpec>();
		/* load new data from dto */
		Collection<AdditionalColumnSpecDto> tmpCol_additionalColumns = dto.getAdditionalColumns();

		/* merge or add data */
		for(AdditionalColumnSpecDto refDto : tmpCol_additionalColumns){
			col_additionalColumns.add((AdditionalColumnSpec) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setAdditionalColumns(col_additionalColumns);

		/*  set allowCubification */
		try{
			poso.setAllowCubification(dto.isAllowCubification() );
		} catch(NullPointerException e){
		}

		/*  set allowMdx */
		try{
			poso.setAllowMdx(dto.isAllowMdx() );
		} catch(NullPointerException e){
		}

		/*  set columns */
		final List<Column> col_columns = new ArrayList<Column>();
		/* load new data from dto */
		Collection<ColumnDto> tmpCol_columns = dto.getColumns();

		/* merge or add data */
		for(ColumnDto refDto : tmpCol_columns){
			col_columns.add((Column) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setColumns(col_columns);

		/*  set cubeFlag */
		try{
			poso.setCubeFlag(dto.isCubeFlag() );
		} catch(NullPointerException e){
		}

		/*  set cubeXml */
		poso.setCubeXml(dto.getCubeXml() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set distinctFlag */
		poso.setDistinctFlag(dto.isDistinctFlag() );

		/*  set enableSubtotals */
		try{
			poso.setEnableSubtotals(dto.isEnableSubtotals() );
		} catch(NullPointerException e){
		}

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set hideParents */
		try{
			poso.setHideParents(dto.isHideParents() );
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

		/*  set preFilter */
		PreFilterDto tmpDto_preFilter = dto.getPreFilter();
		poso.setPreFilter((PreFilter)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_preFilter));

	}

	protected void mergeProxy2UnmanagedPoso(TableReportVariantDto dto, final TableReportVariant poso)  throws ExpectedException {
		/*  set additionalColumns */
		if(dto.isAdditionalColumnsModified()){
			final List<AdditionalColumnSpec> col_additionalColumns = new ArrayList<AdditionalColumnSpec>();
			/* load new data from dto */
			Collection<AdditionalColumnSpecDto> tmpCol_additionalColumns = null;
			tmpCol_additionalColumns = dto.getAdditionalColumns();

			/* merge or add data */
			for(AdditionalColumnSpecDto refDto : tmpCol_additionalColumns){
				col_additionalColumns.add((AdditionalColumnSpec) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setAdditionalColumns(col_additionalColumns);
		}

		/*  set allowCubification */
		if(dto.isAllowCubificationModified()){
			try{
				poso.setAllowCubification(dto.isAllowCubification() );
			} catch(NullPointerException e){
			}
		}

		/*  set allowMdx */
		if(dto.isAllowMdxModified()){
			try{
				poso.setAllowMdx(dto.isAllowMdx() );
			} catch(NullPointerException e){
			}
		}

		/*  set columns */
		if(dto.isColumnsModified()){
			final List<Column> col_columns = new ArrayList<Column>();
			/* load new data from dto */
			Collection<ColumnDto> tmpCol_columns = null;
			tmpCol_columns = dto.getColumns();

			/* merge or add data */
			for(ColumnDto refDto : tmpCol_columns){
				col_columns.add((Column) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setColumns(col_columns);
		}

		/*  set cubeFlag */
		if(dto.isCubeFlagModified()){
			try{
				poso.setCubeFlag(dto.isCubeFlag() );
			} catch(NullPointerException e){
			}
		}

		/*  set cubeXml */
		if(dto.isCubeXmlModified()){
			poso.setCubeXml(dto.getCubeXml() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set distinctFlag */
		if(dto.isDistinctFlagModified()){
			poso.setDistinctFlag(dto.isDistinctFlag() );
		}

		/*  set enableSubtotals */
		if(dto.isEnableSubtotalsModified()){
			try{
				poso.setEnableSubtotals(dto.isEnableSubtotals() );
			} catch(NullPointerException e){
			}
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set hideParents */
		if(dto.isHideParentsModified()){
			try{
				poso.setHideParents(dto.isHideParents() );
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

		/*  set preFilter */
		if(dto.isPreFilterModified()){
			PreFilterDto tmpDto_preFilter = dto.getPreFilter();
			poso.setPreFilter((PreFilter)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_preFilter));
		}

	}

	public TableReportVariant loadAndMergePoso(TableReportVariantDto dto)  throws ExpectedException {
		TableReportVariant poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TableReportVariantDto dto, TableReportVariant poso)  {

		this.postProcessor_1.posoCreated(dto, poso);
		this.postProcessor_2.posoCreated(dto, poso);
	}


	public void postProcessCreateUnmanaged(TableReportVariantDto dto, TableReportVariant poso)  {

		this.postProcessor_1.posoCreatedUnmanaged(dto, poso);
		this.postProcessor_2.posoCreatedUnmanaged(dto, poso);
	}


	public void postProcessLoad(TableReportVariantDto dto, TableReportVariant poso)  {

		this.postProcessor_1.posoLoaded(dto, poso);
		this.postProcessor_2.posoLoaded(dto, poso);
	}


	public void postProcessMerge(TableReportVariantDto dto, TableReportVariant poso)  {

		this.postProcessor_1.posoMerged(dto, poso);
		this.postProcessor_2.posoMerged(dto, poso);
	}


	public void postProcessInstantiate(TableReportVariant poso)  {

		this.postProcessor_1.posoInstantiated(poso);
		this.postProcessor_2.posoInstantiated(poso);
	}


	public boolean validateKeyProperty(TableReportVariantDto dto, TableReportVariant poso)  throws ExpectedException {
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
