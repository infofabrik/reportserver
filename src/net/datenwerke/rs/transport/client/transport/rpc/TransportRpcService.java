package net.datenwerke.rs.transport.client.transport.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

@RemoteServiceRelativePath("transports_service")
public interface TransportRpcService extends RemoteService {

   void addElement(TransportDto transportDto, AbstractNodeDto elementDto, boolean includeVariants) throws ServerCallFailedException;

   void addAllDescendants(TransportDto transportDto, AbstractNodeDto elementDto, boolean includeVariants) throws ServerCallFailedException;

   List<TransportDto> loadImportedTransports() throws ServerCallFailedException;
   
   void rpull() throws ServerCallFailedException;
   
   void apply(TransportDto transportDto) throws ServerCallFailedException;
   
   List<TransportCheckEntryDto> checkPreconditions(TransportDto transportDto) throws ServerCallFailedException;

   void removeElements(TransportDto transportDto, List<ImportTreeModel> elementsToRemove) throws ServerCallFailedException;
   
   List<ImportTreeModel> extractTreeModel(TransportDto transportDto) throws ServerCallFailedException;
   
}