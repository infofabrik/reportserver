package net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces;

public interface Dto2PosoPostProcessor<D, P> {

	public void posoCreated(D dto, P poso);
	
	public void posoCreatedUnmanaged(D dto, P poso);
	
	public void posoInstantiated(P poso);
	
	public void posoMerged(D dto, P poso);
	
	public void posoLoaded(D dto, P poso);
	
}
