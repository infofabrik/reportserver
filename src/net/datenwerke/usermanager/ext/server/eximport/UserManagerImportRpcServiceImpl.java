package net.datenwerke.usermanager.ext.server.eximport;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.eximport.service.genrights.ImportSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.service.eximport.http.HttpTreeImportService;
import net.datenwerke.usermanager.ext.client.eximport.im.rpc.UserManagerImportRpcService;
import net.datenwerke.usermanager.ext.service.eximport.UserManagerExporter;


/**
 * 
 *
 */
@Singleton
public class UserManagerImportRpcServiceImpl extends
		SecuredRemoteServiceServlet implements UserManagerImportRpcService {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1941995028242765905L;
	private final Provider<HttpTreeImportService> httpImportServiceProvider;
	
	@Inject
	public UserManagerImportRpcServiceImpl(
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

		List<ImportTreeModel> tree = httpImportService.loadTreeDto(UserManagerExporter.class);
		
		return tree;
	}



}
