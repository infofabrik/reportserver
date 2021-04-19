package net.datenwerke.rs.globalconstants.service.globalconstants;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class GlobalConstantsServiceImpl implements GlobalConstantsService {

	private final Provider<EntityManager> entityManagerProvider;
	
	@Inject
	public GlobalConstantsServiceImpl(
		Provider<EntityManager> entityManagerProvider	
		){
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	@SimpleQuery
	public List<GlobalConstant> getAllGlobalConstants() {
		return null; // magic
	}

	@Override
	public GlobalConstant merge(GlobalConstant constant) {
		return entityManagerProvider.get().merge(constant);
	}

	@Override
	public void remove(GlobalConstant constant) {
		EntityManager em = entityManagerProvider.get();
		em.remove(em.find(constant.getClass(), constant.getId()));
	}

	@Override
	public void removeAllConstants() {
		Collection<GlobalConstant> constants = getAllGlobalConstants();
		if(null != constants){
			for(GlobalConstant constant : constants)
				remove(constant);
		}
			
	}

	@Override
	public void persist(GlobalConstant constant) {
		entityManagerProvider.get().persist(constant);
	}
	
	@Override
	public String getConstantFor(String name) {
		if(null == name)
			return null;
		for(GlobalConstant g : getAllGlobalConstants())
			if(name.equals(g.getName()))
				return g.getValue();
		return null;
	}
}
