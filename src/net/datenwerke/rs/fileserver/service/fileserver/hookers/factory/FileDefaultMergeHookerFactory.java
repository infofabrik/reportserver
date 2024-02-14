package net.datenwerke.rs.fileserver.service.fileserver.hookers.factory;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.hookers.FileServerDefaultMergeHooker;

public interface FileDefaultMergeHookerFactory {
   
   FileServerDefaultMergeHooker<? extends FileServerFile> create(Class<? extends FileServerFile> targetClass);

}
