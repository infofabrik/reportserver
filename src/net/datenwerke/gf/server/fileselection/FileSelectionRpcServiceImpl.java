package net.datenwerke.gf.server.fileselection;

import java.util.ArrayList;

import net.datenwerke.gf.client.fileselection.FileSelectionConfig;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.fileselection.rpc.FileSelectionRpcService;
import net.datenwerke.gf.service.fileselection.hooks.FileSelectionHandlerHook;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class FileSelectionRpcServiceImpl  extends SecuredRemoteServiceServlet implements FileSelectionRpcService {


	/**
	 * 
	 */
	private static final long serialVersionUID = 514135211083245835L;
	
	private final Provider<HookHandlerService> hookHandlerProvider;

	@Inject
	public FileSelectionRpcServiceImpl(
		Provider<HookHandlerService> hookHandlerProvider
		){
		this.hookHandlerProvider = hookHandlerProvider;
		
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public void submit(ArrayList<SelectedFileWrapper> data, FileSelectionConfig config)
			throws ServerCallFailedException {
		/* hand call over to handler */
		for(FileSelectionHandlerHook hooker : hookHandlerProvider.get().getHookers(FileSelectionHandlerHook.class)){
			if(hooker.consumes(config.getHandler())){
				try{
					hooker.processData(data, config.getHandler(), config.getMetadata());
					return;
				} catch(RuntimeException e){
					throw new ServerCallFailedException(e);
				}
			}
		}
		throw new ServerCallFailedException("Could not find handler for: " + config.getHandler());		
	}

}
