package net.datenwerke.rs.base.service.reportengines.jasper.util;

import java.util.List;

import org.w3c.dom.Document;

import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile;

public interface JasperUtilsService {

   /**
    * Returns the 'queryString' tag of the given JRXML
    * 
    * @param jrxml The JRXML as a {@link String}
    * @return The 'queryString' tag
    */
   public String getQueryFromJRXML(String jrxml);

   /**
    * Returns the 'queryString' tag of the given JRXML
    * 
    * @param jrxml The JRXML as a {@link Document}
    * @return The 'queryString' tag
    */
   public String getQueryFromJRXML(Document jrxml);

   /**
    * Returns a {@link List} of {@link JasperParameterProposal}s holding all
    * 'parameter' tags of the given JRXML.
    * 
    * @param jrxml The JRXML as a {@link String}
    * @return A {@link List} of {@link JasperParameterProposal}s
    */
   public List<JasperParameterProposal> extractParameters(String jrxml);

   /**
    * Returns a {@link List} of {@link JasperParameterProposal}s holding all
    * 'parameter' tags of the given JRXML.
    * 
    * @param jrxml The JRXML as a {@link Document}
    * @return A {@link List} of {@link JasperParameterProposal}s
    */
   public List<JasperParameterProposal> extractParameters(Document jrxml);

   /**
    * Removes JRXML file and adapts corresponding report.
    * 
    * @param file
    * @return The corresponding report
    */
   public JasperReport removeJRXMLFile(JasperReportJRXMLFile file);

   public void persist(JasperReportJRXMLFile jrxmlFile);

   /**
    * Finds the report that hosts the given jrxml file
    * 
    * @param file
    */
   public JasperReport getReportWithJRXMLFile(JasperReportJRXMLFile file);

   public JasperReportJRXMLFile getJRXMLFileById(Long id);

   public JasperReportJRXMLFile merge(JasperReportJRXMLFile realFile);

   List<String> getJasperAllowedLanguages();

   boolean isJasperEnabled();
}
