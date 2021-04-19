package net.datenwerke.rs.base.ext.server.reportmanager;

import net.datenwerke.eximport.ExportService;
import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex.rpc.ReportManagerExportRpcService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

/**
 * 
 *
 */
@Singleton
public class ReportManagerExportRpcServiceImpl extends
		SecuredRemoteServiceServlet implements ReportManagerExportRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4930126638050553141L;

	private final DtoService dtoService;
	private final ExportService exportService;
	private final Provider<HttpExportService> httpExportServiceProvider;
	
	@Inject
	public ReportManagerExportRpcServiceImpl(
		DtoService dtoService,
		ExportService exportService,
		Provider<HttpExportService> httpExportServiceProvider
		){
		
		/* store objects */
		this.dtoService = dtoService;
		this.exportService = exportService;
		this.httpExportServiceProvider = httpExportServiceProvider;
	}
	
	@Override
	@SecurityChecked(
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = ExportSecurityTarget.class, 
					verify = @RightsVerification(rights = Execute.class)) 
			})
	@Transactional(rollbackOn={Exception.class})
	public void quickExport(AbstractReportManagerNodeDto nodeDto,
			boolean includeVariants) throws ServerCallFailedException {
		AbstractReportManagerNode node = (AbstractReportManagerNode) dtoService.loadPoso(nodeDto);
		
		/* export report */
		ExportConfig exportConfig = new ExportConfig();
		exportConfig.setName("Report-Export");
		exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));
		
		addChildren(exportConfig, node, includeVariants);
		
		String exportXML = exportService.exportIndent(exportConfig);
		
		httpExportServiceProvider.get().storeExport(exportXML, node.getName());
	}
	
	private void addChildren(ExportConfig exportConfig, AbstractReportManagerNode report, boolean includeVariants) {
		for(AbstractReportManagerNode childNode : report.getChildren()){
			if(includeVariants || !(childNode instanceof ReportVariant)){
				exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
				addChildren(exportConfig, childNode, includeVariants);
			}
		}
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public String loadResult() throws ServerCallFailedException {
		return httpExportServiceProvider.get().getAndRemoveStoredExport();
	}

}
