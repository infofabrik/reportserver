package net.datenwerke.gxtdto.client.dtomanager.stores;

public interface TreeDto {

   public int getPosition();

   public Object getParentNodeId();

   public String getParentNodeType();

   public boolean hasChildren();

   public void setHasChildren(Boolean hasChildren);
}
