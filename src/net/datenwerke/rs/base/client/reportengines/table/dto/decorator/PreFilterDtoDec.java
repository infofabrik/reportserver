package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;

/**
 * Dto Decorator for {@link PreFilterDto}
 *
 */
public class PreFilterDtoDec extends PreFilterDto implements IdedDto {


	private static final long serialVersionUID = 1L;
	
	transient private boolean initTypes;

	public PreFilterDtoDec() {
		super();
	}

	public void clearFilter() {
		FilterBlockDtoDec rootBlock = (FilterBlockDtoDec) getRootBlock();
		rootBlock.clearBlock();
		setRootBlock(rootBlock);
	}

	@Override
	public FilterBlockDto getRootBlock() {
		FilterBlockDto block = super.getRootBlock();
		if(! initTypes)
			initBlockTypes();
		
		return block;
	}
	
	public void initBlockTypes(){
		initTypes = true;
		((FilterBlockDtoDec)getRootBlock()).initBlockTypes(getRootBlockType());
		initTypes = false;
	}

}
