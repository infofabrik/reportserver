package net.datenwerke.rs.terminal.client.terminal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto;
import net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.terminal.service.terminal.obj.CommandResult.class)
public interface CommandResultDtoPA extends PropertyAccess<CommandResultDto> {


	public static final CommandResultDtoPA INSTANCE = GWT.create(CommandResultDtoPA.class);


	/* Properties */
	public ValueProvider<CommandResultDto,DisplayModeDto> displayMode();
	public ValueProvider<CommandResultDto,List<CommandResultEntryDto>> entryList();
	public ValueProvider<CommandResultDto,List<CommandResultExtensionDto>> extensions();
	public ValueProvider<CommandResultDto,Set<CommandResultModifierDto>> modifiers();


}
