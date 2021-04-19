package net.datenwerke.rs.base.ext.server.reportmanager;

import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.rpc.ReportManagerImportRpcService;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.ReportManagerExporter;
import net.datenwerke.rs.eximport.service.genrights.ImportSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.service.eximport.http.HttpTreeImportService;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

/**
 * 
 *
 */
@Singleton
public class ReportManagerImportRpcServiceImpl extends
		SecuredRemoteServiceServlet implements ReportManagerImportRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4199359279281665755L;

	private final Provider<HttpTreeImportService> httpImportServiceProvider;
	
	@Inject
	public ReportManagerImportRpcServiceImpl(
		Provider<HttpTreeImportService> httpImportServiceProvider
		
		) {
		
		/* store objects */
		this.httpImportServiceProvider = httpImportServiceProvider;
	}


	@Override
	@SecurityChecked(
			genericTargetVerification = { 
				@GenericTargetVerification(
					target = ImportSecurityTarget.class, 
					verify = @RightsVerification(rights = Execute.class)) 
			})
	@Transactional(rollbackOn={Exception.class})
	public List<ImportTreeModel> loadTree() throws ServerCallFailedException {
		HttpTreeImportService httpImportService = httpImportServiceProvider.get();

		List<ImportTreeModel> tree = httpImportService.loadTreeDto(ReportManagerExporter.class);
		
		return tree;
	}



}
