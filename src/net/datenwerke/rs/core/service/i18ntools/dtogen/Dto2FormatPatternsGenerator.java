package net.datenwerke.rs.core.service.i18ntools.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;
import net.datenwerke.rs.core.service.i18ntools.FormatPatterns;
import net.datenwerke.rs.core.service.i18ntools.dtogen.Dto2FormatPatternsGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FormatPatterns
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FormatPatternsGenerator implements Dto2PosoGenerator<FormatPatternsDto,FormatPatterns> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FormatPatternsGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public FormatPatterns loadPoso(FormatPatternsDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public FormatPatterns instantiatePoso()  {
		FormatPatterns poso = new FormatPatterns();
		return poso;
	}

	public FormatPatterns createPoso(FormatPatternsDto dto)  throws ExpectedException {
		FormatPatterns poso = new FormatPatterns();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FormatPatterns createUnmanagedPoso(FormatPatternsDto dto)  throws ExpectedException {
		FormatPatterns poso = new FormatPatterns();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(FormatPatternsDto dto, final FormatPatterns poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FormatPatternsDto dto, final FormatPatterns poso)  throws ExpectedException {
		/*  set currencyPattern */
		poso.setCurrencyPattern(dto.getCurrencyPattern() );

		/*  set integerPattern */
		poso.setIntegerPattern(dto.getIntegerPattern() );

		/*  set longDatePattern */
		poso.setLongDatePattern(dto.getLongDatePattern() );

		/*  set longDateTimePattern */
		poso.setLongDateTimePattern(dto.getLongDateTimePattern() );

		/*  set longTimePattern */
		poso.setLongTimePattern(dto.getLongTimePattern() );

		/*  set numberPattern */
		poso.setNumberPattern(dto.getNumberPattern() );

		/*  set percentPattern */
		poso.setPercentPattern(dto.getPercentPattern() );

		/*  set shortDatePattern */
		poso.setShortDatePattern(dto.getShortDatePattern() );

		/*  set shortDateTimePattern */
		poso.setShortDateTimePattern(dto.getShortDateTimePattern() );

		/*  set shortTimePattern */
		poso.setShortTimePattern(dto.getShortTimePattern() );

	}

	protected void mergeProxy2Poso(FormatPatternsDto dto, final FormatPatterns poso)  throws ExpectedException {
		/*  set currencyPattern */
		if(dto.isCurrencyPatternModified()){
			poso.setCurrencyPattern(dto.getCurrencyPattern() );
		}

		/*  set integerPattern */
		if(dto.isIntegerPatternModified()){
			poso.setIntegerPattern(dto.getIntegerPattern() );
		}

		/*  set longDatePattern */
		if(dto.isLongDatePatternModified()){
			poso.setLongDatePattern(dto.getLongDatePattern() );
		}

		/*  set longDateTimePattern */
		if(dto.isLongDateTimePatternModified()){
			poso.setLongDateTimePattern(dto.getLongDateTimePattern() );
		}

		/*  set longTimePattern */
		if(dto.isLongTimePatternModified()){
			poso.setLongTimePattern(dto.getLongTimePattern() );
		}

		/*  set numberPattern */
		if(dto.isNumberPatternModified()){
			poso.setNumberPattern(dto.getNumberPattern() );
		}

		/*  set percentPattern */
		if(dto.isPercentPatternModified()){
			poso.setPercentPattern(dto.getPercentPattern() );
		}

		/*  set shortDatePattern */
		if(dto.isShortDatePatternModified()){
			poso.setShortDatePattern(dto.getShortDatePattern() );
		}

		/*  set shortDateTimePattern */
		if(dto.isShortDateTimePatternModified()){
			poso.setShortDateTimePattern(dto.getShortDateTimePattern() );
		}

		/*  set shortTimePattern */
		if(dto.isShortTimePatternModified()){
			poso.setShortTimePattern(dto.getShortTimePattern() );
		}

	}

	public void mergeUnmanagedPoso(FormatPatternsDto dto, final FormatPatterns poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FormatPatternsDto dto, final FormatPatterns poso)  throws ExpectedException {
		/*  set currencyPattern */
		poso.setCurrencyPattern(dto.getCurrencyPattern() );

		/*  set integerPattern */
		poso.setIntegerPattern(dto.getIntegerPattern() );

		/*  set longDatePattern */
		poso.setLongDatePattern(dto.getLongDatePattern() );

		/*  set longDateTimePattern */
		poso.setLongDateTimePattern(dto.getLongDateTimePattern() );

		/*  set longTimePattern */
		poso.setLongTimePattern(dto.getLongTimePattern() );

		/*  set numberPattern */
		poso.setNumberPattern(dto.getNumberPattern() );

		/*  set percentPattern */
		poso.setPercentPattern(dto.getPercentPattern() );

		/*  set shortDatePattern */
		poso.setShortDatePattern(dto.getShortDatePattern() );

		/*  set shortDateTimePattern */
		poso.setShortDateTimePattern(dto.getShortDateTimePattern() );

		/*  set shortTimePattern */
		poso.setShortTimePattern(dto.getShortTimePattern() );

	}

	protected void mergeProxy2UnmanagedPoso(FormatPatternsDto dto, final FormatPatterns poso)  throws ExpectedException {
		/*  set currencyPattern */
		if(dto.isCurrencyPatternModified()){
			poso.setCurrencyPattern(dto.getCurrencyPattern() );
		}

		/*  set integerPattern */
		if(dto.isIntegerPatternModified()){
			poso.setIntegerPattern(dto.getIntegerPattern() );
		}

		/*  set longDatePattern */
		if(dto.isLongDatePatternModified()){
			poso.setLongDatePattern(dto.getLongDatePattern() );
		}

		/*  set longDateTimePattern */
		if(dto.isLongDateTimePatternModified()){
			poso.setLongDateTimePattern(dto.getLongDateTimePattern() );
		}

		/*  set longTimePattern */
		if(dto.isLongTimePatternModified()){
			poso.setLongTimePattern(dto.getLongTimePattern() );
		}

		/*  set numberPattern */
		if(dto.isNumberPatternModified()){
			poso.setNumberPattern(dto.getNumberPattern() );
		}

		/*  set percentPattern */
		if(dto.isPercentPatternModified()){
			poso.setPercentPattern(dto.getPercentPattern() );
		}

		/*  set shortDatePattern */
		if(dto.isShortDatePatternModified()){
			poso.setShortDatePattern(dto.getShortDatePattern() );
		}

		/*  set shortDateTimePattern */
		if(dto.isShortDateTimePatternModified()){
			poso.setShortDateTimePattern(dto.getShortDateTimePattern() );
		}

		/*  set shortTimePattern */
		if(dto.isShortTimePatternModified()){
			poso.setShortTimePattern(dto.getShortTimePattern() );
		}

	}

	public FormatPatterns loadAndMergePoso(FormatPatternsDto dto)  throws ExpectedException {
		FormatPatterns poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FormatPatternsDto dto, FormatPatterns poso)  {
	}


	public void postProcessCreateUnmanaged(FormatPatternsDto dto, FormatPatterns poso)  {
	}


	public void postProcessLoad(FormatPatternsDto dto, FormatPatterns poso)  {
	}


	public void postProcessMerge(FormatPatternsDto dto, FormatPatterns poso)  {
	}


	public void postProcessInstantiate(FormatPatterns poso)  {
	}



}
