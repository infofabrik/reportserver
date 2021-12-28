package net.datenwerke.rs.core.service.urlview.hooks;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration2.HierarchicalConfiguration;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface UrlViewServerHook extends Hook {

   Map<String, Map<String, List<String[]>>> provideConfiguration(HierarchicalConfiguration config);

}
