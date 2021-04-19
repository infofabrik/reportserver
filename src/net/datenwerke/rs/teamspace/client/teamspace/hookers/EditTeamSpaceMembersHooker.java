package net.datenwerke.rs.teamspace.client.teamspace.hookers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.SplitButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.TreeSelectionPopup;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utilityservices.grid.GridHelperService;
import net.datenwerke.gxtdto.client.utilityservices.grid.GridHelperService.CCContainer;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceDao;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.StrippedDownTeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.StrippedDownTeamSpaceMemberDto.Type;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.pa.StrippedDownTeamSpaceMemberDtoPa;
import net.datenwerke.rs.teamspace.client.teamspace.hooks.TeamSpaceEditDialogHookImpl;
import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroupPA;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUserPA;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIService;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeGroups;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeUsers;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class EditTeamSpaceMembersHooker extends
		TeamSpaceEditDialogHookImpl {

	private static StrippedDownUserPA userPa = GWT.create(StrippedDownUserPA.class);
	private static StrippedDownGroupPA groupPa = GWT.create(StrippedDownGroupPA.class);
	private static StrippedDownTeamSpaceMemberDtoPa memberPa = GWT.create(StrippedDownTeamSpaceMemberDtoPa.class);
	
	private final GridHelperService gridHelperService;
	private final ToolbarService toolbarService;
	private final UserManagerUIService userManagerService;
	private final TeamSpaceDao tsDao;
	private final TeamSpaceUIService teamSpaceService;
	
	private ListStore<StrippedDownUser> allUsersStore;
	private ListStore<StrippedDownGroup> allGroupsStore;
	private ListStore<StrippedDownTeamSpaceMemberDto> memberStore;
	private boolean changed = false;
	
	private final UITree basicTreeForUserSelection;
	private final UITree basicTreeForGroupSelection;
	
	@Inject
	public EditTeamSpaceMembersHooker(
		GridHelperService gridHelperService,
		ToolbarService toolbarService,
		UserManagerUIService userManagerService,
		TeamSpaceDao tsDao,
		TeamSpaceUIService teamSpaceService,
		@UserManagerTreeUsers UITree basicTreeForUserSelection,
		@UserManagerTreeGroups UITree basicTreeForGroupSelection
		){
		
		/* store objects */
		this.gridHelperService = gridHelperService;
		this.toolbarService = toolbarService;
		this.userManagerService = userManagerService;
		this.tsDao = tsDao;
		this.teamSpaceService = teamSpaceService;
		
		this.basicTreeForUserSelection = basicTreeForUserSelection;
		this.basicTreeForGroupSelection = basicTreeForGroupSelection;
	}
	
	@Override
	public ImageResource getIcon() {
		return BaseIcon.GROUP.toImageResource(1);
	}

	@Override
	public String getName() {
		return TeamSpaceMessages.INSTANCE.editTeamSpaceMembersName();
	}

	@Override
	public Widget getCard() {
		Widget editGrid = createEditComponent();
		
		return editGrid;
	}
	
	@Override
	public void setCurrentSpace(TeamSpaceDto teamSpace) {
		super.setCurrentSpace(teamSpace);
		
		/* create store */
		allUsersStore =  new ListStore<StrippedDownUser>(userPa.dtoId());
		allGroupsStore =  new ListStore<StrippedDownGroup>(groupPa.dtoId());
		memberStore = new ListStore<StrippedDownTeamSpaceMemberDto>(memberPa.dtoId());
		memberStore.setAutoCommit(true);
		
		/* fill member store */
		if(null != teamSpace.getOwner())
			memberStore.add(StrippedDownTeamSpaceMemberDto.createForOwner(teamSpace.getOwner()));
		
		for(TeamSpaceMemberDto member : teamSpace.getMembers())
			memberStore.add(StrippedDownTeamSpaceMemberDto.createFrom(member));
	}

	private Widget createEditComponent() {
		VerticalLayoutContainer nsContainer = new VerticalLayoutContainer();
		
		/* create toolbar */
		ToolBar toolbar = new DwToolBar();
		nsContainer.add(toolbar, new VerticalLayoutData(1,-1));
		
		/* create grid */
		final Grid<StrippedDownTeamSpaceMemberDto> userGrid = createMembersGrid();
		nsContainer.add(userGrid, new VerticalLayoutData(1,-1));
		
		
		/* add member */
		DwTextButton addMemberBtn = toolbarService.createSmallButtonLeft(TeamSpaceMessages.INSTANCE.editTeamSpaceMembersAddMember(), BaseIcon.USER_ADD);
		addMemberBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				TreeSelectionPopup popup = new TreeSelectionPopup(basicTreeForUserSelection, UserDto.class){
					@Override
					protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
						/* add members */
						for(AbstractNodeDto user : selectedItems){
							if (null == memberStore.findModelWithKey(String.valueOf(user.getId())))
								memberStore.add(StrippedDownTeamSpaceMemberDto.createFrom(StrippedDownUser.fromUser((UserDto)user)));
						}
						/* set changed */
						changed = true;
							
					}
				};
				List<UserDto> selectedUsers = new ArrayList<>();
				for (StrippedDownTeamSpaceMemberDto strippedMember: memberStore.getAll()) {
					if (strippedMember.getType()!= Type.USER)
						continue;
					
					UserDto selectedUser = new UserDtoDec();
					selectedUser.setId(strippedMember.getId());
					selectedUser.setFirstname(strippedMember.getFirstname());
					selectedUser.setLastname(strippedMember.getLastname());
					selectedUsers.add(selectedUser);
				}
				popup.setSelectedValues(selectedUsers.toArray(new AbstractNodeDto[]{}));
				popup.setSelectionMode(SelectionMode.MULTI);
				popup.setHeaderIcon(BaseIcon.ADD);
				popup.setHeading(BaseMessages.INSTANCE.add());
				popup.show();
				
			}
		});
		
		toolbar.add(addMemberBtn);
		
		/* add member */
		DwTextButton addGroupBtn = toolbarService.createSmallButtonLeft(TeamSpaceMessages.INSTANCE.editTeamSpaceMembersAddGroupMember(), BaseIcon.GROUP_ADD);
		addGroupBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				Map<ValueProvider<StrippedDownGroup,String>, String> displayProperties = new LinkedHashMap<ValueProvider<StrippedDownGroup,String>, String>();
				displayProperties.put(groupPa.name(), BaseMessages.INSTANCE.name());
				displayProperties.put(groupPa.parentOu(), TeamSpaceMessages.INSTANCE.ou());
				
				TreeSelectionPopup popup = new TreeSelectionPopup(basicTreeForGroupSelection, GroupDto.class){
					@Override
					protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
						/* add members */
						for(AbstractNodeDto group : selectedItems){
							if (null == memberStore.findModelWithKey(String.valueOf(group.getId())))
								memberStore.add(StrippedDownTeamSpaceMemberDto.createFrom(StrippedDownGroup.fromGroup((GroupDto)group)));
						}
						/* set changed */
						changed = true;
							
					}
				};
				List<GroupDto> selectedGroups = new ArrayList<>();
				for (StrippedDownTeamSpaceMemberDto strippedMember: memberStore.getAll()) {
					if (strippedMember.getType()!= Type.GROUP)
						continue;
					
					GroupDto selectedGroup = new GroupDto();
					selectedGroup.setId(strippedMember.getId());
					selectedGroup.setName(strippedMember.getName());
					selectedGroups.add(selectedGroup);
				}
				popup.setSelectedValues(selectedGroups.toArray(new AbstractNodeDto[]{}));
				popup.setSelectionMode(SelectionMode.MULTI);
				popup.setHeaderIcon(BaseIcon.ADD);
				popup.setHeading(BaseMessages.INSTANCE.add());
				popup.show();
				
			}
		});
		
		toolbar.add(addGroupBtn);
		
		toolbar.add(new SeparatorToolItem());
		
		/* remove members */
		SplitButton removeButton = toolbarService.configureButton(new DwSplitButton(), TeamSpaceMessages.INSTANCE.editTeamSpaceMembersRemoveMember(), BaseIcon.DELETE);
		toolbar.add(removeButton);
		
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<StrippedDownTeamSpaceMemberDto> items = userGrid.getSelectionModel().getSelectedItems();
				if(null != items && ! items.isEmpty())
					changed = true;
				for(StrippedDownTeamSpaceMemberDto model : items)
					memberStore.remove(model);
			}
		});
		
		/* remove all members */
		MenuItem removeAllMenuItem =  new DwMenuItem(TeamSpaceMessages.INSTANCE.editTeamSpaceMembersRemoveAllMembers(), BaseIcon.DELETE);
		removeAllMenuItem.addSelectionHandler(new SelectionHandler() {
			@Override
			public void onSelection(SelectionEvent event) {
				changed = true;
				memberStore.clear();
			}
		});
		
		Menu removeMenu = new DwMenu();
		removeMenu.add(removeAllMenuItem);
		removeButton.setMenu(removeMenu);
		
		nsContainer.setHeight(280);
		
		return nsContainer;
	}

	private Grid<StrippedDownTeamSpaceMemberDto> createMembersGrid() {
		/* create columns */
		List<ColumnConfig<StrippedDownTeamSpaceMemberDto,?>> configs = new ArrayList<ColumnConfig<StrippedDownTeamSpaceMemberDto,?>>();   
		
		
		/* add user column */
		ColumnConfig<StrippedDownTeamSpaceMemberDto,StrippedDownTeamSpaceMemberDto> ccicon = new ColumnConfig<StrippedDownTeamSpaceMemberDto,StrippedDownTeamSpaceMemberDto>(new IdentityValueProvider<StrippedDownTeamSpaceMemberDto>(), 30);
		ccicon.setCell(new AbstractCell<StrippedDownTeamSpaceMemberDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					StrippedDownTeamSpaceMemberDto value, SafeHtmlBuilder sb) {
				if(value.isOwner())
					 sb.append(BaseIcon.USER_MD.toSafeHtml());
				else if(value.getType() == Type.GROUP)
					sb.append(BaseIcon.GROUP.toSafeHtml());
			}
		});
		configs.add(ccicon);
		
		ColumnConfig<StrippedDownTeamSpaceMemberDto,String> ccName = new ColumnConfig<StrippedDownTeamSpaceMemberDto,String>(memberPa.name(), 140,BaseMessages.INSTANCE.name());
		configs.add(ccName);

		ColumnConfig<StrippedDownTeamSpaceMemberDto,String> ccFirst = new ColumnConfig<StrippedDownTeamSpaceMemberDto,String>(memberPa.firstname(), 150, UsermanagerMessages.INSTANCE.firstname());
		configs.add(ccFirst);
		
		ColumnConfig<StrippedDownTeamSpaceMemberDto,String> ccLast = new ColumnConfig<StrippedDownTeamSpaceMemberDto,String>(memberPa.lastname(), 150, UsermanagerMessages.INSTANCE.lastname());
		configs.add(ccLast);
		
		final CCContainer<StrippedDownTeamSpaceMemberDto, TeamSpaceRoleDto> cccRole = gridHelperService.createComboBoxColumnConfig(TeamSpaceRoleDto.values(), memberPa.role(), false, null, 140);
		ColumnConfig<StrippedDownTeamSpaceMemberDto,TeamSpaceRoleDto> roleConfig = cccRole.getConfig();
		roleConfig.setHeader( TeamSpaceMessages.INSTANCE.editTeamSpaceMembersGridRoleColumn());
		configs.add(roleConfig);
		
		/* create grid */
		final Grid<StrippedDownTeamSpaceMemberDto> grid = new Grid<StrippedDownTeamSpaceMemberDto>(memberStore, new ColumnModel<StrippedDownTeamSpaceMemberDto>(configs));
		
		/* create editing */
		final GridEditing<StrippedDownTeamSpaceMemberDto> editing = new GridInlineEditing<StrippedDownTeamSpaceMemberDto>(grid);
		
		
		if (teamSpaceService.isAdmin(teamSpace)) {
			editing.addEditor(cccRole.getConfig(), cccRole.getConverter(), cccRole.getCombo());
		} else {
			SimpleComboBox<Object> rolesComboBox = cccRole.getCombo();
			// only ADMIN users should see the ADMIN role in the combobox
			rolesComboBox.remove(TeamSpaceRoleDto.ADMIN);
			editing.addEditor(cccRole.getConfig(), cccRole.getConverter(), rolesComboBox);
		}
		
		editing.addCompleteEditHandler(new CompleteEditHandler<StrippedDownTeamSpaceMemberDto>() {
			@Override
			public void onCompleteEdit(
					CompleteEditEvent<StrippedDownTeamSpaceMemberDto> event) {
				changed = true;
			}
		});
		
		grid.setSelectionModel(new GridSelectionModel<StrippedDownTeamSpaceMemberDto>());
		grid.getView().setShowDirtyCells(false);
		grid.getSelectionModel().setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.MULTI);

		return grid;
	}

	@Override
	public int getHeight() {
		return 460;
	}

	@Override
	public boolean applies(TeamSpaceDto teamSpace) {
		return true;
	}
	
	@Override
	public void submitPressed(final SubmitTrackerToken submitTrackerToken) {
		if(! changed){
			submitTrackerToken.setCompleted();
			return;
		}
		
		/* remove owner from member store */
		for(StrippedDownTeamSpaceMemberDto member : memberStore.getAll()){
			if(member.isOwner()){
				memberStore.remove(member);
				break;
			}
		}
		
		tsDao.setMembers(teamSpace, new ArrayList<StrippedDownTeamSpaceMemberDto>(memberStore.getAll()), new NotamCallback<TeamSpaceDto>(TeamSpaceMessages.INSTANCE.editTeamSpaceMembersSubmitted()){
			@Override
			public void doOnSuccess(TeamSpaceDto result) {
				submitTrackerToken.setCompleted();
			}
			@Override
			public void doOnFailure(Throwable caught) {
				submitTrackerToken.failure(caught);
			}
		});
	}


}
