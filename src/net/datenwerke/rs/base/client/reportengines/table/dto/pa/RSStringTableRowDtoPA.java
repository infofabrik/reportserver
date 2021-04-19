package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.RSTableRowDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow.class)
public interface RSStringTableRowDtoPA extends RSTableRowDtoPA {


	public static final RSStringTableRowDtoPA INSTANCE = GWT.create(RSStringTableRowDtoPA.class);


	/* Properties */
	public ValueProvider<RSStringTableRowDto,List<String>> stringRow();


}
