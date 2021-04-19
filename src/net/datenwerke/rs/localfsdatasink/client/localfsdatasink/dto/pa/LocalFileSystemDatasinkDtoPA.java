package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink.class)
public interface LocalFileSystemDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final LocalFileSystemDatasinkDtoPA INSTANCE = GWT.create(LocalFileSystemDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<LocalFileSystemDatasinkDto,String> folder();
	public ValueProvider<LocalFileSystemDatasinkDto,String> path();


}
