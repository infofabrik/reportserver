package net.datenwerke.rs.saiku.client.datasource.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionDtoPA;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.saiku.service.datasource.MondrianDatasource.class)
public interface MondrianDatasourceDtoPA extends DatasourceDefinitionDtoPA {


	public static final MondrianDatasourceDtoPA INSTANCE = GWT.create(MondrianDatasourceDtoPA.class);


	/* Properties */
	public ValueProvider<MondrianDatasourceDto,String> mondrianSchema();
	public ValueProvider<MondrianDatasourceDto,String> password();
	public ValueProvider<MondrianDatasourceDto,String> properties();
	public ValueProvider<MondrianDatasourceDto,String> url();
	public ValueProvider<MondrianDatasourceDto,String> username();
	public ValueProvider<MondrianDatasourceDto,Boolean> hasPassword();


}
