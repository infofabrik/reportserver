package net.datenwerke.rs.core.client.reportproperties.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;

@RemoteServiceRelativePath("reportproperties")
public interface ReportPropertiesRpcService extends RemoteService {

   public List<String> getPropertyKeys(ReportDto report) throws ServerCallFailedException;

   public List<String> getSupportedPropertyKeys(ReportDto reportDto) throws ServerCallFailedException;

   public ReportDto updateProperties(ReportDto report, List<ReportStringPropertyDto> addedProperties,
         List<ReportStringPropertyDto> modifiedProperties, List<ReportStringPropertyDto> removedProperties)
         throws ServerCallFailedException;

   public List<ReportStringPropertyDto> getInheritedProperties(ReportDto report) throws ServerCallFailedException;
}
