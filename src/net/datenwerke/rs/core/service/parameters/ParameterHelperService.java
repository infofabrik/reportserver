package net.datenwerke.rs.core.service.parameters;

import java.util.List;
import java.util.Map;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.terminal.service.terminal.CopyResultType;

public interface ParameterHelperService {

   /**
    * Copies all parameter definitions from an origin report to a target report. If
    * the reports are variants, their parent base reports are used. Note that
    * dependencies on other parameters are not copied, so these have to be copied
    * manually.
    * 
    * @param origin                    the origin report to copy the parameter
    *                                  definitions from
    * @param target                    the target report to copy the parameter
    *                                  definitions to
    * @param replaceExistingParameters if true, replaces parameters in the target
    *                                  report having the same key in the origin
    *                                  report. If false, ignores these.
    * @return a map containing all copied parameter definitions and all existing
    *         parameter definitions. These are sorted by key.
    */
   Map<CopyResultType, List<ParameterDefinition>> copyParameterDefinitions(Report origin, Report target,
         boolean replaceExistingParameters);

}
