package net.datenwerke.eximport.obj;

import java.util.Collection;
import java.util.Collections;

import nu.xom.Element;

/**
 * 
 *
 */
public class ReferenceItemProperty extends ItemProperty {

   protected final String referenceId;
   protected final Class<?> exporterType;
   protected final boolean optional;

   public ReferenceItemProperty(String name, Class<?> type, String referenceId, Class<?> exporterType,
         boolean isOptional, Element el) {
      super(name, type, el);

      /* store object */
      this.referenceId = referenceId;
      this.exporterType = exporterType;
      this.optional = isOptional;
   }

   public String getReferenceId() {
      return referenceId;
   }

   public Class<?> getExporterType() {
      return exporterType;
   }

   public Collection<String> getReferencedIds() {
      if (!isOptional())
         return Collections.singleton(referenceId);
      return Collections.emptySet();
   }

   public boolean isOptional() {
      return optional;
   }

}
