package net.datenwerke.treedb.client.treedb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.EntireTreeDTO;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoaderAsync;

public class TreeDbLoaderDao extends Dao {

   protected final RPCTreeLoaderAsync treeLoader;

   protected Dto state;

   protected final TreeDbFtoConverter treeDbFtoConverter;

   public TreeDbLoaderDao(RPCTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter) {
      this.treeLoader = treeLoader;
      this.treeDbFtoConverter = treeDbFtoConverter;
   }

   public void getRoot(AsyncCallback<List<AbstractNodeDto>> callback) {
      DaoAsyncCallback asyncCallback = transformListCallback(callback);
      treeLoader.getRoot(state, asyncCallback);
   }

   public void getChildren(AbstractNodeDto node, boolean useFto, final Collection<Dto2PosoMapper> wlFilters,
         final Collection<Dto2PosoMapper> blFilters, AsyncCallback<List<AbstractNodeDto>> callback) {
      if (!useFto || null == this.treeDbFtoConverter) {
         DaoAsyncCallback asyncCallback = transformListCallback(callback);
         treeLoader.getChildren(node, state, wlFilters, blFilters, asyncCallback);
      } else {
         getChildrenAsFto(node, wlFilters, blFilters, callback);
      }
   }

   public void loadAll(Collection<Dto2PosoMapper> whiteListFilters, Collection<Dto2PosoMapper> blackListFilters,
         AsyncCallback<EntireTreeDTO> callback, boolean useFto) {
      if (null == whiteListFilters && null == blackListFilters)
         loadAll(callback, useFto);
      else {
         DaoAsyncCallback asyncCallback = transformContainerCallback(callback);
         treeLoader.loadAll(state, whiteListFilters, blackListFilters, asyncCallback);
      }
   }

   public void loadAll(AsyncCallback<EntireTreeDTO> callback, boolean useFto) {
      if (useFto && null != this.treeDbFtoConverter) {
         loadAllAsFto(callback);
      } else {
         DaoAsyncCallback asyncCallback = transformContainerCallback(callback);
         treeLoader.loadAll(state, asyncCallback);
      }
   }

   private void getChildrenAsFto(AbstractNodeDto node, final Collection<Dto2PosoMapper> wlFilters,
         final Collection<Dto2PosoMapper> blFilters, final AsyncCallback<List<AbstractNodeDto>> callback) {
      final AsyncCallback handledCallback = transformListCallback(callback);
      treeLoader.getChildrenAsFto(node, node, wlFilters, blFilters, new AsyncCallback<String[][]>() {

         @Override
         public void onFailure(Throwable caught) {
            handledCallback.onFailure(caught);
         }

         @Override
         public void onSuccess(String[][] result) {
            List<AbstractNodeDto> nodes = new ArrayList<AbstractNodeDto>();

            for (String[] fto : result) {
               AbstractNodeDtoDec dto = treeDbFtoConverter.convert(fto);
               nodes.add(dto);
            }

            handledCallback.onSuccess(nodes);
         }
      });
   }

   private void loadAllAsFto(final AsyncCallback<EntireTreeDTO> callback) {
//		final DaoAsyncCallback asyncCallback = transformContainerCallback(callback);
      final AsyncCallback asyncCallback = callback;

      treeLoader.loadAllAsFto(state, new AsyncCallback<String[][]>() {
         @Override
         public void onFailure(Throwable caught) {
            asyncCallback.onFailure(caught);
         }

         @Override
         public void onSuccess(String[][] result) {
            HashMap<Long, AbstractNodeDtoDec> nodes = new HashMap<Long, AbstractNodeDtoDec>();

            for (String[] fto : result) {
               AbstractNodeDtoDec dto = treeDbFtoConverter.convert(fto);
               nodes.put(dto.getId(), dto);
            }

            EntireTreeDTO etdto = new EntireTreeDTO();

            for (AbstractNodeDtoDec node : nodes.values()) {
               if (null != node.getParentNodeId()) {
                  etdto.addChild(nodes.get(node.getParentNodeId()), node);
               } else {
                  etdto.addRoot(node);
               }
            }

            asyncCallback.onSuccess(etdto);
         }
      });
   }

   public void loadNode(AbstractNodeDto node, AsyncCallback<AbstractNodeDto> callback) {
      treeLoader.loadNodeById(node.getId(), state, transformDtoCallback(callback));
   }

   public void loadNodeById(Long id, AsyncCallback<AbstractNodeDto> callback) {
      treeLoader.loadNodeById(id, state, transformDtoCallback(callback));
   }

   public void loadFullViewNode(AbstractNodeDto node, AsyncCallback<AbstractNodeDto> callback) {
      treeLoader.loadFullViewNode(node, state, transformDtoCallback(callback));
   }

   public Dto getState() {
      return state;
   }

   public void setState(Dto state) {
      this.state = state;
   }

}
