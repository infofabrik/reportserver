package net.datenwerke.rs.search.service.search.hooks;

import org.apache.lucene.document.Document;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface AdditionalFieldsIndexerHook extends Hook {

   void addToIndex(Object toIndex, StringBuilder catchall, Document doc);
}
