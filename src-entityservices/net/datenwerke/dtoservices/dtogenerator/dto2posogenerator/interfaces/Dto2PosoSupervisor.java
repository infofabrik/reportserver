package net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces;

import java.util.Collection;

public interface Dto2PosoSupervisor<D, P> {

   public void enclosedObjectsRemovedFromCollection(D dto, P poso, Collection<?> object, String fieldname);

   public void referencedObjectRemoved(D dto, P poso, Object removedProperty, Object replacement, String fieldname);

   public void enclosedObjectRemoved(D dto, P poso, Object removedProperty, Object replacement, String fieldname);
}
