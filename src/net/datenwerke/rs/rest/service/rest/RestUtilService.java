package net.datenwerke.rs.rest.service.rest;

import net.datenwerke.rs.rest.objects.RestAbstractNode;

public interface RestUtilService {

   RestAbstractNode toRestObject(Object object);
   
   RestAbstractNode toDetailedRestObject(Object object);
}
