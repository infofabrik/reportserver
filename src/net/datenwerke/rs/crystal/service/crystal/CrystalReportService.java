package net.datenwerke.rs.crystal.service.crystal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportFile;

public interface CrystalReportService {

   public void remove(CrystalReportFile file);

   public boolean isCrystalEnabled();

   public void replaceDatasourceWithJndi(ReportClientDocument reportClientDoc, String dsJndiName)
         throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ReportSDKException;

   public List<CrystalParameterProposal> extractParameters(CrystalReportFile reportFile)
         throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException,
         IOException, ReportSDKException;

   ReportClientDocument openReportClientDoc(CrystalReportFile crFile) throws IOException, IllegalArgumentException,
         IllegalAccessException, InvocationTargetException, InstantiationException, ReportSDKException;

   public void setParameters(ReportClientDocument reportClientDoc, ParameterSet parameters) throws ReportSDKException;

}
