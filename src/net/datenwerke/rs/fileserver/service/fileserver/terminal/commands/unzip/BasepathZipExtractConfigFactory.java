package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;

public interface BasepathZipExtractConfigFactory {

   public BasepathZipExtractConfig create(FileServerFolder parent);
}
