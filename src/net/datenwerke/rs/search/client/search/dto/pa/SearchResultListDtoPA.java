package net.datenwerke.rs.search.client.search.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.search.service.search.results.SearchResultList.class)
public interface SearchResultListDtoPA extends PropertyAccess<SearchResultListDto> {


	public static final SearchResultListDtoPA INSTANCE = GWT.create(SearchResultListDtoPA.class);


	/* Properties */
	public ValueProvider<SearchResultListDto,List<SearchResultEntryDto>> data();
	public ValueProvider<SearchResultListDto,Integer> offset();
	public ValueProvider<SearchResultListDto,Set<SearchResultTagDto>> tags();
	public ValueProvider<SearchResultListDto,Integer> totalLength();


}
