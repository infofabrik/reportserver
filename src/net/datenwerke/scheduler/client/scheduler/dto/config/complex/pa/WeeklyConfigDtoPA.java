package net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Integer;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.WeeklyConfigDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa.DateTriggerConfigDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.WeeklyConfig.class)
public interface WeeklyConfigDtoPA extends DateTriggerConfigDtoPA {


	public static final WeeklyConfigDtoPA INSTANCE = GWT.create(WeeklyConfigDtoPA.class);


	/* Properties */
	public ValueProvider<WeeklyConfigDto,Set<DaysDto>> weeklyDays();
	public ValueProvider<WeeklyConfigDto,Integer> weeklyN();


}
