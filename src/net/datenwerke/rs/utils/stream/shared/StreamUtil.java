package net.datenwerke.rs.utils.stream.shared;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamUtil {

   // can be replaced in java 9 by Stream.<T>ofNullable(T)
   public static <T> Stream<T> streamOfNullable(List<T> list) {
      return Optional.ofNullable(list).map(List::stream).orElseGet(Stream::empty);
   }

   // can be replaced in java 9 by Stream.<T>ofNullable(T)
   public static <T> Stream<T> streamOfNullable(T[] array) {
      return Optional.ofNullable(array).map(Arrays::stream).orElseGet(Stream::empty);
   }
}
