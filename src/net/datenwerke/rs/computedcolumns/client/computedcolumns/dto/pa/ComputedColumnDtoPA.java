package net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.AdditionalColumnSpecDtoPA;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.ComputedColumn.class)
public interface ComputedColumnDtoPA extends AdditionalColumnSpecDtoPA {


	public static final ComputedColumnDtoPA INSTANCE = GWT.create(ComputedColumnDtoPA.class);


	/* Properties */
	public ValueProvider<ComputedColumnDto,String> expression();


}
