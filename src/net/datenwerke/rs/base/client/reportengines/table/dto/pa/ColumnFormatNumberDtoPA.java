package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFormatDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber.class)
public interface ColumnFormatNumberDtoPA extends ColumnFormatDtoPA {


	public static final ColumnFormatNumberDtoPA INSTANCE = GWT.create(ColumnFormatNumberDtoPA.class);


	/* Properties */
	public ValueProvider<ColumnFormatNumberDto,Integer> numberOfDecimalPlaces();
	public ValueProvider<ColumnFormatNumberDto,Boolean> thousandSeparator();
	public ValueProvider<ColumnFormatNumberDto,NumberTypeDto> type();


}
