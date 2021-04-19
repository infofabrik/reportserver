package net.datenwerke.gf.client.fileselection.rpc;

import java.util.ArrayList;

import net.datenwerke.gf.client.fileselection.FileSelectionConfig;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fileselectionservice")
public interface FileSelectionRpcService extends RemoteService {

	void submit(ArrayList<SelectedFileWrapper> data, FileSelectionConfig config) throws ServerCallFailedException;

}
