package net.datenwerke.security.ext.client.security.ui.genericview;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;

public class GenericSecurityViewAdminModule implements AdminModule {

   private final Provider<GenericSecurityView> genericSecurityViewProvider;

   private GenericSecurityView genericSecurityViewInstance;

   @Inject
   public GenericSecurityViewAdminModule(Provider<GenericSecurityView> genericSecurityViewProvider) {

      /* store objects */
      this.genericSecurityViewProvider = genericSecurityViewProvider;
   }

   @Override
   public Widget getMainWidget() {
      if (null == genericSecurityViewInstance)
         genericSecurityViewInstance = genericSecurityViewProvider.get();
      return genericSecurityViewInstance;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.LOCK.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return SecurityMessages.INSTANCE.genericSecurityViewAdminModuleHeading();
   }

   @Override
   public void notifyOfSelection() {
   }
}
