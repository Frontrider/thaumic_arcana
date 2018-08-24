package testutil;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 * This class is from stackoverflow, which means that it is under MIT license.I can't find the original, so I include an another header to clarify it.
* Feel free to use it, because it's a powerfull tool at bypassing some of the bad designs of minecraft.
 *
 * Copyright 2018 Kis András
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * */

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