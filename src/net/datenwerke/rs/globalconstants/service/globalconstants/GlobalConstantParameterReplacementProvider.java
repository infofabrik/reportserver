package net.datenwerke.rs.globalconstants.service.globalconstants;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetReplacementProviderImpl;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValueImpl;
import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public class GlobalConstantParameterReplacementProvider extends ParameterSetReplacementProviderImpl {

   private final GlobalConstantsService globalConstantService;

   @Inject
   public GlobalConstantParameterReplacementProvider(GlobalConstantsService globalConstantService) {
      this.globalConstantService = globalConstantService;
   }

   @Override
   public Map<String, ParameterValue> provideReplacements(User user, Report report) {
      Map<String, ParameterValue> replacementMap = new HashMap<String, ParameterValue>();

      for (GlobalConstant constant : globalConstantService.getAllGlobalConstants())
         replacementMap.put(constant.getName(),
               new ParameterValueImpl(constant.getName(), constant.getValue(), String.class));

      return replacementMap;
   }
}
