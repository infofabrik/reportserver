package net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatText;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatTextGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ColumnFormatText
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ColumnFormatTextGenerator implements Dto2PosoGenerator<ColumnFormatTextDto,ColumnFormatText> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ColumnFormatTextGenerator(
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

	public ColumnFormatText loadPoso(ColumnFormatTextDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ColumnFormatText poso = entityManager.find(ColumnFormatText.class, id);
		return poso;
	}

	public ColumnFormatText instantiatePoso()  {
		ColumnFormatText poso = new ColumnFormatText();
		return poso;
	}

	public ColumnFormatText createPoso(ColumnFormatTextDto dto)  throws ExpectedException {
		ColumnFormatText poso = new ColumnFormatText();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ColumnFormatText createUnmanagedPoso(ColumnFormatTextDto dto)  throws ExpectedException {
		ColumnFormatText poso = new ColumnFormatText();

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

	public void mergePoso(ColumnFormatTextDto dto, final ColumnFormatText poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ColumnFormatTextDto dto, final ColumnFormatText poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(ColumnFormatTextDto dto, final ColumnFormatText poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(ColumnFormatTextDto dto, final ColumnFormatText poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ColumnFormatTextDto dto, final ColumnFormatText poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(ColumnFormatTextDto dto, final ColumnFormatText poso)  throws ExpectedException {
	}

	public ColumnFormatText loadAndMergePoso(ColumnFormatTextDto dto)  throws ExpectedException {
		ColumnFormatText poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ColumnFormatTextDto dto, ColumnFormatText poso)  {
	}


	public void postProcessCreateUnmanaged(ColumnFormatTextDto dto, ColumnFormatText poso)  {
	}


	public void postProcessLoad(ColumnFormatTextDto dto, ColumnFormatText poso)  {
	}


	public void postProcessMerge(ColumnFormatTextDto dto, ColumnFormatText poso)  {
	}


	public void postProcessInstantiate(ColumnFormatText poso)  {
	}



}
