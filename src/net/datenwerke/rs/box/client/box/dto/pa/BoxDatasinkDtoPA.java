package net.datenwerke.rs.box.client.box.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.box.service.box.definitions.BoxDatasink.class)
public interface BoxDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final BoxDatasinkDtoPA INSTANCE = GWT.create(BoxDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<BoxDatasinkDto,String> appKey();
	public ValueProvider<BoxDatasinkDto,String> folder();
	public ValueProvider<BoxDatasinkDto,String> refreshToken();
	public ValueProvider<BoxDatasinkDto,String> secretKey();
	public ValueProvider<BoxDatasinkDto,Boolean> hasRefreshToken();
	public ValueProvider<BoxDatasinkDto,Boolean> hasSecretKey();


}
