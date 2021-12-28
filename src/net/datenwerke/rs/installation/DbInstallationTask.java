package net.datenwerke.rs.installation;

public interface DbInstallationTask {

   public void executeOnFirstRun();

   public void executeOnStartup();
}
