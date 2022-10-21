package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hooks;

import org.olap4j.OlapConnection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface OlapConnectionHook extends Hook {

   public OlapConnection postprocessConnection(OlapConnection connection);

}
