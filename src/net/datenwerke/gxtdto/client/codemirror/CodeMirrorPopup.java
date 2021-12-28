package net.datenwerke.gxtdto.client.codemirror;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class CodeMirrorPopup extends DwWindow {

   private final Resources resources = GWT.create(Resources.class);

   interface Resources extends ClientBundle {
      @Source("codemirror.gss")
      Style css();
   }

   interface Style extends CssResource {
      @ClassName("popup-codemirror")
      String rsPopupCodeMirror();
   }

   private final ToolBarEnhancer enhancer;

   private CodeMirrorPanel panel;
   private CodeMirror parentMirror;

   public CodeMirrorPopup(CodeMirror mirror, CodeMirrorConfig cmc, ToolBarEnhancer enhancer) {
      this.parentMirror = mirror;
      this.enhancer = enhancer;

      resources.css().ensureInjected();

      init(cmc);
   }

   private void init(CodeMirrorConfig cmc) {
      cmc.setLineNumbersVisible(true);

      setSize(800, 600);
      panel = new CodeMirrorPanel(cmc, new ToolBarEnhancer() {
         @Override
         public void enhance(ToolBar toolbar, CodeMirrorPanel codeMirrorPanel) {
            enhancer.enhance(toolbar, codeMirrorPanel);
         }

         @Override
         public boolean allowPopup() {
            return false;
         }
      });

      if (null != parentMirror)
         panel.setValue(parentMirror.getValue());

      VerticalLayoutContainer cont = new VerticalLayoutContainer();
      cont.add(panel, new VerticalLayoutData(1, 1));
      add(cont);

      DwTextButton closeButton = new DwTextButton(BaseMessages.INSTANCE.submit());
      closeButton.addSelectHandler(event -> {
         hide();
         if (null != parentMirror)
            parentMirror.getCodeMirrorTextArea().setValue(panel.getValue(), true);

      });

      this.getButtonBar().add(closeButton);
   }

}
