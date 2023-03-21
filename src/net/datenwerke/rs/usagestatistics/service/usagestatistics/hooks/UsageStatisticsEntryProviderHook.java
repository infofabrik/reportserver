package net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks;

import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface UsageStatisticsEntryProviderHook extends Hook {

   Map<ImmutablePair<String, String>, Object> provideEntry();
}
