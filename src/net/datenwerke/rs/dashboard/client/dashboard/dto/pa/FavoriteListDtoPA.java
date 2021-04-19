package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList.class)
public interface FavoriteListDtoPA extends PropertyAccess<FavoriteListDto> {


	public static final FavoriteListDtoPA INSTANCE = GWT.create(FavoriteListDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<FavoriteListDto> dtoId();

	/* Properties */
	public ValueProvider<FavoriteListDto,Long> id();
	public ValueProvider<FavoriteListDto,List<FavoriteListEntryDto>> referenceEntries();


}
