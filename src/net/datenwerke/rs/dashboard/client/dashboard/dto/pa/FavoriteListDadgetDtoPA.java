package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DadgetDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListDadget.class)
public interface FavoriteListDadgetDtoPA extends DadgetDtoPA {


	public static final FavoriteListDadgetDtoPA INSTANCE = GWT.create(FavoriteListDadgetDtoPA.class);


	/* Properties */


}
