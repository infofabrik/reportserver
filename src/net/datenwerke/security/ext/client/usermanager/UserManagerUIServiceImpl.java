package net.datenwerke.security.ext.client.usermanager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;

import net.datenwerke.gxtdto.client.stores.LoadableListStore;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroupPA;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUserPA;

/**
 * 
 *
 */
public class UserManagerUIServiceImpl implements UserManagerUIService {

   private static StrippedDownUserPA sduPa = GWT.create(StrippedDownUserPA.class);
   private static StrippedDownGroupPA sdgPa = GWT.create(StrippedDownGroupPA.class);

   private final UserManagerDao userManagerDao;

   @Inject
   public UserManagerUIServiceImpl(UserManagerDao userManagerDao) {

      /* store objects */
      this.userManagerDao = userManagerDao;
   }

   @Override
   public ListLoader<ListLoadConfig, ListLoadResult<StrippedDownUser>> getStrippedUserLoader() {
      RpcProxy<ListLoadConfig, ListLoadResult<StrippedDownUser>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<StrippedDownUser>>() {
         @Override
         public void load(ListLoadConfig loadConfig, AsyncCallback<ListLoadResult<StrippedDownUser>> callback) {
            userManagerDao.getStrippedDownUsers(callback);
         }
      };

      return new ListLoader<ListLoadConfig, ListLoadResult<StrippedDownUser>>(proxy);
   }

   @Override
   public ListLoader<ListLoadConfig, ListLoadResult<StrippedDownGroup>> getStrippedGroupLoader() {
      RpcProxy<ListLoadConfig, ListLoadResult<StrippedDownGroup>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<StrippedDownGroup>>() {
         @Override
         public void load(ListLoadConfig loadConfig, AsyncCallback<ListLoadResult<StrippedDownGroup>> callback) {
            userManagerDao.getStrippedDownGroups(callback);
         }
      };

      ListLoader<ListLoadConfig, ListLoadResult<StrippedDownGroup>> listLoader = new ListLoader<ListLoadConfig, ListLoadResult<StrippedDownGroup>>(
            proxy);

      return listLoader;
   }

   @Override
   public BaseIcon getIcon(AbstractUserManagerNodeDto node) {
      if (null == node)
         return null;

      if (node instanceof OrganisationalUnitDto)
         return BaseIcon.FOLDER_USER;
      if (node instanceof GroupDto)
         return BaseIcon.GROUP;
      if (node instanceof UserDto)
         return BaseIcon.USER;

      return null;
   }

   @Override
   public LoadableListStore<ListLoadConfig, StrippedDownUser, ListLoadResult<StrippedDownUser>> getStrippedUserStore() {
      return new LoadableListStore<ListLoadConfig, StrippedDownUser, ListLoadResult<StrippedDownUser>>(sduPa.dtoId(),
            getStrippedUserLoader());
   }

   @Override
   public LoadableListStore<ListLoadConfig, StrippedDownGroup, ListLoadResult<StrippedDownGroup>> getStrippedGroupStore() {
      return new LoadableListStore<ListLoadConfig, StrippedDownGroup, ListLoadResult<StrippedDownGroup>>(sdgPa.dtoId(),
            getStrippedGroupLoader());
   }
}
