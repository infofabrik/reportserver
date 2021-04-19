package net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthsDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.YearlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.DateTriggerConfigDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyNthDayOfWeekConfig.class)
public interface YearlyNthDayOfWeekConfigDtoPA extends DateTriggerConfigDtoPA {


	public static final YearlyNthDayOfWeekConfigDtoPA INSTANCE = GWT.create(YearlyNthDayOfWeekConfigDtoPA.class);


	/* Properties */
	public ValueProvider<YearlyNthDayOfWeekConfigDto,DaysDto> yearlyDay();
	public ValueProvider<YearlyNthDayOfWeekConfigDto,MonthsDto> yearlyMonth();
	public ValueProvider<YearlyNthDayOfWeekConfigDto,NthDto> yearlyNth();


}
