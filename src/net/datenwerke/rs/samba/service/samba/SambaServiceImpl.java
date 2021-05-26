package net.datenwerke.rs.samba.service.samba;

import static net.datenwerke.rs.samba.service.samba.SambaModule.PROPERTY_SAMBA_DISABLED;
import static net.datenwerke.rs.samba.service.samba.SambaModule.PROPERTY_SAMBA_SCHEDULING_ENABLED;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import com.google.inject.Provider;

import jcifs.CIFSContext;
import jcifs.context.SingletonContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.samba.service.samba.annotations.DefaultSambaDatasink;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class SambaServiceImpl implements SambaService {

   private final Provider<ReportService> reportServiceProvider;
   
   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
   private final Provider<Optional<SambaDatasink>> defaultDatasinkProvider;
   private final Provider<DatasinkService> datasinkServiceProvider;

   @Inject
   public SambaServiceImpl(
         Provider<ReportService> reportServiceProvider,
         Provider<DatasinkService> datasinkServiceProvider,
         @DefaultSambaDatasink Provider<Optional<SambaDatasink>> defaultDatasinkProvider
         ) {
      this.reportServiceProvider = reportServiceProvider;
      this.defaultDatasinkProvider = defaultDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public void sendToSambaServer(Object report, SambaDatasink sambaDatasink, String filename, String folder)
         throws IOException {
      if (!isSambaEnabled())
         throw new IllegalStateException("samba is disabled");

      Objects.requireNonNull(sambaDatasink, "sambaDatasink is null!");
      Objects.requireNonNull(folder);
      Objects.requireNonNull(filename);

      final String host = Objects.requireNonNull(sambaDatasink.getHost());
      final int port = sambaDatasink.getPort();
      final String domain = (null == sambaDatasink.getDomain() ? "" : sambaDatasink.getDomain());
      final String username = sambaDatasink.getUsername();

      if (null == host || host.trim().contentEquals("") || null == username || username.trim().contentEquals(""))
         throw new IllegalArgumentException("Samba server is not configured correctly");

      try (InputStream bis = reportServiceProvider.get().createInputStream(report)) {

         NtlmPasswordAuthenticator ntlmPasswordAuthenticator = new NtlmPasswordAuthenticator(domain, username,
               sambaDatasink.getPassword());
         CIFSContext context = SingletonContext.getInstance().withCredentials(ntlmPasswordAuthenticator);
         String path = host + ":" + port + "/" + folder + "/" + filename;
         SmbFile resource = (SmbFile) context.get(path);

         try (OutputStream ostream = resource.getOutputStream()) {
            IOUtils.copy(bis, ostream);
         }

         if (!resource.exists())
            throw new IOException("Samba file was not uploaded successfully");
      }

   }

   @Override
   public boolean isSambaEnabled() {
      return datasinkServiceProvider.get().isEnabled(PROPERTY_SAMBA_DISABLED);
   }

   @Override
   public boolean isSambaSchedulingEnabled() {
      return datasinkServiceProvider.get().isSchedulingEnabled(PROPERTY_SAMBA_SCHEDULING_ENABLED);
   }
   
   @Override
   public void testSambaDatasink(SambaDatasink sambaDatasink) throws IOException {
      if (!isSambaEnabled())
         throw new IllegalStateException("samba is disabled");

      sendToSambaServer("ReportServer Samba Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            sambaDatasink, "reportserver-samba-test.txt", sambaDatasink.getFolder());
   }

   @Override
   public Map<StorageType, Boolean> getSambaEnabledConfigs() {
      return datasinkServiceProvider.get().getEnabledConfigs(StorageType.SAMBA, PROPERTY_SAMBA_DISABLED,
            StorageType.SAMBA_SCHEDULING, PROPERTY_SAMBA_SCHEDULING_ENABLED);
   }
   
   @Override
   public Optional<SambaDatasink> getDefaultDatasink() {
      return defaultDatasinkProvider.get();
   }

}