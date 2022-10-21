package net.datenwerke.rs.theme.client.toolbar;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.neptune.client.base.toolbar.Css3PagingToolBarAppearance;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class RsPagingToolbarAppearance extends Css3PagingToolBarAppearance {

   @Override
   public ImageResource first() {
      return BaseIcon.ANGLE_DOUBLE_LEFT.toImageResource();
   }

   @Override
   public ImageResource last() {
      return BaseIcon.ANGLE_DOUBLE_RIGHT.toImageResource();
   }

   @Override
   public ImageResource next() {
      return BaseIcon.CHEVRON_RIGHT.toImageResource();
   }

   @Override
   public ImageResource prev() {
      return BaseIcon.CHEVRON_LEFT.toImageResource();
   }

   @Override
   public ImageResource refresh() {
      return BaseIcon.REFRESH.toImageResource();
   }

   @Override
   public ImageResource loading() {
      return BaseIcon.LOADING.toImageResource();
   }
}
