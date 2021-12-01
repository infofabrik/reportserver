package net.datenwerke.treedb.client.treedb.dto.decorator;

import net.datenwerke.gxtdto.client.dtomanager.stores.TreeDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * Dto Decorator for {@link AbstractNodeDto}
 *
 */
abstract public class AbstractNodeDtoDec extends AbstractNodeDto implements TreeDto {

	public static final long FLAG_WRITE_PROTECT = 1;
	public static final long FLAG_CONFIGURATION_PROTECT = 2;
	
	private static final long serialVersionUID = 1L;

	public AbstractNodeDtoDec() {
		super();
	}

	@Override
	public boolean hasChildren() {
		return isHasChildren();
	}
	
	public boolean testFlags(long flag){
		return ((long)getFlags() & flag) == flag;
	}
	
	public boolean isWriteProtected(){
		return testFlags(FLAG_WRITE_PROTECT);
	}
	
	public boolean isConfigurationProtected() {
		return testFlags(FLAG_CONFIGURATION_PROTECT);
	}
	
	public String getDescription(){
		return "";
	}
	
}
