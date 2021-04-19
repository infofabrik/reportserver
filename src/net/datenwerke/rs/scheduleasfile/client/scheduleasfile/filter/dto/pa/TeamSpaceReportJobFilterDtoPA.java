package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter.TeamSpaceReportJobFilter.class)
public interface TeamSpaceReportJobFilterDtoPA extends PropertyAccess<TeamSpaceReportJobFilterDto> {


	public static final TeamSpaceReportJobFilterDtoPA INSTANCE = GWT.create(TeamSpaceReportJobFilterDtoPA.class);


	/* Properties */
	public ValueProvider<TeamSpaceReportJobFilterDto,Long> teamspaceId();


}
