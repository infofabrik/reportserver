package net.datenwerke.rs.fileserver.service.fileserver.eximport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class FileServerExporter extends TreeNodeExporter {

   public static final String EXPORTER_ID = "FileServerExporter";
   
   public static final String EXPORTER_NAME = "FileServer-Export";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<? extends AbstractNode<?>> getTreeType() {
      return AbstractFileServerNode.class;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { FileServerFolder.class, FileServerFile.class };
   }

   @Override
   public Set<Class<?>> getAllowedReferenceTypes() {
      return new HashSet<>(Arrays.asList(AbstractFileServerNode.class));
   }

}
