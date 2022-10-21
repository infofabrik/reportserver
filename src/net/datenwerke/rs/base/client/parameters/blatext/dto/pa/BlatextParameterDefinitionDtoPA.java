package net.datenwerke.rs.base.client.parameters.blatext.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.parameters.blatext.BlatextParameterDefinition.class)
public interface BlatextParameterDefinitionDtoPA extends ParameterDefinitionDtoPA {


	public static final BlatextParameterDefinitionDtoPA INSTANCE = GWT.create(BlatextParameterDefinitionDtoPA.class);


	/* Properties */
	public ValueProvider<BlatextParameterDefinitionDto,String> value();


}
