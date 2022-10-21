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
import net.datenwerke.rs.base.client.reportengines.table.dto.BlockTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2PreFilterGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for PreFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2PreFilterGenerator implements Dto2PosoGenerator<PreFilterDto,PreFilter> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2PreFilterGenerator(
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

	public PreFilter loadPoso(PreFilterDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		PreFilter poso = entityManager.find(PreFilter.class, id);
		return poso;
	}

	public PreFilter instantiatePoso()  {
		PreFilter poso = new PreFilter();
		return poso;
	}

	public PreFilter createPoso(PreFilterDto dto)  throws ExpectedException {
		PreFilter poso = new PreFilter();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public PreFilter createUnmanagedPoso(PreFilterDto dto)  throws ExpectedException {
		PreFilter poso = new PreFilter();

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

	public void mergePoso(PreFilterDto dto, final PreFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(PreFilterDto dto, final PreFilter poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set rootBlock */
		FilterBlockDto tmpDto_rootBlock = dto.getRootBlock();
		if(null != tmpDto_rootBlock && null != tmpDto_rootBlock.getId()){
			if(null != poso.getRootBlock() && null != poso.getRootBlock().getId() && poso.getRootBlock().getId().equals(tmpDto_rootBlock.getId()))
				poso.setRootBlock((FilterBlock)dtoServiceProvider.get().loadAndMergePoso(tmpDto_rootBlock));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (rootBlock)");
		} else if(null != poso.getRootBlock()){
			FilterBlock newPropertyValue = (FilterBlock)dtoServiceProvider.get().createPoso(tmpDto_rootBlock);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getRootBlock(), newPropertyValue, "rootBlock");
			poso.setRootBlock(newPropertyValue);
		} else {
			poso.setRootBlock((FilterBlock)dtoServiceProvider.get().createPoso(tmpDto_rootBlock));
		}

		/*  set rootBlockType */
		BlockTypeDto tmpDto_rootBlockType = dto.getRootBlockType();
		poso.setRootBlockType((BlockType)dtoServiceProvider.get().createPoso(tmpDto_rootBlockType));

	}

	protected void mergeProxy2Poso(PreFilterDto dto, final PreFilter poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set rootBlock */
		if(dto.isRootBlockModified()){
			FilterBlockDto tmpDto_rootBlock = dto.getRootBlock();
			if(null != tmpDto_rootBlock && null != tmpDto_rootBlock.getId()){
				if(null != poso.getRootBlock() && null != poso.getRootBlock().getId() && poso.getRootBlock().getId().equals(tmpDto_rootBlock.getId()))
					poso.setRootBlock((FilterBlock)dtoServiceProvider.get().loadAndMergePoso(tmpDto_rootBlock));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (rootBlock)");
			} else if(null != poso.getRootBlock()){
				FilterBlock newPropertyValue = (FilterBlock)dtoServiceProvider.get().createPoso(tmpDto_rootBlock);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getRootBlock(), newPropertyValue, "rootBlock");
				poso.setRootBlock(newPropertyValue);
			} else {
				poso.setRootBlock((FilterBlock)dtoServiceProvider.get().createPoso(tmpDto_rootBlock));
			}
		}

		/*  set rootBlockType */
		if(dto.isRootBlockTypeModified()){
			BlockTypeDto tmpDto_rootBlockType = dto.getRootBlockType();
			poso.setRootBlockType((BlockType)dtoServiceProvider.get().createPoso(tmpDto_rootBlockType));
		}

	}

	public void mergeUnmanagedPoso(PreFilterDto dto, final PreFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(PreFilterDto dto, final PreFilter poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set rootBlock */
		FilterBlockDto tmpDto_rootBlock = dto.getRootBlock();
		poso.setRootBlock((FilterBlock)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_rootBlock));

		/*  set rootBlockType */
		BlockTypeDto tmpDto_rootBlockType = dto.getRootBlockType();
		poso.setRootBlockType((BlockType)dtoServiceProvider.get().createPoso(tmpDto_rootBlockType));

	}

	protected void mergeProxy2UnmanagedPoso(PreFilterDto dto, final PreFilter poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set rootBlock */
		if(dto.isRootBlockModified()){
			FilterBlockDto tmpDto_rootBlock = dto.getRootBlock();
			poso.setRootBlock((FilterBlock)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_rootBlock));
		}

		/*  set rootBlockType */
		if(dto.isRootBlockTypeModified()){
			BlockTypeDto tmpDto_rootBlockType = dto.getRootBlockType();
			poso.setRootBlockType((BlockType)dtoServiceProvider.get().createPoso(tmpDto_rootBlockType));
		}

	}

	public PreFilter loadAndMergePoso(PreFilterDto dto)  throws ExpectedException {
		PreFilter poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(PreFilterDto dto, PreFilter poso)  {
	}


	public void postProcessCreateUnmanaged(PreFilterDto dto, PreFilter poso)  {
	}


	public void postProcessLoad(PreFilterDto dto, PreFilter poso)  {
	}


	public void postProcessMerge(PreFilterDto dto, PreFilter poso)  {
	}


	public void postProcessInstantiate(PreFilter poso)  {
	}



}
