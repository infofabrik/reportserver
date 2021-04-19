package net.datenwerke.rs.base.client.parameters.datasource.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData.class)
public interface DatasourceParameterDataDtoPA extends PropertyAccess<DatasourceParameterDataDto> {


	public static final DatasourceParameterDataDtoPA INSTANCE = GWT.create(DatasourceParameterDataDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DatasourceParameterDataDto> dtoId();

	/* Properties */
	public ValueProvider<DatasourceParameterDataDto,Long> id();
	public ValueProvider<DatasourceParameterDataDto,String> key();
	public ValueProvider<DatasourceParameterDataDto,String> value();


}
