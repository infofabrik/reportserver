package net.datenwerke.rs.base.ext.service

enum RemoteEntityImports {
   REPORTS('reportmanager'),
   USERS('usermanager'),
   DATASOURCES('datasources'),
   DATASINKS('datasinks'),
   FILESERVER('fileserver'),
   DASHBOARDLIB('dashboardlib'),

   private String manager

   RemoteEntityImports(String manager) {
      this.manager = manager
   }
}