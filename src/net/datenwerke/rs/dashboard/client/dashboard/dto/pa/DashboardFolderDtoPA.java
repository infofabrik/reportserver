package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.AbstractDashboardManagerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardFolder.class)
public interface DashboardFolderDtoPA extends AbstractDashboardManagerNodeDtoPA {


	public static final DashboardFolderDtoPA INSTANCE = GWT.create(DashboardFolderDtoPA.class);


	/* Properties */
	public ValueProvider<DashboardFolderDto,String> description();
	public ValueProvider<DashboardFolderDto,String> name();


}
