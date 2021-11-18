package net.datenwerke.rs.configservice.service.configservice.terminal.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Provider;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.installation.PackagedScriptHelper;

public class VfsHelper {

   private final FileServerService fileServerService;
   private final Provider<PackagedScriptHelper> packagedScriptHelper;
   private final HistoryService historyService;

   @Inject
   public VfsHelper(FileServerService fileServerService,
         Provider<PackagedScriptHelper> packagedScriptHelper,
         HistoryService historyService) {
      this.packagedScriptHelper = packagedScriptHelper;
      this.fileServerService = fileServerService;
      this.historyService = historyService;
   }

   /**
    * Returns the file server root folder. Throws NoSuchElementException if the
    * root server could not be found.
    * 
    * @return
    */
   public FileServerFolder getFileServerRoot() {
      List<AbstractFileServerNode> rootNodes = fileServerService.getRoots();
      Optional<AbstractFileServerNode> rootFolder = rootNodes
            .stream()
            .filter(node -> node instanceof FileServerFolder
                  && ((FileServerFolder) node).getName().equals("FileServer Root"))
            .findAny();
      return (FileServerFolder) rootFolder.get();
   }

   /**
    * Attempts to find a child folder with the name subFolderName. Throws
    * NoSuchElementException if there no matching folder is found.
    * 
    * @param folder        folder to query
    * @param subFolderName name of the sub-folder
    * @return FileServerFolder matching the name of subFolderName
    */
   public FileServerFolder getSubFolder(FileServerFolder folder, String subFolderName) {
      List<FileServerFolder> childFolder = folder.getChildrenOfType(FileServerFolder.class);
      Optional<FileServerFolder> subfolder = childFolder
            .stream()
            .filter(child -> child.getName()
                  .equals(subFolderName))
            .findAny();
      return subfolder.get();
   }

   /**
    * Collects all FileServerFiles under a given FileServerFolder
    * 
    * @param folder folder to query
    * @return a list of all found files
    */
   public List<FileServerFile> getFilesFromFileServerFolder(FileServerFolder folder) {
      List<FileServerFolder> foldersToExplore = new ArrayList<>();
      List<FileServerFile> foundFiles = new ArrayList<>();
      foldersToExplore.add(folder);

      while (!foldersToExplore.isEmpty()) {
         FileServerFolder currentFolder = foldersToExplore.remove(0);
         foldersToExplore.addAll(currentFolder.getChildrenOfType(FileServerFolder.class));
         foundFiles.addAll(currentFolder.getChildrenOfType(FileServerFile.class));
      }
      return foundFiles;
   }

   /**
    * Get all files contained within the directory Roo/tmpFolderName
    * 
    * @param tmpFolderName name of the tmp folder - has to have Root as parent
    *                      directory
    * @return a list of all found files
    */
   public List<FileServerFile> getExpectedConfigFileList(String tmpFolderName) {
      return getFilesFromFileServerFolder(getSubFolder(getFileServerRoot(), tmpFolderName));
   }

   /**
    * Get all files of Root/ excluding the Root/tmpFolder.
    * 
    * @param tmpFolderName name of the folder to exclude - has to have Root as
    *                      parent directory
    * @return a list of all found files
    */
   public List<FileServerFile> getActualConfigFileList(String tmpFolderName) {
      List<FileServerFolder> subFolders = getFileServerRoot().getChildrenOfType(FileServerFolder.class);
      List<FileServerFolder> configFolders = subFolders
            .stream()
            .filter(folder -> !folder.getName().equals(tmpFolderName))
            .collect(Collectors.toList());
      List<FileServerFile> configFiles = new ArrayList<>();
      configFolders.forEach(folder -> configFiles.addAll(getFilesFromFileServerFolder(folder)));
      return configFiles;
   }

   public List<HistoryLink> getHistoryLinks(List<FileServerFile> files) {
      List<HistoryLink> links = new ArrayList<>();
      files.forEach(file -> links.addAll(historyService.buildLinksFor(file)));
      return links;
   }

   /**
    * Removes the directory Root/tmpFolderName
    * 
    * @param tmpFolderName name of the tmp folder which is to be removed
    */
   public void removeTmpFolder(String tmpFolderName) {
      fileServerService.remove(getSubFolder(getFileServerRoot(), tmpFolderName));
   }

   public void removeFolder(FileServerFolder folder) {
      fileServerService.remove(folder);
   }

   // TODO Figure out how to deal with exceptions
   /**
    * Executes the copyFile commands of the baseconfig package with a custom
    * directory prefix. The config package will be copied to Root + path
    * 
    * @param path Eg: "/tmp" or "" for default directory
    */
   public void unzipAndCopyBaseConfigPackageTo(String path) {
      PackagedScriptHelper helper = packagedScriptHelper.get();
      File pkgDir = helper.getPackageDirectory();

      if (pkgDir.exists() && pkgDir.isDirectory()) {
         Optional<File> baseConfigPackageScriptFile = helper
               .listPackages()
               .stream()
               .filter(f -> f.getName().matches("^baseconfig-RS.*[.]zip$"))
               .findAny();

         if (baseConfigPackageScriptFile.isPresent() && helper.validateZip(baseConfigPackageScriptFile.get(), true)) {
            FileServerFolder targetDir = null;
            try {
               targetDir = helper.extractPackageTemporarily(new FileInputStream(baseConfigPackageScriptFile.get()));
               helper.executePackage(targetDir, "", false, true, path);
            } catch (FileNotFoundException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            } finally {
               if (null != targetDir)
                  fileServerService.forceRemove(targetDir);
            }
         }
      }
   }

}
