package net.datenwerke.rs.core.client.datasinkmanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.AbstractDatasinkManagerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder.class)
public interface DatasinkFolderDtoPA extends AbstractDatasinkManagerNodeDtoPA {


	public static final DatasinkFolderDtoPA INSTANCE = GWT.create(DatasinkFolderDtoPA.class);


	/* Properties */
	public ValueProvider<DatasinkFolderDto,String> description();
	public ValueProvider<DatasinkFolderDto,String> name();


}
