package net.datenwerke.gxtdto.client.ui.helper.wrapper;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;

public class HeadDescMainWrapper extends DwContentPanel {

   public HeadDescMainWrapper(String headline, String description, Widget main) {
      setHeaderVisible(false);

      VerticalLayoutContainer container = new VerticalLayoutContainer();
      setWidget(container);

      container.add(createHeadline(headline), new VerticalLayoutData(1, -1));
      container.add(createDescription(description), new VerticalLayoutData(1, -1));
      container.add(main, new VerticalLayoutData(1, -1, new Margins(10, 0, 0, 0)));
   }

   public HeadDescMainWrapper(String headline, String description, Widget main, Margins margins) {
      setHeaderVisible(false);

      VerticalLayoutContainer container = new VerticalLayoutContainer();
      setWidget(container);

      container.add(createHeadline(headline), new VerticalLayoutData(1, -1, margins));
      container.add(createDescription(description), new VerticalLayoutData(1, -1, margins));
      container.add(main, new VerticalLayoutData(1, -1, margins));
   }

   public HeadDescMainWrapper(String headline, String description, Widget main, VerticalLayoutData layoutData,
         Margins margins) {
      setHeaderVisible(false);

      VerticalLayoutContainer container = new VerticalLayoutContainer();
      setWidget(container);

      container.add(createHeadline(headline), new VerticalLayoutData(1, -1, margins));
      container.add(createDescription(description), new VerticalLayoutData(1, -1, margins));
      container.add(main, layoutData);
   }

   protected Widget createDescription(String description) {
      return SeparatorTextLabel.createText(description);
   }

   protected Widget createHeadline(String headline) {
      return SeparatorTextLabel.createHeadlineLarge(headline);
   }

}
