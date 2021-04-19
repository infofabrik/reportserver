package net.datenwerke.rs.saiku.client.saiku.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart.class)
public interface RECSaikuChartDtoPA extends PropertyAccess<RECSaikuChartDto> {


	public static final RECSaikuChartDtoPA INSTANCE = GWT.create(RECSaikuChartDtoPA.class);


	/* Properties */
	public ValueProvider<RECSaikuChartDto,String> type();


}
