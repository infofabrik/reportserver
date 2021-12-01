package net.datenwerke.rs.utils.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.lang.model.type.NullType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 */
public class ReflectionServiceImpl implements ReflectionService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public static Set<String> primitiveTypeNames;
	static {
		primitiveTypeNames = new HashSet<String>();
		primitiveTypeNames.add("byte");
		primitiveTypeNames.add("short");
		primitiveTypeNames.add("int");
		primitiveTypeNames.add("long");
		primitiveTypeNames.add("char");
		primitiveTypeNames.add("float");
		primitiveTypeNames.add("double");
		primitiveTypeNames.add("boolean");
		primitiveTypeNames.add("void");
	}
	
	@Override
	public boolean isSimpleField(Field exportableField) {
		Class<?> type = exportableField.getType();
		return isSimpleType(type);
	}
	
	@Override
	public boolean isSimpleType(Class<?> type) {
		if(Boolean.class.equals(type))
			return true;
		if(Short.class.equals(type))
			return true;
		if(Integer.class.equals(type))
			return true;
		if(Long.class.equals(type))
			return true;
		if(Double.class.equals(type))
			return true;
		if(Float.class.equals(type))
			return true;
		if(Character.class.equals(type))
			return true;
		if(Byte.class.equals(type))
			return true;
		
		return type.isPrimitive();
	}
	
	@Override
	public boolean isCollection(Field exportableField){
		return isCollection(exportableField.getType());
	}
	
	@Override
	public boolean isCollection(Class<?> type){
		return Collection.class.isAssignableFrom(type);
	}
	
	@Override
	public boolean isList(Class<?> type) {
		return List.class.isAssignableFrom(type);
	}
	
	@Override
	public boolean isSet(Class<?> type) {
		return Set.class.isAssignableFrom(type);
	}
	
	@Override
	public Class<?> getGenericType(Field exportableField) {
		return getGenericType((ParameterizedType)exportableField.getGenericType());
	}

	@Override
	public Class<?> getGenericType(Class<?> clazz){
		Type type = clazz.getGenericSuperclass();
		while(! (type instanceof ParameterizedType) && null != type )
			type = ((Class)type).getGenericSuperclass();
		
		if(null != type)
			return getGenericType((ParameterizedType)type);
		return null;
	}
	
	@Override
	public Class<?> getGenericType(ParameterizedType type) {
		Type argument = type.getActualTypeArguments()[0];
		if(argument instanceof TypeVariable<?>){
			Type bound = ((TypeVariable<?>)argument).getBounds()[0];
			if(bound instanceof Class)
				return (Class<?>) bound;
			if(bound instanceof ParameterizedType)
				return (Class<?>) ((ParameterizedType)bound).getRawType();
		}

		return (Class<?>) argument;
	}

	@Override
	public Class<?> getClassForName(String typeName) throws ClassNotFoundException {
		if(typeName.contains("."))
			return Class.forName(typeName);
		
		if(isPrimitiveTypeName(typeName)){
			if ("byte".equals(typeName)) return byte.class;
			if ("short".equals(typeName)) return short.class;
			if ("int".equals(typeName)) return int.class;
			if ("long".equals(typeName)) return long.class;
			if ("char".equals(typeName)) return char.class;
			if ("float".equals(typeName)) return float.class;
			if ("double".equals(typeName)) return double.class;
			if ("boolean".equals(typeName)) return boolean.class;
			if ("void".equals(typeName)) return void.class;
		}
		
		return Class.forName(typeName);
	}

	@Override
	public boolean isPrimitiveTypeName(String typeName) {
		return primitiveTypeNames.contains(typeName);
	}

	@Override
	public Object convertStringToSimpleType(String value, Class<?> type) {
		if(type.isPrimitive()){
			if (type.equals(byte.class)) return Byte.valueOf(value);
			if (type.equals(short.class)) return Short.valueOf(value);
			if (type.equals(int.class)) return Integer.parseInt(value);
			if (type.equals(long.class)) return Long.valueOf(value);
			if (type.equals(char.class)) return value.charAt(0);
			if (type.equals(float.class)) return Float.valueOf(value);
			if (type.equals(double.class)) return Double.valueOf(value);
			if (type.equals(boolean.class)) return Boolean.valueOf(value);
		} else {
			if(Boolean.class.equals(type))
				return Boolean.valueOf(value);
			if(Short.class.equals(type))
				return Short.valueOf(value);
			if(Integer.class.equals(type))
				return Integer.parseInt(value);
			if(Long.class.equals(type))
				return Long.valueOf(value);
			if(Double.class.equals(type))
				return Double.valueOf(value);
			if(Float.class.equals(type))
				return Float.valueOf(value);
			if(Character.class.equals(type))
				return value.charAt(0);
			if(Byte.class.equals(type))
				return Byte.valueOf(value);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<?> createCollection(Class<?> type) {
		if(Set.class.equals(type))
			return new HashSet();
		if(List.class.equals(type))
			return new ArrayList();
		if(Queue.class.equals(type))
			return new LinkedList();
		if(SortedSet.class.equals(type))
			return new TreeSet();
		if(Collection.class.isAssignableFrom(type))
			return new HashSet();
			
		return null;
	}

	@Override
	public Object getEnumByString(Class<?> enumType, String name){
		if(! enumType.isEnum())
			throw new IllegalArgumentException("Expected enum, got: " + enumType);
		
		for(Object constant : enumType.getEnumConstants())
			if(constant.toString().equals(name))
				return constant;
		
		return null;
	}


	@Override
	public Field getFieldByAnnotation(Object object, Class<? extends Annotation> annotation) {
		return getFieldByAnnotation(object.getClass(), annotation);
	}

	@Override
	public Field getFieldByAnnotation(Class<?> type, Class<? extends Annotation> annotation) {
		Class<?> targetClass = type;
		
		while(null != targetClass){
			for(Field field : targetClass.getDeclaredFields()){
				if(field.isAnnotationPresent(annotation))
					return field;
			}
			targetClass = targetClass.getSuperclass();
		}
		
		return null;
	}
	
	@Override
	public Method getMethodByAnnotation(Object object, Class<? extends Annotation> annotation) {
		return getMethodByAnnotation(object.getClass(), annotation);
	}

	@Override
	public Method getMethodByAnnotation(Class<?> type, Class<? extends Annotation> annotation) {
		Class<?> targetClass = type;
		
		while(null != targetClass){
			for(Method method : targetClass.getDeclaredMethods()){
				if(method.isAnnotationPresent(annotation))
					return method;
			}
			targetClass = targetClass.getSuperclass();
		}
		
		return null;
	}
	
	
	@Override
	public Set<Field> getAllFields(Class<?> clazz){
		HashSet<Field> res = new HashSet<Field>(Arrays.asList(clazz.getDeclaredFields()));
		if(null != clazz.getSuperclass()){
			res.addAll(getAllFields(clazz.getSuperclass()));
		}
		return res;
	}
	
	@Override
	public List<Field> getFieldsByAnnotation(Class<?> type, Class<? extends Annotation> annotation) {
		List<Field> fields = new ArrayList<Field>();
		
		Class<?> targetClass = type;
		
		while(null != targetClass){
			for(Field field : targetClass.getDeclaredFields()){
				if(field.isAnnotationPresent(annotation))
					fields.add(field);
			}
			targetClass = targetClass.getSuperclass();
		}
		
		return fields;
	}

	@Override
	public Field getFieldByName(Object object, String fieldName) {
		if(null == object)
			return null;
		
		return getFieldByName(object.getClass(), fieldName);
	}

	@Override
	public Field getFieldByName(Class<?> type, String fieldName) {
		if(null == fieldName || null == type)
			return null;
		
		Class<?> targetClass = type;
		
		while(null != targetClass){
			for(Field field : targetClass.getDeclaredFields()){
				if(fieldName.equals(field.getName()))
					return field;
			}
			targetClass = targetClass.getSuperclass();
		}
		
		return null;
	}

	@Override
	public boolean representsNull(Class<?> type) {
		return NullType.class.equals(type);
	}
	
	@Override
	public Object getFieldValueNoSecurity(Field f, Object o){
		boolean acc = f.isAccessible();
		f.setAccessible(true);
		Object res = null;
		try {
			res = f.get(o);
		} catch (IllegalArgumentException e) {
			logger.warn( e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.warn( e.getMessage(), e);
		}finally{
			f.setAccessible(acc);
		}
		return res;
	}
	
	
	@Override
	public <A extends Annotation> A getAnnotationRecursive(Class<A> annotationClass, Class<?> type) {
		if(null == type)
			return null;
		
		if(type.isAnnotationPresent(annotationClass))
			return type.getAnnotation(annotationClass);
		
		return getAnnotationRecursive(annotationClass, type.getSuperclass());
	}
}
