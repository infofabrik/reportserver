package net.datenwerke.security.client.security.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.security.dto.AccessTypeDto;
import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.security.dto.decorator.AceDtoDec;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.security.entities.Ace.class)
public interface AceDtoPA extends PropertyAccess<AceDto> {


	public static final AceDtoPA INSTANCE = GWT.create(AceDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<AceDto> dtoId();

	/* Properties */
	public ValueProvider<AceDto,Set<AceAccessMapDto>> accessMaps();
	public ValueProvider<AceDto,AccessTypeDto> accesstype();
	public ValueProvider<AceDto,AbstractUserManagerNodeDto> folk();
	public ValueProvider<AceDto,Long> id();
	public ValueProvider<AceDto,Integer> n();


}
