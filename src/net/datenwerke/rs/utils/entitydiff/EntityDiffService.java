package net.datenwerke.rs.utils.entitydiff;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.utils.entitydiff.config.EntityDiffConfig;
import net.datenwerke.rs.utils.entitydiff.result.EntityDiffResult;

@ImplementedBy(EntityDiffServiceImpl.class)
public interface EntityDiffService {

   public EntityDiffResult diff(Object a, Object b);

   public EntityDiffResult diff(Object a, Object b, EntityDiffConfig config);

   EntityDiffResult diff(Object a, Object b, String guideName);

   public boolean isEqual(Object a, Object b);

   public boolean isEqual(Object a, Object b, EntityDiffConfig config);

   boolean isEqual(Object a, Object b, String guideName);

}
