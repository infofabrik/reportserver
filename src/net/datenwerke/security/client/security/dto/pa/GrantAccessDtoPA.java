package net.datenwerke.security.client.security.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.security.dto.GrantAccessDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.security.rights.GrantAccess.class)
public interface GrantAccessDtoPA extends PropertyAccess<GrantAccessDto> {


	public static final GrantAccessDtoPA INSTANCE = GWT.create(GrantAccessDtoPA.class);


	/* Properties */
	public ValueProvider<GrantAccessDto,String> abbreviation();
	public ValueProvider<GrantAccessDto,Long> bitField();
	public ValueProvider<GrantAccessDto,String> description();


}
