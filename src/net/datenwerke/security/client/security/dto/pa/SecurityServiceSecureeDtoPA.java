package net.datenwerke.security.client.security.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.security.dto.SecurityServiceSecureeDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.security.SecurityServiceSecuree.class)
public interface SecurityServiceSecureeDtoPA extends PropertyAccess<SecurityServiceSecureeDto> {


	public static final SecurityServiceSecureeDtoPA INSTANCE = GWT.create(SecurityServiceSecureeDtoPA.class);


	/* Properties */
	public ValueProvider<SecurityServiceSecureeDto,String> name();
	public ValueProvider<SecurityServiceSecureeDto,List<RightDto>> rights();
	public ValueProvider<SecurityServiceSecureeDto,String> secureeId();


}
