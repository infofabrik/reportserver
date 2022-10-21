package net.datenwerke.hookhandler.shared.hookhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.annotations.ConcurrentMap;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

/**
 * 
 *
 */
@Singleton
public class HookHandlerServiceImpl implements HookHandlerService {

   private final Map<Class<? extends Hook>, Map<Integer, List<? extends Hook>>> hookers;
   private final Map<Class<? extends Hook>, Map<Integer, List<Provider<? extends Hook>>>> hookersProviders;

   private final Map<Class<? extends Hook>, HookConfiguration> configMap;

   @Inject
   public HookHandlerServiceImpl(@ConcurrentMap Provider<Map> mapProvider) {
      hookers = mapProvider.get();
      hookersProviders = mapProvider.get();
      configMap = mapProvider.get();
   }

   public static HookHandlerServiceImpl createInstance() {
      return new HookHandlerServiceImpl(new Provider<Map>() {
         @Override
         public Map get() {
            return new HashMap();
         }
      });
   }

   public <H extends Hook> void attachHooker(Class<? extends H> hook, Provider<? extends H> hookerProvider) {
      attachHooker(hook, hookerProvider, PRIORITY_MEDIUM);
   }

   public <H extends Hook> void attachHooker(Class<? extends H> hook, Provider<? extends H> hookerProvider,
         int priority) {
      /* do we already have a map */
      if (!hookersProviders.containsKey(hook))
         hookersProviders.put(hook, new HashMap<Integer, List<Provider<? extends Hook>>>());

      /* get map */
      Map<Integer, List<Provider<? extends Hook>>> map = hookersProviders.get(hook);

      /* do we have a list for the priority */
      if (!map.containsKey(priority))
         map.put(priority, new ArrayList<Provider<? extends Hook>>());

      /* store object */
      map.get(priority).add(hookerProvider);
   }

   public <H extends Hook> void attachHooker(Class<? extends H> hook, H hooker) {
      attachHooker(hook, hooker, PRIORITY_MEDIUM);
   }

   @SuppressWarnings("unchecked")
   public <H extends Hook> void attachHooker(Class<? extends H> hook, H hooker, int priority) {
      /* do we already have a map */
      if (!hookers.containsKey(hook))
         hookers.put(hook, new HashMap<Integer, List<? extends Hook>>());

      /* get map */
      Map<Integer, List<? extends Hook>> map = hookers.get(hook);

      /* do we have a list for the priority */
      if (!map.containsKey(priority))
         map.put(priority, new ArrayList<Hook>());

      ((List<H>) map.get(priority)).add(hooker);
   }

   public <H extends Hook> void detachHooker(Class<? extends H> hook, Provider<? extends H> hookerProvider) {
      if (!hookersProviders.containsKey(hook))
         return;

      Map<Integer, List<Provider<? extends Hook>>> hookMap = (hookersProviders.get(hook));
      for (List<Provider<? extends Hook>> hooks : hookMap.values()) {
         Iterator<Provider<? extends Hook>> it = hooks.iterator();
         while (it.hasNext()) {
            Provider<? extends Hook> toComp = it.next();
            if (toComp == hookerProvider) {
               it.remove();
               break;
            }
         }
      }
   }

   public <H extends Hook> void detachHooker(Class<? extends H> hook, H hooker) {
      if (!hookers.containsKey(hook))
         return;

      Map<Integer, List<? extends Hook>> hookMap = (hookers.get(hook));
      for (List<? extends Hook> hooks : hookMap.values()) {
         Iterator<? extends Hook> it = hooks.iterator();
         while (it.hasNext()) {
            Hook toComp = it.next();
            if (toComp == hooker) {
               it.remove();
               break;
            }
         }
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public <H extends Hook> List<Provider<H>> getRawHookerProviders(Class<? extends H> hook) {
      List<Provider<H>> hookers = new ArrayList<Provider<H>>();

      /* indirect hookers */
      if (hookersProviders.containsKey(hook)) {
         for (int prio : hookersProviders.get(hook).keySet()) {
            for (Provider<? extends Hook> hookerProvider : hookersProviders.get(hook).get(prio))
               hookers.add((Provider<H>) hookerProvider);
         }
      }

      return hookers;
   }

   @SuppressWarnings("unchecked")
   @Override
   public <H extends Hook> List<H> getRawHookers(Class<? extends H> hook) {
      List<H> hookerList = new ArrayList<H>();

      /* indirect hookers */
      if (hookers.containsKey(hook)) {
         for (int prio : hookers.get(hook).keySet()) {
            hookerList.addAll((Collection<? extends H>) hookers.get(hook).get(prio));
         }
      }

      return hookerList;
   }

   @SuppressWarnings("unchecked")
   public <H extends Hook> List<H> getHookers(Class<? extends H> hook) {
      Map<Integer, List<H>> mapOfHookers = new HashMap<Integer, List<H>>();

      /* direct hookers */
      if (hookers.containsKey(hook)) {
         for (int prio : hookers.get(hook).keySet()) {
            if (!mapOfHookers.containsKey(prio))
               mapOfHookers.put(prio, new ArrayList<H>());
            mapOfHookers.get(prio).addAll((Collection<? extends H>) hookers.get(hook).get(prio));
         }
      }

      /* indirect hookers */
      if (hookersProviders.containsKey(hook)) {
         for (int prio : hookersProviders.get(hook).keySet()) {
            if (!mapOfHookers.containsKey(prio))
               mapOfHookers.put(prio, new ArrayList<H>());
            for (Provider<? extends Hook> hookerProvider : hookersProviders.get(hook).get(prio))
               mapOfHookers.get(prio).add((H) hookerProvider.get());
         }
      }

      /* create list */
      List<H> listOfHookers = new ArrayList<H>();

      List<Integer> prioList = new ArrayList<Integer>();
      prioList.addAll(mapOfHookers.keySet());
      Collections.sort(prioList, new Comparator<Integer>() {
         public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
         }
      });

      for (int prio : prioList) {
         List<H> sortedHookers = mapOfHookers.get(prio);
         Collections.sort(sortedHookers, new Comparator<H>() {
            @Override
            public int compare(H arg0, H arg1) {
               return arg0.getClass().getName().compareTo(arg1.getClass().getName());
            }
         });
         listOfHookers.addAll(sortedHookers);
      }

      /* run config check */
      List<H> reducedList = checkConfigFor(hook, listOfHookers);

      return reducedList;
   }

   protected <H extends Hook> List<H> checkConfigFor(Class<? extends H> hook, List<H> listOfHookers) {
      HookConfiguration config = getConfig(hook);
      if (null == config)
         return listOfHookers;

      if (config.isSingleton()) {
         List<H> reducedList = new ArrayList<H>();
         if (!listOfHookers.isEmpty())
            reducedList.add(listOfHookers.get(0));
         return reducedList;
      }

      return listOfHookers;
   }

   @Override
   public synchronized HookConfiguration getConfig(Class<? extends Hook> hook) {
      if (!configMap.containsKey(hook))
         configMap.put(hook, new HookConfiguration(hook));
      return configMap.get(hook);
   }

   @Override
   public void setConfig(HookConfiguration config) {
      configMap.put(config.getHook(), config);
   }

}
