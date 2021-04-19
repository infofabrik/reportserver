package net.datenwerke.security.client.usermanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.pa.AbstractUserManagerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.usermanager.entities.OrganisationalUnit.class)
public interface OrganisationalUnitDtoPA extends AbstractUserManagerNodeDtoPA {


	public static final OrganisationalUnitDtoPA INSTANCE = GWT.create(OrganisationalUnitDtoPA.class);


	/* Properties */
	public ValueProvider<OrganisationalUnitDto,String> description();
	public ValueProvider<OrganisationalUnitDto,String> name();
	public ValueProvider<OrganisationalUnitDto,Boolean> isUserRoot();


}
