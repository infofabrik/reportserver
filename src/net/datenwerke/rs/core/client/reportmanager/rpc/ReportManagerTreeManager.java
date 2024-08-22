package net.datenwerke.rs.core.client.reportmanager.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

@RemoteServiceRelativePath("reportmanager_tree")
public interface ReportManagerTreeManager extends RPCTreeManager, RemoteService {

   public AbstractNodeDto duplicateReportWithVariants(AbstractNodeDto toDuplicate, List<AbstractNodeDto> variants,
         Dto state) throws ServerCallFailedException;
}
