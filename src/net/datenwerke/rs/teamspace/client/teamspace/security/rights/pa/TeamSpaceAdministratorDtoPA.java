package net.datenwerke.rs.teamspace.client.teamspace.security.rights.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.teamspace.service.teamspace.security.rights.TeamSpaceAdministrator.class)
public interface TeamSpaceAdministratorDtoPA extends PropertyAccess<TeamSpaceAdministratorDto> {


	public static final TeamSpaceAdministratorDtoPA INSTANCE = GWT.create(TeamSpaceAdministratorDtoPA.class);


	/* Properties */
	public ValueProvider<TeamSpaceAdministratorDto,String> abbreviation();
	public ValueProvider<TeamSpaceAdministratorDto,Long> bitField();
	public ValueProvider<TeamSpaceAdministratorDto,String> description();


}
