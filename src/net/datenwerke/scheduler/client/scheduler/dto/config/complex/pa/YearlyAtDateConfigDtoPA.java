package net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Integer;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyAtDateConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.DateTriggerConfigDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyAtDateConfig.class)
public interface YearlyAtDateConfigDtoPA extends DateTriggerConfigDtoPA {


	public static final YearlyAtDateConfigDtoPA INSTANCE = GWT.create(YearlyAtDateConfigDtoPA.class);


	/* Properties */
	public ValueProvider<YearlyAtDateConfigDto,MonthsDto> yearlyMonth();
	public ValueProvider<YearlyAtDateConfigDto,Integer> yearlyNDay();


}
