package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFormatNumberDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency.class)
public interface ColumnFormatCurrencyDtoPA extends ColumnFormatNumberDtoPA {


	public static final ColumnFormatCurrencyDtoPA INSTANCE = GWT.create(ColumnFormatCurrencyDtoPA.class);


	/* Properties */
	public ValueProvider<ColumnFormatCurrencyDto,CurrencyTypeDto> currencyType();


}
