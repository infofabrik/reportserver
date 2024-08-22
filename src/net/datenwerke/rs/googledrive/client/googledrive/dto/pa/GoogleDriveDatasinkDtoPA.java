package net.datenwerke.rs.googledrive.client.googledrive.dto.pa;

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
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink.class)
public interface GoogleDriveDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final GoogleDriveDatasinkDtoPA INSTANCE = GWT.create(GoogleDriveDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<GoogleDriveDatasinkDto,String> appKey();
	public ValueProvider<GoogleDriveDatasinkDto,String> folder();
	public ValueProvider<GoogleDriveDatasinkDto,String> refreshToken();
	public ValueProvider<GoogleDriveDatasinkDto,String> secretKey();
	public ValueProvider<GoogleDriveDatasinkDto,Boolean> hasRefreshToken();
	public ValueProvider<GoogleDriveDatasinkDto,Boolean> hasSecretKey();


}
