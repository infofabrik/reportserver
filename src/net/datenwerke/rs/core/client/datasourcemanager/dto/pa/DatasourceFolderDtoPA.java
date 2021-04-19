package net.datenwerke.rs.core.client.datasourcemanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.AbstractDatasourceManagerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder.class)
public interface DatasourceFolderDtoPA extends AbstractDatasourceManagerNodeDtoPA {


	public static final DatasourceFolderDtoPA INSTANCE = GWT.create(DatasourceFolderDtoPA.class);


	/* Properties */
	public ValueProvider<DatasourceFolderDto,String> description();
	public ValueProvider<DatasourceFolderDto,String> name();


}
