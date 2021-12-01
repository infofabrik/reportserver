package net.datenwerke.rs.search.client.search.dto;

import java.util.ArrayList;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.rs.search.client.search.locale.SearchMessages;


public class EmptySearchResultDto extends SearchResultEntryDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2917907045257236061L;

	public EmptySearchResultDto(){
		this(SearchMessages.INSTANCE.noResultTitle(), SearchMessages.INSTANCE.noResultDesc(), "exclamation");
	}
	
	public EmptySearchResultDto(String title, String desc, String icon){
		super();
		setTitle(title);
		setDescription(desc);
		setIconSmall(icon);
		setLinks(new ArrayList<HistoryLinkDto>());
	}
}
