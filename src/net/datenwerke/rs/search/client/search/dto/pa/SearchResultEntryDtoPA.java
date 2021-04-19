package net.datenwerke.rs.search.client.search.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.search.service.search.results.SearchResultEntry.class)
public interface SearchResultEntryDtoPA extends PropertyAccess<SearchResultEntryDto> {


	public static final SearchResultEntryDtoPA INSTANCE = GWT.create(SearchResultEntryDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<SearchResultEntryDto> dtoId();

	/* Properties */
	public ValueProvider<SearchResultEntryDto,String> description();
	public ValueProvider<SearchResultEntryDto,String> iconSmall();
	public ValueProvider<SearchResultEntryDto,Long> id();
	public ValueProvider<SearchResultEntryDto,Date> lastModified();
	public ValueProvider<SearchResultEntryDto,List<HistoryLinkDto>> links();
	public ValueProvider<SearchResultEntryDto,Long> objectId();
	public ValueProvider<SearchResultEntryDto,Set<SearchResultTagDto>> tags();
	public ValueProvider<SearchResultEntryDto,String> title();
	public ValueProvider<SearchResultEntryDto,DwModel> resultObject();


}
