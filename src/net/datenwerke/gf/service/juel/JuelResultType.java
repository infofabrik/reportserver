package net.datenwerke.gf.service.juel;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.gf.client.juel.dto", generateDto2Poso = false)
public enum JuelResultType {

   NULL, STRING, INTEGER, LONG, DECIMAL, DOUBLE, FLOAT, DATE, BOOLEAN
}
