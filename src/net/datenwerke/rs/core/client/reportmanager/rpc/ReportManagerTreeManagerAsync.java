package net.datenwerke.rs.core.client.reportmanager.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManagerAsync;

public interface ReportManagerTreeManagerAsync extends RPCTreeManagerAsync {

   public void duplicateReportWithVariants(AbstractNodeDto toDuplicate, List<AbstractNodeDto> variants, Dto state,
         AsyncCallback<AbstractNodeDto> callback);

}
