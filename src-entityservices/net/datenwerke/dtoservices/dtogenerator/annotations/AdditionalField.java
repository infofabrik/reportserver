package net.datenwerke.dtoservices.dtogenerator.annotations;

import net.datenwerke.dtoservices.dtogenerator.Visibility;

public @interface AdditionalField {

   public String name();

   public Class<?> type();

   public Class<?>[] generics() default {};

   public Visibility visibility() default Visibility.PRIVATE;
}
