package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;

public interface SFFCCodeMirror extends SFFCTextArea {

   public String getLanguage();

   public ToolBarEnhancer getEnhancer();

   public boolean lineNumbersVisible();
}
