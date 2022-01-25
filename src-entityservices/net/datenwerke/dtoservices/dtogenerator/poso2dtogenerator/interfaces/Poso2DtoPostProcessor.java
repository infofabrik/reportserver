package net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces;

public interface Poso2DtoPostProcessor<P, D> {

   public void dtoCreated(P poso, D dto);

   public void dtoInstantiated(P poso, D dto);
}
