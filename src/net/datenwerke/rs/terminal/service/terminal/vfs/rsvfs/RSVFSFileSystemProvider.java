/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package net.datenwerke.rs.terminal.service.terminal.vfs.rsvfs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessMode;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.ProviderMismatchException;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RSVFSFileSystemProvider extends FileSystemProvider {

   private RSVFSFileSystem fileSystem;

   @Override
   public String getScheme() {
      return "rs-vfs";
   }

   @Override
   public RSVFSFileSystem newFileSystem(URI uri, Map<String, ?> env) throws IOException {
      synchronized (this) {
         if (null != fileSystem) {
            throw new FileSystemAlreadyExistsException();
         }
         fileSystem = new RSVFSFileSystem(this, env);
      }
      return fileSystem;
   }

   @Override
   public FileSystem getFileSystem(URI uri) {
      return fileSystem;
   }

   @Override
   public Path getPath(URI uri) {
      String str = uri.getSchemeSpecificPart();
      return getFileSystem(uri, true).getPath(str);
   }

   public RSVFSFileSystem getFileSystem(URI uri, boolean create) {
      synchronized (this) {
         if (fileSystem == null) {
            if (create) {
               try {
                  fileSystem = newFileSystem(uri, null);
               } catch (IOException e) {
                  throw (FileSystemNotFoundException) new FileSystemNotFoundException().initCause(e);
               }
            } else {
               throw new FileSystemNotFoundException();
            }
         }
         return fileSystem;
      }
   }

   @Override
   public SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs)
         throws IOException {
      if (!(path instanceof RSVFSPath)) {
         throw new ProviderMismatchException();
      }
      try {
         return ((RSVFSPath) path).getFileSystem().newByteChannel(path, options, attrs);
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   @Override
   public DirectoryStream<Path> newDirectoryStream(Path dir, Filter<? super Path> filter) throws IOException {
      if (!(dir instanceof RSVFSPath)) {
         throw new ProviderMismatchException();
      }
      try {
         return ((RSVFSPath) dir).getFileSystem().newDirectoryStream(dir, filter);
      } catch (NoSuchFileException e) {
         throw e;
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   @Override
   public void createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException {
      if (!(dir instanceof RSVFSPath)) {
         throw new ProviderMismatchException();
      }
      try {
         ((RSVFSPath) dir).getFileSystem().createDirectory(dir, attrs);
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   @Override
   public FileChannel newFileChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs)
         throws IOException {
      if (!(path instanceof RSVFSPath)) {
         throw new ProviderMismatchException();
      }
      try {
         return ((RSVFSPath) path).getFileSystem().newFileChannel(path, options, attrs);
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   @Override
   public InputStream newInputStream(Path path, OpenOption... options) throws IOException {
      try {
         return toRSVFSPath(path).getFileSystem().newInputStream(toRSVFSPath(path), options);
      } catch (NoSuchFileException e) {
         throw e;
      } catch (Exception e) {
         throw new IOException(e);
      }
   }
   
   @Override
   public void delete(Path path) throws IOException {
      if (!(path instanceof RSVFSPath)) {
         throw new ProviderMismatchException();
      }
      try {
         ((RSVFSPath) path).getFileSystem().delete(path);
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   @Override
   public void copy(Path source, Path target, CopyOption... options) throws IOException {
      try {
         (toRSVFSPath(source)).getFileSystem().copy(toRSVFSPath(source), toRSVFSPath(target), options);
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   @Override
   public void move(Path source, Path target, CopyOption... options) throws IOException {
      try {
         (toRSVFSPath(source)).getFileSystem().move(toRSVFSPath(source), toRSVFSPath(target), options);
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   @Override
   public boolean isSameFile(Path path, Path path2) throws IOException {
      return path.toAbsolutePath().equals(path2.toAbsolutePath());
   }

   @Override
   public boolean isHidden(Path path) throws IOException {
      return false;
   }

   @Override
   public FileStore getFileStore(Path path) throws IOException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void checkAccess(Path path, AccessMode... modes) throws IOException {
   }
   
   /*
    * Adapted from
    * org.apache.sshd.sftp.client.fs.SftpFileSystemProvider.toSftpPath(Path),
    * Version 2.6.0
    */
   public RSVFSPath toRSVFSPath(Path path) {
      Objects.requireNonNull(path, "No path provided");
      if (!(path instanceof RSVFSPath)) {
         throw new ProviderMismatchException("Path is not SFTP: " + path);
      }
      return (RSVFSPath) path;
   }

   @Override
   public <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options) {
      throw new UnsupportedOperationException();
   }
   
   @Override
   public <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options)
         throws IOException {
      if (!(path instanceof RSVFSPath)) {
         throw new ProviderMismatchException();
      }
      try {
         return ((RSVFSPath) path).getFileSystem().readAttributes(path, type, options);
      } catch (NoSuchFileException e) {
         throw e;
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   @Override
   public Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException {
      // TODO Auto-generated method stub

   }
   
   

}