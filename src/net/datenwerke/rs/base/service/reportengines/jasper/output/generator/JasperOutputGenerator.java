package net.datenwerke.rs.base.service.reportengines.jasper.output.generator;

import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRSJasperReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGenerator;
import net.datenwerke.security.service.usermanager.entities.User;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * 
 *
 */
public interface JasperOutputGenerator extends ReportOutputGenerator {

   /**
    * May adjust the design according to the output format.
    * 
    * @param jasperDesign
    * @param outputFormat
    */
   public void adjustDesign(JasperDesign jasperDesign, String outputFormat, ReportExecutionConfig... configs);

   public CompiledRSJasperReport exportReport(JasperPrint jasperPrint, String outputFormat, JasperReport report,
         User user, ReportExecutionConfig... configs);
}
