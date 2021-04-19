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
import net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CreMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CreMessageGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CreMessage
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CreMessageGenerator implements Dto2PosoGenerator<CreMessageDto,CreMessage> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CreMessageGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CreMessage loadPoso(CreMessageDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CreMessage instantiatePoso()  {
		CreMessage poso = new CreMessage();
		return poso;
	}

	public CreMessage createPoso(CreMessageDto dto)  throws ExpectedException {
		CreMessage poso = new CreMessage();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CreMessage createUnmanagedPoso(CreMessageDto dto)  throws ExpectedException {
		CreMessage poso = new CreMessage();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CreMessageDto dto, final CreMessage poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CreMessageDto dto, final CreMessage poso)  throws ExpectedException {
		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

		/*  set html */
		poso.setHtml(dto.getHtml() );

		/*  set text */
		poso.setText(dto.getText() );

		/*  set title */
		poso.setTitle(dto.getTitle() );

		/*  set width */
		try{
			poso.setWidth(dto.getWidth() );
		} catch(NullPointerException e){
		}

		/*  set windowTitle */
		poso.setWindowTitle(dto.getWindowTitle() );

	}

	protected void mergeProxy2Poso(CreMessageDto dto, final CreMessage poso)  throws ExpectedException {
		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
		}

		/*  set html */
		if(dto.isHtmlModified()){
			poso.setHtml(dto.getHtml() );
		}

		/*  set text */
		if(dto.isTextModified()){
			poso.setText(dto.getText() );
		}

		/*  set title */
		if(dto.isTitleModified()){
			poso.setTitle(dto.getTitle() );
		}

		/*  set width */
		if(dto.isWidthModified()){
			try{
				poso.setWidth(dto.getWidth() );
			} catch(NullPointerException e){
			}
		}

		/*  set windowTitle */
		if(dto.isWindowTitleModified()){
			poso.setWindowTitle(dto.getWindowTitle() );
		}

	}

	public void mergeUnmanagedPoso(CreMessageDto dto, final CreMessage poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CreMessageDto dto, final CreMessage poso)  throws ExpectedException {
		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

		/*  set html */
		poso.setHtml(dto.getHtml() );

		/*  set text */
		poso.setText(dto.getText() );

		/*  set title */
		poso.setTitle(dto.getTitle() );

		/*  set width */
		try{
			poso.setWidth(dto.getWidth() );
		} catch(NullPointerException e){
		}

		/*  set windowTitle */
		poso.setWindowTitle(dto.getWindowTitle() );

	}

	protected void mergeProxy2UnmanagedPoso(CreMessageDto dto, final CreMessage poso)  throws ExpectedException {
		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
		}

		/*  set html */
		if(dto.isHtmlModified()){
			poso.setHtml(dto.getHtml() );
		}

		/*  set text */
		if(dto.isTextModified()){
			poso.setText(dto.getText() );
		}

		/*  set title */
		if(dto.isTitleModified()){
			poso.setTitle(dto.getTitle() );
		}

		/*  set width */
		if(dto.isWidthModified()){
			try{
				poso.setWidth(dto.getWidth() );
			} catch(NullPointerException e){
			}
		}

		/*  set windowTitle */
		if(dto.isWindowTitleModified()){
			poso.setWindowTitle(dto.getWindowTitle() );
		}

	}

	public CreMessage loadAndMergePoso(CreMessageDto dto)  throws ExpectedException {
		CreMessage poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CreMessageDto dto, CreMessage poso)  {
	}


	public void postProcessCreateUnmanaged(CreMessageDto dto, CreMessage poso)  {
	}


	public void postProcessLoad(CreMessageDto dto, CreMessage poso)  {
	}


	public void postProcessMerge(CreMessageDto dto, CreMessage poso)  {
	}


	public void postProcessInstantiate(CreMessage poso)  {
	}



}
