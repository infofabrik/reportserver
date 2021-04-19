package net.datenwerke.gf.client.managerhelper.hooks;

import java.util.ArrayList;
import java.util.Collection;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public abstract class SimpleFormViewHook implements Hook {
	
	private Collection<Class<? extends SimpleFormView>>  appliesTo; 
	
	public SimpleFormViewHook(Class<? extends SimpleFormView>... appliesTo) {
		this.appliesTo = new ArrayList<Class<? extends SimpleFormView>>();
		
		for(Class<? extends SimpleFormView> c : appliesTo){
			this.appliesTo.add(c);
		}
	}

	public boolean appliesTo(Class<? extends SimpleFormView> clazz){
		return this.appliesTo.contains(clazz);
	}

	public abstract void configureSimpleForm(SimpleForm form, AbstractNodeDto selectedNode);

}
