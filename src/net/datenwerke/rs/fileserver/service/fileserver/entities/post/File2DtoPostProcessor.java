package net.datenwerke.rs.fileserver.service.fileserver.entities.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;

public class File2DtoPostProcessor implements Poso2DtoPostProcessor<FileServerFile, FileServerFileDto> {

   @Override
   public void dtoCreated(FileServerFile poso, FileServerFileDto dto) {
      if (null == poso.getData())
         ((FileServerFileDtoDec) dto).setSize(0);
      else
         ((FileServerFileDtoDec) dto).setSize(poso.getData().length);
   }

   @Override
   public void dtoInstantiated(FileServerFile poso, FileServerFileDto dto) {

   }

}
