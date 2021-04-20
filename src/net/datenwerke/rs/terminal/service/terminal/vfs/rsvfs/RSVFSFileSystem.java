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

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.AccessDeniedException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.ProviderMismatchException;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.sshd.common.file.util.BaseFileSystem;
import org.apache.sshd.common.session.SessionContext;
import org.apache.sshd.common.util.io.IoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Provider;

import net.datenwerke.gf.service.tempfile.TempFileService;
import net.datenwerke.rs.remoteaccess.service.sftp.SftpRequestWrapper;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;

public class RSVFSFileSystem extends BaseFileSystem<RSVFSPath> {

   private final Provider<TerminalService> terminalServiceProvider;
   private final SessionContext sessionContext;
   private final Provider<TempFileService> tempFileServiceProvider;

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());
   
   public final static String VFS = "RS_VFS";
   public final static String SESSION_CONTEXT = "RS_SESSION_CONTEXT";
   public final static String TEMPFILESERVICE_PROVIDER = "RS_TEMPFILESERVICE_PROVIDER";
   

   public RSVFSFileSystem(FileSystemProvider provider, Map<String, ?> env) {
      super(provider);
      this.terminalServiceProvider = Objects.requireNonNull((Provider<TerminalService>) env.get(VFS));
      this.sessionContext = Objects.requireNonNull((SessionContext) env.get(SESSION_CONTEXT));
      this.tempFileServiceProvider = Objects.requireNonNull((Provider<TempFileService>) env.get(TEMPFILESERVICE_PROVIDER));
   }

   protected TerminalSession getSession() {
      return terminalServiceProvider.get().getUnscopedTerminalSession();
   }
   
   @Override
   protected RSVFSPath create(String root, List<String> names) {
      return new RSVFSPath(this, root, names);
   }

   @Override
   public void close() throws IOException {
      // TODO Auto-generated method stub

   }

   @Override
   public boolean isOpen() {
      return true;
   }

   @Override
   public Set<String> supportedFileAttributeViews() {
      return ImmutableSet.of("basic", "posix");
   }

   @Override
   public UserPrincipalLookupService getUserPrincipalLookupService() {
      // TODO Auto-generated method stub
      return null;
   }

   public InputStream newInputStream(final Path path, final OpenOption... options) throws IOException, VFSException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doNewInputStream(path, options), sessionContext).call();
      } catch (NoSuchFileException e) {
         throw e;
      } catch (Exception e) {
         throw new IOException(e);
      }
   }
   
   private InputStream doNewInputStream(Path path, OpenOption[] options)
         throws IOException, VFSException {
      TerminalSession session = getSession();
      VFSLocation loc = session.getFileSystem().getLocation(path.toAbsolutePath().toString());
      if (!loc.exists())
         throw new NoSuchFileException("does not exist");
      InputStream is = new ByteArrayInputStream(loc.getContent());
      return is;
   }
   
   public <A extends BasicFileAttributes> A readAttributes(final Path path, final Class<A> clazz,
         final LinkOption... options) throws IOException, VFSException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doReadAttributes(path, clazz, options), sessionContext).call();
      } catch (NoSuchFileException e) {
         throw e;
      } catch (Exception e) {
         throw new IOException(e);
      }

   }

   private <A extends BasicFileAttributes> A doReadAttributes(Path path, Class<A> clazz, LinkOption... options)
         throws IOException, VFSException {
      TerminalSession session = getSession();
      VFSLocation loc = session.getFileSystem().getLocation(path.toAbsolutePath().toString());
      if (!loc.exists())
         throw new NoSuchFileException("does not exist");

      final boolean folder = loc.isFolder();
      final Date lastModifiedTime = loc.getLastModified();
      
      return (A) new RSVFSFileAttributes(
            folder, 
            folder? 0: loc.getSize(),
            null != lastModifiedTime ? FileTime.fromMillis(lastModifiedTime.getTime()) : FileTime.fromMillis(0l), 
            FileTime.fromMillis(0l),
            FileTime.fromMillis(0l),
            loc.canWriteIntoLocation());
   }

   public DirectoryStream<Path> newDirectoryStream(final Path dir, final DirectoryStream.Filter<? super Path> filter)
         throws IOException, VFSException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doNewDirectoryStream(dir, filter), sessionContext).call();
      } catch (NoSuchFileException e) {
         throw e;
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   private DirectoryStream<Path> doNewDirectoryStream(Path dir, Filter<? super Path> filter) throws IOException, VFSException {
      TerminalSession session = getSession();
      
      VFSLocation loc = session.getFileSystem().getLocation(dir.toAbsolutePath().toString());
      VFSLocationInfo info = session.getFileSystem().getLocationInfo(loc);
      
      if (!loc.exists())
         throw new NoSuchFileException("does not exist");

      if (!loc.isFolder())
         throw new IOException("not a folder");

      final List<String> childList = info.getChildInfos()
            .stream()
            .filter(child -> null != child.getName())
            .map(VFSObjectInfo::getName)
            .collect(toList());

      return new DirectoryStream<Path>() {
         @Override
         public Iterator<Path> iterator() {
            return new Iterator<Path>() {
               final Iterator<String> delegate = childList.iterator();

               @Override
               public boolean hasNext() {
                  return delegate.hasNext();
               }

               @Override
               public Path next() {
                  String val = delegate.next();
                  return new RSVFSPath(RSVFSFileSystem.this, dir.toString(), Arrays.asList(val));
               }

               @Override
               public void remove() {
                  throw new UnsupportedOperationException();
               }
            };
         }

         @Override
         public void close() throws IOException {
         }
      };

   }
   
   public SeekableByteChannel newByteChannel(final Path path, 
         final Set<? extends OpenOption> options, final FileAttribute<?>[] attrs) throws IOException, VFSException {
      return newFileChannel(path, options, attrs);
   }
   
   public FileChannel newFileChannel(final Path path, final Set<? extends OpenOption> options, final FileAttribute<?>[] attrs) 
         throws IOException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doNewFileChannel(path, options, attrs), sessionContext).call();
      } catch (Exception e) {
         throw new IOException(e);
      }
   }
   
   private FileChannel doNewFileChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>[] attrs)
         throws VFSException, IOException {
      TerminalSession session = getSession();

      final Path tmpFile = tempFileServiceProvider.get().createTempFile();
      
      final boolean openForWrite = (options.contains(StandardOpenOption.WRITE) ||
            options.contains(StandardOpenOption.APPEND));
      
      final boolean openForRead = options.contains(StandardOpenOption.READ);
      
      final VFSLocation loc = session.getFileSystem().getLocation(path.toAbsolutePath().toString());
      
      if (openForRead) {
         final byte[] data = loc.getContent();
         if (null != data)
            Files.write(tmpFile, data);
      }

      final FileChannel fileChannel = tmpFile.getFileSystem().provider().newFileChannel(tmpFile, options, attrs);

      return new FileChannel() {
         @Override
         public int write(ByteBuffer src) throws IOException {
            return fileChannel.write(src);
         }

         @Override
         public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
            return fileChannel.write(srcs, offset, length);
         }

         @Override
         public long position() throws IOException {
            return fileChannel.position();
         }

         @Override
         public FileChannel position(long newPosition) throws IOException {
            fileChannel.position(newPosition);
            return this;
         }

         @Override
         public long size() throws IOException {
            return fileChannel.size();
         }

         @Override
         public FileChannel truncate(long size) throws IOException {
            fileChannel.truncate(size);
            return this;
         }

         @Override
         public void force(boolean metaData) throws IOException {
            fileChannel.force(metaData);
         }

         @Override
         public long transferTo(long position, long count, WritableByteChannel target) throws IOException {
            return fileChannel.transferTo(position, count, target);
         }

         @Override
         public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException {
            return fileChannel.transferFrom(src, position, count);
         }

         @Override
         public int read(ByteBuffer dst) throws IOException {
            return fileChannel.read(dst);
         }

         @Override
         public int read(ByteBuffer dst, long position) throws IOException {
            return fileChannel.read(dst, position);
         }

         @Override
         public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
            return fileChannel.read(dsts, offset, length);
         }

         @Override
         public int write(ByteBuffer src, long position) throws IOException {
            return fileChannel.write(src, position);
         }

         @Override
         public MappedByteBuffer map(MapMode mode, long position, long size) throws IOException {
            throw new UnsupportedOperationException();
         }

         @Override
         public FileLock lock(long position, long size, boolean shared) throws IOException {
            return fileChannel.lock(position, size, shared);
         }

         @Override
         public FileLock tryLock(long position, long size, boolean shared) throws IOException {
            return fileChannel.tryLock(position, size, shared);
         }

         @Override
         public void implCloseChannel() throws IOException {
            try {
               if(openForWrite) 
                  upload(loc, tmpFile);
            } catch (IOException e) {
               throw new IOException(e);
            } finally {
               fileChannel.close();
               Files.delete(tmpFile);
            }
         }
      };
   }
   
   public Boolean createDirectory(Path dir, FileAttribute<?>[] attrs) throws IOException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doCreateDirectory(dir, attrs), sessionContext).call();
      } catch (Exception e) {
         throw new IOException(e);
      }
   }
   
   private Boolean doCreateDirectory(Path file, FileAttribute<?>[] attrs) throws VFSException {
      TerminalSession session = getSession();
      final VFSLocation loc = session.getFileSystem().getLocation(file.toAbsolutePath().toString());
      return loc.mkdir();
   }
   
   public Boolean delete(final Path path) throws IOException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doDelete(path), sessionContext).call();
      } catch (Exception e) {
         throw new IOException(e);
      }
   }
   
   private Boolean doDelete(Path path) throws VFSException {
      TerminalSession session = getSession();
      final VFSLocation loc = session.getFileSystem().getLocation(path.toAbsolutePath().toString());
      loc.delete();
      return true;
   }
   
   public Boolean move(final RSVFSPath source, final RSVFSPath target, final CopyOption[] options) throws IOException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doMove(source, target, options), sessionContext).call();
      } catch (Exception e) {
         throw new IOException(e);
      }
   }
   
   /* Adapted from org.apache.sshd.sftp.client.fs.SftpFileSystemProvider.move(Path, Path, CopyOption...), Version 2.6.0 */
   private Boolean doMove(final RSVFSPath source, final RSVFSPath target, final CopyOption[] options) 
         throws VFSException, IOException {
      if (source.getFileSystem() != target.getFileSystem()) {
          throw new ProviderMismatchException("Mismatched file system providers for " + source + " vs. " + target);
      }
      
      provider().checkAccess(source);

      boolean replaceExisting = false;
      boolean copyAttributes = false;
      boolean noFollowLinks = false;
      for (CopyOption opt : options) {
          replaceExisting |= opt == StandardCopyOption.REPLACE_EXISTING;
          copyAttributes |= opt == StandardCopyOption.COPY_ATTRIBUTES;
          noFollowLinks |= opt == LinkOption.NOFOLLOW_LINKS;
      }
      LinkOption[] linkOptions = IoUtils.getLinkOptions(noFollowLinks);

      // attributes of source file
      BasicFileAttributes attrs = readAttributes(source, BasicFileAttributes.class, linkOptions);
      if (attrs.isSymbolicLink()) {
          throw new IOException("Moving of source symbolic link (" + source + ") to " + target + " not supported");
      }

      // delete target if it exists and REPLACE_EXISTING is specified
      Boolean status = IoUtils.checkFileExists(target, linkOptions);
      if (status == null) {
          throw new AccessDeniedException("Existence cannot be determined for move target " + target);
      }

      if (log.isDebugEnabled()) {
          log.debug("move({})[{}] {} => {}", source.getFileSystem(), Arrays.asList(options), source, target);
      }

      if (replaceExisting) {
          provider().deleteIfExists(target);
      } else if (status) {
          throw new FileAlreadyExistsException(target.toString());
      }

      provider().copy(source, target);
      provider().delete(source);

      // copy basic attributes to target
      if (copyAttributes) {
          BasicFileAttributeView view = provider().getFileAttributeView(target, BasicFileAttributeView.class, linkOptions);
          try {
              view.setTimes(attrs.lastModifiedTime(), attrs.lastAccessTime(), attrs.creationTime());
          } catch (Throwable x) {
              // rollback
              try {
                  provider().delete(target);
              } catch (Throwable suppressed) {
                  x.addSuppressed(suppressed);
              }
              throw x;
          }
      }
      
      return true;
   }
   
   public Boolean copy(final RSVFSPath source, final RSVFSPath target, final CopyOption[] options) throws IOException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doCopy(source, target, options), sessionContext).call();
      } catch (Exception e) {
         throw new IOException(e);
      }
   }
   
   /* Adapted from org.apache.sshd.sftp.client.fs.SftpFileSystemProvider.copy(Path, Path, CopyOption...), Version 2.6.0 */
   private Boolean doCopy(final RSVFSPath source, final RSVFSPath target, final CopyOption[] options)
         throws VFSException, IOException {
      if (source.getFileSystem() != target.getFileSystem()) {
         throw new ProviderMismatchException("Mismatched file system providers for " + source + " vs. " + target);
      }
      provider().checkAccess(source);

      boolean replaceExisting = false;
      boolean copyAttributes = false;
      boolean noFollowLinks = false;
      for (CopyOption opt : options) {
          replaceExisting |= opt == StandardCopyOption.REPLACE_EXISTING;
          copyAttributes |= opt == StandardCopyOption.COPY_ATTRIBUTES;
          noFollowLinks |= opt == LinkOption.NOFOLLOW_LINKS;
      }
      
      /* Don't follow links! */
      LinkOption[] linkOptions = IoUtils.getLinkOptions(noFollowLinks);

      // attributes of source file
      BasicFileAttributes attrs = readAttributes(source, BasicFileAttributes.class, linkOptions);
      if (attrs.isSymbolicLink()) {
          throw new IOException("Copying of symbolic links not supported");
      }

      // delete target if it exists and REPLACE_EXISTING is specified
      Boolean status = IoUtils.checkFileExists(target, linkOptions);
      if (status == null) {
          throw new AccessDeniedException("Existence cannot be determined for copy target: " + target);
      }

      if (log.isDebugEnabled()) {
          log.debug("copy({})[{}] {} => {}", source.getFileSystem(), Arrays.asList(options), source, target);
      }

      if (replaceExisting) {
          provider().deleteIfExists(target);
      } else {
          if (status) {
              throw new FileAlreadyExistsException(target.toString());
          }
      }

      // create directory or copy file
      if (attrs.isDirectory()) {
          provider().createDirectory(target);
      } else {
         try (InputStream in = newInputStream(source);
               OutputStream os = provider().newOutputStream(target)) {
              IoUtils.copy(in, os);
          }
      }

      // copy basic attributes to target
      if (copyAttributes) {
          BasicFileAttributeView view = provider().getFileAttributeView(target, BasicFileAttributeView.class, linkOptions);
          try {
              view.setTimes(attrs.lastModifiedTime(), attrs.lastAccessTime(), attrs.creationTime());
          } catch (Throwable x) {
              // rollback
              try {
                  delete(target);
              } catch (Throwable suppressed) {
                  x.addSuppressed(suppressed);
              }
              throw x;
          }
      }

      return true;
   }

   public Boolean upload(final VFSLocation loc, final Path tmpFile) throws IOException {
      try {
         return SftpRequestWrapper.wrapRequest(() -> doUpload(loc, tmpFile), sessionContext).call();
      } catch (Exception e) {
         throw new IOException(e);
      }
   }
   
   private Boolean doUpload(final VFSLocation loc, final Path tmpFile) throws IOException, VFSException {
      if (loc.canWriteIntoLocation()) {
         loc.create();
         loc.writeIntoLocation(Files.readAllBytes(tmpFile));
         return true;
      } else {
         throw new IOException("insufficient rights");
      }
   }

   private static class RSVFSFileAttributes implements PosixFileAttributes {

      private final boolean dir;
      private final long size;
      private final FileTime lastModifiedTime;
      private final FileTime lastAccessTime;
      private final FileTime creationTime;
      private final boolean writeable;

      private RSVFSFileAttributes(boolean dir, long size, FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime, 
            boolean writeable) {
         this.dir = dir;
         this.size = size;
         this.lastModifiedTime = lastModifiedTime;
         this.lastAccessTime = lastAccessTime;
         this.creationTime = creationTime;
         this.writeable = writeable;
      }

      @Override
      public FileTime lastModifiedTime() {
         return lastModifiedTime;
      }

      @Override
      public FileTime lastAccessTime() {
         return lastAccessTime;
      }

      @Override
      public FileTime creationTime() {
         return creationTime;
      }

      @Override
      public boolean isRegularFile() {
         return !dir;
      }

      @Override
      public boolean isDirectory() {
         return dir;
      }

      @Override
      public boolean isSymbolicLink() {
         return false;
      }

      @Override
      public boolean isOther() {
         return false;
      }

      @Override
      public long size() {
         return size;
      }

      @Override
      public Object fileKey() {
         return null;
      }

      @Override
      public UserPrincipal owner() {
         return () -> "owner";
      }

      @Override
      public GroupPrincipal group() {
         return () -> "group";
      }

      @Override
      public Set<PosixFilePermission> permissions() {
         final Set<PosixFilePermission> perms = EnumSet.noneOf(PosixFilePermission.class);
         perms.add(PosixFilePermission.OWNER_READ);
         perms.add(PosixFilePermission.GROUP_READ);
         perms.add(PosixFilePermission.OTHERS_READ);

         if (writeable) {
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.GROUP_WRITE);
            perms.add(PosixFilePermission.OTHERS_WRITE);
         }

         return perms;
      }
   }

}