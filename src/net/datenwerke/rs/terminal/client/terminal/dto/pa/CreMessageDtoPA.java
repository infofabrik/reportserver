package net.datenwerke.rs.terminal.client.terminal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultExtensionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.terminal.service.terminal.obj.CreMessage.class)
public interface CreMessageDtoPA extends CommandResultExtensionDtoPA {


	public static final CreMessageDtoPA INSTANCE = GWT.create(CreMessageDtoPA.class);


	/* Properties */
	public ValueProvider<CreMessageDto,Integer> height();
	public ValueProvider<CreMessageDto,String> html();
	public ValueProvider<CreMessageDto,String> text();
	public ValueProvider<CreMessageDto,String> title();
	public ValueProvider<CreMessageDto,Integer> width();
	public ValueProvider<CreMessageDto,String> windowTitle();


}
