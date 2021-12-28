package net.datenwerke.rs.saiku.service.hooks.adapter;

import org.olap4j.OlapConnection;
import org.olap4j.metadata.Cube;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.saiku.service.hooks.OlapCubeCacheHook;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class OlapCubeCacheHookAdapter implements OlapCubeCacheHook {

	@Override
	public Cube getCubeFromCache(SaikuReport report)  {
		return null;
	}


	@Override
	public void putCubeInCache(SaikuReport report, Cube cube, OlapConnection olapConnection)  {
	}



}
