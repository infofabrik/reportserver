package net.datenwerke.rs.core.service.reportmanager;

import java.util.List;
import java.util.Set;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.service.treedb.TreeDBManager;


/**
 * 
 *
 */
public interface ReportService extends TreeDBManager<AbstractReportManagerNode> {

	public static final String SECUREE_ID = "ReportManagerService_Default"; //$NON-NLS-1$
	
	/**
	 * Returns all installed report types.
	 * 
	 */
	public Set<Class<? extends Report>> getInstalledReportTypes();
	
	public List<Report> getAllReports();
	
	public Report getReportById(long id);
	
	public Report getUnmanagedReportById(long id);

	
	public void persist(ReportMetadata reportMetadata);
	
	public void remove(Report report, ReportMetadata reportMetadata);
	
	public void persist(ReportProperty property);

	void remove(Report report, ReportProperty property);

	public List<ReportVariant> getVariantsOf(
			AbstractReportManagerNode realReport);

	public List<ReportVariant> getVariantsOf(AbstractReportManagerNode report, User user);

	public long getReportIdFromKey(String key);

	public List<String> getReportMetadataKeys();

	public Report getReportByKey(String conditionKey);

	List<Report> getReportsByDatasource(DatasourceDefinition ds);

	void updateParameterDefinitions(Report report,
			List<ParameterDefinition> newDefinitions,
			boolean allowParameterRemoval);

	ReportMetadata getOrCreateMetadata(Report report, String name);
	
	ReportMetadata removeMetadataByName(Report report, String name);

	Report getReportByUUID(String UUID);

	void prepareVariantForStorage(ReportVariant report, String executeToken) throws ExpectedException;
	
	void prepareVariantForEdit(ReportVariant referenceReport, ReportDto reportDto, String executeToken) throws ServerCallFailedException;
	
	public List<String> getReportStringPropertyKeys();

	AbstractReportManagerNode getNodeByPath(String path);
	AbstractReportManagerNode getNodeByPath(String path, boolean checkRights);
	
	String extractQuery(Report report);

}