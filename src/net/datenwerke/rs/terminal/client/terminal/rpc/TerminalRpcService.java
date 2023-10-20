package net.datenwerke.rs.terminal.client.terminal.rpc;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

@RemoteServiceRelativePath("terminal")
public interface TerminalRpcService extends RemoteService {

   public void closeSession(String sessionId) throws ServerCallFailedException;

   public HashMap<String, String> initSession(AbstractNodeDto node, Dto2PosoMapper dto2PosoMapper) throws ServerCallFailedException;

   public AutocompleteResultDto autocomplete(String sessionId, String command, int cursorPosition)
         throws ServerCallFailedException, ExpectedException;

   public AutocompleteResultDto autocomplete(String sessionId, String command, int cursorPosition, boolean forceResult)
         throws ServerCallFailedException, ExpectedException;

   public CommandResultDto execute(String sessionId, String command)
         throws ServerCallFailedException, ExpectedException;

   public CommandResultDto ctrlCPressed(String sessionId) throws ServerCallFailedException, ExpectedException;

}
