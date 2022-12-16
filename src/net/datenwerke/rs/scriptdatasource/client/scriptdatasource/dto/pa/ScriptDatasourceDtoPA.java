package net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.ScriptDatasource.class)
public interface ScriptDatasourceDtoPA extends DatasourceDefinitionDtoPA {


	public static final ScriptDatasourceDtoPA INSTANCE = GWT.create(ScriptDatasourceDtoPA.class);


	/* Properties */
	public ValueProvider<ScriptDatasourceDto,Integer> databaseCache();
	public ValueProvider<ScriptDatasourceDto,Boolean> defineAtTarget();
	public ValueProvider<ScriptDatasourceDto,FileServerFileDto> script();


}
