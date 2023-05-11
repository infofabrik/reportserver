package net.datenwerke.usermanager.ext.service.hookers;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.hooks.UsageStatisticsUserEntryProviderHook;
import net.datenwerke.usermanager.ext.service.locale.UserManagerMessages;

public class UsageStatisticsInhibitedUsersProviderHooker implements UsageStatisticsUserEntryProviderHook {

   private final UserManagerService userManagerService;
   
   private final static String TYPE = "TOTAL_INHIBITED_USERS";
   
   @Inject
   public UsageStatisticsInhibitedUsersProviderHooker(
         UserManagerService userManagerService
         ) {
      this.userManagerService = userManagerService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return Stream
            .of(new SimpleEntry<>(ImmutablePair.of(TYPE, UserManagerMessages.INSTANCE.inhibitedUsers()),
                  userManagerService.getNumberOfInhibitedUsers()))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
