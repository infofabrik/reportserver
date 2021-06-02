package net.datenwerke.rs.onedrive.client.onedrive.dto.pa;

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
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink.class)
public interface OneDriveDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final OneDriveDatasinkDtoPA INSTANCE = GWT.create(OneDriveDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<OneDriveDatasinkDto,String> appKey();
	public ValueProvider<OneDriveDatasinkDto,String> folder();
	public ValueProvider<OneDriveDatasinkDto,String> refreshToken();
	public ValueProvider<OneDriveDatasinkDto,String> secretKey();
	public ValueProvider<OneDriveDatasinkDto,Boolean> hasRefreshToken();
	public ValueProvider<OneDriveDatasinkDto,Boolean> hasSecretKey();


}
