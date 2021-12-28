package net.datenwerke.rs.legacysaiku.service.hooks;

import org.olap4j.OlapConnection;
import org.olap4j.metadata.Cube;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

@HookConfig
public interface OlapCubeCacheHook extends Hook {

   public Cube getCubeFromCache(SaikuReport report);

   public void putCubeInCache(SaikuReport report, Cube cube, OlapConnection olapConnection);

}
