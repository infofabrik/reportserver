package net.datenwerke.rs.utils.instancedescription;

import com.google.inject.ImplementedBy;

@ImplementedBy(InstanceDescriptionServiceImpl.class)
public interface InstanceDescriptionService {

	public InstanceDescription getDescriptionFor(Object object);
}
