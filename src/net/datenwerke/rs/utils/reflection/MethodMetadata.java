package net.datenwerke.rs.utils.reflection;

import static java.util.stream.Collectors.toList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class  MethodMetadata <T>{
   
   private final Class<T> classType;
   private final String name;
   private final List<String> argsAsString;
   private final Method method;
   private final List<Object> methodArgs;
   
   public MethodMetadata(Class<T> classType, String name, List<String> argsAsString) {
      this.classType = classType;
      this.name = name;
      this.argsAsString = argsAsString;
      this.method = getMethodMatchingNameAndArgCount();
      this.methodArgs = parseArgsToParamTypes();
   }
   
   public MethodMetadata(Class<T> classType, String name, String... argsAsString) {
      this.classType = classType;
      this.name = name;
      this.argsAsString = Arrays.asList(argsAsString);
      this.method = getMethodMatchingNameAndArgCount();
      this.methodArgs = parseArgsToParamTypes();
   }
   

   
   
   public Object invokeMethodOn(T object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      return method.invoke(object, methodArgs.toArray(new Object[0]));
   }
   
   private Method getMethodMatchingNameAndArgCount() {
      Method[] methods = classType.getMethods();
      List<Method> matchingMethods = Arrays.asList(methods).stream()
            .filter(method -> method.getName().equals(name))
            .filter(method -> method.getParameterCount() == argsAsString.size())
            .collect(toList());
      if(matchingMethods.size() == 0) {
         throw new IllegalArgumentException("No method matching arg size and name");
      } else if (matchingMethods.size() > 1) {
         throw new IllegalArgumentException("Too many methods matching arg size and name");
      }
      return matchingMethods.get(0);
   }
   
   private List<Object> parseArgsToParamTypes() {
      Class<?>[] types = method.getParameterTypes();
      List<Object> parsedArgs = IntStream.range(0, types.length)
            .mapToObj(i -> mapArgToClass(types[i], argsAsString.get(i)))
            .collect(toList());
      return parsedArgs;
   }
   
   private Object mapArgToClass(Class<?> classObj, String arg) {
      if(arg.equals("null"))
         return null;
      switch(classObj.getSimpleName()) {
         case("boolean"):
            return Boolean.parseBoolean(arg);
         case("String"):
            return arg;
         case("int"):
            return Integer.parseInt(arg);
         default:
            throw new IllegalArgumentException("unknown parameter type detected");
      }
   }
}
