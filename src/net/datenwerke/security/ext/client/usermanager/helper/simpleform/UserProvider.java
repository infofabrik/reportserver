package net.datenwerke.security.ext.client.usermanager.helper.simpleform;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SelectionFilter;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCTreeNodeSelectionFilter;
import net.datenwerke.gf.client.treedb.simpleform.TreeNodeDtoProvider;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.posomap.OrganisationalUnitDto2PosoMap;
import net.datenwerke.security.client.usermanager.dto.posomap.UserDto2PosoMap;
import net.datenwerke.security.ext.client.usermanager.UserManagerTreeLoaderDao;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeUsers;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class UserProvider extends TreeNodeDtoProvider {

   private final UserManagerTreeLoaderDao treeLoaderDao;
   private final UITree basicTreeForUserSelection;

   @Inject
   public UserProvider(ClipboardUiService clipboardService, UserManagerTreeLoaderDao treeLoaderDao,
         @UserManagerTreeUsers UITree basicTreeForUserSelection) {
      super(clipboardService);

      this.treeLoaderDao = treeLoaderDao;
      this.basicTreeForUserSelection = basicTreeForUserSelection;

   }

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      if (configs.length == 0 || !(configs[0] instanceof SFFCUserSelector))
         return false;

      while (type != null) {
         if (type.equals(UserDto.class) || type.equals(OrganisationalUnitDto.class))
            return true;
         type = type.getSuperclass();
      }
      return false;
   }

   @SuppressWarnings("unchecked")
   @Override
   public Widget createFormField() {
      final SFFCUserSelector config = (SFFCUserSelector) configs[0];

      final SelectionFilter filter = getSelectionFilter(configs);

      final SingleTreeSelectionField ssf = new SingleTreeSelectionField(UserDto.class);
      ssf.setIgnoreTriggerClick(true);
      ssf.addTriggerClickHandler(new TriggerClickHandler() {

         private UserDto theUser;

         @Override
         public void onTriggerClick(TriggerClickEvent event) {
            UserDto user = config.getUser();
            if (null == ssf.getTreePanel() || null == theUser || !theUser.equals(user)) {
               theUser = user;

               treeLoaderDao.setState(theUser);

               Set<Dto2PosoMapper> filters = new HashSet<>();
               filters.add(new UserDto2PosoMap());
               filters.add(new OrganisationalUnitDto2PosoMap());

               ssf.setSelectionFilter(filter);

               ssf.setTreePanel(basicTreeForUserSelection);
               ssf.getTreePanel().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

               ssf.triggerClicked();

            } else if (theUser.equals(user))
               ssf.triggerClicked();

         }
      });

      ssf.addValueChangeHandler(new ValueChangeHandler<AbstractNodeDto>() {
         @Override
         public void onValueChange(ValueChangeEvent<AbstractNodeDto> event) {
            ValueChangeEvent.fire(UserProvider.this, event.getValue());
         }
      });

      ssf.setName(name);

      return ssf;
   }

   private SelectionFilter getSelectionFilter(SimpleFormFieldConfiguration[] configs) {
      for (SimpleFormFieldConfiguration c : configs)
         if (c instanceof SFFCTreeNodeSelectionFilter)
            return ((SFFCTreeNodeSelectionFilter) c).getFilter();
      return SelectionFilter.DUMMY_FILTER;
   }

}
