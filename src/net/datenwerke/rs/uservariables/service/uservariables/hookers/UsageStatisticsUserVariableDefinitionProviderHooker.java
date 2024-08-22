package net.datenwerke.rs.uservariables.service.uservariables.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.hooks.UserVariableEntryProviderHook;
import net.datenwerke.rs.uservariables.service.uservariables.locale.UserVariableMessages;

public class UsageStatisticsUserVariableDefinitionProviderHooker implements UserVariableEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "TOTAL_USER_VARIABLE_DEFINITIONS";
   
   @Inject
   public UsageStatisticsUserVariableDefinitionProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE,
            UserVariableMessages.INSTANCE.userVariableDefinitions(), UserVariableDefinition.class);
   }

}
