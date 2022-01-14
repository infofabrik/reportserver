package net.datenwerke.gxtdto.client.dtomanager;

import com.sencha.gxt.core.client.ValueProvider;

public interface PropertyAccessor<O, T> extends ValueProvider<O, T> {

   public Class<?> getType();

   public boolean isModified(O container);

   public void setModified(O container, boolean modified);
}
