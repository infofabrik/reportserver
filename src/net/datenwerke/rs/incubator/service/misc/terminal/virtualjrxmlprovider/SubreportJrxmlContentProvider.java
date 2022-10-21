package net.datenwerke.rs.incubator.service.misc.terminal.virtualjrxmlprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualContentProviderImpl;
import net.datenwerke.security.service.security.SecurityService;

public class SubreportJrxmlContentProvider extends VirtualContentProviderImpl {

   protected static final Logger logger = LoggerFactory.getLogger(SubreportJrxmlContentProvider.class.getName());

   public static final String VIRTUAL_NAME = "subreports";

   private final JasperUtilsService jasperReportsService;

   private final ReportService reportService;

   @Inject
   public SubreportJrxmlContentProvider(SecurityService securityService, JasperUtilsService jasperReportsService,
         ReportService reportService) {
      super(securityService);
      this.jasperReportsService = jasperReportsService;
      this.reportService = reportService;
   }

   @Override
   public String getName() {
      return VIRTUAL_NAME;
   }

   @Override
   protected void addVirtualChildInfos(VFSLocationInfo info) {
      Object obj;
      try {
         obj = info.getLocation().getVirtualParentLocation().getObject();
      } catch (VFSException e) {
         logger.warn(e.getMessage(), e);
         throw new IllegalArgumentException(e);
      }
      if (!(obj instanceof JasperReport))
         throw new IllegalArgumentException();

      JasperReport report = (JasperReport) obj;

      for (JasperReportJRXMLFile file : report.getSubFiles()) {
         info.addChildInfo(new VFSObjectInfo(getClass(), file.getName(), file.getName(), false));
      }
   }

   @Override
   protected boolean doHasContent(VFSLocation vfsLocation) {
      return true;
   }

   @Override
   protected byte[] doGetContent(VFSLocation vfsLocation) throws VFSException {
      String name = vfsLocation.getPathHelper().getLastPathway();

      VFSLocation parent = vfsLocation.getVirtualParentLocation();

      JasperReport report = (JasperReport) parent.getObject();

      for (JasperReportJRXMLFile file : report.getSubFiles()) {
         if (name.equals(file.getName()))
            return null == file.getContent() ? new byte[0] : file.getContent().getBytes();
      }

      return null;
   }

   @Override
   protected void doSetContent(VFSLocation vfsLocation, byte[] content) {
   }

   @Override
   protected String doGetContentType(VFSLocation vfsLocation) {
      return "application/xml";
   }

   @Override
   public boolean enhanceNonVirtual(VFSLocation location) throws VFSException {
      Object obj = location.getObject();
      return null != obj && obj instanceof JasperReport && !(obj instanceof ReportVariant);
   }

   @Override
   protected boolean doesExist(VFSLocation vfsLocation) {
      String name = vfsLocation.getPathHelper().getLastPathway();

      VFSLocation parent = vfsLocation.getVirtualParentLocation();

      JasperReport report;
      try {
         report = (JasperReport) parent.getObject();
      } catch (VFSException e) {
         logger.warn(e.getMessage(), e);
         throw new IllegalStateException(e);
      }

      for (JasperReportJRXMLFile file : report.getSubFiles()) {
         if (name.equals(file.getName()))
            return true;
      }

      return false;
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      return vfsLocation.getPathHelper().getLastPathway().endsWith(".jrxml")
            && vfsLocation.getParentLocation().equals(vfsLocation.getVirtualBaseLocation());
   }

   @Override
   protected boolean canWriteIntoBaseLocation() {
      return super.canWriteIntoBaseLocation();
   }

   @Override
   protected boolean doIsLocationDeletable(VFSLocation vfsLocation) {
      return doesExist(vfsLocation);
   }

   @Override
   public void delete(VFSLocation vfsLocation) throws VFSException {
      String name = vfsLocation.getPathHelper().getLastPathway();

      VFSLocation parent = vfsLocation.getVirtualParentLocation();

      JasperReport report;
      try {
         report = (JasperReport) parent.getObject();
      } catch (VFSException e) {
         logger.warn(e.getMessage(), e);
         throw new IllegalStateException(e);
      }

      for (JasperReportJRXMLFile file : report.getSubFiles()) {
         if (name.equals(file.getName())) {
            jasperReportsService.removeJRXMLFile(file);
            break;
         }
      }
   }

   @Override
   public Object getObjectFor(VFSLocation vfsLocation) throws VFSException {
      String name = vfsLocation.getPathHelper().getLastPathway();

      VFSLocation parent = vfsLocation.getVirtualParentLocation();

      JasperReport report;
      try {
         report = (JasperReport) parent.getObject();
      } catch (VFSException e) {
         logger.warn(e.getMessage(), e);
         throw new IllegalStateException(e);
      }

      for (JasperReportJRXMLFile file : report.getSubFiles()) {
         if (name.equals(file.getName())) {
            return file;
         }
      }

      return null;
   }

   @Override
   public VFSLocation doCreate(VFSLocation location) throws VFSException {
      if (!canWriteIntoLocation(location)) {
         throw new VFSException("Cannot write");
      }

      String name = location.getPathHelper().getLastPathway();

      if (!name.endsWith(".jrxml"))
         throw new VFSException("Expect .jrxml file");

      VFSLocation parent = location.getVirtualParentLocation();

      JasperReport report;
      try {
         report = (JasperReport) parent.getObject();
      } catch (VFSException e) {
         logger.warn(e.getMessage(), e);
         throw new IllegalStateException(e);
      }

      JasperReportJRXMLFile file = new JasperReportJRXMLFile();
      file.setName(name);
      report.addSubfile(file);

      jasperReportsService.persist(file);
      reportService.merge(report);

      return location.getParentLocation().newSubLocation(name, false);
   }

   @Override
   public void writeIntoLocation(VFSLocation vfsLocation, byte[] uploadData) throws VFSException {
      String name = vfsLocation.getPathHelper().getLastPathway();

      VFSLocation parent = vfsLocation.getVirtualParentLocation();

      JasperReport report;
      try {
         report = (JasperReport) parent.getObject();
      } catch (VFSException e) {
         logger.warn(e.getMessage(), e);
         throw new VFSException(e);
      }

      for (JasperReportJRXMLFile file : report.getSubFiles()) {
         if (name.equals(file.getName())) {
            writeIntoJasper(file, report, uploadData);
            break;
         }
      }
   }

   private void writeIntoJasper(JasperReportJRXMLFile file, JasperReport report, byte[] uploadData) {
      String jrxml = new String(uploadData);

      file.setContent(jrxml);

      reportService.merge(report);
      jasperReportsService.merge(file);
   }

}
