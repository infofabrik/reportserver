package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

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
import net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto;
import net.datenwerke.rs.terminal.service.terminal.obj.InteractiveResultModifier;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2InteractiveResultModifierGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for InteractiveResultModifier
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2InteractiveResultModifierGenerator implements Dto2PosoGenerator<InteractiveResultModifierDto,InteractiveResultModifier> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2InteractiveResultModifierGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public InteractiveResultModifier loadPoso(InteractiveResultModifierDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public InteractiveResultModifier instantiatePoso()  {
		InteractiveResultModifier poso = new InteractiveResultModifier();
		return poso;
	}

	public InteractiveResultModifier createPoso(InteractiveResultModifierDto dto)  throws ExpectedException {
		InteractiveResultModifier poso = new InteractiveResultModifier();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public InteractiveResultModifier createUnmanagedPoso(InteractiveResultModifierDto dto)  throws ExpectedException {
		InteractiveResultModifier poso = new InteractiveResultModifier();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(InteractiveResultModifierDto dto, final InteractiveResultModifier poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(InteractiveResultModifierDto dto, final InteractiveResultModifier poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(InteractiveResultModifierDto dto, final InteractiveResultModifier poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(InteractiveResultModifierDto dto, final InteractiveResultModifier poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(InteractiveResultModifierDto dto, final InteractiveResultModifier poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(InteractiveResultModifierDto dto, final InteractiveResultModifier poso)  throws ExpectedException {
	}

	public InteractiveResultModifier loadAndMergePoso(InteractiveResultModifierDto dto)  throws ExpectedException {
		InteractiveResultModifier poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(InteractiveResultModifierDto dto, InteractiveResultModifier poso)  {
	}


	public void postProcessCreateUnmanaged(InteractiveResultModifierDto dto, InteractiveResultModifier poso)  {
	}


	public void postProcessLoad(InteractiveResultModifierDto dto, InteractiveResultModifier poso)  {
	}


	public void postProcessMerge(InteractiveResultModifierDto dto, InteractiveResultModifier poso)  {
	}


	public void postProcessInstantiate(InteractiveResultModifier poso)  {
	}



}
