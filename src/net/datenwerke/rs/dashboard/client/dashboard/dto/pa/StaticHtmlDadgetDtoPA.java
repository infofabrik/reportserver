package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DadgetDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.dagets.StaticHtmlDadget.class)
public interface StaticHtmlDadgetDtoPA extends DadgetDtoPA {


	public static final StaticHtmlDadgetDtoPA INSTANCE = GWT.create(StaticHtmlDadgetDtoPA.class);


	/* Properties */
	public ValueProvider<StaticHtmlDadgetDto,String> data();
	public ValueProvider<StaticHtmlDadgetDto,String> title();


}
