package net.datenwerke.gf.service.tempfile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import net.datenwerke.security.service.usermanager.entities.User;

public class TempFile {

   private String webId;
   private Path path;
   private Collection<User> permittedUsers;

   private String mimeType;
   private String downloadName;

   private Date created;
   private Date lastAccessed;

   public TempFile(Path path) {
      this(path, UUID.randomUUID().toString());
   }

   public TempFile(Path path, String webId) {
      this(path, webId, null);
   }

   public TempFile(Path path, User permittedUser) {
      this(path, UUID.randomUUID().toString(), Collections.singleton(permittedUser));
   }

   public TempFile(Path path, Collection<User> permittedUsers) {
      this(path, UUID.randomUUID().toString(), permittedUsers);
   }

   public TempFile(Path path, String webId, Collection<User> permittedUsers) {
      this.path = path;
      this.webId = webId;
      this.permittedUsers = permittedUsers;

      this.created = new Date();
   }

   public String getWebId() {
      return webId;
   }

   public Path getPath() {
      return path;
   }

   public Collection<User> getPermittedUsers() {
      if (null == permittedUsers)
         permittedUsers = new ArrayList<User>();
      return permittedUsers;
   }

   public boolean isPermittedUser(User user) {
      if (null == permittedUsers || permittedUsers.isEmpty())
         return true;

      return permittedUsers.contains(user);
   }

   public String getMimeType() {
      return mimeType;
   }

   public void setMimeType(String mimeType) {
      this.mimeType = mimeType;
   }

   public String getDownloadName() {
      return downloadName;
   }

   public void setDownloadName(String downloadName) {
      this.downloadName = downloadName;
   }

   public Date getCreated() {
      return created;
   }

   public Date getLastAccessed() {
      return lastAccessed;
   }

   public void setLastAccessed(Date lastAccessed) {
      this.lastAccessed = lastAccessed;
   }

   public void setLastAccessed() {
      this.lastAccessed = new Date();
   }

}
