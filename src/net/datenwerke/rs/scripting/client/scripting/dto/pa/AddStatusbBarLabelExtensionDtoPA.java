package net.datenwerke.rs.scripting.client.scripting.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scripting.service.scripting.extensions.AddStatusbBarLabelExtension.class)
public interface AddStatusbBarLabelExtensionDtoPA extends CommandResultExtensionDtoPA {


	public static final AddStatusbBarLabelExtensionDtoPA INSTANCE = GWT.create(AddStatusbBarLabelExtensionDtoPA.class);


	/* Properties */
	public ValueProvider<AddStatusbBarLabelExtensionDto,Boolean> clear();
	public ValueProvider<AddStatusbBarLabelExtensionDto,String> icon();
	public ValueProvider<AddStatusbBarLabelExtensionDto,String> label();
	public ValueProvider<AddStatusbBarLabelExtensionDto,Boolean> left();


}
