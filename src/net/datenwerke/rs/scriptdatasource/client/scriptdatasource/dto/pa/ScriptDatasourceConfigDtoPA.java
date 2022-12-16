package net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceDefinitionConfigDtoPA;
import net.datenwerke.rs.scriptdatasource.client.scriptdatasource.dto.ScriptDatasourceConfigDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.ScriptDatasourceConfig.class)
public interface ScriptDatasourceConfigDtoPA extends DatasourceDefinitionConfigDtoPA {


	public static final ScriptDatasourceConfigDtoPA INSTANCE = GWT.create(ScriptDatasourceConfigDtoPA.class);


	/* Properties */
	public ValueProvider<ScriptDatasourceConfigDto,String> arguments();
	public ValueProvider<ScriptDatasourceConfigDto,String> queryWrapper();
	public ValueProvider<ScriptDatasourceConfigDto,String> script();


}
