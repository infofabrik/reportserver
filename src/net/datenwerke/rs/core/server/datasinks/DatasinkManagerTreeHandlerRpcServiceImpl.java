package net.datenwerke.rs.core.server.datasinks;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasinkmanager.rpc.DatasinkTreeLoader;
import net.datenwerke.rs.core.client.datasinkmanager.rpc.DatasinkTreeManager;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;

/**
 * 
 *
 */
@Singleton
public class DatasinkManagerTreeHandlerRpcServiceImpl 
	extends TreeDBManagerTreeHandler<AbstractDatasinkManagerNode>
	implements DatasinkTreeLoader, DatasinkTreeManager {


	/**
	 * 
	 */
	private static final long serialVersionUID = -455777535667237770L;

	private final DatasinkTreeService datasinkService;

	
	@Inject
	public DatasinkManagerTreeHandlerRpcServiceImpl(
			DatasinkTreeService datasinkService,
			DtoService dtoGenerator,
			SecurityService securityService,
			EntityClonerService entityClonerService
		) {
	
		super(datasinkService, dtoGenerator, securityService, entityClonerService);
		
		/* store objects */
		this.datasinkService = datasinkService;
	}
	
	@Override
	protected boolean allowDuplicateNode(AbstractDatasinkManagerNode node) {
		return node instanceof DatasinkDefinition;
	}

	@Override
	protected void nodeCloned(AbstractDatasinkManagerNode clonedNode) {
		if(! (clonedNode instanceof DatasinkDefinition))
			throw new IllegalArgumentException();
		DatasinkDefinition datasink = (DatasinkDefinition) clonedNode;
		
		datasink.setName(datasink.getName() == null ? "copy" : datasink.getName() + " (copy)");
	}

	
}
