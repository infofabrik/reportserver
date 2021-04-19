package net.datenwerke.rs.adminutils.client.logs.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.adminutils.service.logs.terminal.commands.ViewLogFileCommandResultExtension.class)
public interface ViewLogFileCommandResultExtensionDtoPA extends CommandResultExtensionDtoPA {


	public static final ViewLogFileCommandResultExtensionDtoPA INSTANCE = GWT.create(ViewLogFileCommandResultExtensionDtoPA.class);


	/* Properties */
	public ValueProvider<ViewLogFileCommandResultExtensionDto,List<String>> data();
	public ValueProvider<ViewLogFileCommandResultExtensionDto,String> filename();


}
