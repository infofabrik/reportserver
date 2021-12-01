package net.datenwerke.eximport.ex;

import net.datenwerke.eximport.ExImportIdService;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;

/**
 * 
 *
 * @param <I>
 */
public abstract class ExportItemConfig<I> {

   @Inject
   protected static ExImportIdService idService;

   protected final I item;
   protected String id;

   public ExportItemConfig(I item) {
      if (item instanceof HibernateProxy)
         item = (I) ((HibernateProxy) item).getHibernateLazyInitializer().getImplementation();
      this.item = item;
   }

   public I getItem() {
      return item;
   }

   /**
    * Returns a unique Id
    */
   public final String getId() {
      if (null == id) {
         id = idService.provideId(item);
         if (null == id)
            throw new IllegalStateException("Could not find id for item " + item.getClass());
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
      if (!(obj instanceof ExportItemConfig<?>))
         return false;

      if (null == getId())
         return super.equals(obj);

      return getId().equals(((ExportItemConfig<?>) obj).getId());
   }

}
