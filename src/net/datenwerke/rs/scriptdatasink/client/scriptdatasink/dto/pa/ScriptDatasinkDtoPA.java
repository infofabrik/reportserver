package net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.dto.ScriptDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scriptdatasink.service.scriptdatasink.definitions.ScriptDatasink.class)
public interface ScriptDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final ScriptDatasinkDtoPA INSTANCE = GWT.create(ScriptDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<ScriptDatasinkDto,FileServerFileDto> script();


}
