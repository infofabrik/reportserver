package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

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
import net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CreOverlay;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CreOverlayGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CreOverlay
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CreOverlayGenerator implements Dto2PosoGenerator<CreOverlayDto,CreOverlay> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CreOverlayGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CreOverlay loadPoso(CreOverlayDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CreOverlay instantiatePoso()  {
		CreOverlay poso = new CreOverlay();
		return poso;
	}

	public CreOverlay createPoso(CreOverlayDto dto)  throws ExpectedException {
		CreOverlay poso = new CreOverlay();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CreOverlay createUnmanagedPoso(CreOverlayDto dto)  throws ExpectedException {
		CreOverlay poso = new CreOverlay();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CreOverlayDto dto, final CreOverlay poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CreOverlayDto dto, final CreOverlay poso)  throws ExpectedException {
		/*  set cssProperties */
		poso.setCssProperties(dto.getCssProperties() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set remove */
		try{
			poso.setRemove(dto.isRemove() );
		} catch(NullPointerException e){
		}

		/*  set text */
		poso.setText(dto.getText() );

	}

	protected void mergeProxy2Poso(CreOverlayDto dto, final CreOverlay poso)  throws ExpectedException {
		/*  set cssProperties */
		if(dto.isCssPropertiesModified()){
			poso.setCssProperties(dto.getCssProperties() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set remove */
		if(dto.isRemoveModified()){
			try{
				poso.setRemove(dto.isRemove() );
			} catch(NullPointerException e){
			}
		}

		/*  set text */
		if(dto.isTextModified()){
			poso.setText(dto.getText() );
		}

	}

	public void mergeUnmanagedPoso(CreOverlayDto dto, final CreOverlay poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CreOverlayDto dto, final CreOverlay poso)  throws ExpectedException {
		/*  set cssProperties */
		poso.setCssProperties(dto.getCssProperties() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set remove */
		try{
			poso.setRemove(dto.isRemove() );
		} catch(NullPointerException e){
		}

		/*  set text */
		poso.setText(dto.getText() );

	}

	protected void mergeProxy2UnmanagedPoso(CreOverlayDto dto, final CreOverlay poso)  throws ExpectedException {
		/*  set cssProperties */
		if(dto.isCssPropertiesModified()){
			poso.setCssProperties(dto.getCssProperties() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set remove */
		if(dto.isRemoveModified()){
			try{
				poso.setRemove(dto.isRemove() );
			} catch(NullPointerException e){
			}
		}

		/*  set text */
		if(dto.isTextModified()){
			poso.setText(dto.getText() );
		}

	}

	public CreOverlay loadAndMergePoso(CreOverlayDto dto)  throws ExpectedException {
		CreOverlay poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CreOverlayDto dto, CreOverlay poso)  {
	}


	public void postProcessCreateUnmanaged(CreOverlayDto dto, CreOverlay poso)  {
	}


	public void postProcessLoad(CreOverlayDto dto, CreOverlay poso)  {
	}


	public void postProcessMerge(CreOverlayDto dto, CreOverlay poso)  {
	}


	public void postProcessInstantiate(CreOverlay poso)  {
	}



}
