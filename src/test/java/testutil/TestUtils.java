package testutil;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

public class TestUtils {
    // get a static class value
    public static Object reflectValue(Class<?> classToReflect, String fieldNameValueToFetch) {
        try {
            Field reflectField  = reflectField(classToReflect, fieldNameValueToFetch);
            reflectField.setAccessible(true);
            Object reflectValue = reflectField.get(classToReflect);
            return reflectValue;
        } catch (Exception e) {
            fail("Failed to reflect "+fieldNameValueToFetch);
        }
        return null;
    }
    // get an instance value
    public static Object reflectValue(Object objToReflect, String fieldNameValueToFetch) {
        try {
            Field reflectField  = reflectField(objToReflect.getClass(), fieldNameValueToFetch);
            Object reflectValue = reflectField.get(objToReflect);
            return reflectValue;
        } catch (Exception e) {
            fail("Failed to reflect "+fieldNameValueToFetch);
        }
        return null;
    }
    // find a field in the class tree
    public static Field reflectField(Class<?> classToReflect, String fieldNameValueToFetch) {
        try {
            Field reflectField = null;
            Class<?> classForReflect = classToReflect;
            do {
                try {
                    reflectField = classForReflect.getDeclaredField(fieldNameValueToFetch);
                } catch (NoSuchFieldException e) {
                    classForReflect = classForReflect.getSuperclass();
                }
            } while (reflectField==null || classForReflect==null);
            reflectField.setAccessible(true);
            return reflectField;
        } catch (Exception e) {
            fail("Failed to reflect "+fieldNameValueToFetch +" from "+ classToReflect);
        }
        return null;
    }
    // set a value with no setter
    public static void refectSetValue(Object objToReflect, String fieldNameToSet, Object valueToSet) {
        try {
            Field reflectField  = reflectField(objToReflect.getClass(), fieldNameToSet);
            reflectField.set(objToReflect, valueToSet);
        } catch (Exception e) {
            fail("Failed to reflectively set "+ fieldNameToSet +"="+ valueToSet);
        }
    }

}