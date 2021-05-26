package net.datenwerke.rs.ftp.client.ftp.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@RemoteServiceRelativePath("sftp")
public interface SftpRpcService extends RemoteService {

	void exportIntoSftp(ReportDto reportDto, String executorToken, SftpDatasinkDto sftpDatasinkDto,
			String format, List<ReportExecutionConfigDto> configs, String name, String folder) throws ServerCallFailedException;
	
	Map<StorageType,Boolean> getStorageEnabledConfigs() throws ServerCallFailedException;
	
	boolean testSftpDatasink(SftpDatasinkDto sftpDatasinkDto) throws ServerCallFailedException;
	
	DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException;
	
}