package net.datenwerke.rs.search.service.search.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.search.service.search.hooks.SearchProvider;
import net.datenwerke.rs.search.service.search.index.SearchIndexService;
import net.datenwerke.rs.search.service.search.locale.SearchMessages;
import net.datenwerke.rs.search.service.search.results.SearchFilter;
import net.datenwerke.rs.search.service.search.results.SearchResultEntry;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.SearchResultTagType;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.instancedescription.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.InstanceDescriptionService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class EntitySearchProvider implements SearchProvider {

	private final InstanceDescriptionService instanceDescriber;
	private final HistoryService historyService;
	private final SecurityService securityService;
	private final SearchIndexService searchIndexService;
	private final TsDiskService tsDiskService;
	private final TeamSpaceService teamSpaceService;
	
	@Inject
	public EntitySearchProvider(
		InstanceDescriptionService instanceDescriber,
		HistoryService historyService, 
		SearchIndexService searchIndexService,
		SecurityService securityService,
		TsDiskService tsDiskService,
		TeamSpaceService teamSpaceService) {
		this.instanceDescriber = instanceDescriber;
		this.historyService = historyService;
		this.searchIndexService = searchIndexService;
		this.securityService = securityService;
		this.tsDiskService = tsDiskService;
		this.teamSpaceService = teamSpaceService;
	}


	@Override
	public List<SearchResultEntry> search(String query, SearchFilter filter) {
		
		List<SearchResultEntry> entryList = new ArrayList<SearchResultEntry>();
		List<?> results = searchIndexService.locate(filter.getBaseType(), query, filter.getLimit());
		long id = 1;
		int countedResults = 0;
		for (Object entity : results) {

		    if(null == entity)
		    	continue;
		    
		    if(entity instanceof SecurityTarget && ! securityService.checkRights((SecurityTarget)entity, Read.class))
		    	continue;
		    
		    if (entity instanceof AbstractTsDiskNode) {
		    	TeamSpace teamSpace = tsDiskService.getTeamSpaceFor((AbstractTsDiskNode)entity);
				if(! teamSpaceService.mayAccess(teamSpace))
					continue;
		    }
		    
		    countedResults++;
		    
		    SearchResultEntry sr = new SearchResultEntry();

		    InstanceDescription instanceDesc = instanceDescriber.getDescriptionFor(entity);
		    sr.setTitle(instanceDesc.getTitle());
		    sr.setDescription(instanceDesc.getDescription());
		    sr.setIconSmall(instanceDesc.getIcon());
		    sr.setId(id++);
		    if(entity instanceof AbstractNode){
	    		sr.setLastModified(((AbstractNode)entity).getLastUpdated());
	    		sr.setObjectId(((AbstractNode)entity).getId());
		    }
		    sr.setResultObject(entity);

		    /* links and only add those that can be linked */
		    sr.setLinks(historyService.buildLinksFor(entity));
		    if(null != sr.getLinks() && sr.getLinks().size() > 0)
		    	entryList.add(sr);
		    
		    /* add tags */
		    SearchResultTag tag = new SearchResultTag(
		    		new SearchResultTagType(SearchFilter.TAG_BASE_TYPE, SearchMessages.INSTANCE.objectTypeTagDisplay()), 
		    		entity.getClass().getName(), instanceDesc.getObjectType());
		    sr.addTag(tag);
		}
		
		return entryList;
	}



}
