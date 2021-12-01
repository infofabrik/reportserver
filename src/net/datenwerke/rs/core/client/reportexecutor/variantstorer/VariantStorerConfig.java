package net.datenwerke.rs.core.client.reportexecutor.variantstorer;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;

public interface VariantStorerConfig {

	public boolean displayVariantStorer();
	
	public boolean displayEditVariantOnStore();
	
	public boolean allowEditVariant();
	
	public VariantStorerHandleServerCalls getServerCallHandler();
	
	public TeamSpaceDto getTeamSpace();
	
	public TsDiskFolderDto getTeamSpaceFolder();
	
	public interface VariantStorerHandleServerCalls{

		void createNewVariant(ReportDto report, TeamSpaceDto teamSpace, TsDiskFolderDto folder, String executeToken,
				String name, String desc, AsyncCallback<ReportDto> callback);

		void deleteVariant(ReportDto report, AsyncCallback<Void> callback);

		void editVariant(ReportDto report, String executeToken, String name,
				String description, AsyncCallback<ReportDto> callback);
		
	}
	
	public boolean allowNullTeamSpace();
	
}
