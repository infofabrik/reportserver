package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto;
import net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultEntry;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultModifier;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResult2DtoGenerator;

/**
 * Poso2DtoGenerator for CommandResult
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CommandResult2DtoGenerator implements Poso2DtoGenerator<CommandResult,CommandResultDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CommandResult2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CommandResultDtoDec instantiateDto(CommandResult poso)  {
		CommandResultDtoDec dto = new CommandResultDtoDec();
		return dto;
	}

	public CommandResultDtoDec createDto(CommandResult poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CommandResultDtoDec dto = new CommandResultDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set displayMode */
			Object tmpDtoDisplayModeDtogetDisplayMode = dtoServiceProvider.get().createDto(poso.getDisplayMode(), referenced, referenced);
			dto.setDisplayMode((DisplayModeDto)tmpDtoDisplayModeDtogetDisplayMode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDisplayModeDtogetDisplayMode, poso.getDisplayMode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDisplayMode((DisplayModeDto)refDto);
				}
			});

			/*  set entryList */
			final List<CommandResultEntryDto> col_entryList = new ArrayList<CommandResultEntryDto>();
			if( null != poso.getEntryList()){
				for(CommandResultEntry refPoso : poso.getEntryList()){
					final Object tmpDtoCommandResultEntryDtogetEntryList = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_entryList.add((CommandResultEntryDto) tmpDtoCommandResultEntryDtogetEntryList);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoCommandResultEntryDtogetEntryList, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (entryList)");
							int tmp_index = col_entryList.indexOf(tmpDtoCommandResultEntryDtogetEntryList);
							col_entryList.set(tmp_index,(CommandResultEntryDto) dto);
						}
					});
				}
				dto.setEntryList(col_entryList);
			}

			/*  set extensions */
			final List<CommandResultExtensionDto> col_extensions = new ArrayList<CommandResultExtensionDto>();
			if( null != poso.getExtensions()){
				for(CommandResultExtension refPoso : poso.getExtensions()){
					final Object tmpDtoCommandResultExtensionDtogetExtensions = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_extensions.add((CommandResultExtensionDto) tmpDtoCommandResultExtensionDtogetExtensions);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoCommandResultExtensionDtogetExtensions, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (extensions)");
							int tmp_index = col_extensions.indexOf(tmpDtoCommandResultExtensionDtogetExtensions);
							col_extensions.set(tmp_index,(CommandResultExtensionDto) dto);
						}
					});
				}
				dto.setExtensions(col_extensions);
			}

			/*  set modifiers */
			final Set<CommandResultModifierDto> col_modifiers = new HashSet<CommandResultModifierDto>();
			if( null != poso.getModifiers()){
				for(CommandResultModifier refPoso : poso.getModifiers()){
					final Object tmpDtoCommandResultModifierDtogetModifiers = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_modifiers.add((CommandResultModifierDto) tmpDtoCommandResultModifierDtogetModifiers);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoCommandResultModifierDtogetModifiers, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (modifiers)");
							col_modifiers.remove(tmpDtoCommandResultModifierDtogetModifiers);
							col_modifiers.add((CommandResultModifierDto) dto);
						}
					});
				}
				dto.setModifiers(col_modifiers);
			}

		}

		return dto;
	}


}
