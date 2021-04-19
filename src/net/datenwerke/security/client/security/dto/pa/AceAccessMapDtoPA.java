package net.datenwerke.security.client.security.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.security.entities.AceAccessMap.class)
public interface AceAccessMapDtoPA extends PropertyAccess<AceAccessMapDto> {


	public static final AceAccessMapDtoPA INSTANCE = GWT.create(AceAccessMapDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<AceAccessMapDto> dtoId();

	/* Properties */
	public ValueProvider<AceAccessMapDto,Long> access();
	public ValueProvider<AceAccessMapDto,Long> id();
	public ValueProvider<AceAccessMapDto,String> securee();


}
