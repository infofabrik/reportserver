package net.datenwerke.rs.core.service.reportmanager.output;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

/**
 * 
 *
 */
abstract public class AbstractReportOutputGeneratorManager<G extends ReportOutputGenerator> {

	protected final HookHandlerService hookHandler;
	protected final Class<? extends ReportOutputGeneratorProvider<G>> providerType;
	
	
	public AbstractReportOutputGeneratorManager(
		HookHandlerService hookHandler,
		Class<? extends ReportOutputGeneratorProvider<G>> providerType
		){
		this.hookHandler = hookHandler;
		this.providerType = providerType;
	}
	
	/**
	 * Gets a specific output generator that generates the specified format.
	 * 
	 * @return The corresponding output generator
	 */
	public G getOutputGenerator(String format){
		if(null == format)
			throw new IllegalArgumentException("No format specified"); //$NON-NLS-1$
		
		G catchAll = null;
		for(G g : getRegisteredOutputGenerators()){
			if(g.isCatchAll() && null == catchAll)
				catchAll = g;
			for(String f: g.getFormats())
				if(format.equals(f))
					return g;
		}
		
		if(null == catchAll)
			throw new IllegalArgumentException("Could not find generator for format " + format); //$NON-NLS-1$
		
		return catchAll;
	}
	
	/**
	 * Returns all registered generators
	 */
	public List<G> getRegisteredOutputGenerators(){
		List<G> generators = new ArrayList<G>();
		for(ReportOutputGeneratorProvider<G> provider : hookHandler.getHookers(providerType)){
			Collection<G> genList = provider.provideGenerators();
			if(null != genList)
				generators.addAll(genList);
		}
		return generators;
	}
	
	/**
	 * Returns an array with all registered output formats
	 * 
	 */
	public String[] getRegisteredOutputFormats(){
		Set<String> formats = new HashSet<String>();

		for(G g : getRegisteredOutputGenerators())
			for(String format : g.getFormats())
				formats.add(format);
		
		return formats.toArray(new String[]{});
	}

	public boolean hasCatchAllOutputGen() {
		for(G g : getRegisteredOutputGenerators())
			if(g.isCatchAll())
				return true;
		return false;
	}
}
