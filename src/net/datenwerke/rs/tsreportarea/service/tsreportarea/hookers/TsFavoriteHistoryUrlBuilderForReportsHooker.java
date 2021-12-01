package net.datenwerke.rs.tsreportarea.service.tsreportarea.hookers;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gf.client.treedb.TreeDBHistoryCallback;
import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIModule;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.locale.TsDiskMessages;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.treedb.service.treedb.AbstractNode;

import com.google.inject.Inject;

public class TsFavoriteHistoryUrlBuilderForReportsHooker extends TreePanelHistoryUrlBuilderHooker {

	private final TsDiskMessages messages = LocalizationServiceImpl.getMessages(TsDiskMessages.class);
	
	private final static String HISTORY_BUILDER_NAME = "TsFavoritesUrlBuilder";
	
	private final TsDiskService favoriteService;
	private final TeamSpaceService teamSpaceService;
	
	@Inject
	public TsFavoriteHistoryUrlBuilderForReportsHooker(
		TsDiskService favoriteService,
		TeamSpaceService teamSpaceService
		){
		
		/* store object */
		this.favoriteService = favoriteService;
		this.teamSpaceService = teamSpaceService;
	}
	
	@Override
	public boolean consumes(Object o) {
		if(! (o instanceof Report))
			return false;
		
		for(AbstractTsDiskNode node : favoriteService.getReferencesTo((Report)o)){
			TeamSpace teamSpace = favoriteService.getTeamSpaceFor(node);
			if(teamSpaceService.mayAccess(teamSpace))
				return true;
		}
		return false;
	}
	
	@Override
	public List<HistoryLink> buildLinksFor(Object o) {
		if(!consumes(o))
			return new ArrayList<HistoryLink>();
		
		List<HistoryLink> list = new ArrayList<HistoryLink>();
		
		for(AbstractTsDiskNode node : favoriteService.getReferencesTo((Report)o)){
			TeamSpace teamSpace = favoriteService.getTeamSpaceFor(node);
			if(teamSpaceService.mayAccess(teamSpace)){
				HistoryLink lnk = new HistoryLink();
				AbstractNode n = (AbstractNode) o;
				lnk.setObjectCaption(assembleReadablePathFor(n));
				
				lnk.setHistoryLinkBuilderId(getBuilderId());
				lnk.setHistoryLinkBuilderName(getNameFor(node));
				lnk.setHistoryLinkBuilderIcon(getIconFor(null));

				String path = assemblePathFor((AbstractNode<?>) node); 
				HistoryLocation loc = new HistoryLocation(getTokenName(), HistoryLocation.makeParameter(TreeDBHistoryCallback.HISTORY_PARAMETER_TREE_PATH, path));
				adjustLocation(node, loc);
				lnk.setHistoryToken(loc.asString());
				
				list.add(lnk);
			}
		}
		
		return list;
	}
	
	@Override
	protected void adjustLocation(Object o, HistoryLocation location) {
		AbstractTsDiskNode node = (AbstractTsDiskNode) o;

		TeamSpace teamSpace = favoriteService.getTeamSpaceFor(node);
		location.addParameter("ts", teamSpace.getId().toString());
		
		if(! (node instanceof TsDiskRoot)){
			location.addParameter("sel", node.getId().toString());
			location.addParameter("id", node.getParent().getId().toString());
		}
	}

	@Override
	protected String getTokenName() {
		return TsDiskUIModule.TEAMSPACE_SELECT_ITEM_HISTORY_TOKEN;
	}

	@Override
	protected String getBuilderId() {
		return HISTORY_BUILDER_NAME;
	}

	@Override
	protected String getNameFor(Object o) {
		AbstractTsDiskNode node = (AbstractTsDiskNode) o;
		TeamSpace teamSpace = favoriteService.getTeamSpaceFor(node);
		
		return messages.historyUrlBuilderName(teamSpace.getName());
	}

	@Override
	protected String getIconFor(Object o) {
		return "file";
	}

}
