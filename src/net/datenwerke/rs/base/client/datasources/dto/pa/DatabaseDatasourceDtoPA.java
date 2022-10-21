package net.datenwerke.rs.base.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource.class)
public interface DatabaseDatasourceDtoPA extends DatasourceDefinitionDtoPA {


	public static final DatabaseDatasourceDtoPA INSTANCE = GWT.create(DatabaseDatasourceDtoPA.class);


	/* Properties */
	public ValueProvider<DatabaseDatasourceDto,String> databaseDescriptor();
	public ValueProvider<DatabaseDatasourceDto,String> jdbcProperties();
	public ValueProvider<DatabaseDatasourceDto,String> password();
	public ValueProvider<DatabaseDatasourceDto,String> url();
	public ValueProvider<DatabaseDatasourceDto,String> username();
	public ValueProvider<DatabaseDatasourceDto,Boolean> hasPassword();


}
