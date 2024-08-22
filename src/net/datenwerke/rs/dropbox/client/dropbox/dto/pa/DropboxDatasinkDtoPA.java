package net.datenwerke.rs.dropbox.client.dropbox.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink.class)
public interface DropboxDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final DropboxDatasinkDtoPA INSTANCE = GWT.create(DropboxDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<DropboxDatasinkDto,String> appKey();
	public ValueProvider<DropboxDatasinkDto,String> folder();
	public ValueProvider<DropboxDatasinkDto,String> refreshToken();
	public ValueProvider<DropboxDatasinkDto,String> secretKey();
	public ValueProvider<DropboxDatasinkDto,Boolean> hasRefreshToken();
	public ValueProvider<DropboxDatasinkDto,Boolean> hasSecretKey();


}
