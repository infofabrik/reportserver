package net.datenwerke.rs.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import net.datenwerke.rs.rest.service.rest.annotations.RestAuthenticationBypass;

public class RsRestResource {

   @Context
   private HttpServletRequest request;
   
   @RestAuthenticationBypass
   public HttpServletRequest getRequest() {
      return request;
   }
}
