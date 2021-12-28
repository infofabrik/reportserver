package net.datenwerke.rs.utils.zip;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.core.service.mail.SimpleAttachment;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;

public interface ZipUtilsService {

   public interface FileFilter {
      public boolean addNode(AbstractFileServerNode node);
   }

   /**
    * Marks a directory for zipping.
    */
   public static Object DIRECTORY_MARKER = new Object();

   /**
    * Packs the given list of files and/or directories into a zip archive.
    * 
    * @param content map containing the files to zip. In case of zipping files, the
    *                key contains the filename, while the value contains the object
    *                to be zipped. The object may be a String or a byte array. In
    *                case of zipping directories, the key contains the relative
    *                directory path, and the value contains
    *                {@link DIRECTORY_MARKER}. In this case, the files inside the
    *                given directory should contain the relative path to the file.
    *                <p>
    *                Example zipping c.txt and mydirectory containing a.txt and
    *                b.txt:<br />
    *                <ul>
    *                <li>mydirectory={@link DIRECTORY_MARKER}</li>
    *                <li>mydirectory/a.txt="a"</li>
    *                <li>mydirectory/b.txt="b"</li>
    *                <li>c.txt="c"</li>
    *                </ul>
    *                </p>
    * 
    * @param os      the OutputStream where the zip should be written to
    * @throws IOException if an I/O error has occurred
    */
   void createZip(Map<String, ? extends Object> content, OutputStream os) throws IOException;

   /**
    * Packs the given content into a zip archive.
    * 
    * @param contentFilename the filename of the file to be zipped
    * @param content         the content to be zipped. It may be a String or a byte
    *                        array
    * @param os              the OutputStream where the zip should be written to
    * @throws IOException if an I/O error has occurred
    */
   void createZip(String contentFilename, Object content, OutputStream os) throws IOException;

   /**
    * Replaces invalid characters from a filename to be zipped with valid
    * characters.
    * 
    * @param filename the filename to be cleaned
    * @return the cleaned filename
    */
   String cleanFilename(String filename);

   /**
    * Replaces invalid characters from a directory name to be zipped with valid
    * characters.
    * 
    * @param dirname the directory name to be cleaned
    * @return the cleaned directory name
    */
   String cleanDirectoryName(String dirname);

   void createZip(List<Path> files, OutputStream os) throws IOException;

   void createZipFromEmailAttachments(List<SimpleAttachment> attachments, OutputStream os) throws IOException;

   void extractZip(byte[] data, ZipExtractionConfig config) throws IOException;

   void extractZip(InputStream is, ZipExtractionConfig config) throws IOException;

   void createZip(byte[] content, OutputStream os) throws IOException;

   void createZip(FileServerFolder folder, OutputStream os) throws IOException;

   void createZip(FileServerFolder folder, OutputStream os, FileFilter filter) throws IOException;
}
