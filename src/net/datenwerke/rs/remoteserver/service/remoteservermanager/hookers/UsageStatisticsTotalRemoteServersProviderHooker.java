package net.datenwerke.rs.remoteserver.service.remoteservermanager.hookers;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.locale.RemoteServerManagerMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public class UsageStatisticsTotalRemoteServersProviderHooker implements UsageStatisticsEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String REMOTE_RS_SERVERS = "REMOTE_RS_SERVERS";
   
   @Inject
   public UsageStatisticsTotalRemoteServersProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return Stream
            .of(new SimpleEntry<>(
                  ImmutablePair.of(REMOTE_RS_SERVERS, RemoteServerManagerMessages.INSTANCE.totalRemoteServers()),
                  usageStatisticsService.getNodeCount(RemoteServerDefinition.class)))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
