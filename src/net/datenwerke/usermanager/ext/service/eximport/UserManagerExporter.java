package net.datenwerke.usermanager.ext.service.eximport;

import net.datenwerke.eximport.obj.ComplexItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class UserManagerExporter extends TreeNodeExporter {

	private static final String EXPORTER_ID = "UserManagerExporter";
	
	@Override
	public String getExporterId() {
		return EXPORTER_ID;
	}
	
	@Override
	protected Class<? extends AbstractNode<?>> getTreeType() {
		return AbstractUserManagerNode.class;
	}
	
	@Override
	protected Class<?>[] getExportableTypes() {
		return new Class<?>[]{User.class, OrganisationalUnit.class, Group.class};
	}

	@Override
	public String getDisplayNameFor(ExportedItem exportedItem) {

		if(exportedItem.getType().equals(User.class)){
			String name = "";

			ComplexItemProperty firstNameProperty = (ComplexItemProperty) exportedItem.getPropertyByName("firstname");
			if(null != firstNameProperty)
				name += firstNameProperty.getElement().getValue() + " ";

			ComplexItemProperty lastNameProperty = (ComplexItemProperty) exportedItem.getPropertyByName("lastname");
			if(null != lastNameProperty)
				name += lastNameProperty.getElement().getValue();

			return name;
		}else
			return super.getDisplayNameFor(exportedItem);
	}

}
