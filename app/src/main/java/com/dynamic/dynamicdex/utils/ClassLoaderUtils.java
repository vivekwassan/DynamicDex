package com.dynamic.dynamicdex.utils;

import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;

public class ClassLoaderUtils {
    private static Object getField(Object paramObject, Class<?> paramClass, String paramString)
            throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field localField = paramClass.getDeclaredField(paramString);
        localField.setAccessible(true);
        return localField.get(paramObject);
    }

    private static void setField(Object paramObject1, Class<?> paramClass, String paramString, Object paramObject2)
            throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field localField = paramClass.getDeclaredField(paramString);
        localField.setAccessible(true);
        localField.set(paramObject1, paramObject2);
    }

    private static Object getPathList(BaseDexClassLoader paramBaseDexClassLoader) throws NoSuchFieldException,
            IllegalAccessException, IllegalArgumentException {
        return getField(paramBaseDexClassLoader, BaseDexClassLoader.class, "pathList");
    }

    private static Object getDexElements(Object paramObject) throws NoSuchFieldException, IllegalAccessException,
            IllegalArgumentException {
        return getField(paramObject, paramObject.getClass(), "dexElements");
    }

    private static void setDexElements(Object paramObject1, Object paramObject2) throws NoSuchFieldException,
            IllegalAccessException, IllegalArgumentException {
        setField(paramObject1, paramObject1.getClass(), "dexElements", paramObject2);
    }

    @SuppressWarnings("rawtypes")
    private static Object joinArrays(Object paramObject1, Object paramObject2) {
        Class localClass = paramObject1.getClass().getComponentType();
        int i = Array.getLength(paramObject1);
        int j = i + Array.getLength(paramObject2);
        Object localObject = Array.newInstance(localClass, j);
        int k = 0;
        while (k < i) {
            Array.set(localObject, k, Array.get(paramObject1, k));
            k++;
        }
        while (k < j) {
            Array.set(localObject, k, Array.get(paramObject2, k - i));
            k++;
        }
        return localObject;
    }

    public static void add(File dexFile, File optimizedDir, PathClassLoader paramPathClassLoader) {
        try {
            Class<?> dexPathListClass = Class.forName("dalvik.system.DexPathList");
            Log.d("vivekwassan","dexFile.getAbsolutePath() :"+dexFile.getAbsolutePath());
            Log.d("vivekwassan","optimizedDir :"+optimizedDir.getAbsolutePath());
            Constructor<?> constructor =
                    dexPathListClass.getConstructor(ClassLoader.class, String.class, String.class,
                            File.class);
            Log.d("vivekwassan","fine 22");
            Object dexPathList =
                    constructor.newInstance(paramPathClassLoader, dexFile.getAbsolutePath(), null, optimizedDir);
            Log.d("vivekwassan","fine 33");
            Object localObject = joinArrays(getDexElements(getPathList(paramPathClassLoader)),
                    getDexElements(dexPathList));
            Log.d("vivekwassan","fine 44");
            setDexElements(getPathList(paramPathClassLoader), localObject);
            Log.d("vivekwassan","fine 55");
        } catch (Exception e) {
            Log.e("ClassLoaderInjector", "Error", e);
        }
    }
}