package net.datenwerke.rs.teamspace.client.teamspace.security.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto;
import net.datenwerke.security.client.security.dto.RightDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.teamspace.service.teamspace.security.TeamSpaceSecuree.class)
public interface TeamSpaceSecureeDtoPA extends PropertyAccess<TeamSpaceSecureeDto> {


	public static final TeamSpaceSecureeDtoPA INSTANCE = GWT.create(TeamSpaceSecureeDtoPA.class);


	/* Properties */
	public ValueProvider<TeamSpaceSecureeDto,String> name();
	public ValueProvider<TeamSpaceSecureeDto,List<RightDto>> rights();
	public ValueProvider<TeamSpaceSecureeDto,String> secureeId();


}
