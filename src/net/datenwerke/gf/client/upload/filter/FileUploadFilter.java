package net.datenwerke.gf.client.upload.filter;

public interface FileUploadFilter {

   public static FileUploadFilter DUMMY_UPLOAD_FILTER = new FileUploadFilter() {
      @Override
      public String doProcess(String name, long size, String base64) {
         return null;
      }
   };

   public String doProcess(String name, long size, String base64);
}