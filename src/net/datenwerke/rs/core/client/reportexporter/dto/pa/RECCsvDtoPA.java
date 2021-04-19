package net.datenwerke.rs.core.client.reportexporter.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto;
import net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECCsvDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv.class)
public interface RECCsvDtoPA extends PropertyAccess<RECCsvDto> {


	public static final RECCsvDtoPA INSTANCE = GWT.create(RECCsvDtoPA.class);


	/* Properties */
	public ValueProvider<RECCsvDto,String> charset();
	public ValueProvider<RECCsvDto,String> lineSeparator();
	public ValueProvider<RECCsvDto,Boolean> printHeader();
	public ValueProvider<RECCsvDto,String> quote();
	public ValueProvider<RECCsvDto,String> separator();


}
