package net.datenwerke.rs.core.client.contexthelp;

import javax.inject.Inject;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.button.ToolButton;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;
import net.datenwerke.rs.core.client.contexthelp.locale.ContextHelpMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

@Singleton
public class ContextHelpUiServiceImpl implements ContextHelpUiService {

   private final ToolbarService toolbarService;
   private final ContextHelpDao contextHelpDao;
   private boolean enabled;

   @Inject
   public ContextHelpUiServiceImpl(ToolbarService toolbarService, ContextHelpDao contextHelpDao) {
      this.toolbarService = toolbarService;
      this.contextHelpDao = contextHelpDao;
   }

   @Override
   public DwTextButton createDwTextButton(final ContextHelpInfo contextHelpInfo) {
      DwTextButton infoButton = toolbarService.createSmallButtonLeft(BaseMessages.INSTANCE.helpLabel(),
            BaseIcon.INFO_CIRCLE);
      infoButton.addSelectHandler(event -> displayContextHelp(contextHelpInfo));

      return infoButton;
   }

   @Override
   public ToolButton createToolButton(final ContextHelpInfo contextHelpInfo) {
      ToolButton toolbtn = new ToolButton(ToolButton.QUESTION);
      toolbtn.addSelectHandler(event ->  displayContextHelp(contextHelpInfo));
      return toolbtn;
   }

   @Override
   public void displayContextHelp(final ContextHelpInfo info) {
      info.addData("historyToken", History.getToken());

      contextHelpDao.getContextHelp(info, new RsAsyncCallback<String>() {
         @Override
         public void onSuccess(String result) {
            if (null == result || "".equals(result.trim()))
               return;
            HTML html = new HTML(result);

            DwWindow window = new DwWindow();
            window.setTitle(ContextHelpMessages.INSTANCE.windowTitlePrefix() + " " + info.getTitle());
            window.setSize(640, 480);
            window.add(html);

            window.show();
         }
      });
   }

   @Override
   public void enable() {
      this.enabled = true;
   }

   @Override
   public boolean isEnabled() {
      return enabled;
   }
}
