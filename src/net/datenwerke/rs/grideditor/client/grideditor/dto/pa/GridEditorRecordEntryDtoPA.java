package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecordEntry.class)
public interface GridEditorRecordEntryDtoPA extends PropertyAccess<GridEditorRecordEntryDto> {


	public static final GridEditorRecordEntryDtoPA INSTANCE = GWT.create(GridEditorRecordEntryDtoPA.class);


	/* Properties */
	public ValueProvider<GridEditorRecordEntryDto,Boolean> booleanValue();
	public ValueProvider<GridEditorRecordEntryDto,Date> dateValue();
	public ValueProvider<GridEditorRecordEntryDto,BigDecimal> decimalValue();
	public ValueProvider<GridEditorRecordEntryDto,Double> doubleValue();
	public ValueProvider<GridEditorRecordEntryDto,Boolean> entryNull();
	public ValueProvider<GridEditorRecordEntryDto,Float> floatValue();
	public ValueProvider<GridEditorRecordEntryDto,Integer> intValue();
	public ValueProvider<GridEditorRecordEntryDto,Long> longValue();
	public ValueProvider<GridEditorRecordEntryDto,String> stringValue();
	public ValueProvider<GridEditorRecordEntryDto,Integer> type();


}
