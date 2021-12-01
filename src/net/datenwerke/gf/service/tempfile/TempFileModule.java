package net.datenwerke.gf.service.tempfile;

import java.io.File;
import java.util.UUID;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.gf.service.tempfile.annotations.TempDirLocation;
import net.datenwerke.gf.service.tempfile.annotations.TempFileLifeTime;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;

public class TempFileModule extends AbstractModule {

	private static final String CONFIG_FILE = "main/main.cf";

	public static final String TEMP_DIR_PROP = "tempdir";
	
	public static final String RS_TEMPFILE_LIFETIME = "tempfile.lifetime";
	
	@Override
	protected void configure() {
	   bind(TempFileService.class).to(TempFileServiceImpl.class).asEagerSingleton();
	   
		bind(TempFileStartup.class).asEagerSingleton();
	}

	@Inject @Provides @TempDirLocation
	public String provideTempDir(ConfigService configService){
		String tmpDir = null;
		tmpDir = configService.getConfigFailsafe(CONFIG_FILE).getString(TEMP_DIR_PROP);
		
		if(null == tmpDir || ! canWriteIn(tmpDir))
			tmpDir = System.getProperty("java.io.tmpdir");
		
		return tmpDir;
	}
	
	private boolean canWriteIn(String path){
		File f = new File(path, UUID.randomUUID().toString());
		try{
			f.createNewFile();
			f.delete();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Inject @Provides @TempFileLifeTime
	public int provideTempFileLifeSpan(ConfigService configService){
		return configService.getConfigFailsafe(CONFIG_FILE).getInt(RS_TEMPFILE_LIFETIME, 10800);
	}
}
