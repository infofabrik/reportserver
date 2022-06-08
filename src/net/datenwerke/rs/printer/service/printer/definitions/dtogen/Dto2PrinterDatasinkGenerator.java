package net.datenwerke.rs.printer.service.printer.definitions.dtogen;

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
import net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto;
import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.printer.service.printer.definitions.dtogen.Dto2PrinterDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for PrinterDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2PrinterDatasinkGenerator implements Dto2PosoGenerator<PrinterDatasinkDto,PrinterDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2PrinterDatasinkGenerator(
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

	public PrinterDatasink loadPoso(PrinterDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		PrinterDatasink poso = entityManager.find(PrinterDatasink.class, id);
		return poso;
	}

	public PrinterDatasink instantiatePoso()  {
		PrinterDatasink poso = new PrinterDatasink();
		return poso;
	}

	public PrinterDatasink createPoso(PrinterDatasinkDto dto)  throws ExpectedException {
		PrinterDatasink poso = new PrinterDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public PrinterDatasink createUnmanagedPoso(PrinterDatasinkDto dto)  throws ExpectedException {
		PrinterDatasink poso = new PrinterDatasink();

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

	public void mergePoso(PrinterDatasinkDto dto, final PrinterDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(PrinterDatasinkDto dto, final PrinterDatasink poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set printerName */
		poso.setPrinterName(dto.getPrinterName() );

	}

	protected void mergeProxy2Poso(PrinterDatasinkDto dto, final PrinterDatasink poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set printerName */
		if(dto.isPrinterNameModified()){
			poso.setPrinterName(dto.getPrinterName() );
		}

	}

	public void mergeUnmanagedPoso(PrinterDatasinkDto dto, final PrinterDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(PrinterDatasinkDto dto, final PrinterDatasink poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set printerName */
		poso.setPrinterName(dto.getPrinterName() );

	}

	protected void mergeProxy2UnmanagedPoso(PrinterDatasinkDto dto, final PrinterDatasink poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set printerName */
		if(dto.isPrinterNameModified()){
			poso.setPrinterName(dto.getPrinterName() );
		}

	}

	public PrinterDatasink loadAndMergePoso(PrinterDatasinkDto dto)  throws ExpectedException {
		PrinterDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(PrinterDatasinkDto dto, PrinterDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(PrinterDatasinkDto dto, PrinterDatasink poso)  {
	}


	public void postProcessLoad(PrinterDatasinkDto dto, PrinterDatasink poso)  {
	}


	public void postProcessMerge(PrinterDatasinkDto dto, PrinterDatasink poso)  {
	}


	public void postProcessInstantiate(PrinterDatasink poso)  {
	}



}
