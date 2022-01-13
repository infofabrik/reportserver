package net.datenwerke.security.ext.client.usermanager.objectinfo;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoAdditionalInfoProvider;
import net.datenwerke.gxtdto.client.ui.helper.info.InfoWindow;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.usermanager.UserManagerDao;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

public class UserGroupsObjectInfo implements ObjectInfoAdditionalInfoProvider {

   private final UserManagerDao userManagerDao;

   @Inject
   public UserGroupsObjectInfo(
         UserManagerDao userManagerDao
         ) {
      this.userManagerDao = userManagerDao;
   }

   @Override
   public boolean consumes(Object object) {
      return object instanceof UserDto;
   }

   @Override
   public void addInfoFor(Object object, InfoWindow window) {
      final DwContentPanel panel = window.addDelayedSimpelInfoPanel(UsermanagerMessages.INSTANCE.groups());
      userManagerDao.getUserGroupDetailsAsHtml((UserDto) object, new RsAsyncCallback<SafeHtml>() {

         @Override
         public void onSuccess(SafeHtml result) {
            panel.clear();
            panel.enableScrollContainer();

            if (null == result)
               panel.add(new Label(UsermanagerMessages.INSTANCE.userNotInGroupMessages()));
            else {
               SafeHtmlBuilder builder = new SafeHtmlBuilder();
               builder.appendHtmlConstant("<div class=\"rs-infopanel-usergroups\">");
               builder.append(result);
               panel.add(new HTML(builder.toSafeHtml()));
            }

            panel.forceLayout();
         }
      });

   }

}
