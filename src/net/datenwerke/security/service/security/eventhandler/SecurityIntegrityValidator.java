package net.datenwerke.security.service.security.eventhandler;

import java.util.List;

import javax.persistence.EntityManager;

import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.entities.Ace;
import net.datenwerke.security.service.security.entities.Ace__;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class SecurityIntegrityValidator implements EventHandler<RemoveEntityEvent>  {

	private final Provider<EntityManager> entityManagerProvider;
	
	@Inject
	public SecurityIntegrityValidator(
		Provider<EntityManager> entityManagerProvider	
		){
		
		/* store objects */
		this.entityManagerProvider = entityManagerProvider;
	}
	
	@Override
	public void handle(RemoveEntityEvent event) {
		AbstractUserManagerNode folk = (AbstractUserManagerNode) event.getObject();
		
		EntityManager em = entityManagerProvider.get();

		/* delete ace's */
		for(Ace ace : getAcesByFolk(folk))
			em.remove(ace);
	}

	@QueryByAttribute(where=Ace__.folk)
	public List<Ace> getAcesByFolk(AbstractUserManagerNode folk){
		return null; // by magic
	}

}
