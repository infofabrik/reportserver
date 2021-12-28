package net.datenwerke.rs.terminal.service.terminal.vfs.rsvfs;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;

import org.apache.sshd.common.file.util.BasePath;

public class RSVFSPath extends BasePath<RSVFSPath, RSVFSFileSystem> {
   public RSVFSPath(RSVFSFileSystem fileSystem, String root, List<String> names) {
      super(fileSystem, root, names);
   }

   @Override
   public RSVFSPath toRealPath(LinkOption... options) throws IOException {
      // TODO: handle links
      RSVFSPath absolute = toAbsolutePath();
      FileSystem fs = getFileSystem();
      FileSystemProvider provider = fs.provider();
      provider.checkAccess(absolute);
      return absolute;
   }
}
