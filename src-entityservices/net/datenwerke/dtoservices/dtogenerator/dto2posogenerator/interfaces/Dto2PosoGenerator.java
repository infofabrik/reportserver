package net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;


/**
 * 
 *
 */
public interface Dto2PosoGenerator<D, P> {

	/**
	 * Creates a new Poso obejct with the data provided by the dto.
	 * 
	 * @param dto
	 */
	public P createPoso(D dto) throws ExpectedException;
	
	public P createUnmanagedPoso(D dto) throws ExpectedException;
	
	public P instantiatePoso();
	
	public void mergePoso(D dto, P poso) throws ExpectedException;

	public void mergeUnmanagedPoso(D dto, P poso) throws ExpectedException;
	
	public P loadAndMergePoso(D dto) throws ExpectedException;
	
	/**
	 * Loads the poso referenced by the dto.
	 * 
	 * @param dto
	 */
	public P loadPoso(D dto);
	
	void postProcessCreate(D dto, P poso);
	
	void postProcessCreateUnmanaged(D dto, P poso);
	
	void postProcessLoad(D dto, P poso);
	
	void postProcessMerge(D dto, P poso);
	
	void postProcessInstantiate(P poso);
	
}
