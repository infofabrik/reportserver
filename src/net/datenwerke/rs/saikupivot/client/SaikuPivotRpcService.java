package net.datenwerke.rs.saikupivot.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

@RemoteServiceRelativePath("saikupivot")
public interface SaikuPivotRpcService extends RemoteService {

   public void stashReport(String token, TableReportDto report) throws ServerCallFailedException;

   public String cubeExport(String token, TableReportDto report) throws ServerCallFailedException;

   public String cubeExportMondrian9(String token, TableReportDto report) throws ServerCallFailedException;

}
