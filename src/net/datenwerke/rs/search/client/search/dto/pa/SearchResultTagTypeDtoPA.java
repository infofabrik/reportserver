package net.datenwerke.rs.search.client.search.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.search.service.search.results.SearchResultTagType.class)
public interface SearchResultTagTypeDtoPA extends PropertyAccess<SearchResultTagTypeDto> {


	public static final SearchResultTagTypeDtoPA INSTANCE = GWT.create(SearchResultTagTypeDtoPA.class);


	/* Properties */
	public ValueProvider<SearchResultTagTypeDto,String> display();
	public ValueProvider<SearchResultTagTypeDto,String> type();


}
