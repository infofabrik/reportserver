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
import net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECJxls;
import net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen.Dto2RECJxlsGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for RECJxls
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2RECJxlsGenerator implements Dto2PosoGenerator<RECJxlsDto,RECJxls> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2RECJxlsGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public RECJxls loadPoso(RECJxlsDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public RECJxls instantiatePoso()  {
		RECJxls poso = new RECJxls();
		return poso;
	}

	public RECJxls createPoso(RECJxlsDto dto)  throws ExpectedException {
		RECJxls poso = new RECJxls();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public RECJxls createUnmanagedPoso(RECJxlsDto dto)  throws ExpectedException {
		RECJxls poso = new RECJxls();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(RECJxlsDto dto, final RECJxls poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(RECJxlsDto dto, final RECJxls poso)  throws ExpectedException {
		/*  set currencyColumnWidth */
		try{
			poso.setCurrencyColumnWidth(dto.getCurrencyColumnWidth() );
		} catch(NullPointerException e){
		}

		/*  set dateColumnWidth */
		try{
			poso.setDateColumnWidth(dto.getDateColumnWidth() );
		} catch(NullPointerException e){
		}

		/*  set jxls1 */
		try{
			poso.setJxls1(dto.isJxls1() );
		} catch(NullPointerException e){
		}

		/*  set jxlsReport */
		try{
			poso.setJxlsReport(dto.isJxlsReport() );
		} catch(NullPointerException e){
		}

		/*  set numberColumnWidth */
		try{
			poso.setNumberColumnWidth(dto.getNumberColumnWidth() );
		} catch(NullPointerException e){
		}

		/*  set textColumnWidth */
		try{
			poso.setTextColumnWidth(dto.getTextColumnWidth() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(RECJxlsDto dto, final RECJxls poso)  throws ExpectedException {
		/*  set currencyColumnWidth */
		if(dto.isCurrencyColumnWidthModified()){
			try{
				poso.setCurrencyColumnWidth(dto.getCurrencyColumnWidth() );
			} catch(NullPointerException e){
			}
		}

		/*  set dateColumnWidth */
		if(dto.isDateColumnWidthModified()){
			try{
				poso.setDateColumnWidth(dto.getDateColumnWidth() );
			} catch(NullPointerException e){
			}
		}

		/*  set jxls1 */
		if(dto.isJxls1Modified()){
			try{
				poso.setJxls1(dto.isJxls1() );
			} catch(NullPointerException e){
			}
		}

		/*  set jxlsReport */
		if(dto.isJxlsReportModified()){
			try{
				poso.setJxlsReport(dto.isJxlsReport() );
			} catch(NullPointerException e){
			}
		}

		/*  set numberColumnWidth */
		if(dto.isNumberColumnWidthModified()){
			try{
				poso.setNumberColumnWidth(dto.getNumberColumnWidth() );
			} catch(NullPointerException e){
			}
		}

		/*  set textColumnWidth */
		if(dto.isTextColumnWidthModified()){
			try{
				poso.setTextColumnWidth(dto.getTextColumnWidth() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(RECJxlsDto dto, final RECJxls poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(RECJxlsDto dto, final RECJxls poso)  throws ExpectedException {
		/*  set currencyColumnWidth */
		try{
			poso.setCurrencyColumnWidth(dto.getCurrencyColumnWidth() );
		} catch(NullPointerException e){
		}

		/*  set dateColumnWidth */
		try{
			poso.setDateColumnWidth(dto.getDateColumnWidth() );
		} catch(NullPointerException e){
		}

		/*  set jxls1 */
		try{
			poso.setJxls1(dto.isJxls1() );
		} catch(NullPointerException e){
		}

		/*  set jxlsReport */
		try{
			poso.setJxlsReport(dto.isJxlsReport() );
		} catch(NullPointerException e){
		}

		/*  set numberColumnWidth */
		try{
			poso.setNumberColumnWidth(dto.getNumberColumnWidth() );
		} catch(NullPointerException e){
		}

		/*  set textColumnWidth */
		try{
			poso.setTextColumnWidth(dto.getTextColumnWidth() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(RECJxlsDto dto, final RECJxls poso)  throws ExpectedException {
		/*  set currencyColumnWidth */
		if(dto.isCurrencyColumnWidthModified()){
			try{
				poso.setCurrencyColumnWidth(dto.getCurrencyColumnWidth() );
			} catch(NullPointerException e){
			}
		}

		/*  set dateColumnWidth */
		if(dto.isDateColumnWidthModified()){
			try{
				poso.setDateColumnWidth(dto.getDateColumnWidth() );
			} catch(NullPointerException e){
			}
		}

		/*  set jxls1 */
		if(dto.isJxls1Modified()){
			try{
				poso.setJxls1(dto.isJxls1() );
			} catch(NullPointerException e){
			}
		}

		/*  set jxlsReport */
		if(dto.isJxlsReportModified()){
			try{
				poso.setJxlsReport(dto.isJxlsReport() );
			} catch(NullPointerException e){
			}
		}

		/*  set numberColumnWidth */
		if(dto.isNumberColumnWidthModified()){
			try{
				poso.setNumberColumnWidth(dto.getNumberColumnWidth() );
			} catch(NullPointerException e){
			}
		}

		/*  set textColumnWidth */
		if(dto.isTextColumnWidthModified()){
			try{
				poso.setTextColumnWidth(dto.getTextColumnWidth() );
			} catch(NullPointerException e){
			}
		}

	}

	public RECJxls loadAndMergePoso(RECJxlsDto dto)  throws ExpectedException {
		RECJxls poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(RECJxlsDto dto, RECJxls poso)  {
	}


	public void postProcessCreateUnmanaged(RECJxlsDto dto, RECJxls poso)  {
	}


	public void postProcessLoad(RECJxlsDto dto, RECJxls poso)  {
	}


	public void postProcessMerge(RECJxlsDto dto, RECJxls poso)  {
	}


	public void postProcessInstantiate(RECJxls poso)  {
	}



}
