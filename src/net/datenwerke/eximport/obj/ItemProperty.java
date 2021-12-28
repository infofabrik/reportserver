package net.datenwerke.eximport.obj;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import nu.xom.Element;

/**
 * 
 *
 */
public abstract class ItemProperty {

   protected final String name;
   protected final Class<?> type;
   protected final Element element;

   public ItemProperty(String name, Class<?> type, Element element) {
      super();
      this.name = name;
      this.type = type;
      this.element = element;
   }

   public String getName() {
      return name;
   }

   public Class<?> getType() {
      return type;
   }

   public Collection<String> getReferencedIds() {
      return new HashSet<String>();
   }

   public Collection<String> getEnclosedObjectIds() {
      return Collections.emptySet();
   }

   public Element getElement() {
      return element;
   }

}
