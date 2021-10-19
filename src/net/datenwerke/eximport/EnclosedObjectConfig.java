package net.datenwerke.eximport;

import com.google.inject.Inject;

final public class EnclosedObjectConfig {

   @Inject
   private static ExImportIdService idService;

   private final Object enclosed;

   private String id;

   public EnclosedObjectConfig(Object enclosed) {

      /* store objects */
      this.enclosed = enclosed;
   }

   public Object getEnclosed() {
      return enclosed;
   }

   /**
    * Returns a unique Id
    */
   public final String getId() {
      if (null == id) {
         id = idService.provideId(enclosed);
         if (null == id)
            throw new IllegalStateException("Could not find id for item " + enclosed.getClass());
      }

      return id;
   }

   @Override
   public int hashCode() {
      if (null == getId())
         return super.hashCode();
      return getId().hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof EnclosedObjectConfig))
         return false;

      if (null == getId())
         return super.equals(obj);

      return getId().equals(((EnclosedObjectConfig) obj).getId());
   }

}
