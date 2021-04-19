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
import net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddStatusbBarLabelExtension;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2AddStatusbBarLabelExtensionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for AddStatusbBarLabelExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2AddStatusbBarLabelExtensionGenerator implements Dto2PosoGenerator<AddStatusbBarLabelExtensionDto,AddStatusbBarLabelExtension> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2AddStatusbBarLabelExtensionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public AddStatusbBarLabelExtension loadPoso(AddStatusbBarLabelExtensionDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public AddStatusbBarLabelExtension instantiatePoso()  {
		AddStatusbBarLabelExtension poso = new AddStatusbBarLabelExtension();
		return poso;
	}

	public AddStatusbBarLabelExtension createPoso(AddStatusbBarLabelExtensionDto dto)  throws ExpectedException {
		AddStatusbBarLabelExtension poso = new AddStatusbBarLabelExtension();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public AddStatusbBarLabelExtension createUnmanagedPoso(AddStatusbBarLabelExtensionDto dto)  throws ExpectedException {
		AddStatusbBarLabelExtension poso = new AddStatusbBarLabelExtension();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(AddStatusbBarLabelExtensionDto dto, final AddStatusbBarLabelExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(AddStatusbBarLabelExtensionDto dto, final AddStatusbBarLabelExtension poso)  throws ExpectedException {
		/*  set clear */
		try{
			poso.setClear(dto.isClear() );
		} catch(NullPointerException e){
		}

		/*  set icon */
		poso.setIcon(dto.getIcon() );

		/*  set label */
		poso.setLabel(dto.getLabel() );

		/*  set left */
		try{
			poso.setLeft(dto.isLeft() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(AddStatusbBarLabelExtensionDto dto, final AddStatusbBarLabelExtension poso)  throws ExpectedException {
		/*  set clear */
		if(dto.isClearModified()){
			try{
				poso.setClear(dto.isClear() );
			} catch(NullPointerException e){
			}
		}

		/*  set icon */
		if(dto.isIconModified()){
			poso.setIcon(dto.getIcon() );
		}

		/*  set label */
		if(dto.isLabelModified()){
			poso.setLabel(dto.getLabel() );
		}

		/*  set left */
		if(dto.isLeftModified()){
			try{
				poso.setLeft(dto.isLeft() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(AddStatusbBarLabelExtensionDto dto, final AddStatusbBarLabelExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(AddStatusbBarLabelExtensionDto dto, final AddStatusbBarLabelExtension poso)  throws ExpectedException {
		/*  set clear */
		try{
			poso.setClear(dto.isClear() );
		} catch(NullPointerException e){
		}

		/*  set icon */
		poso.setIcon(dto.getIcon() );

		/*  set label */
		poso.setLabel(dto.getLabel() );

		/*  set left */
		try{
			poso.setLeft(dto.isLeft() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(AddStatusbBarLabelExtensionDto dto, final AddStatusbBarLabelExtension poso)  throws ExpectedException {
		/*  set clear */
		if(dto.isClearModified()){
			try{
				poso.setClear(dto.isClear() );
			} catch(NullPointerException e){
			}
		}

		/*  set icon */
		if(dto.isIconModified()){
			poso.setIcon(dto.getIcon() );
		}

		/*  set label */
		if(dto.isLabelModified()){
			poso.setLabel(dto.getLabel() );
		}

		/*  set left */
		if(dto.isLeftModified()){
			try{
				poso.setLeft(dto.isLeft() );
			} catch(NullPointerException e){
			}
		}

	}

	public AddStatusbBarLabelExtension loadAndMergePoso(AddStatusbBarLabelExtensionDto dto)  throws ExpectedException {
		AddStatusbBarLabelExtension poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(AddStatusbBarLabelExtensionDto dto, AddStatusbBarLabelExtension poso)  {
	}


	public void postProcessCreateUnmanaged(AddStatusbBarLabelExtensionDto dto, AddStatusbBarLabelExtension poso)  {
	}


	public void postProcessLoad(AddStatusbBarLabelExtensionDto dto, AddStatusbBarLabelExtension poso)  {
	}


	public void postProcessMerge(AddStatusbBarLabelExtensionDto dto, AddStatusbBarLabelExtension poso)  {
	}


	public void postProcessInstantiate(AddStatusbBarLabelExtension poso)  {
	}



}
