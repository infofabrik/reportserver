package net.datenwerke.rs.globalconstants.service.globalconstants;

import java.util.List;

import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;

/**
 * 
 *
 */
public interface GlobalConstantsService {

	public List<GlobalConstant> getAllGlobalConstants();

	public void removeAllConstants();

	public void remove(GlobalConstant constant);

	public GlobalConstant merge(GlobalConstant constant);
	
	public void persist(GlobalConstant constant);
	
	public String getConstantFor(String name);
}
