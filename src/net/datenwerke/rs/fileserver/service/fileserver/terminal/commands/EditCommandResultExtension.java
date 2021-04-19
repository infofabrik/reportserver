package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.fileserver.client.fileserver.dto"
)
public class EditCommandResultExtension extends CommandResultExtension {

	@ExposeToClient(allowArbitraryLobSize=true, disableHtmlEncode=true)
	private String data;
	
	@EnclosedEntity
	@ExposeToClient
	private FileServerFile file;

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setFile(FileServerFile file) {
		this.file = file;
	}

	public FileServerFile getFile() {
		return file;
	}

	
}
