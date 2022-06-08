package net.datenwerke.rs.printer.client.printer.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;
import net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink.class)
public interface PrinterDatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final PrinterDatasinkDtoPA INSTANCE = GWT.create(PrinterDatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<PrinterDatasinkDto,String> printerName();


}
