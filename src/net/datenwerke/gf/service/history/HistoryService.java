package net.datenwerke.gf.service.history;

import java.util.List;

/**
 * Service to build links that allow to jump directly to a given object.
 * 
 *
 */
public interface HistoryService {

   /**
    * Returns a list of links to the given object.
    * 
    * @param o the object
    * @return A list of links
    */
   List<HistoryLink> buildLinksFor(Object o);
   
   List<HistoryLink> buildLinksForList(List<? extends Object> objects); 

}
