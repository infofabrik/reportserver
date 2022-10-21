package net.datenwerke.rs.teamspace.client.teamspace.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp.class)
public interface TeamSpaceAppDtoPA extends PropertyAccess<TeamSpaceAppDto> {


	public static final TeamSpaceAppDtoPA INSTANCE = GWT.create(TeamSpaceAppDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<TeamSpaceAppDto> dtoId();

	/* Properties */
	public ValueProvider<TeamSpaceAppDto,Set<AppPropertyDto>> appProperties();
	public ValueProvider<TeamSpaceAppDto,Long> id();
	public ValueProvider<TeamSpaceAppDto,Boolean> installed();
	public ValueProvider<TeamSpaceAppDto,String> type();


}
