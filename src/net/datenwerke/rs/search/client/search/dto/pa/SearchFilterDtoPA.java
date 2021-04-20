package net.datenwerke.rs.search.client.search.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.search.service.search.results.SearchFilter.class)
public interface SearchFilterDtoPA extends PropertyAccess<SearchFilterDto> {


	public static final SearchFilterDtoPA INSTANCE = GWT.create(SearchFilterDtoPA.class);


	/* Properties */
	public ValueProvider<SearchFilterDto,Integer> limit();
	public ValueProvider<SearchFilterDto,Integer> offset();
	public ValueProvider<SearchFilterDto,Boolean> showEntriesWithUnaccessibleHistoryPath();
	public ValueProvider<SearchFilterDto,Set<SearchResultTagDto>> tags();
	public ValueProvider<SearchFilterDto,Dto> baseType();


}
