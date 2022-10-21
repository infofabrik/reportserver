package net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatNumberGenerator;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.NumberType;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ColumnFormatNumber
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ColumnFormatNumberGenerator implements Dto2PosoGenerator<ColumnFormatNumberDto,ColumnFormatNumber> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ColumnFormatNumberGenerator(
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

	public ColumnFormatNumber loadPoso(ColumnFormatNumberDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ColumnFormatNumber poso = entityManager.find(ColumnFormatNumber.class, id);
		return poso;
	}

	public ColumnFormatNumber instantiatePoso()  {
		ColumnFormatNumber poso = new ColumnFormatNumber();
		return poso;
	}

	public ColumnFormatNumber createPoso(ColumnFormatNumberDto dto)  throws ExpectedException {
		ColumnFormatNumber poso = new ColumnFormatNumber();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ColumnFormatNumber createUnmanagedPoso(ColumnFormatNumberDto dto)  throws ExpectedException {
		ColumnFormatNumber poso = new ColumnFormatNumber();

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

	public void mergePoso(ColumnFormatNumberDto dto, final ColumnFormatNumber poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ColumnFormatNumberDto dto, final ColumnFormatNumber poso)  throws ExpectedException {
		/*  set numberOfDecimalPlaces */
		try{
			poso.setNumberOfDecimalPlaces(dto.getNumberOfDecimalPlaces() );
		} catch(NullPointerException e){
		}

		/*  set thousandSeparator */
		try{
			poso.setThousandSeparator(dto.isThousandSeparator() );
		} catch(NullPointerException e){
		}

		/*  set type */
		NumberTypeDto tmpDto_type = dto.getType();
		poso.setType((NumberType)dtoServiceProvider.get().createPoso(tmpDto_type));

	}

	protected void mergeProxy2Poso(ColumnFormatNumberDto dto, final ColumnFormatNumber poso)  throws ExpectedException {
		/*  set numberOfDecimalPlaces */
		if(dto.isNumberOfDecimalPlacesModified()){
			try{
				poso.setNumberOfDecimalPlaces(dto.getNumberOfDecimalPlaces() );
			} catch(NullPointerException e){
			}
		}

		/*  set thousandSeparator */
		if(dto.isThousandSeparatorModified()){
			try{
				poso.setThousandSeparator(dto.isThousandSeparator() );
			} catch(NullPointerException e){
			}
		}

		/*  set type */
		if(dto.isTypeModified()){
			NumberTypeDto tmpDto_type = dto.getType();
			poso.setType((NumberType)dtoServiceProvider.get().createPoso(tmpDto_type));
		}

	}

	public void mergeUnmanagedPoso(ColumnFormatNumberDto dto, final ColumnFormatNumber poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ColumnFormatNumberDto dto, final ColumnFormatNumber poso)  throws ExpectedException {
		/*  set numberOfDecimalPlaces */
		try{
			poso.setNumberOfDecimalPlaces(dto.getNumberOfDecimalPlaces() );
		} catch(NullPointerException e){
		}

		/*  set thousandSeparator */
		try{
			poso.setThousandSeparator(dto.isThousandSeparator() );
		} catch(NullPointerException e){
		}

		/*  set type */
		NumberTypeDto tmpDto_type = dto.getType();
		poso.setType((NumberType)dtoServiceProvider.get().createPoso(tmpDto_type));

	}

	protected void mergeProxy2UnmanagedPoso(ColumnFormatNumberDto dto, final ColumnFormatNumber poso)  throws ExpectedException {
		/*  set numberOfDecimalPlaces */
		if(dto.isNumberOfDecimalPlacesModified()){
			try{
				poso.setNumberOfDecimalPlaces(dto.getNumberOfDecimalPlaces() );
			} catch(NullPointerException e){
			}
		}

		/*  set thousandSeparator */
		if(dto.isThousandSeparatorModified()){
			try{
				poso.setThousandSeparator(dto.isThousandSeparator() );
			} catch(NullPointerException e){
			}
		}

		/*  set type */
		if(dto.isTypeModified()){
			NumberTypeDto tmpDto_type = dto.getType();
			poso.setType((NumberType)dtoServiceProvider.get().createPoso(tmpDto_type));
		}

	}

	public ColumnFormatNumber loadAndMergePoso(ColumnFormatNumberDto dto)  throws ExpectedException {
		ColumnFormatNumber poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ColumnFormatNumberDto dto, ColumnFormatNumber poso)  {
	}


	public void postProcessCreateUnmanaged(ColumnFormatNumberDto dto, ColumnFormatNumber poso)  {
	}


	public void postProcessLoad(ColumnFormatNumberDto dto, ColumnFormatNumber poso)  {
	}


	public void postProcessMerge(ColumnFormatNumberDto dto, ColumnFormatNumber poso)  {
	}


	public void postProcessInstantiate(ColumnFormatNumber poso)  {
	}



}
