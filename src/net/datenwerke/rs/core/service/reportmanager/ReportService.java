package net.datenwerke.rs.core.service.reportmanager;

import java.io.InputStream;
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
   Set<Class<? extends Report>> getInstalledReportTypes();
   
   /**
    * Returns all installed report variant types.
    * 
    */
   Set<Class<? extends Report>> getInstalledReportVariantTypes();

   List<Report> getAllReports();

   Report getReportById(long id);

   Report getUnmanagedReportById(long id);

   void persist(ReportMetadata reportMetadata);

   void remove(Report report, ReportMetadata reportMetadata);

   void persist(ReportProperty property);

   void remove(Report report, ReportProperty property);

   List<ReportVariant> getVariantsOf(AbstractReportManagerNode realReport);

   List<ReportVariant> getVariantsOf(AbstractReportManagerNode report, User user);

   long getReportIdFromKey(String key);

   List<String> getReportMetadataKeys();

   Report getReportByKey(String conditionKey);

   List<Report> getReportsByDatasource(DatasourceDefinition ds);

   void updateParameterDefinitions(Report report, List<ParameterDefinition> newDefinitions,
         boolean allowParameterRemoval);

   ReportMetadata getOrCreateMetadata(Report report, String name);

   ReportMetadata removeMetadataByName(Report report, String name);

   Report getReportByUUID(String UUID);

   void prepareVariantForStorage(ReportVariant report, String executeToken) throws ExpectedException;

   void prepareVariantForEdit(ReportVariant referenceReport, ReportDto reportDto, String executeToken)
         throws ServerCallFailedException;

   List<String> getReportStringPropertyKeys();

   AbstractReportManagerNode getNodeByPath(String path);

   AbstractReportManagerNode getNodeByPath(String path, boolean checkRights);

   String extractQuery(Report report);

   InputStream createInputStream(Object report);

}