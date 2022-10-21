package net.datenwerke.rs.incubator.service.jaspertotable.entities.dtogen;

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
import net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto;
import net.datenwerke.rs.incubator.service.jaspertotable.entities.JasperToTableConfig;
import net.datenwerke.rs.incubator.service.jaspertotable.entities.dtogen.Dto2JasperToTableConfigGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for JasperToTableConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2JasperToTableConfigGenerator implements Dto2PosoGenerator<JasperToTableConfigDto,JasperToTableConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2JasperToTableConfigGenerator(
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

	public JasperToTableConfig loadPoso(JasperToTableConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		JasperToTableConfig poso = entityManager.find(JasperToTableConfig.class, id);
		return poso;
	}

	public JasperToTableConfig instantiatePoso()  {
		JasperToTableConfig poso = new JasperToTableConfig();
		return poso;
	}

	public JasperToTableConfig createPoso(JasperToTableConfigDto dto)  throws ExpectedException {
		JasperToTableConfig poso = new JasperToTableConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public JasperToTableConfig createUnmanagedPoso(JasperToTableConfigDto dto)  throws ExpectedException {
		JasperToTableConfig poso = new JasperToTableConfig();

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

	public void mergePoso(JasperToTableConfigDto dto, final JasperToTableConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(JasperToTableConfigDto dto, final JasperToTableConfig poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(JasperToTableConfigDto dto, final JasperToTableConfig poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(JasperToTableConfigDto dto, final JasperToTableConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(JasperToTableConfigDto dto, final JasperToTableConfig poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(JasperToTableConfigDto dto, final JasperToTableConfig poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public JasperToTableConfig loadAndMergePoso(JasperToTableConfigDto dto)  throws ExpectedException {
		JasperToTableConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(JasperToTableConfigDto dto, JasperToTableConfig poso)  {
	}


	public void postProcessCreateUnmanaged(JasperToTableConfigDto dto, JasperToTableConfig poso)  {
	}


	public void postProcessLoad(JasperToTableConfigDto dto, JasperToTableConfig poso)  {
	}


	public void postProcessMerge(JasperToTableConfigDto dto, JasperToTableConfig poso)  {
	}


	public void postProcessInstantiate(JasperToTableConfig poso)  {
	}



}
