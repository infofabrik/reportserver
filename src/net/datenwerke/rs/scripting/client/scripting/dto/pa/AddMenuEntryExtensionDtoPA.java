package net.datenwerke.rs.scripting.client.scripting.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuEntryExtension.class)
public interface AddMenuEntryExtensionDtoPA extends CommandResultExtensionDtoPA {


	public static final AddMenuEntryExtensionDtoPA INSTANCE = GWT.create(AddMenuEntryExtensionDtoPA.class);


	/* Properties */
	public ValueProvider<AddMenuEntryExtensionDto,String> arguments();
	public ValueProvider<AddMenuEntryExtensionDto,List<DisplayConditionDto>> displayConditions();
	public ValueProvider<AddMenuEntryExtensionDto,String> icon();
	public ValueProvider<AddMenuEntryExtensionDto,String> javaScript();
	public ValueProvider<AddMenuEntryExtensionDto,String> label();
	public ValueProvider<AddMenuEntryExtensionDto,String> menuName();
	public ValueProvider<AddMenuEntryExtensionDto,String> scriptLocation();
	public ValueProvider<AddMenuEntryExtensionDto,List<AddMenuEntryExtensionDto>> subMenuEntries();


}
