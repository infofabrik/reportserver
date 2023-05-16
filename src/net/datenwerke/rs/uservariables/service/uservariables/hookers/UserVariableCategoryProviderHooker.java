package net.datenwerke.rs.uservariables.service.uservariables.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.uservariables.service.uservariables.hooks.UserVariableEntryProviderHook;
import net.datenwerke.rs.uservariables.service.uservariables.locale.UserVariableMessages;

public class UserVariableCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<UsageStatisticsService> usageStatisticsServiceProvider;
   
   private final static String TYPE = "USER_VARIABLE_USAGE_STATISTICS";
   
   @Inject
   public UserVariableCategoryProviderHooker(
         Provider<UsageStatisticsService> usageStatisticsServiceProvider
         ) {
      this.usageStatisticsServiceProvider = usageStatisticsServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      return usageStatisticsServiceProvider.get().provideCategory(TYPE,
            UserVariableMessages.INSTANCE.userVariableStatistics(), UserVariableEntryProviderHook.class);
   }
}
