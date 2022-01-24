package net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces;

import net.datenwerke.gxtdto.client.dtomanager.DtoView;

public interface Poso2DtoGenerator<P, D> {

   public D createDto(P poso, DtoView here, DtoView referenced);

   public D instantiateDto(P poso);
}
