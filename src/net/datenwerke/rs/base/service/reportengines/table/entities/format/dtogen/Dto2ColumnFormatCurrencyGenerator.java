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
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatCurrencyGenerator;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.CurrencyType;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.NumberType;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ColumnFormatCurrency
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ColumnFormatCurrencyGenerator implements Dto2PosoGenerator<ColumnFormatCurrencyDto,ColumnFormatCurrency> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ColumnFormatCurrencyGenerator(
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

	public ColumnFormatCurrency loadPoso(ColumnFormatCurrencyDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ColumnFormatCurrency poso = entityManager.find(ColumnFormatCurrency.class, id);
		return poso;
	}

	public ColumnFormatCurrency instantiatePoso()  {
		ColumnFormatCurrency poso = new ColumnFormatCurrency();
		return poso;
	}

	public ColumnFormatCurrency createPoso(ColumnFormatCurrencyDto dto)  throws ExpectedException {
		ColumnFormatCurrency poso = new ColumnFormatCurrency();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ColumnFormatCurrency createUnmanagedPoso(ColumnFormatCurrencyDto dto)  throws ExpectedException {
		ColumnFormatCurrency poso = new ColumnFormatCurrency();

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

	public void mergePoso(ColumnFormatCurrencyDto dto, final ColumnFormatCurrency poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ColumnFormatCurrencyDto dto, final ColumnFormatCurrency poso)  throws ExpectedException {
		/*  set currencyType */
		CurrencyTypeDto tmpDto_currencyType = dto.getCurrencyType();
		poso.setCurrencyType((CurrencyType)dtoServiceProvider.get().createPoso(tmpDto_currencyType));

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

	protected void mergeProxy2Poso(ColumnFormatCurrencyDto dto, final ColumnFormatCurrency poso)  throws ExpectedException {
		/*  set currencyType */
		if(dto.isCurrencyTypeModified()){
			CurrencyTypeDto tmpDto_currencyType = dto.getCurrencyType();
			poso.setCurrencyType((CurrencyType)dtoServiceProvider.get().createPoso(tmpDto_currencyType));
		}

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

	public void mergeUnmanagedPoso(ColumnFormatCurrencyDto dto, final ColumnFormatCurrency poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ColumnFormatCurrencyDto dto, final ColumnFormatCurrency poso)  throws ExpectedException {
		/*  set currencyType */
		CurrencyTypeDto tmpDto_currencyType = dto.getCurrencyType();
		poso.setCurrencyType((CurrencyType)dtoServiceProvider.get().createPoso(tmpDto_currencyType));

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

	protected void mergeProxy2UnmanagedPoso(ColumnFormatCurrencyDto dto, final ColumnFormatCurrency poso)  throws ExpectedException {
		/*  set currencyType */
		if(dto.isCurrencyTypeModified()){
			CurrencyTypeDto tmpDto_currencyType = dto.getCurrencyType();
			poso.setCurrencyType((CurrencyType)dtoServiceProvider.get().createPoso(tmpDto_currencyType));
		}

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

	public ColumnFormatCurrency loadAndMergePoso(ColumnFormatCurrencyDto dto)  throws ExpectedException {
		ColumnFormatCurrency poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ColumnFormatCurrencyDto dto, ColumnFormatCurrency poso)  {
	}


	public void postProcessCreateUnmanaged(ColumnFormatCurrencyDto dto, ColumnFormatCurrency poso)  {
	}


	public void postProcessLoad(ColumnFormatCurrencyDto dto, ColumnFormatCurrency poso)  {
	}


	public void postProcessMerge(ColumnFormatCurrencyDto dto, ColumnFormatCurrency poso)  {
	}


	public void postProcessInstantiate(ColumnFormatCurrency poso)  {
	}



}
