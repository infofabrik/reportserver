package net.datenwerke.security.service.eventlogger.jpa;

import java.util.Map;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.eventbus.ObjectEvent;
import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public abstract class JpaEvent extends DwLoggedEvent implements ObjectEvent {

   @Inject
   protected EntityUtils entityUtils;

   protected final Object entity;

   public JpaEvent(Object entity, Object... properties) {
      super(properties);
      this.entity = entity;
   }

   @Override
   public Object getObject() {
      return entity;
   }

   @Override
   public Map<String, String> getLoggedProperties() {
      Map<String, String> props = super.getLoggedProperties();

      props.put("entity_type", null != entity ? entity.getClass().toString() : "NULL");
      Object id = entityUtils.getId(entity);
      props.put("entity_id", null != id ? id.toString() : "NULL");

      return props;
   }
}
