package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel.class)
public interface RSTableModelDtoPA extends PropertyAccess<RSTableModelDto> {


	public static final RSTableModelDtoPA INSTANCE = GWT.create(RSTableModelDtoPA.class);


	/* Properties */
	public ValueProvider<RSTableModelDto,List<RSTableRowDto>> data();
	public ValueProvider<RSTableModelDto,TableDefinitionDto> tableDefinition();


}
