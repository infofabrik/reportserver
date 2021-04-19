package net.datenwerke.rs.base.client.datasources.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.FormatBasedDatasourceConfigDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.datasources.definitions.CsvDatasourceConfig.class)
public interface CsvDatasourceConfigDtoPA extends FormatBasedDatasourceConfigDtoPA {


	public static final CsvDatasourceConfigDtoPA INSTANCE = GWT.create(CsvDatasourceConfigDtoPA.class);


	/* Properties */
	public ValueProvider<CsvDatasourceConfigDto,String> queryWrapper();


}
