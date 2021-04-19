package net.datenwerke.gf.client.uiutils.info;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.error.SideErrorHandler;

/**
 * Based on {@link SideErrorHandler}
 * 
 *
 */
public class FieldErrorHandler extends FieldInfoHandler {

	public FieldErrorHandler(Widget target) {
		super(target, BaseIcon.ERROR);
	}
	
	

}