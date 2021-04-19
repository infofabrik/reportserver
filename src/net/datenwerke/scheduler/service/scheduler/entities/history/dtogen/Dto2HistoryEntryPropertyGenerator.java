package net.datenwerke.scheduler.service.scheduler.entities.history.dtogen;

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
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto;
import net.datenwerke.scheduler.service.scheduler.entities.history.HistoryEntryProperty;
import net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2HistoryEntryPropertyGenerator;

/**
 * Dto2PosoGenerator for HistoryEntryProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2HistoryEntryPropertyGenerator implements Dto2PosoGenerator<HistoryEntryPropertyDto,HistoryEntryProperty> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2HistoryEntryPropertyGenerator(
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

	public HistoryEntryProperty loadPoso(HistoryEntryPropertyDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		HistoryEntryProperty poso = entityManager.find(HistoryEntryProperty.class, id);
		return poso;
	}

	public HistoryEntryProperty instantiatePoso()  {
		HistoryEntryProperty poso = new HistoryEntryProperty();
		return poso;
	}

	public HistoryEntryProperty createPoso(HistoryEntryPropertyDto dto)  throws ExpectedException {
		HistoryEntryProperty poso = new HistoryEntryProperty();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public HistoryEntryProperty createUnmanagedPoso(HistoryEntryPropertyDto dto)  throws ExpectedException {
		HistoryEntryProperty poso = new HistoryEntryProperty();

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

	public void mergePoso(HistoryEntryPropertyDto dto, final HistoryEntryProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(HistoryEntryPropertyDto dto, final HistoryEntryProperty poso)  throws ExpectedException {
		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2Poso(HistoryEntryPropertyDto dto, final HistoryEntryProperty poso)  throws ExpectedException {
		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public void mergeUnmanagedPoso(HistoryEntryPropertyDto dto, final HistoryEntryProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(HistoryEntryPropertyDto dto, final HistoryEntryProperty poso)  throws ExpectedException {
		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2UnmanagedPoso(HistoryEntryPropertyDto dto, final HistoryEntryProperty poso)  throws ExpectedException {
		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public HistoryEntryProperty loadAndMergePoso(HistoryEntryPropertyDto dto)  throws ExpectedException {
		HistoryEntryProperty poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(HistoryEntryPropertyDto dto, HistoryEntryProperty poso)  {
	}


	public void postProcessCreateUnmanaged(HistoryEntryPropertyDto dto, HistoryEntryProperty poso)  {
	}


	public void postProcessLoad(HistoryEntryPropertyDto dto, HistoryEntryProperty poso)  {
	}


	public void postProcessMerge(HistoryEntryPropertyDto dto, HistoryEntryProperty poso)  {
	}


	public void postProcessInstantiate(HistoryEntryProperty poso)  {
	}



}
