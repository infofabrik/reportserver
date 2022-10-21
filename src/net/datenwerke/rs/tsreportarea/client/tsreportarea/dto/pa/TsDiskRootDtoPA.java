package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.AbstractTsDiskNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot.class)
public interface TsDiskRootDtoPA extends AbstractTsDiskNodeDtoPA {


	public static final TsDiskRootDtoPA INSTANCE = GWT.create(TsDiskRootDtoPA.class);


	/* Properties */
	public ValueProvider<TsDiskRootDto,String> description();
	public ValueProvider<TsDiskRootDto,String> name();
	public ValueProvider<TsDiskRootDto,TeamSpaceDto> teamSpace();


}
