package net.datenwerke.rs.tsreportarea.service.tsreportarea;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface TsDiskService extends TreeDBManager<AbstractTsDiskNode> {

	/**
	 * Returns a {@link List} of {@link AbstractTsDiskNode}s representing the
	 * root folders of {@link TeamSpace} favorites.
	 * 
	 * @return A {@link List} of {@link AbstractTsDiskNode}s
	 */
	public List<AbstractTsDiskNode> getRoots();
	
	/**
	 * Returns the {@link TsDiskRoot} for the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace} to get the {@link TsDiskRoot} for
	 * @return The corresponding {@link TsDiskRoot}
	 */
	public TsDiskRoot getRoot(TeamSpace teamSpace);

	/**
	 * Creates a new {@link TsDiskRoot} for the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace} to create the favorite root for
	 * @return The newly created {@link TsDiskRoot}
	 */
	public TsDiskRoot createRoot(TeamSpace teamSpace);

	/**
	 * Returns a {@link List} of all existing {@link TsDiskReportReference}s for
	 * the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace} to get the favorite references for
	 * @return A {@link List} of {@link TsDiskReportReference}s
	 */
	public List<TsDiskReportReference> getReferencesFor(TeamSpace teamSpace);
	
	/**
	 * Returns a {@link List} of all existing {@link TsDiskGeneralReference}s for
	 * the given {@link TeamSpace}. 
	 * 
	 * @param teamSpace The {@link TeamSpace} to get the general references for
	 * @return A {@link List} of {@link TsDiskGeneralReference}s
	 */
	public List<TsDiskGeneralReference> getGeneralReferencesFor(TeamSpace teamSpace);

	/**
	 * Returns the {@link TsDiskRoot} of the given {@link AbstractTsDiskNode}
	 * 
	 * @param node The {@link AbstractTsDiskNode} to get the root from
	 * @return The corresponding {@link TsDiskRoot}
	 */
	public TsDiskRoot getRootFor(AbstractTsDiskNode node);
	
	/**
	 * Returns the {@link TeamSpace} which holds the given {@link AbstractTsDiskNode}
	 * 
	 * @param node The {@link AbstractTsDiskNode}
	 * @return The corresponding {@link TeamSpace}
	 */
	public TeamSpace getTeamSpaceFor(AbstractTsDiskNode node);

	/**
	 * Returns a {@link Set} of {@link TeamSpace}s which contain a link to the given
	 * {@link Report}
	 * 
	 * @param report The {@link Report}
	 * @return A {@link Set} of {@link TeamSpace}s
	 */
	public Set<TeamSpace> getTeamSpacesThatLinkTo(Report report);
	
	/**
	 * Retuns a {@link Map} of {@link TeamSpace}s which contain a link to the given 
	 * {@link Report} mapped to their paths in the respective {@link TeamSpace}. The path is returned as a 
	 * list of {@link AbstractTsDiskNode}s.
	 * 
	 * @param report The {@link Report}
	 * @return The mapping between {@link TeamSpace}s and paths for the given report.
	 */
	Map<TeamSpace, List<List<AbstractTsDiskNode>>> getTeamSpacesWithPathsThatLinkTo(Report report);
	
	/**
	 * Returns the path which contains the given {@link AbstractTsDiskNode} as a list of {@link AbstractTsDiskNode}s.
	 * 
	 * @param node The {@link AbstractTsDiskNode}
	 * @return The corresponding path as a list of {@link AbstractTsDiskNode}s
	 */
	List<AbstractTsDiskNode> getPathFor(AbstractTsDiskNode node);

	List<TsDiskReportReference> getReferencesTo(Report report);

	public TsDiskReportReference importReport(Report report, AbstractTsDiskNode parent,
			boolean copyReport, boolean isReference);
	
	public TsDiskReportReference importReport(Report report, AbstractTsDiskNode parent,
			boolean copyReport, String name, String description, boolean isReference);

	List<TsDiskReportReference> getReferencesIn(AbstractTsDiskNode folder);
}
