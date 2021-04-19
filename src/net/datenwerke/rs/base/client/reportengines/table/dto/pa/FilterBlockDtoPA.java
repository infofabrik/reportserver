package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock.class)
public interface FilterBlockDtoPA extends PropertyAccess<FilterBlockDto> {


	public static final FilterBlockDtoPA INSTANCE = GWT.create(FilterBlockDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<FilterBlockDto> dtoId();

	/* Properties */
	public ValueProvider<FilterBlockDto,Set<FilterBlockDto>> childBlocks();
	public ValueProvider<FilterBlockDto,String> description();
	public ValueProvider<FilterBlockDto,Set<FilterSpecDto>> filters();
	public ValueProvider<FilterBlockDto,Long> id();


}
