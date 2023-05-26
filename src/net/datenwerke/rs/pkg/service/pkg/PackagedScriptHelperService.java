package net.datenwerke.rs.pkg.service.pkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;

public interface PackagedScriptHelperService {

   File getPackageDirectory();

   FileServerFolder extractPackageTemporarily(InputStream is) throws FileNotFoundException, IOException;

   FileServerFolder getFileServerTempDir();

   String executePackage(FileServerFolder targetDir);

   String executePackage(FileServerFolder targetDir, String scriptOptions);

   String executePackage(FileServerFolder targetDir, String scriptOptions, boolean executeRunScriptCommands,
         boolean executeCopyFilesCommands, Optional<String> copyFilesCustomDstPathPrefix);

   List<File> listPackages();

   boolean validateZip(InputStream is, final boolean requireAutorun);

   boolean validateZip(File f, final boolean requireAutorun);
}
