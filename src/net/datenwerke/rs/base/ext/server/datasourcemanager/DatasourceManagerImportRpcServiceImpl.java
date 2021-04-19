package net.datenwerke.rs.base.ext.server.datasourcemanager;

import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.rpc.DatasourceManagerImportRpcService;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.DatasourceManagerExporter;
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
public class DatasourceManagerImportRpcServiceImpl extends
		SecuredRemoteServiceServlet implements DatasourceManagerImportRpcService {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7633633174743769046L;
	
	private final Provider<HttpTreeImportService> httpImportServiceProvider;
	
	@Inject
	public DatasourceManagerImportRpcServiceImpl(
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

		List<ImportTreeModel> tree = httpImportService.loadTreeDto(DatasourceManagerExporter.class);
		
		return tree;
	}



}
