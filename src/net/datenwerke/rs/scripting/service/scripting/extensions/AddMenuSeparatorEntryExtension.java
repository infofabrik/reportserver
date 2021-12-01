package net.datenwerke.rs.scripting.service.scripting.extensions;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.scripting.client.scripting.dto"
)
public class AddMenuSeparatorEntryExtension extends CommandResultExtension {

	@ExposeToClient
	private String menuName;
	
	public AddMenuSeparatorEntryExtension(){
		
	}
	
	public AddMenuSeparatorEntryExtension(String name){
		menuName = name;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuName() {
		return menuName;
	}
	
}
