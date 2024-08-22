package net.datenwerke.rs.base.ext.service

enum RemoteEntityImports {
   REPORTS('reportmanager'),
   USERS('usermanager'),
   DATASOURCES('datasources'),
   DATASINKS('datasinks'),
   FILESERVER('fileserver'),
   DASHBOARDLIB('dashboardlib'),
   REMOTESERVERS('remoteservers'),
   TRANSPORTS('transports')

   private String manager

   RemoteEntityImports(String manager) {
      this.manager = manager
   }
}