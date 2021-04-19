package net.datenwerke.gf.client.entityselector;

import java.util.Collection;
import java.util.Collections;

import com.sencha.gxt.widget.core.client.Window;

public class EntitySelectionPopup<T> extends Window {
	
	public EntitySelectionPopup() {

	}
	
	public Collection<T> getSelection(){
		return Collections.EMPTY_LIST;
	}
	
	public T getSelectionFirst(){
		return null;
	}
}
