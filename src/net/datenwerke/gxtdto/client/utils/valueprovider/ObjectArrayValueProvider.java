package net.datenwerke.gxtdto.client.utils.valueprovider;

import com.sencha.gxt.core.client.ValueProvider;

public class ObjectArrayValueProvider<T> implements ValueProvider<T[], T> {

   private final int pos;

   public ObjectArrayValueProvider(int pos) {
      this.pos = pos;
   }

   @Override
   public T getValue(T[] object) {
      return object[pos];
   }

   @Override
   public void setValue(T[] object, T value) {
      object[pos] = value;
   }

   @Override
   public String getPath() {
      return "pos-" + pos;
   }

}
