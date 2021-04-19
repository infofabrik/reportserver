package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange.class)
public interface FilterRangeDtoPA extends PropertyAccess<FilterRangeDto> {


	public static final FilterRangeDtoPA INSTANCE = GWT.create(FilterRangeDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<FilterRangeDto> dtoId();

	/* Properties */
	public ValueProvider<FilterRangeDto,Long> id();
	public ValueProvider<FilterRangeDto,String> rangeFrom();
	public ValueProvider<FilterRangeDto,String> rangeTo();


}
