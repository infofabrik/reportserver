package net.datenwerke.rs.eximport.server.eximport;

import java.util.Collection;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.eximport.ImportService;
import net.datenwerke.eximport.exceptions.InvalidImportDocumentException;
import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.rpc.ImportRpcService;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.rs.eximport.service.genrights.ImportSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;

/**
 * 
 *
 */
@Singleton
public class ImportRpcServiceImpl extends SecuredRemoteServiceServlet implements ImportRpcService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1313928144420728778L;
	
	private final Provider<HttpImportService> httpImportServiceProvider;
	private final Provider<ImportService> importServiceProvider;
	
	@Inject
	public ImportRpcServiceImpl(
		Provider<HttpImportService> httpImportServiceProvider,
		Provider<ImportService> importServiceProvider
		) {
		
		/* store objects */
		this.httpImportServiceProvider = httpImportServiceProvider;
		this.importServiceProvider = importServiceProvider;
	}
	
	
	@Override
	@SecurityChecked(
		genericTargetVerification = { 
			@GenericTargetVerification(
				target = ImportSecurityTarget.class, 
				verify = @RightsVerification(rights = Execute.class)) 
		})
	@Transactional(rollbackOn={Exception.class})
	public Collection<String> uploadXML(String xmldata) throws ServerCallFailedException {
		HttpImportService httpImportService = httpImportServiceProvider.get();
		httpImportService.createNewConfig();
		try{
			httpImportService.setData(xmldata);
		} catch (InvalidImportDocumentException e) {
			throw new ServerCallFailedException(e.getMessage());
		}
		
		return httpImportService.getInvolvedExporterIds();
	}
	
	@Override
	public Collection<String> initViaFile() throws ServerCallFailedException {
		HttpImportService httpImportService = httpImportServiceProvider.get();
		return httpImportService.getInvolvedExporterIds();
	}
	

	@Override
	@SecurityChecked(
		genericTargetVerification = { 
			@GenericTargetVerification(
				target = ImportSecurityTarget.class, 
				verify = @RightsVerification(rights = Execute.class)) 
		})
	@Transactional(rollbackOn={Exception.class})
	public void performImport(Map<String, ImportConfigDto> configMap, Map<String, ImportPostProcessConfigDto> postProcessMap) throws ServerCallFailedException {
		/* reset config */
		HttpImportService httpImportService = httpImportServiceProvider.get();
		httpImportService.resetImportConfig();
		
		/* store config */
		httpImportService.configureImport(configMap);
		
		ImportResult result = importServiceProvider.get().importData(httpImportService.getCurrentConfig().getImportConfig());
		
		httpImportService.runPostProcess(postProcessMap, result);
		
		httpImportService.resetImportConfig();
	}

	@Override
	@SecurityChecked(
		genericTargetVerification = { 
			@GenericTargetVerification(
				target = ImportSecurityTarget.class, 
				verify = @RightsVerification(rights = Execute.class)) 
		})
	@Transactional(rollbackOn={Exception.class})
	public void reset() throws ServerCallFailedException {
		httpImportServiceProvider.get().invalidateCurrentConfig();
	}


	@Override
	public void invalidateConfig() throws ServerCallFailedException {
		HttpImportService httpImportService = httpImportServiceProvider.get();
		httpImportService.invalidateCurrentConfig();
	}




}
