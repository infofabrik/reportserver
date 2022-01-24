package net.datenwerke.dtoservices.dtogenerator.annotations;

/**
 * 
 *
 */
public enum LoadPosoType {
   /**
    * Tries to load the entity from the database if an id was set. If no Id is set
    * the entity is created.
    */
   Entity, Create, None
}
