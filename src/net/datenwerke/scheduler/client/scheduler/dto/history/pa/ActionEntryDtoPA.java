package net.datenwerke.scheduler.client.scheduler.dto.history.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry.class)
public interface ActionEntryDtoPA extends PropertyAccess<ActionEntryDto> {


	public static final ActionEntryDtoPA INSTANCE = GWT.create(ActionEntryDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<ActionEntryDto> dtoId();

	/* Properties */
	public ValueProvider<ActionEntryDto,String> actionName();
	public ValueProvider<ActionEntryDto,String> errorDescription();
	public ValueProvider<ActionEntryDto,Long> id();
	public ValueProvider<ActionEntryDto,OutcomeDto> outcome();


}
