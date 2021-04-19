package net.datenwerke.eximport.hooks;

import net.datenwerke.eximport.obj.ItemProperty;

/**
 * 
 *
 * @param <I>
 */
public abstract class BasicObjectExImporterHelperHookImpl<I extends ItemProperty> implements
		BasicObjectExImporterHelperHook {

	
	@SuppressWarnings("unchecked")
	@Override
	public final Object importData(ItemProperty property) {
		return doImport((I) property);
	}

	abstract protected Object doImport(I property);

}
