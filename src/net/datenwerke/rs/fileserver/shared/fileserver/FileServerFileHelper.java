package net.datenwerke.rs.fileserver.shared.fileserver;

public class FileServerFileHelper {

   public boolean isTextFile(String filename, String contentType) {
      if (null != filename && (filename.endsWith(".rs") || filename.endsWith(".groovy") || filename.endsWith(".txt")
            || filename.endsWith(".xml")  || filename.endsWith(".cf") ))
         return true;

      if (null == contentType)
         return false;

      return (contentType.startsWith("text") || contentType.endsWith("xml") || contentType.contains("groovy"));
   }

}
