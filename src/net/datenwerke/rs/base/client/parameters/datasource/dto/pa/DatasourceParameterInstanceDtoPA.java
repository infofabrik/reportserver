package net.datenwerke.rs.base.client.parameters.datasource.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterInstanceDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterInstance.class)
public interface DatasourceParameterInstanceDtoPA extends ParameterInstanceDtoPA {


	public static final DatasourceParameterInstanceDtoPA INSTANCE = GWT.create(DatasourceParameterInstanceDtoPA.class);


	/* Properties */
	public ValueProvider<DatasourceParameterInstanceDto,List<DatasourceParameterDataDto>> multiValue();
	public ValueProvider<DatasourceParameterInstanceDto,DatasourceParameterDataDto> singleValue();


}
