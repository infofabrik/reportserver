package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

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
import net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddReportExportFormatProvider;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddReportExportFormatProviderGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for AddReportExportFormatProvider
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2AddReportExportFormatProviderGenerator implements Dto2PosoGenerator<AddReportExportFormatProviderDto,AddReportExportFormatProvider> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2AddReportExportFormatProviderGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public AddReportExportFormatProvider loadPoso(AddReportExportFormatProviderDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public AddReportExportFormatProvider instantiatePoso()  {
		AddReportExportFormatProvider poso = new AddReportExportFormatProvider();
		return poso;
	}

	public AddReportExportFormatProvider createPoso(AddReportExportFormatProviderDto dto)  throws ExpectedException {
		AddReportExportFormatProvider poso = new AddReportExportFormatProvider();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public AddReportExportFormatProvider createUnmanagedPoso(AddReportExportFormatProviderDto dto)  throws ExpectedException {
		AddReportExportFormatProvider poso = new AddReportExportFormatProvider();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(AddReportExportFormatProviderDto dto, final AddReportExportFormatProvider poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(AddReportExportFormatProviderDto dto, final AddReportExportFormatProvider poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set icon */
		poso.setIcon(dto.getIcon() );

		/*  set outputFormat */
		poso.setOutputFormat(dto.getOutputFormat() );

		/*  set reportType */
		poso.setReportType(dto.getReportType() );

		/*  set skipDownload */
		try{
			poso.setSkipDownload(dto.isSkipDownload() );
		} catch(NullPointerException e){
		}

		/*  set title */
		poso.setTitle(dto.getTitle() );

	}

	protected void mergeProxy2Poso(AddReportExportFormatProviderDto dto, final AddReportExportFormatProvider poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set icon */
		if(dto.isIconModified()){
			poso.setIcon(dto.getIcon() );
		}

		/*  set outputFormat */
		if(dto.isOutputFormatModified()){
			poso.setOutputFormat(dto.getOutputFormat() );
		}

		/*  set reportType */
		if(dto.isReportTypeModified()){
			poso.setReportType(dto.getReportType() );
		}

		/*  set skipDownload */
		if(dto.isSkipDownloadModified()){
			try{
				poso.setSkipDownload(dto.isSkipDownload() );
			} catch(NullPointerException e){
			}
		}

		/*  set title */
		if(dto.isTitleModified()){
			poso.setTitle(dto.getTitle() );
		}

	}

	public void mergeUnmanagedPoso(AddReportExportFormatProviderDto dto, final AddReportExportFormatProvider poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(AddReportExportFormatProviderDto dto, final AddReportExportFormatProvider poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set icon */
		poso.setIcon(dto.getIcon() );

		/*  set outputFormat */
		poso.setOutputFormat(dto.getOutputFormat() );

		/*  set reportType */
		poso.setReportType(dto.getReportType() );

		/*  set skipDownload */
		try{
			poso.setSkipDownload(dto.isSkipDownload() );
		} catch(NullPointerException e){
		}

		/*  set title */
		poso.setTitle(dto.getTitle() );

	}

	protected void mergeProxy2UnmanagedPoso(AddReportExportFormatProviderDto dto, final AddReportExportFormatProvider poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set icon */
		if(dto.isIconModified()){
			poso.setIcon(dto.getIcon() );
		}

		/*  set outputFormat */
		if(dto.isOutputFormatModified()){
			poso.setOutputFormat(dto.getOutputFormat() );
		}

		/*  set reportType */
		if(dto.isReportTypeModified()){
			poso.setReportType(dto.getReportType() );
		}

		/*  set skipDownload */
		if(dto.isSkipDownloadModified()){
			try{
				poso.setSkipDownload(dto.isSkipDownload() );
			} catch(NullPointerException e){
			}
		}

		/*  set title */
		if(dto.isTitleModified()){
			poso.setTitle(dto.getTitle() );
		}

	}

	public AddReportExportFormatProvider loadAndMergePoso(AddReportExportFormatProviderDto dto)  throws ExpectedException {
		AddReportExportFormatProvider poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(AddReportExportFormatProviderDto dto, AddReportExportFormatProvider poso)  {
	}


	public void postProcessCreateUnmanaged(AddReportExportFormatProviderDto dto, AddReportExportFormatProvider poso)  {
	}


	public void postProcessLoad(AddReportExportFormatProviderDto dto, AddReportExportFormatProvider poso)  {
	}


	public void postProcessMerge(AddReportExportFormatProviderDto dto, AddReportExportFormatProvider poso)  {
	}


	public void postProcessInstantiate(AddReportExportFormatProvider poso)  {
	}



}
