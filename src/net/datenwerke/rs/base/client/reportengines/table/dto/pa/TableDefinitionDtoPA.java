package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.HashMap;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition.class)
public interface TableDefinitionDtoPA extends PropertyAccess<TableDefinitionDto> {


	public static final TableDefinitionDtoPA INSTANCE = GWT.create(TableDefinitionDtoPA.class);


	/* Properties */
	public ValueProvider<TableDefinitionDto,HashMap<String, Integer>> columnIndex();
	public ValueProvider<TableDefinitionDto,List<String>> columnNames();
	public ValueProvider<TableDefinitionDto,List<Integer>> displaySizes();
	public ValueProvider<TableDefinitionDto,List<Integer>> sqlColumnTypes();


}
