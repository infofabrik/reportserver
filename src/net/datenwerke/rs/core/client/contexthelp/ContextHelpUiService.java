package net.datenwerke.rs.core.client.contexthelp;

import com.sencha.gxt.widget.core.client.button.ToolButton;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;

public interface ContextHelpUiService {

   DwTextButton createDwTextButton(ContextHelpInfo contextHelpInfo);

   void displayContextHelp(ContextHelpInfo info);

   ToolButton createToolButton(ContextHelpInfo contextHelpInfo);

   void enable();

   boolean isEnabled();

}
