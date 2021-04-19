package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.UrlDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.UrlDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DadgetDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.dagets.UrlDadget.class)
public interface UrlDadgetDtoPA extends DadgetDtoPA {


	public static final UrlDadgetDtoPA INSTANCE = GWT.create(UrlDadgetDtoPA.class);


	/* Properties */
	public ValueProvider<UrlDadgetDto,String> title();
	public ValueProvider<UrlDadgetDto,String> url();


}
