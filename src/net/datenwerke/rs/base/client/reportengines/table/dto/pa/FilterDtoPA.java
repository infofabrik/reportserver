package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.Long;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter.class)
public interface FilterDtoPA extends PropertyAccess<FilterDto> {


	public static final FilterDtoPA INSTANCE = GWT.create(FilterDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<FilterDto> dtoId();

	/* Properties */
	public ValueProvider<FilterDto,Boolean> caseSensitive();
	public ValueProvider<FilterDto,List<FilterRangeDto>> excludeRanges();
	public ValueProvider<FilterDto,List<String>> excludeValues();
	public ValueProvider<FilterDto,Long> id();
	public ValueProvider<FilterDto,List<FilterRangeDto>> includeRanges();
	public ValueProvider<FilterDto,List<String>> includeValues();


}
