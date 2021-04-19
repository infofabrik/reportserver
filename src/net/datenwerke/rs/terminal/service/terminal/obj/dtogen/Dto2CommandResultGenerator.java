package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto;
import net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultEntry;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultModifier;
import net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CommandResult
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CommandResultGenerator implements Dto2PosoGenerator<CommandResultDto,CommandResult> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CommandResultGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CommandResult loadPoso(CommandResultDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CommandResult instantiatePoso()  {
		CommandResult poso = new CommandResult();
		return poso;
	}

	public CommandResult createPoso(CommandResultDto dto)  throws ExpectedException {
		CommandResult poso = new CommandResult();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CommandResult createUnmanagedPoso(CommandResultDto dto)  throws ExpectedException {
		CommandResult poso = new CommandResult();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CommandResultDto dto, final CommandResult poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CommandResultDto dto, final CommandResult poso)  throws ExpectedException {
		/*  set displayMode */
		DisplayModeDto tmpDto_displayMode = dto.getDisplayMode();
		poso.setDisplayMode((DisplayMode)dtoServiceProvider.get().createPoso(tmpDto_displayMode));

		/*  set entryList */
		final List<CommandResultEntry> col_entryList = new ArrayList<CommandResultEntry>();
		/* load new data from dto */
		Collection<CommandResultEntryDto> tmpCol_entryList = dto.getEntryList();

		for(CommandResultEntryDto refDto : tmpCol_entryList){
			col_entryList.add((CommandResultEntry) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setEntryList(col_entryList);

		/*  set extensions */
		final List<CommandResultExtension> col_extensions = new ArrayList<CommandResultExtension>();
		/* load new data from dto */
		Collection<CommandResultExtensionDto> tmpCol_extensions = dto.getExtensions();

		for(CommandResultExtensionDto refDto : tmpCol_extensions){
			col_extensions.add((CommandResultExtension) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setExtensions(col_extensions);

		/*  set modifiers */
		final Set<CommandResultModifier> col_modifiers = new HashSet<CommandResultModifier>();
		/* load new data from dto */
		Collection<CommandResultModifierDto> tmpCol_modifiers = dto.getModifiers();

		for(CommandResultModifierDto refDto : tmpCol_modifiers){
			col_modifiers.add((CommandResultModifier) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setModifiers(col_modifiers);

	}

	protected void mergeProxy2Poso(CommandResultDto dto, final CommandResult poso)  throws ExpectedException {
		/*  set displayMode */
		if(dto.isDisplayModeModified()){
			DisplayModeDto tmpDto_displayMode = dto.getDisplayMode();
			poso.setDisplayMode((DisplayMode)dtoServiceProvider.get().createPoso(tmpDto_displayMode));
		}

		/*  set entryList */
		if(dto.isEntryListModified()){
			final List<CommandResultEntry> col_entryList = new ArrayList<CommandResultEntry>();
			/* load new data from dto */
			Collection<CommandResultEntryDto> tmpCol_entryList = null;
			tmpCol_entryList = dto.getEntryList();

			for(CommandResultEntryDto refDto : tmpCol_entryList){
				col_entryList.add((CommandResultEntry) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setEntryList(col_entryList);
		}

		/*  set extensions */
		if(dto.isExtensionsModified()){
			final List<CommandResultExtension> col_extensions = new ArrayList<CommandResultExtension>();
			/* load new data from dto */
			Collection<CommandResultExtensionDto> tmpCol_extensions = null;
			tmpCol_extensions = dto.getExtensions();

			for(CommandResultExtensionDto refDto : tmpCol_extensions){
				col_extensions.add((CommandResultExtension) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setExtensions(col_extensions);
		}

		/*  set modifiers */
		if(dto.isModifiersModified()){
			final Set<CommandResultModifier> col_modifiers = new HashSet<CommandResultModifier>();
			/* load new data from dto */
			Collection<CommandResultModifierDto> tmpCol_modifiers = dto.getModifiers();

			for(CommandResultModifierDto refDto : tmpCol_modifiers){
				col_modifiers.add((CommandResultModifier) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setModifiers(col_modifiers);
		}

	}

	public void mergeUnmanagedPoso(CommandResultDto dto, final CommandResult poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CommandResultDto dto, final CommandResult poso)  throws ExpectedException {
		/*  set displayMode */
		DisplayModeDto tmpDto_displayMode = dto.getDisplayMode();
		poso.setDisplayMode((DisplayMode)dtoServiceProvider.get().createPoso(tmpDto_displayMode));

		/*  set entryList */
		final List<CommandResultEntry> col_entryList = new ArrayList<CommandResultEntry>();
		/* load new data from dto */
		Collection<CommandResultEntryDto> tmpCol_entryList = dto.getEntryList();

		for(CommandResultEntryDto refDto : tmpCol_entryList){
			col_entryList.add((CommandResultEntry) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setEntryList(col_entryList);

		/*  set extensions */
		final List<CommandResultExtension> col_extensions = new ArrayList<CommandResultExtension>();
		/* load new data from dto */
		Collection<CommandResultExtensionDto> tmpCol_extensions = dto.getExtensions();

		for(CommandResultExtensionDto refDto : tmpCol_extensions){
			col_extensions.add((CommandResultExtension) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setExtensions(col_extensions);

		/*  set modifiers */
		final Set<CommandResultModifier> col_modifiers = new HashSet<CommandResultModifier>();
		/* load new data from dto */
		Collection<CommandResultModifierDto> tmpCol_modifiers = dto.getModifiers();

		for(CommandResultModifierDto refDto : tmpCol_modifiers){
			col_modifiers.add((CommandResultModifier) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setModifiers(col_modifiers);

	}

	protected void mergeProxy2UnmanagedPoso(CommandResultDto dto, final CommandResult poso)  throws ExpectedException {
		/*  set displayMode */
		if(dto.isDisplayModeModified()){
			DisplayModeDto tmpDto_displayMode = dto.getDisplayMode();
			poso.setDisplayMode((DisplayMode)dtoServiceProvider.get().createPoso(tmpDto_displayMode));
		}

		/*  set entryList */
		if(dto.isEntryListModified()){
			final List<CommandResultEntry> col_entryList = new ArrayList<CommandResultEntry>();
			/* load new data from dto */
			Collection<CommandResultEntryDto> tmpCol_entryList = null;
			tmpCol_entryList = dto.getEntryList();

			for(CommandResultEntryDto refDto : tmpCol_entryList){
				col_entryList.add((CommandResultEntry) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setEntryList(col_entryList);
		}

		/*  set extensions */
		if(dto.isExtensionsModified()){
			final List<CommandResultExtension> col_extensions = new ArrayList<CommandResultExtension>();
			/* load new data from dto */
			Collection<CommandResultExtensionDto> tmpCol_extensions = null;
			tmpCol_extensions = dto.getExtensions();

			for(CommandResultExtensionDto refDto : tmpCol_extensions){
				col_extensions.add((CommandResultExtension) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setExtensions(col_extensions);
		}

		/*  set modifiers */
		if(dto.isModifiersModified()){
			final Set<CommandResultModifier> col_modifiers = new HashSet<CommandResultModifier>();
			/* load new data from dto */
			Collection<CommandResultModifierDto> tmpCol_modifiers = dto.getModifiers();

			for(CommandResultModifierDto refDto : tmpCol_modifiers){
				col_modifiers.add((CommandResultModifier) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setModifiers(col_modifiers);
		}

	}

	public CommandResult loadAndMergePoso(CommandResultDto dto)  throws ExpectedException {
		CommandResult poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CommandResultDto dto, CommandResult poso)  {
	}


	public void postProcessCreateUnmanaged(CommandResultDto dto, CommandResult poso)  {
	}


	public void postProcessLoad(CommandResultDto dto, CommandResult poso)  {
	}


	public void postProcessMerge(CommandResultDto dto, CommandResult poso)  {
	}


	public void postProcessInstantiate(CommandResult poso)  {
	}



}
