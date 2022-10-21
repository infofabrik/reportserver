package net.datenwerke.rs.base.client.parameters.datasource.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition.class)
public interface DatasourceParameterDefinitionDtoPA extends ParameterDefinitionDtoPA {


	public static final DatasourceParameterDefinitionDtoPA INSTANCE = GWT.create(DatasourceParameterDefinitionDtoPA.class);


	/* Properties */
	public ValueProvider<DatasourceParameterDefinitionDto,BoxLayoutModeDto> boxLayoutMode();
	public ValueProvider<DatasourceParameterDefinitionDto,Integer> boxLayoutPackColSize();
	public ValueProvider<DatasourceParameterDefinitionDto,BoxLayoutPackModeDto> boxLayoutPackMode();
	public ValueProvider<DatasourceParameterDefinitionDto,DatasourceContainerDto> datasourceContainer();
	public ValueProvider<DatasourceParameterDefinitionDto,String> format();
	public ValueProvider<DatasourceParameterDefinitionDto,Integer> height();
	public ValueProvider<DatasourceParameterDefinitionDto,ModeDto> mode();
	public ValueProvider<DatasourceParameterDefinitionDto,List<DatasourceParameterDataDto>> multiDefaultValueSimpleData();
	public ValueProvider<DatasourceParameterDefinitionDto,MultiSelectionModeDto> multiSelectionMode();
	public ValueProvider<DatasourceParameterDefinitionDto,String> postProcess();
	public ValueProvider<DatasourceParameterDefinitionDto,DatatypeDto> returnType();
	public ValueProvider<DatasourceParameterDefinitionDto,DatasourceParameterDataDto> singleDefaultValueSimpleData();
	public ValueProvider<DatasourceParameterDefinitionDto,SingleSelectionModeDto> singleSelectionMode();
	public ValueProvider<DatasourceParameterDefinitionDto,Integer> width();


}
