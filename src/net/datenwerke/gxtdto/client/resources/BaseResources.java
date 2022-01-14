package net.datenwerke.gxtdto.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface BaseResources extends ClientBundle {

   public static BaseResources INSTANCE = GWT.create(BaseResources.class);

   @Source("img/i/s.gif")
   ImageResource emptyImage();

   @Source("img/i/column_single.png")
   ImageResource columnSingle();

   @Source("img/i/column_double.png")
   ImageResource columnDouble();

   @Source("img/i/column_double_l.png")
   ImageResource columnDoubleL();

   @Source("img/i/column_double_r.png")
   ImageResource columnDoubleR();

   @Source("img/i/column_tripple.png")
   ImageResource columnTripple();

   @Source("img/i/100bar.png")
   ImageResource saikuChart100bar();

   @Source("img/i/area.png")
   ImageResource saikuChartArea();

   @Source("img/i/bar.png")
   ImageResource saikuChartBar();

   @Source("img/i/dot.png")
   ImageResource saikuChartDot();

   @Source("img/i/heat.png")
   ImageResource saikuChartHeat();

   @Source("img/i/line.png")
   ImageResource saikuChartLine();

   @Source("img/i/multiple.png")
   ImageResource saikuChartMultiple();

   @Source("img/i/pie.png")
   ImageResource saikuChartPie();

   @Source("img/i/stackedbar.png")
   ImageResource saikuChartStackedbar();

   @Source("img/i/waterfall.png")
   ImageResource saikuChartWaterfall();

   @Source("img/i/sunburst.png")
   ImageResource saikuChartSunburst();

   @Source("img/i/multiplesunburst.png")
   ImageResource saikuChartMultiSunburst();
}
