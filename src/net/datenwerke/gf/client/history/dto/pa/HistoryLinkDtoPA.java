package net.datenwerke.gf.client.history.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.gf.service.history.HistoryLink.class)
public interface HistoryLinkDtoPA extends PropertyAccess<HistoryLinkDto> {


	public static final HistoryLinkDtoPA INSTANCE = GWT.create(HistoryLinkDtoPA.class);


	/* Properties */
	public ValueProvider<HistoryLinkDto,String> historyLinkBuilderIcon();
	public ValueProvider<HistoryLinkDto,String> historyLinkBuilderId();
	public ValueProvider<HistoryLinkDto,String> historyLinkBuilderName();
	public ValueProvider<HistoryLinkDto,String> historyToken();
	public ValueProvider<HistoryLinkDto,String> objectCaption();


}
