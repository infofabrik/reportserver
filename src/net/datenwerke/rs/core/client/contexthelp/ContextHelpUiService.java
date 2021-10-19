package net.datenwerke.rs.core.client.contexthelp;

import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;

public interface ContextHelpUiService {

   DwTextButton createDwTextButton(ContextHelpInfo contextHelpInfo);

   void displayContextHelp(ContextHelpInfo info);

   ToolButton createToolButton(ContextHelpInfo contextHelpInfo);

   void enable();

   boolean isEnabled();

}
