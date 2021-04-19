package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.Column.class)
public interface ColumnDtoPA extends PropertyAccess<ColumnDto> {


	public static final ColumnDtoPA INSTANCE = GWT.create(ColumnDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<ColumnDto> dtoId();

	/* Properties */
	public ValueProvider<ColumnDto,AggregateFunctionDto> aggregateFunction();
	public ValueProvider<ColumnDto,String> alias();
	public ValueProvider<ColumnDto,String> defaultAlias();
	public ValueProvider<ColumnDto,String> defaultPreviewWidth();
	public ValueProvider<ColumnDto,String> description();
	public ValueProvider<ColumnDto,String> dimension();
	public ValueProvider<ColumnDto,FilterDto> filter();
	public ValueProvider<ColumnDto,ColumnFormatDto> format();
	public ValueProvider<ColumnDto,Boolean> hidden();
	public ValueProvider<ColumnDto,Long> id();
	public ValueProvider<ColumnDto,Boolean> indexColumn();
	public ValueProvider<ColumnDto,String> name();
	public ValueProvider<ColumnDto,NullHandlingDto> nullHandling();
	public ValueProvider<ColumnDto,String> nullReplacementFormat();
	public ValueProvider<ColumnDto,OrderDto> order();
	public ValueProvider<ColumnDto,Integer> previewWidth();
	public ValueProvider<ColumnDto,String> semanticType();
	public ValueProvider<ColumnDto,Boolean> subtotalGroup();
	public ValueProvider<ColumnDto,Integer> type();


}
