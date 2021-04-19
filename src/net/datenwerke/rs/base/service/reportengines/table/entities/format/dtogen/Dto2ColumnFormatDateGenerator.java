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
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatDateGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ColumnFormatDate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ColumnFormatDateGenerator implements Dto2PosoGenerator<ColumnFormatDateDto,ColumnFormatDate> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ColumnFormatDateGenerator(
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

	public ColumnFormatDate loadPoso(ColumnFormatDateDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ColumnFormatDate poso = entityManager.find(ColumnFormatDate.class, id);
		return poso;
	}

	public ColumnFormatDate instantiatePoso()  {
		ColumnFormatDate poso = new ColumnFormatDate();
		return poso;
	}

	public ColumnFormatDate createPoso(ColumnFormatDateDto dto)  throws ExpectedException {
		ColumnFormatDate poso = new ColumnFormatDate();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ColumnFormatDate createUnmanagedPoso(ColumnFormatDateDto dto)  throws ExpectedException {
		ColumnFormatDate poso = new ColumnFormatDate();

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

	public void mergePoso(ColumnFormatDateDto dto, final ColumnFormatDate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ColumnFormatDateDto dto, final ColumnFormatDate poso)  throws ExpectedException {
		/*  set baseFormat */
		poso.setBaseFormat(dto.getBaseFormat() );

		/*  set errorReplacement */
		poso.setErrorReplacement(dto.getErrorReplacement() );

		/*  set replaceErrors */
		poso.setReplaceErrors(dto.isReplaceErrors() );

		/*  set rollOver */
		poso.setRollOver(dto.isRollOver() );

		/*  set targetFormat */
		poso.setTargetFormat(dto.getTargetFormat() );

	}

	protected void mergeProxy2Poso(ColumnFormatDateDto dto, final ColumnFormatDate poso)  throws ExpectedException {
		/*  set baseFormat */
		if(dto.isBaseFormatModified()){
			poso.setBaseFormat(dto.getBaseFormat() );
		}

		/*  set errorReplacement */
		if(dto.isErrorReplacementModified()){
			poso.setErrorReplacement(dto.getErrorReplacement() );
		}

		/*  set replaceErrors */
		if(dto.isReplaceErrorsModified()){
			poso.setReplaceErrors(dto.isReplaceErrors() );
		}

		/*  set rollOver */
		if(dto.isRollOverModified()){
			poso.setRollOver(dto.isRollOver() );
		}

		/*  set targetFormat */
		if(dto.isTargetFormatModified()){
			poso.setTargetFormat(dto.getTargetFormat() );
		}

	}

	public void mergeUnmanagedPoso(ColumnFormatDateDto dto, final ColumnFormatDate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ColumnFormatDateDto dto, final ColumnFormatDate poso)  throws ExpectedException {
		/*  set baseFormat */
		poso.setBaseFormat(dto.getBaseFormat() );

		/*  set errorReplacement */
		poso.setErrorReplacement(dto.getErrorReplacement() );

		/*  set replaceErrors */
		poso.setReplaceErrors(dto.isReplaceErrors() );

		/*  set rollOver */
		poso.setRollOver(dto.isRollOver() );

		/*  set targetFormat */
		poso.setTargetFormat(dto.getTargetFormat() );

	}

	protected void mergeProxy2UnmanagedPoso(ColumnFormatDateDto dto, final ColumnFormatDate poso)  throws ExpectedException {
		/*  set baseFormat */
		if(dto.isBaseFormatModified()){
			poso.setBaseFormat(dto.getBaseFormat() );
		}

		/*  set errorReplacement */
		if(dto.isErrorReplacementModified()){
			poso.setErrorReplacement(dto.getErrorReplacement() );
		}

		/*  set replaceErrors */
		if(dto.isReplaceErrorsModified()){
			poso.setReplaceErrors(dto.isReplaceErrors() );
		}

		/*  set rollOver */
		if(dto.isRollOverModified()){
			poso.setRollOver(dto.isRollOver() );
		}

		/*  set targetFormat */
		if(dto.isTargetFormatModified()){
			poso.setTargetFormat(dto.getTargetFormat() );
		}

	}

	public ColumnFormatDate loadAndMergePoso(ColumnFormatDateDto dto)  throws ExpectedException {
		ColumnFormatDate poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ColumnFormatDateDto dto, ColumnFormatDate poso)  {
	}


	public void postProcessCreateUnmanaged(ColumnFormatDateDto dto, ColumnFormatDate poso)  {
	}


	public void postProcessLoad(ColumnFormatDateDto dto, ColumnFormatDate poso)  {
	}


	public void postProcessMerge(ColumnFormatDateDto dto, ColumnFormatDate poso)  {
	}


	public void postProcessInstantiate(ColumnFormatDate poso)  {
	}



}
