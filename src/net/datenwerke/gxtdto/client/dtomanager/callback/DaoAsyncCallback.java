package net.datenwerke.gxtdto.client.dtomanager.callback;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoContainer;
import net.datenwerke.gxtdto.client.servercommunication.callback.HandledAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;

public class DaoAsyncCallback<D> extends HandledAsyncCallback<D> {

   private AsyncCallback<D> callback;
   private Dao dao;

   private boolean preRegisterDtos = true;
   private boolean postRegisterDtos = false;

   private Collection<Dto> dtosToDetach;
   private boolean ignoreExpectedExceptions = false;
   private boolean ignoreAllExceptions = false;

   public DaoAsyncCallback(AsyncCallback<D> callback, Dao dao) {
      super(null);
      this.callback = callback;
      this.dao = dao;
   }

   @Override
   public void doOnSuccess(D result) {
      /* detach if necessary */
      if (null != dtosToDetach)
         dao.detachDtos(dtosToDetach);

      /* register and run original callback */
      if (preRegisterDtos)
         result = registerDtos(result);

      callback.onSuccess(result);

      if (postRegisterDtos)
         registerDtos(result);
   };

   @Override
   public void onFailure(Throwable caught) {
      if (ignoreAllExceptions || (ignoreExpectedExceptions && caught instanceof ExpectedException)) {
         doOnFailure(caught);
         return;
      }

      super.onFailure(caught);
   }

   @Override
   public void doOnFailure(Throwable caught) {
      callback.onFailure(caught);
   }

   private D registerDtos(D result) {
      /* register dto and call callback */
      if (result instanceof Dto) {
         D proxy = (D) dao.registerDto((Dto) result);
         return proxy;
      } else if (result instanceof ListLoadResult) {
         Collection data = ((ListLoadResult) result).getData();
         if (null != data && !data.isEmpty()) {
            D proxies = (D) dao.registerDtos((Collection<Dto>) data);
            return (D) new ListLoadResultBean<Dto>((List<Dto>) proxies);
         }
      } else if (result instanceof Collection && !((Collection) result).isEmpty()
            && ((Collection) result).iterator().next() instanceof Dto) {
         D proxies = (D) dao.registerDtos((Collection<Dto>) result);
         return proxies;
      } else if (result instanceof DtoContainer) {
         dao.registerDtoContainer((DtoContainer) result);
      } else if (result instanceof Collection && !((Collection) result).isEmpty()
            && ((Collection) result).iterator().next() instanceof DtoContainer) {
         dao.registerDtoContainer((Collection<DtoContainer>) result);
      }

      return result;
   }

   public void setPostRegisterDtos() {
      preRegisterDtos = false;
      postRegisterDtos = true;
   }

   public void setPreRegisterDtos() {
      preRegisterDtos = true;
      postRegisterDtos = false;
   }

   public void doNotRegisterDtos() {
      preRegisterDtos = false;
      postRegisterDtos = false;
   }

   public void addDtoToDetach(Dto dto) {
      if (null == dtosToDetach)
         dtosToDetach = new HashSet<Dto>();
      dtosToDetach.add(dto);
   }

   public void addDtosToDetach(Collection<? extends Dto> dtos) {
      if (null == dtosToDetach)
         dtosToDetach = new HashSet<Dto>();
      dtosToDetach.addAll(dtos);
   }

   public void setDtosToDetach(Collection<Dto> dtos) {
      dtosToDetach = dtos;
   }

   public void ignoreExpectedExceptions(boolean b) {
      this.ignoreExpectedExceptions = b;
   }

   public boolean isIgnoreAllExceptions() {
      return ignoreAllExceptions;
   }

   public void setIgnoreAllExceptions(boolean ignoreAllExceptions) {
      this.ignoreAllExceptions = ignoreAllExceptions;
   }
}
