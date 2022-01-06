package net.datenwerke.rs.search.service.search.hooks;

import org.apache.lucene.document.Document;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

/**
 * Allows to add additional fields to the Lucene search index.
 */
public interface AdditionalFieldsIndexerHook extends Hook {

   /**
    * Allows to add additional fields to the Lucene search index.
    * 
    * @param toIndex  the object being added to the index
    * @param catchall contains all fields currently added for the current toIndex
    *                 object. You should add the new field into this in order to be
    *                 able to find it during search.
    * @param doc      the Lucene {@link Document}
    */
   void addToIndex(Object toIndex, StringBuilder catchall, Document doc);
}
