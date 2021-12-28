package net.datenwerke.gxtdto.client.dtomanager.redoundo;

import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;

public class ChangeTracker<T, V> {

   private final PropertyAccessor<T, V> vp;
   private final V oldValue;
   private final V newValue;
   private final boolean oldModified;

   public ChangeTracker(PropertyAccessor<T, V> vp, V oldValue, V newValue, boolean oldModified) {
      this.vp = vp;
      this.oldValue = oldValue;
      this.newValue = newValue;
      this.oldModified = oldModified;
   }

   public ValueProvider<T, V> getVp() {
      return vp;
   }

   public Object getOldValue() {
      return oldValue;
   }

   public Object getNewValue() {
      return newValue;
   }

   public void undo(T object) {
      vp.setValue(object, oldValue);
      vp.setModified(object, oldModified);
   }

   public void redo(T object) {
      vp.setValue(object, newValue);
   }

}
