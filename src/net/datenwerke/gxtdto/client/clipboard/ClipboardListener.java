package net.datenwerke.gxtdto.client.clipboard;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ClipboardListener extends Hook {

	public void itemCopiedToClipboard(ClipboardItem item);
	
}
