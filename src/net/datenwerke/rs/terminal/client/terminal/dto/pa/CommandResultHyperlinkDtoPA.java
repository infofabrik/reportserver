package net.datenwerke.rs.terminal.client.terminal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultEntryDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHyperlink.class)
public interface CommandResultHyperlinkDtoPA extends CommandResultEntryDtoPA {


	public static final CommandResultHyperlinkDtoPA INSTANCE = GWT.create(CommandResultHyperlinkDtoPA.class);


	/* Properties */
	public ValueProvider<CommandResultHyperlinkDto,String> caption();
	public ValueProvider<CommandResultHyperlinkDto,String> historyToken();


}
