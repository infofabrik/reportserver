package net.datenwerke.rs.terminal.client.terminal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultTableDtoDec;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultEntryDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultTable.class)
public interface CommandResultTableDtoPA extends CommandResultEntryDtoPA {


	public static final CommandResultTableDtoPA INSTANCE = GWT.create(CommandResultTableDtoPA.class);


	/* Properties */
	public ValueProvider<CommandResultTableDto,RSTableModelDto> table();


}
