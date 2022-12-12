package net.datenwerke.rs.core.service.reportmanager.output;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

/**
 * 
 *
 */
abstract public class AbstractReportOutputGeneratorManager<G extends ReportOutputGenerator> {

   protected final HookHandlerService hookHandler;
   protected final Class<? extends ReportOutputGeneratorProvider<G>> providerType;

   public AbstractReportOutputGeneratorManager(HookHandlerService hookHandler,
         Class<? extends ReportOutputGeneratorProvider<G>> providerType) {
      this.hookHandler = hookHandler;
      this.providerType = providerType;
   }

   /**
    * Gets a specific output generator that generates the specified format.
    * 
    * @return The corresponding output generator
    */
   public G getOutputGenerator(String format) {
      if (null == format)
         throw new IllegalArgumentException("No format specified"); //$NON-NLS-1$

      G catchAll = null;
      for (G g : getRegisteredOutputGenerators()) {
         if (g.isCatchAll() && null == catchAll)
            catchAll = g;
         for (String f : g.getFormats())
            if (format.equals(f))
               return g;
      }

      if (null == catchAll)
         throw new IllegalArgumentException("Could not find generator for format " + format); //$NON-NLS-1$

      return catchAll;
   }

   /**
    * Returns all registered generators
    */
   public List<G> getRegisteredOutputGenerators() {
      return hookHandler.getHookers(providerType)
         .stream()
         .map(ReportOutputGeneratorProvider::provideGenerators)
         .filter(Objects::nonNull)
         .flatMap(Collection::stream)
         .collect(toList());
   }

   /**
    * Returns an array with all registered output formats
    * 
    */
   public String[] getRegisteredOutputFormats() {
      return getRegisteredOutputGenerators()
         .stream()
         .map(G::getFormats)
         .flatMap(Arrays::stream)
         .toArray(String[]::new);
   }

   public boolean hasCatchAllOutputGen() {
      return getRegisteredOutputGenerators()
         .stream()
         .anyMatch(G::isCatchAll);
   }
}
