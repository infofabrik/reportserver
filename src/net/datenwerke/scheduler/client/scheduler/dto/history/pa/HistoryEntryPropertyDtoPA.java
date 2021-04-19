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
import net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.scheduler.service.scheduler.entities.history.HistoryEntryProperty.class)
public interface HistoryEntryPropertyDtoPA extends PropertyAccess<HistoryEntryPropertyDto> {


	public static final HistoryEntryPropertyDtoPA INSTANCE = GWT.create(HistoryEntryPropertyDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<HistoryEntryPropertyDto> dtoId();

	/* Properties */
	public ValueProvider<HistoryEntryPropertyDto,Long> id();
	public ValueProvider<HistoryEntryPropertyDto,String> key();
	public ValueProvider<HistoryEntryPropertyDto,String> value();


}
