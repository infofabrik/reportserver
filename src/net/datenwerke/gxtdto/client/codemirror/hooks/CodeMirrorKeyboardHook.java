package net.datenwerke.gxtdto.client.codemirror.hooks;

import com.google.gwt.event.dom.client.KeyPressEvent;

import net.datenwerke.gxtdto.client.codemirror.CodeMirrorTextArea;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface CodeMirrorKeyboardHook extends Hook {

   public boolean consumes(CodeMirrorTextArea codeMirrorTextArea);

   public void handleKeyEvent(KeyPressEvent event, CodeMirrorTextArea codeMirrorTextArea);

}
