package net.datenwerke.rs.json.service.gson;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

public class TypeTokens<T> {
   public static <T> Type getType() {
      TypeToken<T> token = new TypeToken<T>() {};
      
      return token.getType();
   }
}
