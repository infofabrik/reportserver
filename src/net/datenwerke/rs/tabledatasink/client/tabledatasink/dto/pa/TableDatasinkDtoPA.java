package net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink.class)
public interface TableDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final TableDatasinkDtoPA INSTANCE = GWT.create(TableDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<TableDatasinkDto,Integer> batchSize();
	public ValueProvider<TableDatasinkDto,Boolean> copyPrimaryKeys();
	public ValueProvider<TableDatasinkDto,DatasourceContainerDto> datasourceContainer();
	public ValueProvider<TableDatasinkDto,String> primaryKeys();
	public ValueProvider<TableDatasinkDto,String> tableName();


}
