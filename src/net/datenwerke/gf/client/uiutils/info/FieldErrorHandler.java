package net.datenwerke.gf.client.uiutils.info;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.error.SideErrorHandler;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

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