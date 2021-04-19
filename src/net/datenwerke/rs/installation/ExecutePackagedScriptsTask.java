package net.datenwerke.rs.installation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutePackagedScriptsTask implements DbInstallationTask {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private FileServerService fileServerService;


	private Provider<PackagedScriptHelper> packagedScriptHelper;

	@Inject
	public ExecutePackagedScriptsTask(
			FileServerService fileServerService,
			Provider<PackagedScriptHelper> packagedScriptHelper
			) {

		this.fileServerService = fileServerService;
		this.packagedScriptHelper = packagedScriptHelper;
	}

	@Override
	public void executeOnStartup() {
		
	}
	
	@Override
	public void executeOnFirstRun() {
		PackagedScriptHelper helper = packagedScriptHelper.get();
		File pkgDir = helper.getPackageDirectory();


		logger.info("Executing package scripts from: " + pkgDir.getAbsolutePath());

		if(pkgDir.exists() && pkgDir.isDirectory()){
			List<File> packagedScripts = helper.listPackages();

			for(File packagedScript : packagedScripts){
				if(helper.validateZip(packagedScript, true)){

					logger.info( "Executing package: " + packagedScript.getAbsolutePath());
					FileServerFolder targetDir = null;
					try {
						targetDir = helper.extractPackageTemporarily(new FileInputStream(packagedScript));

						helper.executePackage(targetDir);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if(null != targetDir)
							fileServerService.forceRemove(targetDir);
					}
				}
			}
		}
	}




}
