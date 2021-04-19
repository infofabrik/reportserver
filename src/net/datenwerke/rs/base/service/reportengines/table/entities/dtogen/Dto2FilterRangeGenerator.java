package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.Dto2FilterRangeGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FilterRange
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FilterRangeGenerator implements Dto2PosoGenerator<FilterRangeDto,FilterRange> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FilterRangeGenerator(
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

	public FilterRange loadPoso(FilterRangeDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		FilterRange poso = entityManager.find(FilterRange.class, id);
		return poso;
	}

	public FilterRange instantiatePoso()  {
		FilterRange poso = new FilterRange();
		return poso;
	}

	public FilterRange createPoso(FilterRangeDto dto)  throws ExpectedException {
		FilterRange poso = new FilterRange();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FilterRange createUnmanagedPoso(FilterRangeDto dto)  throws ExpectedException {
		FilterRange poso = new FilterRange();

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

	public void mergePoso(FilterRangeDto dto, final FilterRange poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FilterRangeDto dto, final FilterRange poso)  throws ExpectedException {
		/*  set rangeFrom */
		poso.setRangeFrom(dto.getRangeFrom() );

		/*  set rangeTo */
		poso.setRangeTo(dto.getRangeTo() );

	}

	protected void mergeProxy2Poso(FilterRangeDto dto, final FilterRange poso)  throws ExpectedException {
		/*  set rangeFrom */
		if(dto.isRangeFromModified()){
			poso.setRangeFrom(dto.getRangeFrom() );
		}

		/*  set rangeTo */
		if(dto.isRangeToModified()){
			poso.setRangeTo(dto.getRangeTo() );
		}

	}

	public void mergeUnmanagedPoso(FilterRangeDto dto, final FilterRange poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FilterRangeDto dto, final FilterRange poso)  throws ExpectedException {
		/*  set rangeFrom */
		poso.setRangeFrom(dto.getRangeFrom() );

		/*  set rangeTo */
		poso.setRangeTo(dto.getRangeTo() );

	}

	protected void mergeProxy2UnmanagedPoso(FilterRangeDto dto, final FilterRange poso)  throws ExpectedException {
		/*  set rangeFrom */
		if(dto.isRangeFromModified()){
			poso.setRangeFrom(dto.getRangeFrom() );
		}

		/*  set rangeTo */
		if(dto.isRangeToModified()){
			poso.setRangeTo(dto.getRangeTo() );
		}

	}

	public FilterRange loadAndMergePoso(FilterRangeDto dto)  throws ExpectedException {
		FilterRange poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FilterRangeDto dto, FilterRange poso)  {
	}


	public void postProcessCreateUnmanaged(FilterRangeDto dto, FilterRange poso)  {
	}


	public void postProcessLoad(FilterRangeDto dto, FilterRange poso)  {
	}


	public void postProcessMerge(FilterRangeDto dto, FilterRange poso)  {
	}


	public void postProcessInstantiate(FilterRange poso)  {
	}



}
