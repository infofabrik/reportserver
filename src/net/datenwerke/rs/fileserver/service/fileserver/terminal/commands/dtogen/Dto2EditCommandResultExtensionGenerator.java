package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.dtogen;

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
import net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.EditCommandResultExtension;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.dtogen.Dto2EditCommandResultExtensionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for EditCommandResultExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2EditCommandResultExtensionGenerator implements Dto2PosoGenerator<EditCommandResultExtensionDto,EditCommandResultExtension> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2EditCommandResultExtensionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public EditCommandResultExtension loadPoso(EditCommandResultExtensionDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public EditCommandResultExtension instantiatePoso()  {
		EditCommandResultExtension poso = new EditCommandResultExtension();
		return poso;
	}

	public EditCommandResultExtension createPoso(EditCommandResultExtensionDto dto)  throws ExpectedException {
		EditCommandResultExtension poso = new EditCommandResultExtension();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public EditCommandResultExtension createUnmanagedPoso(EditCommandResultExtensionDto dto)  throws ExpectedException {
		EditCommandResultExtension poso = new EditCommandResultExtension();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(EditCommandResultExtensionDto dto, final EditCommandResultExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(EditCommandResultExtensionDto dto, final EditCommandResultExtension poso)  throws ExpectedException {
		/*  set data */
		poso.setData(dto.getData() );

		/*  set file */
		FileServerFileDto tmpDto_file = dto.getFile();
		if(null != tmpDto_file && null != tmpDto_file.getId()){
			if(null != poso.getFile() && null != poso.getFile().getId() && poso.getFile().getId().equals(tmpDto_file.getId()))
				poso.setFile((FileServerFile)dtoServiceProvider.get().loadAndMergePoso(tmpDto_file));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (file)");
		} else if(null != poso.getFile()){
			FileServerFile newPropertyValue = (FileServerFile)dtoServiceProvider.get().createPoso(tmpDto_file);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getFile(), newPropertyValue, "file");
			poso.setFile(newPropertyValue);
		} else {
			poso.setFile((FileServerFile)dtoServiceProvider.get().createPoso(tmpDto_file));
		}

	}

	protected void mergeProxy2Poso(EditCommandResultExtensionDto dto, final EditCommandResultExtension poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			poso.setData(dto.getData() );
		}

		/*  set file */
		if(dto.isFileModified()){
			FileServerFileDto tmpDto_file = dto.getFile();
			if(null != tmpDto_file && null != tmpDto_file.getId()){
				if(null != poso.getFile() && null != poso.getFile().getId() && poso.getFile().getId().equals(tmpDto_file.getId()))
					poso.setFile((FileServerFile)dtoServiceProvider.get().loadAndMergePoso(tmpDto_file));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (file)");
			} else if(null != poso.getFile()){
				FileServerFile newPropertyValue = (FileServerFile)dtoServiceProvider.get().createPoso(tmpDto_file);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getFile(), newPropertyValue, "file");
				poso.setFile(newPropertyValue);
			} else {
				poso.setFile((FileServerFile)dtoServiceProvider.get().createPoso(tmpDto_file));
			}
		}

	}

	public void mergeUnmanagedPoso(EditCommandResultExtensionDto dto, final EditCommandResultExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(EditCommandResultExtensionDto dto, final EditCommandResultExtension poso)  throws ExpectedException {
		/*  set data */
		poso.setData(dto.getData() );

		/*  set file */
		FileServerFileDto tmpDto_file = dto.getFile();
		poso.setFile((FileServerFile)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_file));

	}

	protected void mergeProxy2UnmanagedPoso(EditCommandResultExtensionDto dto, final EditCommandResultExtension poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			poso.setData(dto.getData() );
		}

		/*  set file */
		if(dto.isFileModified()){
			FileServerFileDto tmpDto_file = dto.getFile();
			poso.setFile((FileServerFile)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_file));
		}

	}

	public EditCommandResultExtension loadAndMergePoso(EditCommandResultExtensionDto dto)  throws ExpectedException {
		EditCommandResultExtension poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(EditCommandResultExtensionDto dto, EditCommandResultExtension poso)  {
	}


	public void postProcessCreateUnmanaged(EditCommandResultExtensionDto dto, EditCommandResultExtension poso)  {
	}


	public void postProcessLoad(EditCommandResultExtensionDto dto, EditCommandResultExtension poso)  {
	}


	public void postProcessMerge(EditCommandResultExtensionDto dto, EditCommandResultExtension poso)  {
	}


	public void postProcessInstantiate(EditCommandResultExtension poso)  {
	}



}
