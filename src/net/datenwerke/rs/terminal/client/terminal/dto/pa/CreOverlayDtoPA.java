package net.datenwerke.rs.terminal.client.terminal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.Map;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.terminal.service.terminal.obj.CreOverlay.class)
public interface CreOverlayDtoPA extends CommandResultExtensionDtoPA {


	public static final CreOverlayDtoPA INSTANCE = GWT.create(CreOverlayDtoPA.class);


	/* Properties */
	public ValueProvider<CreOverlayDto,Map<String, String>> cssProperties();
	public ValueProvider<CreOverlayDto,String> name();
	public ValueProvider<CreOverlayDto,Boolean> remove();
	public ValueProvider<CreOverlayDto,String> text();


}
