package net.datenwerke.gf.client.fileselection.rpc;

import java.util.ArrayList;

import net.datenwerke.gf.client.fileselection.FileSelectionConfig;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileSelectionRpcServiceAsync {

	void submit(ArrayList<SelectedFileWrapper> data,
			FileSelectionConfig config, AsyncCallback<Void> callback);

}
