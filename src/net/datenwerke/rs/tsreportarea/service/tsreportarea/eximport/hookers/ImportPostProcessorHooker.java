package net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.hookers;

import java.util.HashSet;
import java.util.Set;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportPostProcessProviderHook;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskPostProcessorConfigDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskTeamSpaceAppDefinition;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFolder;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;

import com.google.inject.Inject;

/**
 * 
 *
 */
public class ImportPostProcessorHooker implements
		HttpImportPostProcessProviderHook {

	private static final String POST_PROCESSOR_ID = "FavoritesPostProcessor";
	private static final String IMPORT_FOLDER_NAME = "IMPORTS";
	
	private final TeamSpaceService teamSpaceService;
	private final TsDiskService favoriteService;
	
	@Inject
	public ImportPostProcessorHooker(
		TeamSpaceService teamSpaceService,
		TsDiskService favoriteService
		){
		
		/* store objects */
		this.teamSpaceService = teamSpaceService;
		this.favoriteService = favoriteService;
	}
	
	@Override
	public boolean consumes(String id) {
		return POST_PROCESSOR_ID.equals(id);
	}

	@Override
	public void postProcess(ImportPostProcessConfigDto config,
			ImportResult result) {
		TsDiskPostProcessorConfigDto ppConfig = (TsDiskPostProcessorConfigDto) config;
		TeamSpaceDto tsDto = ppConfig.getTeamSpace();
		
		/* get all variants */
		Set<Report> variants = new HashSet<Report>();
		for(Object imported : result.getImportedObjects().values())
			if(imported instanceof ReportVariant)
				variants.add((Report) imported);
		
		if(variants.isEmpty())
			return;
		
		TeamSpace teamSpace = teamSpaceService.getTeamSpaceById(tsDto.getId());
		if(null == teamSpace)
			throw new IllegalArgumentException("Could not load teamspace");
		
		TeamSpaceApp app = teamSpace.getAppByType(TsDiskTeamSpaceAppDefinition.APP_ID);
		if(! app.isInstalled())
			throw new IllegalArgumentException("The reports app is not installed in the teamspace");
		
		TsDiskRoot root = favoriteService.getRoot(teamSpace);
		
		/* search for import folder */
		TsDiskFolder folder = null;
		for(AbstractTsDiskNode node : root.getChildren()){
			if(! (node instanceof TsDiskFolder))
				continue;
			
			if(IMPORT_FOLDER_NAME.equals(((TsDiskFolder)node).getName())){
				folder = (TsDiskFolder) node;
				break;
			}
		}
		
		if(null == folder){
			folder = new TsDiskFolder();
			folder.setName(IMPORT_FOLDER_NAME);
			root.addChild(folder);
			favoriteService.persist(folder);
		}
		
		for(Report variant : variants){
			TsDiskReportReference reference = new TsDiskReportReference();
			reference.setName(variant.getName());
			reference.setDescription(variant.getDescription());
			reference.setReport(variant);
			folder.addChild(reference);
			
			/* persist reference */
			favoriteService.persist(reference);
		}
	}

}
