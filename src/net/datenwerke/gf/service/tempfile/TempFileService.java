package net.datenwerke.gf.service.tempfile;

import java.io.IOException;
import java.nio.file.Path;

import net.datenwerke.rs.base.server.helpers.tempfile.TempFileServlet;
import net.datenwerke.security.service.usermanager.entities.User;

public interface TempFileService {

   /**
    * Creates a temporary file in the temp directory configured.
    * 
    * @return the temporary file
    * @throws IOException if an I/O error occurs
    */
   Path createTempFile() throws IOException;

   /**
    * Creates a web-accessible temporary file which can be retrieved by the given
    * id.
    * 
    * @param id             the web-id of the temporary file.
    * @param permittedUsers users allowed to access the temporary file
    * @return the web-accessible temp file
    * @throws IOException if an I/O error occurs
    */
   TempFile createWebAccessibleTempFile(String id, User... permittedUsers) throws IOException;

   /**
    * Creates a temporary file which can be retrieved by the returned identifier
    * ({@link TempFile#getWebId()}) using the {@link TempFileServlet}.
    * 
    * @param permittedUsers users allowed to access the temporary file
    * @throws IOException if an I/O error occurs
    */
   TempFile createWebAccessibleTempFile(User... permittedUsers) throws IOException;

   /**
    * Given an identifier (web-id), retrieves the corresponding temporary file.
    * 
    * @param id the web-id
    * @return the temporary file corresponding to the given web-id
    */
   TempFile getTempFileById(String id);

   /**
    * Reads all bytes of a given temporary file into a byte array. Note that this
    * method is intended for simple cases where it is convenient to read all bytes
    * into a byte array. It is not intended for reading in large files.
    * 
    * @param path the temporary file to read
    * @return the content of the temporary file as a byte array
    * @throws IOException if an I/O error occurs
    */
   byte[] readTmpFileIntoByteArray(Path path) throws IOException;

   /**
    * Reads all bytes of a given temporary file into a byte array. Note that this
    * method is intended for simple cases where it is convenient to read all bytes
    * into a byte array. It is not intended for reading in large files.
    * 
    * @param path       the temporary file to read
    * @param removeFile true if the temporary file should be deleted after reading
    *                   into a byte array
    * @return the content of the temporary file as a byte array
    * @throws IOException if an I/O error occurs
    */
   byte[] readTmpFileIntoByteArray(Path path, boolean removeFile) throws IOException;

   /**
    * Checks if the given file is a temporary file, that is, if is located in a
    * temporary file location.
    * 
    * @param path the file to check
    * @return true if the given file is a temporary file
    */
   boolean isTmpFile(Path path);

}
