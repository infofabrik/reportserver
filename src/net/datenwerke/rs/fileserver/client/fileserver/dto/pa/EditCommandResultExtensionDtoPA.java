package net.datenwerke.rs.fileserver.client.fileserver.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.EditCommandResultExtension.class)
public interface EditCommandResultExtensionDtoPA extends CommandResultExtensionDtoPA {


	public static final EditCommandResultExtensionDtoPA INSTANCE = GWT.create(EditCommandResultExtensionDtoPA.class);


	/* Properties */
	public ValueProvider<EditCommandResultExtensionDto,String> data();
	public ValueProvider<EditCommandResultExtensionDto,FileServerFileDto> file();


}
