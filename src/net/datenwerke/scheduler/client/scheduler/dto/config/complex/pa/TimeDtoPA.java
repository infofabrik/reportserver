package net.datenwerke.scheduler.client.scheduler.dto.config.complex.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Integer;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time.class)
public interface TimeDtoPA extends PropertyAccess<TimeDto> {


	public static final TimeDtoPA INSTANCE = GWT.create(TimeDtoPA.class);


	/* Properties */
	public ValueProvider<TimeDto,Integer> hour();
	public ValueProvider<TimeDto,Integer> minutes();


}
