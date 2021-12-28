package net.datenwerke.security.ext.client.usermanager.ui.forms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import net.datenwerke.gf.client.managerhelper.helper.ToolBarEnhancedDeletableRowsGridForTreeItemSelection;
import net.datenwerke.gf.client.managerhelper.mainpanel.FormView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.ui.helper.grid.TypeAwareGridDropTarget;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;
import net.datenwerke.security.client.usermanager.dto.pa.GroupDtoPA;
import net.datenwerke.security.client.usermanager.dto.pa.OrganisationalUnitDtoPA;
import net.datenwerke.security.client.usermanager.dto.pa.UserDtoPA;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeFolders;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeGroups;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeUsers;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class GroupForm extends FormView {

   private final UITree basicTreeForUserSelection;
   private final UITree basicTreeForGroupSelection;
   private final UITree basicTreeForOuSelection;

   private ListStore<OrganisationalUnitDto> ouStore;
   private ListStore<GroupDto> groupStore;
   private ListStore<UserDto> userStore;
   private TextArea descriptionField;
   private TextField nameField;

   @Inject
   public GroupForm(@UserManagerTreeUsers UITree basicTreeForUserSelection,
         @UserManagerTreeGroups UITree basicTreeForGroupSelection,
         @UserManagerTreeFolders UITree basicTreeForOuSelection) {
      /* store objects */
      this.basicTreeForUserSelection = basicTreeForUserSelection;
      this.basicTreeForGroupSelection = basicTreeForGroupSelection;
      this.basicTreeForOuSelection = basicTreeForOuSelection;
   }

   @Override
   protected void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper) {
      GroupDto role = (GroupDto) getSelectedNode();

      /* name name */
      nameField = new TextField();
      fieldWrapper.add(new FieldLabel(nameField, BaseMessages.INSTANCE.propertyName()), new VerticalLayoutData(1, -1));

      /* description */
      descriptionField = new TextArea();
      descriptionField.setHeight(150);
      fieldWrapper.add(new FieldLabel(descriptionField, BaseMessages.INSTANCE.propertyDescription()),
            new VerticalLayoutData(1, -1));

      fieldWrapper.add(SeparatorTextLabel.createText(""), new VerticalLayoutData(1, 10)); // as margin

      /* users */
      DwContentPanel ufs = DwContentPanel.newLightHeaderPanel(UsermanagerMessages.INSTANCE.assignedUsers(),
            getUserGrid(role));
      fieldWrapper.add(ufs, new VerticalLayoutData(1, 300));

      fieldWrapper.add(SeparatorTextLabel.createText(""), new VerticalLayoutData(1, 10)); // as margin

      /* groups */
      DwContentPanel rfs = DwContentPanel.newLightHeaderPanel(UsermanagerMessages.INSTANCE.assignedRoles(),
            getGroupGrid(role));
      fieldWrapper.add(rfs, new VerticalLayoutData(1, 300));

      fieldWrapper.add(SeparatorTextLabel.createText(""), new VerticalLayoutData(1, 10)); // as margin

      /* ous */
      DwContentPanel oufs = DwContentPanel.newLightHeaderPanel(UsermanagerMessages.INSTANCE.memberOus(),
            getOuGrid(role));
      fieldWrapper.add(oufs, new VerticalLayoutData(1, 300));
   }

   @Override
   protected String getHeader() {
      return UsermanagerMessages.INSTANCE.editRole()
            + (((GroupDto) getSelectedNode()) == null ? "" : " (" + getSelectedNode().getId() + ")");
   }

   @Override
   protected void doInitFormBinding(FormBinding binding, AbstractNodeDto model) {
      binding.addHtmlSafeStringBinding(nameField, model, GroupDtoPA.INSTANCE.name());
      binding.addHtmlSafeStringBinding(descriptionField, model, GroupDtoPA.INSTANCE.description());
   }

   private Widget getUserGrid(GroupDto role) {
      UserDtoPA userPa = GWT.create(UserDtoPA.class);

      /* create store */
      userStore = new ListStore<UserDto>(userPa.dtoId());
      if (null != role.getUsers())
         for (UserDto user : role.getUsers())
            userStore.add(user);

      /* configure columns */
      List<ColumnConfig<UserDto, ?>> columns = new ArrayList<ColumnConfig<UserDto, ?>>();
      columns.add(new ColumnConfig(userPa.firstname(), 120, UsermanagerMessages.INSTANCE.firstname()));
      columns.add(new ColumnConfig(userPa.lastname(), 120, UsermanagerMessages.INSTANCE.lastname()));

      /* create grid */
      ToolBarEnhancedDeletableRowsGridForTreeItemSelection wrapper = new ToolBarEnhancedDeletableRowsGridForTreeItemSelection(
            userStore, new ColumnModel<UserDto>(columns), basicTreeForUserSelection, UserDto.class);
      Grid<AbstractNodeDto> grid = wrapper.getGrid();

      /* configure drag n drop */
      new TypeAwareGridDropTarget(grid, UserDtoDec.class);

      return wrapper;
   }

   private Widget getOuGrid(GroupDto group) {
      OrganisationalUnitDtoPA ouPa = GWT.create(OrganisationalUnitDtoPA.class);

      /* create store */
      ouStore = new ListStore<OrganisationalUnitDto>(ouPa.dtoId());
      if (null != group.getOus())
         for (OrganisationalUnitDto r : group.getOus())
            ouStore.add(r);

      /* configure columns */
      List<ColumnConfig<GroupDto, ?>> columns = new ArrayList<ColumnConfig<GroupDto, ?>>();
      columns.add(new ColumnConfig(ouPa.name(), 100, BaseMessages.INSTANCE.name()));

      /* create grid */
      ToolBarEnhancedDeletableRowsGridForTreeItemSelection wrapper = new ToolBarEnhancedDeletableRowsGridForTreeItemSelection(
            ouStore, new ColumnModel<GroupDto>(columns), basicTreeForOuSelection, OrganisationalUnitDto.class);
      Grid<AbstractNodeDto> grid = wrapper.getGrid();

      /* configure drag n drop */
      new TypeAwareGridDropTarget(grid, OrganisationalUnitDto.class);

      return wrapper;
   }

   private Widget getGroupGrid(GroupDto role) {
      GroupDtoPA groupPa = GWT.create(GroupDtoPA.class);

      /* create store */
      groupStore = new ListStore<GroupDto>(groupPa.dtoId());
      if (null != role.getReferencedGroups())
         for (GroupDto r : role.getReferencedGroups())
            groupStore.add(r);

      /* configure columns */
      List<ColumnConfig<GroupDto, ?>> columns = new ArrayList<ColumnConfig<GroupDto, ?>>();
      columns.add(new ColumnConfig(groupPa.name(), 100, BaseMessages.INSTANCE.name()));

      /* create grid */
      ToolBarEnhancedDeletableRowsGridForTreeItemSelection wrapper = new ToolBarEnhancedDeletableRowsGridForTreeItemSelection(
            groupStore, new ColumnModel<GroupDto>(columns), basicTreeForGroupSelection, GroupDto.class);
      Grid<AbstractNodeDto> grid = wrapper.getGrid();

      /* configure drag n drop */
      new TypeAwareGridDropTarget(grid, GroupDto.class);

      return wrapper;
   }

   @Override
   protected void doPreSubmit(AbstractNodeDto node) {
      /* sync users and roles */
      Set<UserDto> users = new HashSet<UserDto>();
      users.addAll(userStore.getAll());

      Set<GroupDto> roles = new HashSet<GroupDto>();
      roles.addAll(groupStore.getAll());

      Set<OrganisationalUnitDto> ous = new HashSet<OrganisationalUnitDto>();
      ous.addAll(ouStore.getAll());

      if (!(node instanceof GroupDto))
         throw new IllegalArgumentException("passed argument should be of type GroupDto"); //$NON-NLS-1$

      ((GroupDto) node).setUsers(users);
      ((GroupDto) node).setReferencedGroups(roles);
      ((GroupDto) node).setOus(ous);
   }

}
