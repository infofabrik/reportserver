package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.FilterSpecDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter.class)
public interface BinaryColumnFilterDtoPA extends FilterSpecDtoPA {


	public static final BinaryColumnFilterDtoPA INSTANCE = GWT.create(BinaryColumnFilterDtoPA.class);


	/* Properties */
	public ValueProvider<BinaryColumnFilterDto,ColumnDto> columnA();
	public ValueProvider<BinaryColumnFilterDto,ColumnDto> columnB();
	public ValueProvider<BinaryColumnFilterDto,BinaryOperatorDto> operator();


}
