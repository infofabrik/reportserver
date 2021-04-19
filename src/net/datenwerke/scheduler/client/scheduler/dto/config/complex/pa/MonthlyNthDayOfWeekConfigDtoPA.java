package net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Integer;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.MonthlyNthDayOfWeekConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.NthDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.DateTriggerConfigDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayOfWeekConfig.class)
public interface MonthlyNthDayOfWeekConfigDtoPA extends DateTriggerConfigDtoPA {


	public static final MonthlyNthDayOfWeekConfigDtoPA INSTANCE = GWT.create(MonthlyNthDayOfWeekConfigDtoPA.class);


	/* Properties */
	public ValueProvider<MonthlyNthDayOfWeekConfigDto,Integer> month();
	public ValueProvider<MonthlyNthDayOfWeekConfigDto,DaysDto> monthlyDay();
	public ValueProvider<MonthlyNthDayOfWeekConfigDto,NthDto> monthlyNth();


}
