package net.datenwerke.gxtdto.client.utilityservices.grid;

import java.util.Comparator;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public interface GridHelperService {

   public static class CCContainer<D, V> {
      ColumnConfig<D, V> config;
      SimpleComboBox<Object> combo;
      Converter<V, Object> converter;

      public CCContainer(ColumnConfig<D, V> config, SimpleComboBox<Object> combo, Converter<V, Object> converter) {
         super();
         this.config = config;
         this.combo = combo;
         this.converter = converter;
      }

      public ColumnConfig<D, V> getConfig() {
         return config;
      }

      public SimpleComboBox<Object> getCombo() {
         return combo;
      }

      public Converter<V, Object> getConverter() {
         return converter;
      }
   }

   public <D, V extends Enum<?>> CCContainer<D, V> createComboBoxColumnConfig(V[] values, ValueProvider<D, V> vp,
         boolean nullable, SortDir sortDir, int width);

   public <D, V> CCContainer<D, V> createComboBoxColumnConfig(V[] values, ValueProvider<D, V> vp,
         Converter<V, Object> converter, Comparator<Object> comparator, boolean nullable, SortDir sortDir, int width);

   public <D, V> CCContainer<D, V> createComboBoxColumnConfig(V[] values, ValueProvider<D, V> vp,
         Converter<V, Object> converter, LabelProvider<? super Object> labelProvider, Comparator<Object> comparator,
         boolean nullable, SortDir sortDir, int width);

   public <D> CCContainer<D, Boolean> createBooleanComboBoxColumnConfig(ValueProvider<D, Boolean> vp, boolean nullable,
         boolean trueFirst, int width, String tString, String fString);
}
