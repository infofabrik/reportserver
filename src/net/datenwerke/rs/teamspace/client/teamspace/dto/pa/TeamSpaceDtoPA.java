package net.datenwerke.rs.teamspace.client.teamspace.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec;
import net.datenwerke.security.client.usermanager.dto.UserDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace.class)
public interface TeamSpaceDtoPA extends PropertyAccess<TeamSpaceDto> {


	public static final TeamSpaceDtoPA INSTANCE = GWT.create(TeamSpaceDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<TeamSpaceDto> dtoId();

	/* Properties */
	public ValueProvider<TeamSpaceDto,Set<TeamSpaceAppDto>> apps();
	public ValueProvider<TeamSpaceDto,String> description();
	public ValueProvider<TeamSpaceDto,Long> id();
	public ValueProvider<TeamSpaceDto,Set<TeamSpaceMemberDto>> members();
	public ValueProvider<TeamSpaceDto,String> name();
	public ValueProvider<TeamSpaceDto,UserDto> owner();


}
