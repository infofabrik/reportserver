package net.datenwerke.rs.markdown.client.markdown.hookers;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.fileserver.client.fileserver.hookers.ReloadButtonToolbarHooker;

public class ReloadButtonToolbarHookerMarkdown extends ReloadButtonToolbarHooker implements MainPanelViewToolbarConfiguratorHook {

   @Inject
   public ReloadButtonToolbarHookerMarkdown(ToolbarService toolbarUtils) {
      super(toolbarUtils);
   }

   @Override
   protected String getExpectedViewID() {
      return "_file_markdown_preview";
   }
}
