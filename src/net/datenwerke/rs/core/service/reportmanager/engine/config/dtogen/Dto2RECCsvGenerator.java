package net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv;
import net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen.Dto2RECCsvGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for RECCsv
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2RECCsvGenerator implements Dto2PosoGenerator<RECCsvDto,RECCsv> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2RECCsvGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public RECCsv loadPoso(RECCsvDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public RECCsv instantiatePoso()  {
		RECCsv poso = new RECCsv();
		return poso;
	}

	public RECCsv createPoso(RECCsvDto dto)  throws ExpectedException {
		RECCsv poso = new RECCsv();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public RECCsv createUnmanagedPoso(RECCsvDto dto)  throws ExpectedException {
		RECCsv poso = new RECCsv();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(RECCsvDto dto, final RECCsv poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(RECCsvDto dto, final RECCsv poso)  throws ExpectedException {
		/*  set charset */
		poso.setCharset(dto.getCharset() );

		/*  set lineSeparator */
		poso.setLineSeparator(dto.getLineSeparator() );

		/*  set printHeader */
		try{
			poso.setPrintHeader(dto.isPrintHeader() );
		} catch(NullPointerException e){
		}

		/*  set quote */
		poso.setQuote(dto.getQuote() );

		/*  set separator */
		poso.setSeparator(dto.getSeparator() );

	}

	protected void mergeProxy2Poso(RECCsvDto dto, final RECCsv poso)  throws ExpectedException {
		/*  set charset */
		if(dto.isCharsetModified()){
			poso.setCharset(dto.getCharset() );
		}

		/*  set lineSeparator */
		if(dto.isLineSeparatorModified()){
			poso.setLineSeparator(dto.getLineSeparator() );
		}

		/*  set printHeader */
		if(dto.isPrintHeaderModified()){
			try{
				poso.setPrintHeader(dto.isPrintHeader() );
			} catch(NullPointerException e){
			}
		}

		/*  set quote */
		if(dto.isQuoteModified()){
			poso.setQuote(dto.getQuote() );
		}

		/*  set separator */
		if(dto.isSeparatorModified()){
			poso.setSeparator(dto.getSeparator() );
		}

	}

	public void mergeUnmanagedPoso(RECCsvDto dto, final RECCsv poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(RECCsvDto dto, final RECCsv poso)  throws ExpectedException {
		/*  set charset */
		poso.setCharset(dto.getCharset() );

		/*  set lineSeparator */
		poso.setLineSeparator(dto.getLineSeparator() );

		/*  set printHeader */
		try{
			poso.setPrintHeader(dto.isPrintHeader() );
		} catch(NullPointerException e){
		}

		/*  set quote */
		poso.setQuote(dto.getQuote() );

		/*  set separator */
		poso.setSeparator(dto.getSeparator() );

	}

	protected void mergeProxy2UnmanagedPoso(RECCsvDto dto, final RECCsv poso)  throws ExpectedException {
		/*  set charset */
		if(dto.isCharsetModified()){
			poso.setCharset(dto.getCharset() );
		}

		/*  set lineSeparator */
		if(dto.isLineSeparatorModified()){
			poso.setLineSeparator(dto.getLineSeparator() );
		}

		/*  set printHeader */
		if(dto.isPrintHeaderModified()){
			try{
				poso.setPrintHeader(dto.isPrintHeader() );
			} catch(NullPointerException e){
			}
		}

		/*  set quote */
		if(dto.isQuoteModified()){
			poso.setQuote(dto.getQuote() );
		}

		/*  set separator */
		if(dto.isSeparatorModified()){
			poso.setSeparator(dto.getSeparator() );
		}

	}

	public RECCsv loadAndMergePoso(RECCsvDto dto)  throws ExpectedException {
		RECCsv poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(RECCsvDto dto, RECCsv poso)  {
	}


	public void postProcessCreateUnmanaged(RECCsvDto dto, RECCsv poso)  {
	}


	public void postProcessLoad(RECCsvDto dto, RECCsv poso)  {
	}


	public void postProcessMerge(RECCsvDto dto, RECCsv poso)  {
	}


	public void postProcessInstantiate(RECCsv poso)  {
	}



}
