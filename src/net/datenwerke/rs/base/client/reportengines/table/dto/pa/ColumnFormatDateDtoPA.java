package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFormatDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate.class)
public interface ColumnFormatDateDtoPA extends ColumnFormatDtoPA {


	public static final ColumnFormatDateDtoPA INSTANCE = GWT.create(ColumnFormatDateDtoPA.class);


	/* Properties */
	public ValueProvider<ColumnFormatDateDto,String> baseFormat();
	public ValueProvider<ColumnFormatDateDto,String> errorReplacement();
	public ValueProvider<ColumnFormatDateDto,Boolean> replaceErrors();
	public ValueProvider<ColumnFormatDateDto,Boolean> rollOver();
	public ValueProvider<ColumnFormatDateDto,String> targetFormat();


}
