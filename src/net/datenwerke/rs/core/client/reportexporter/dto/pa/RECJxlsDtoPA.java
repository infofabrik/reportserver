package net.datenwerke.rs.core.client.reportexporter.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto;
import net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.reportmanager.engine.config.RECJxls.class)
public interface RECJxlsDtoPA extends PropertyAccess<RECJxlsDto> {


	public static final RECJxlsDtoPA INSTANCE = GWT.create(RECJxlsDtoPA.class);


	/* Properties */
	public ValueProvider<RECJxlsDto,Integer> currencyColumnWidth();
	public ValueProvider<RECJxlsDto,Integer> dateColumnWidth();
	public ValueProvider<RECJxlsDto,Boolean> jxlsReport();
	public ValueProvider<RECJxlsDto,Integer> numberColumnWidth();
	public ValueProvider<RECJxlsDto,Integer> textColumnWidth();


}
