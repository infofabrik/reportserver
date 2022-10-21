package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.base.service.reportengines.table.entities.NullHandling;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2ColumnReferenceGenerator;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormat;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ColumnReference
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ColumnReferenceGenerator implements Dto2PosoGenerator<ColumnReferenceDto,ColumnReference> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.rs.base.service.reportengines.table.entities.post.ColumnDtoPost postProcessor_1;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ColumnReferenceGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		net.datenwerke.rs.base.service.reportengines.table.entities.post.ColumnDtoPost postProcessor_1,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.postProcessor_1 = postProcessor_1;
		this.reflectionService = reflectionService;
	}

	public ColumnReference loadPoso(ColumnReferenceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ColumnReference poso = entityManager.find(ColumnReference.class, id);
		return poso;
	}

	public ColumnReference instantiatePoso()  {
		ColumnReference poso = new ColumnReference();
		return poso;
	}

	public ColumnReference createPoso(ColumnReferenceDto dto)  throws ExpectedException {
		ColumnReference poso = new ColumnReference();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ColumnReference createUnmanagedPoso(ColumnReferenceDto dto)  throws ExpectedException {
		ColumnReference poso = new ColumnReference();

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

	public void mergePoso(ColumnReferenceDto dto, final ColumnReference poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ColumnReferenceDto dto, final ColumnReference poso)  throws ExpectedException {
		/*  set aggregateFunction */
		AggregateFunctionDto tmpDto_aggregateFunction = dto.getAggregateFunction();
		poso.setAggregateFunction((AggregateFunction)dtoServiceProvider.get().createPoso(tmpDto_aggregateFunction));

		/*  set alias */
		poso.setAlias(dto.getAlias() );

		/*  set defaultAlias */
		poso.setDefaultAlias(dto.getDefaultAlias() );

		/*  set defaultPreviewWidth */
		poso.setDefaultPreviewWidth(dto.getDefaultPreviewWidth() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set dimension */
		poso.setDimension(dto.getDimension() );

		/*  set filter */
		FilterDto tmpDto_filter = dto.getFilter();
		if(null != tmpDto_filter && null != tmpDto_filter.getId()){
			if(null != poso.getFilter() && null != poso.getFilter().getId() && poso.getFilter().getId().equals(tmpDto_filter.getId()))
				poso.setFilter((Filter)dtoServiceProvider.get().loadAndMergePoso(tmpDto_filter));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (filter)");
		} else if(null != poso.getFilter()){
			Filter newPropertyValue = (Filter)dtoServiceProvider.get().createPoso(tmpDto_filter);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getFilter(), newPropertyValue, "filter");
			poso.setFilter(newPropertyValue);
		} else {
			poso.setFilter((Filter)dtoServiceProvider.get().createPoso(tmpDto_filter));
		}

		/*  set format */
		ColumnFormatDto tmpDto_format = dto.getFormat();
		if(null != tmpDto_format && null != tmpDto_format.getId()){
			if(null != poso.getFormat() && null != poso.getFormat().getId() && poso.getFormat().getId().equals(tmpDto_format.getId()))
				poso.setFormat((ColumnFormat)dtoServiceProvider.get().loadAndMergePoso(tmpDto_format));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (format)");
		} else if(null != poso.getFormat()){
			ColumnFormat newPropertyValue = (ColumnFormat)dtoServiceProvider.get().createPoso(tmpDto_format);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getFormat(), newPropertyValue, "format");
			poso.setFormat(newPropertyValue);
		} else {
			poso.setFormat((ColumnFormat)dtoServiceProvider.get().createPoso(tmpDto_format));
		}

		/*  set hidden */
		poso.setHidden(dto.isHidden() );

		/*  set indexColumn */
		try{
			poso.setIndexColumn(dto.isIndexColumn() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set nullHandling */
		NullHandlingDto tmpDto_nullHandling = dto.getNullHandling();
		poso.setNullHandling((NullHandling)dtoServiceProvider.get().createPoso(tmpDto_nullHandling));

		/*  set nullReplacementFormat */
		poso.setNullReplacementFormat(dto.getNullReplacementFormat() );

		/*  set order */
		OrderDto tmpDto_order = dto.getOrder();
		poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));

		/*  set previewWidth */
		poso.setPreviewWidth(dto.getPreviewWidth() );

		/*  set reference */
		AdditionalColumnSpecDto tmpDto_reference = dto.getReference();
		if(null != tmpDto_reference && null != tmpDto_reference.getId()){
			if(null != poso.getReference() && null != poso.getReference().getId() && ! poso.getReference().getId().equals(tmpDto_reference.getId())){
				AdditionalColumnSpec newPropertyValue = (AdditionalColumnSpec)dtoServiceProvider.get().loadPoso(tmpDto_reference);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReference(), newPropertyValue, "reference");
				poso.setReference(newPropertyValue);
			} else if(null == poso.getReference()){
				poso.setReference((AdditionalColumnSpec)dtoServiceProvider.get().loadPoso(tmpDto_reference));
			}
		} else if(null != tmpDto_reference && null == tmpDto_reference.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (reference)");
					poso.setReference((AdditionalColumnSpec)refPoso);
				}
			});
		} else if(null == tmpDto_reference){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReference(), null, "reference");
			poso.setReference(null);
		}

		/*  set semanticType */
		poso.setSemanticType(dto.getSemanticType() );

		/*  set subtotalGroup */
		poso.setSubtotalGroup(dto.isSubtotalGroup() );

		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2Poso(ColumnReferenceDto dto, final ColumnReference poso)  throws ExpectedException {
		/*  set aggregateFunction */
		if(dto.isAggregateFunctionModified()){
			AggregateFunctionDto tmpDto_aggregateFunction = dto.getAggregateFunction();
			poso.setAggregateFunction((AggregateFunction)dtoServiceProvider.get().createPoso(tmpDto_aggregateFunction));
		}

		/*  set alias */
		if(dto.isAliasModified()){
			poso.setAlias(dto.getAlias() );
		}

		/*  set defaultAlias */
		if(dto.isDefaultAliasModified()){
			poso.setDefaultAlias(dto.getDefaultAlias() );
		}

		/*  set defaultPreviewWidth */
		if(dto.isDefaultPreviewWidthModified()){
			poso.setDefaultPreviewWidth(dto.getDefaultPreviewWidth() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set dimension */
		if(dto.isDimensionModified()){
			poso.setDimension(dto.getDimension() );
		}

		/*  set filter */
		if(dto.isFilterModified()){
			FilterDto tmpDto_filter = dto.getFilter();
			if(null != tmpDto_filter && null != tmpDto_filter.getId()){
				if(null != poso.getFilter() && null != poso.getFilter().getId() && poso.getFilter().getId().equals(tmpDto_filter.getId()))
					poso.setFilter((Filter)dtoServiceProvider.get().loadAndMergePoso(tmpDto_filter));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (filter)");
			} else if(null != poso.getFilter()){
				Filter newPropertyValue = (Filter)dtoServiceProvider.get().createPoso(tmpDto_filter);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getFilter(), newPropertyValue, "filter");
				poso.setFilter(newPropertyValue);
			} else {
				poso.setFilter((Filter)dtoServiceProvider.get().createPoso(tmpDto_filter));
			}
		}

		/*  set format */
		if(dto.isFormatModified()){
			ColumnFormatDto tmpDto_format = dto.getFormat();
			if(null != tmpDto_format && null != tmpDto_format.getId()){
				if(null != poso.getFormat() && null != poso.getFormat().getId() && poso.getFormat().getId().equals(tmpDto_format.getId()))
					poso.setFormat((ColumnFormat)dtoServiceProvider.get().loadAndMergePoso(tmpDto_format));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (format)");
			} else if(null != poso.getFormat()){
				ColumnFormat newPropertyValue = (ColumnFormat)dtoServiceProvider.get().createPoso(tmpDto_format);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getFormat(), newPropertyValue, "format");
				poso.setFormat(newPropertyValue);
			} else {
				poso.setFormat((ColumnFormat)dtoServiceProvider.get().createPoso(tmpDto_format));
			}
		}

		/*  set hidden */
		if(dto.isHiddenModified()){
			poso.setHidden(dto.isHidden() );
		}

		/*  set indexColumn */
		if(dto.isIndexColumnModified()){
			try{
				poso.setIndexColumn(dto.isIndexColumn() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set nullHandling */
		if(dto.isNullHandlingModified()){
			NullHandlingDto tmpDto_nullHandling = dto.getNullHandling();
			poso.setNullHandling((NullHandling)dtoServiceProvider.get().createPoso(tmpDto_nullHandling));
		}

		/*  set nullReplacementFormat */
		if(dto.isNullReplacementFormatModified()){
			poso.setNullReplacementFormat(dto.getNullReplacementFormat() );
		}

		/*  set order */
		if(dto.isOrderModified()){
			OrderDto tmpDto_order = dto.getOrder();
			poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));
		}

		/*  set previewWidth */
		if(dto.isPreviewWidthModified()){
			poso.setPreviewWidth(dto.getPreviewWidth() );
		}

		/*  set reference */
		if(dto.isReferenceModified()){
			AdditionalColumnSpecDto tmpDto_reference = dto.getReference();
			if(null != tmpDto_reference && null != tmpDto_reference.getId()){
				if(null != poso.getReference() && null != poso.getReference().getId() && ! poso.getReference().getId().equals(tmpDto_reference.getId())){
					AdditionalColumnSpec newPropertyValue = (AdditionalColumnSpec)dtoServiceProvider.get().loadPoso(tmpDto_reference);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReference(), newPropertyValue, "reference");
					poso.setReference(newPropertyValue);
				} else if(null == poso.getReference()){
					poso.setReference((AdditionalColumnSpec)dtoServiceProvider.get().loadPoso(tmpDto_reference));
				}
			} else if(null != tmpDto_reference && null == tmpDto_reference.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (reference)");
						poso.setReference((AdditionalColumnSpec)refPoso);
					}
			});
			} else if(null == tmpDto_reference){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReference(), null, "reference");
				poso.setReference(null);
			}
		}

		/*  set semanticType */
		if(dto.isSemanticTypeModified()){
			poso.setSemanticType(dto.getSemanticType() );
		}

		/*  set subtotalGroup */
		if(dto.isSubtotalGroupModified()){
			poso.setSubtotalGroup(dto.isSubtotalGroup() );
		}

		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public void mergeUnmanagedPoso(ColumnReferenceDto dto, final ColumnReference poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ColumnReferenceDto dto, final ColumnReference poso)  throws ExpectedException {
		/*  set aggregateFunction */
		AggregateFunctionDto tmpDto_aggregateFunction = dto.getAggregateFunction();
		poso.setAggregateFunction((AggregateFunction)dtoServiceProvider.get().createPoso(tmpDto_aggregateFunction));

		/*  set alias */
		poso.setAlias(dto.getAlias() );

		/*  set defaultAlias */
		poso.setDefaultAlias(dto.getDefaultAlias() );

		/*  set defaultPreviewWidth */
		poso.setDefaultPreviewWidth(dto.getDefaultPreviewWidth() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set dimension */
		poso.setDimension(dto.getDimension() );

		/*  set filter */
		FilterDto tmpDto_filter = dto.getFilter();
		poso.setFilter((Filter)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_filter));

		/*  set format */
		ColumnFormatDto tmpDto_format = dto.getFormat();
		poso.setFormat((ColumnFormat)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_format));

		/*  set hidden */
		poso.setHidden(dto.isHidden() );

		/*  set indexColumn */
		try{
			poso.setIndexColumn(dto.isIndexColumn() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set nullHandling */
		NullHandlingDto tmpDto_nullHandling = dto.getNullHandling();
		poso.setNullHandling((NullHandling)dtoServiceProvider.get().createPoso(tmpDto_nullHandling));

		/*  set nullReplacementFormat */
		poso.setNullReplacementFormat(dto.getNullReplacementFormat() );

		/*  set order */
		OrderDto tmpDto_order = dto.getOrder();
		poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));

		/*  set previewWidth */
		poso.setPreviewWidth(dto.getPreviewWidth() );

		/*  set reference */
		AdditionalColumnSpecDto tmpDto_reference = dto.getReference();
		if(null != tmpDto_reference && null != tmpDto_reference.getId()){
			AdditionalColumnSpec newPropertyValue = (AdditionalColumnSpec)dtoServiceProvider.get().loadPoso(tmpDto_reference);
			poso.setReference(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setReference((AdditionalColumnSpec)refPoso);
				}
			});
		} else if(null != tmpDto_reference && null == tmpDto_reference.getId()){
			final AdditionalColumnSpecDto tmpDto_reference_final = tmpDto_reference;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setReference((AdditionalColumnSpec)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_reference_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setReference((AdditionalColumnSpec)refPoso);
					}
				}
			});
		} else if(null == tmpDto_reference){
			poso.setReference(null);
		}

		/*  set semanticType */
		poso.setSemanticType(dto.getSemanticType() );

		/*  set subtotalGroup */
		poso.setSubtotalGroup(dto.isSubtotalGroup() );

		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2UnmanagedPoso(ColumnReferenceDto dto, final ColumnReference poso)  throws ExpectedException {
		/*  set aggregateFunction */
		if(dto.isAggregateFunctionModified()){
			AggregateFunctionDto tmpDto_aggregateFunction = dto.getAggregateFunction();
			poso.setAggregateFunction((AggregateFunction)dtoServiceProvider.get().createPoso(tmpDto_aggregateFunction));
		}

		/*  set alias */
		if(dto.isAliasModified()){
			poso.setAlias(dto.getAlias() );
		}

		/*  set defaultAlias */
		if(dto.isDefaultAliasModified()){
			poso.setDefaultAlias(dto.getDefaultAlias() );
		}

		/*  set defaultPreviewWidth */
		if(dto.isDefaultPreviewWidthModified()){
			poso.setDefaultPreviewWidth(dto.getDefaultPreviewWidth() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set dimension */
		if(dto.isDimensionModified()){
			poso.setDimension(dto.getDimension() );
		}

		/*  set filter */
		if(dto.isFilterModified()){
			FilterDto tmpDto_filter = dto.getFilter();
			poso.setFilter((Filter)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_filter));
		}

		/*  set format */
		if(dto.isFormatModified()){
			ColumnFormatDto tmpDto_format = dto.getFormat();
			poso.setFormat((ColumnFormat)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_format));
		}

		/*  set hidden */
		if(dto.isHiddenModified()){
			poso.setHidden(dto.isHidden() );
		}

		/*  set indexColumn */
		if(dto.isIndexColumnModified()){
			try{
				poso.setIndexColumn(dto.isIndexColumn() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set nullHandling */
		if(dto.isNullHandlingModified()){
			NullHandlingDto tmpDto_nullHandling = dto.getNullHandling();
			poso.setNullHandling((NullHandling)dtoServiceProvider.get().createPoso(tmpDto_nullHandling));
		}

		/*  set nullReplacementFormat */
		if(dto.isNullReplacementFormatModified()){
			poso.setNullReplacementFormat(dto.getNullReplacementFormat() );
		}

		/*  set order */
		if(dto.isOrderModified()){
			OrderDto tmpDto_order = dto.getOrder();
			poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));
		}

		/*  set previewWidth */
		if(dto.isPreviewWidthModified()){
			poso.setPreviewWidth(dto.getPreviewWidth() );
		}

		/*  set reference */
		if(dto.isReferenceModified()){
			AdditionalColumnSpecDto tmpDto_reference = dto.getReference();
			if(null != tmpDto_reference && null != tmpDto_reference.getId()){
				AdditionalColumnSpec newPropertyValue = (AdditionalColumnSpec)dtoServiceProvider.get().loadPoso(tmpDto_reference);
				poso.setReference(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setReference((AdditionalColumnSpec)refPoso);
					}
			});
			} else if(null != tmpDto_reference && null == tmpDto_reference.getId()){
				final AdditionalColumnSpecDto tmpDto_reference_final = tmpDto_reference;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setReference((AdditionalColumnSpec)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_reference_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setReference((AdditionalColumnSpec)refPoso);
						}
					}
			});
			} else if(null == tmpDto_reference){
				poso.setReference(null);
			}
		}

		/*  set semanticType */
		if(dto.isSemanticTypeModified()){
			poso.setSemanticType(dto.getSemanticType() );
		}

		/*  set subtotalGroup */
		if(dto.isSubtotalGroupModified()){
			poso.setSubtotalGroup(dto.isSubtotalGroup() );
		}

		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public ColumnReference loadAndMergePoso(ColumnReferenceDto dto)  throws ExpectedException {
		ColumnReference poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ColumnReferenceDto dto, ColumnReference poso)  {

		this.postProcessor_1.posoCreated(dto, poso);
	}


	public void postProcessCreateUnmanaged(ColumnReferenceDto dto, ColumnReference poso)  {

		this.postProcessor_1.posoCreatedUnmanaged(dto, poso);
	}


	public void postProcessLoad(ColumnReferenceDto dto, ColumnReference poso)  {

		this.postProcessor_1.posoLoaded(dto, poso);
	}


	public void postProcessMerge(ColumnReferenceDto dto, ColumnReference poso)  {

		this.postProcessor_1.posoMerged(dto, poso);
	}


	public void postProcessInstantiate(ColumnReference poso)  {

		this.postProcessor_1.posoInstantiated(poso);
	}



}
