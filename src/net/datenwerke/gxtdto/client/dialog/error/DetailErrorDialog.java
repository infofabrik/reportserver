package net.datenwerke.gxtdto.client.dialog.error;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextArea;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

public class DetailErrorDialog extends DwWindow {

   private final SafeHtml title;
   private final SafeHtml msg;
   private final String stacktrace;

   public DetailErrorDialog(String title, String msg, String stacktrace) {
      super();

      this.title = SafeHtmlUtils.fromString(title);
      this.msg = SimpleHtmlSanitizer.sanitizeHtml(msg);
      this.stacktrace = null == stacktrace ? null
            : new SafeHtmlBuilder().appendEscapedLines(stacktrace).toSafeHtml().asString();

      setModal(true);

      initializeUi();
   }

   public DetailErrorDialog(Throwable caught) {
      this(BaseMessages.INSTANCE.error(), caught.getMessage(),
            (caught instanceof ServerCallFailedException ? ((ServerCallFailedException) caught).getStackTraceAsString()
                  : ""));
   }

   protected void initializeUi() {
      addStyleName("rs-errordialog");

      /* create window */
      setWidth(640);
      setHeading(title);
      setHeight(180);

      DwFlowContainer container = new DwFlowContainer();
      setWidget(container);

      /* error message */
      Label msgHeader = new Label();
      msgHeader.addStyleName("rs-dialog-heading");
      msgHeader.setText(BaseMessages.INSTANCE.error());

      Label textMsg = new HTML(msg);
      textMsg.addStyleName("rs-dialog-msg");

      container.add(msgHeader, new MarginData(4));
      container.add(textMsg, new MarginData(2, 4, 4, 4));

      /* detail area (stack trace */
      if (null != stacktrace) {
         FieldSet wrapper = new FieldSet();
         wrapper.setHeading(BaseMessages.INSTANCE.displayErrorDetails());
         wrapper.setCollapsible(true);
         wrapper.collapse();
         wrapper.addExpandHandler(new ExpandHandler() {
            @Override
            public void onExpand(ExpandEvent event) {
               setHeight(500);
            }
         });
         wrapper.addCollapseHandler(new CollapseHandler() {
            @Override
            public void onCollapse(CollapseEvent event) {
               setHeight(180);
            }
         });

         TextArea stackArea = new TextArea();
         stackArea.setValue(stacktrace);
         stackArea.setWidth(600);
         stackArea.setHeight(300);

         wrapper.add(stackArea);

         container.add(wrapper, new MarginData(8, 8, 2, 8));
      }

      /* buttons */
      DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.ok());
      okButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            hide();
         }
      });

      addButton(okButton);
   }
}
