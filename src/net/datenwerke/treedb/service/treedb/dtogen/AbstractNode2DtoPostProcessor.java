package net.datenwerke.treedb.service.treedb.dtogen;

import java.util.ArrayList;
import java.util.Stack;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;
import net.datenwerke.treedb.service.treedb.AbstractNode;

import com.google.inject.Inject;

/**
 * 
 *
 */
@SuppressWarnings("unchecked")
public class AbstractNode2DtoPostProcessor implements
		Poso2DtoPostProcessor<AbstractNode, AbstractNodeDtoDec> {

	private final DtoService dtoService;
	
	@Inject
	public AbstractNode2DtoPostProcessor(
		DtoService dtoService	
		){
		
		this.dtoService = dtoService;
	}
	
	@Override
	public void dtoCreated(AbstractNode poso, AbstractNodeDtoDec dto) {
		dto.setHasChildren(poso.hasChildren());
		
		/* set parent id */
		AbstractNode parent = poso.getParent();
		if(null != parent){
			dto.setParentNodeId(parent.getId());
			
			AbstractNodeDtoDec parentDto = (AbstractNodeDtoDec) dtoService.instantiateDto(parent);
			dto.setParentNodeType(null == parentDto ? null : parentDto.getClass().getName());
		} else {
			dto.setRootName(poso.getRootNodeName());
		}
		
		/* if full view set path */
		if(dto.getDtoView().compareTo(DtoView.ALL) >= 0){
			ArrayList<Long> path = new ArrayList<Long>();
			while(null != parent){
				path.add(parent.getId());
				parent = parent.getParent();
			}
			dto.setRootPath(path);
		}
	}

	@Override
	public void dtoInstantiated(AbstractNode poso, AbstractNodeDtoDec dto) {
		
	}

}
