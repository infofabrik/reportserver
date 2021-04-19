package net.datenwerke.rs.search.client.search.dto.decorator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * Dto Decorator for {@link SearchResultListDto}
 *
 */
public class SearchResultListDtoDec extends SearchResultListDto implements PagingLoadResult {


	private static final long serialVersionUID = 1L;

	public SearchResultListDtoDec() {
		super();
	}

	public SearchResultListDtoDec(ArrayList<SearchResultEntryDto> data) {
		super();
		silenceEvents(true);
		setData(data);
		silenceEvents(false);
	}

	public List<SearchResultTagTypeDto> getTagTypes(){
		List<SearchResultTagTypeDto> list = new ArrayList<SearchResultTagTypeDto>();
		Set<String> found = new HashSet<String>();
		
		for(SearchResultTagDto tag : getTags()){
			if(! found.contains(tag.getType().getType())){
				list.add(tag.getType());
				found.add(tag.getType().getType());
			}
		}
		
		return list;
	}



}
