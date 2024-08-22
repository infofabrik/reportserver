package net.datenwerke.rs.utils.file;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

import net.datenwerke.rs.base.service.renderer.BinaryFormat;
import net.datenwerke.rs.base.service.renderer.TextFormat;

/**
 * Custom Implementation of FileUtils.byteCountToDisplaySize to fix rounding bug
 * https://issues.apache.org/jira/browse/IO-373
 */
public class RsFileUtils extends FileUtils {
   
   private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

   enum FileSize {
       EXABYTE("EB", ONE_EB_BI),
       PETABYTE("PB", ONE_PB_BI),
       TERABYTE("TB", ONE_TB_BI),
       GIGABYTE("GB", ONE_GB_BI),
       MEGABYTE("MB", ONE_MB_BI),
       KILOBYTE("KB", ONE_KB_BI),
       BYTE("bytes", BigInteger.ONE);

       private final String unit;
       private final BigInteger byteCount;

       FileSize(String unit, BigInteger byteCount) {
           this.unit = unit;
           this.byteCount = byteCount;
       }
       private String unit() {
           return unit;
       }
       private BigInteger byteCount() {
           return byteCount;
       }

   }

   /**
    * Formats a file's size into a human readable format
    *
    * @param fileSize the file's size as BigInteger
    * @return the size as human readable string
    */
   public static String byteCountToDisplaySize(final BigInteger fileSize) {

       String unit = FileSize.BYTE.unit;
       BigDecimal fileSizeInUnit = BigDecimal.ZERO;
       String val;

       for (FileSize fs : FileSize.values()) {
           BigDecimal size_bd = new BigDecimal(fileSize);
           fileSizeInUnit = size_bd.divide(new BigDecimal(fs.byteCount), 5, ROUNDING_MODE);
           if (fileSizeInUnit.compareTo(BigDecimal.ONE) >= 0) {
               unit = fs.unit;
               break;
           }
       }

       // always round so that at least 3 numerics are displayed (###, ##.#, #.##)
       if (fileSizeInUnit.divide(BigDecimal.valueOf(100.0), BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ONE) >= 0) {
           val = fileSizeInUnit.setScale(0, ROUNDING_MODE).toString();
       } else if (fileSizeInUnit.divide(BigDecimal.valueOf(10.0), BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ONE) >= 0) {
           val = fileSizeInUnit.setScale(1, ROUNDING_MODE).toString();
       } else {
           val = fileSizeInUnit.setScale(2, ROUNDING_MODE).toString();
       }

       // trim zeros at the end
       if (val.endsWith(".00")) {
           val = val.substring(0, val.length() - 3);
       } else if (val.endsWith(".0")) {
           val = val.substring(0, val.length() - 2);
       }

       return String.format("%s %s", val, unit);
   }


   /**
    * Formats a file's size into a human readable format
    *
    * @param fileSize the file's size as long
    * @return the size as human readable string
    */
   public static String byteCountToDisplaySize(final long fileSize) {
       return byteCountToDisplaySize(BigInteger.valueOf(fileSize));
   }
   
   public static String appendFileCheck(Path path) {
      StringBuilder sb = new StringBuilder();
      sb.append(path.toAbsolutePath().toString());
      
      sb.append(" (")
         .append((Files.exists(path) && Files.isReadable(path))? "OK)" : "INACCESSIBLE)");
      
      return sb.toString();
   }
   
   public static boolean checkReadable(Path path) {
      return Files.exists(path)  && Files.isReadable(path);
   }
   public static boolean checkWritable(Path path) {
      return Files.exists(path)  && Files.isWritable(path);
   }
   
   public static String getContentType(BinaryFormat format) {
      switch (format) {
      case PNG:
         return "image/png";
      case PS:
         return "application/postscript";
      default:
         return "application/octet-stream";
      }
   }
   
   public static String getContentType(TextFormat format) {
      switch (format) {
      case SVG:
         return "image/svg+xml";
      case DOT:
         return "text/vnd.graphviz";
      case XDOT:
         return "text/vnd.graphviz";
      case PLAIN:
         return "text/plain";
      case PLAIN_EXT:
         return "text/plain";
      case JSON:
         return "application/json";
      case JSON0:
         return "application/json";
      case HTML:
         return "text/html";
      default:
         return "application/octet-stream";
      }
   }

}
