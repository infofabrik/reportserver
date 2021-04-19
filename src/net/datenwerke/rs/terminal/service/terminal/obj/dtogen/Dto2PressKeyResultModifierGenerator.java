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
import net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto;
import net.datenwerke.rs.terminal.service.terminal.obj.PressKeyResultModifier;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2PressKeyResultModifierGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for PressKeyResultModifier
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2PressKeyResultModifierGenerator implements Dto2PosoGenerator<PressKeyResultModifierDto,PressKeyResultModifier> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2PressKeyResultModifierGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public PressKeyResultModifier loadPoso(PressKeyResultModifierDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public PressKeyResultModifier instantiatePoso()  {
		PressKeyResultModifier poso = new PressKeyResultModifier();
		return poso;
	}

	public PressKeyResultModifier createPoso(PressKeyResultModifierDto dto)  throws ExpectedException {
		PressKeyResultModifier poso = new PressKeyResultModifier();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public PressKeyResultModifier createUnmanagedPoso(PressKeyResultModifierDto dto)  throws ExpectedException {
		PressKeyResultModifier poso = new PressKeyResultModifier();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(PressKeyResultModifierDto dto, final PressKeyResultModifier poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(PressKeyResultModifierDto dto, final PressKeyResultModifier poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(PressKeyResultModifierDto dto, final PressKeyResultModifier poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(PressKeyResultModifierDto dto, final PressKeyResultModifier poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(PressKeyResultModifierDto dto, final PressKeyResultModifier poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(PressKeyResultModifierDto dto, final PressKeyResultModifier poso)  throws ExpectedException {
	}

	public PressKeyResultModifier loadAndMergePoso(PressKeyResultModifierDto dto)  throws ExpectedException {
		PressKeyResultModifier poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(PressKeyResultModifierDto dto, PressKeyResultModifier poso)  {
	}


	public void postProcessCreateUnmanaged(PressKeyResultModifierDto dto, PressKeyResultModifier poso)  {
	}


	public void postProcessLoad(PressKeyResultModifierDto dto, PressKeyResultModifier poso)  {
	}


	public void postProcessMerge(PressKeyResultModifierDto dto, PressKeyResultModifier poso)  {
	}


	public void postProcessInstantiate(PressKeyResultModifier poso)  {
	}



}
