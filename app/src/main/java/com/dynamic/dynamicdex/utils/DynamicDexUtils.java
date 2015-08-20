package com.dynamic.dynamicdex.utils;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;

import com.dynamic.dynamicdex.MainActivity;
import com.dynamic.dynamicdex.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by vivek on 16/8/15.
 */
public class DynamicDexUtils {

    private static String SECONDARY_DEX = "classes2.dex";
    private static final int BUF_SIZE = 8 * 1024;
    private static boolean dexListUpdated = false;


    public static Fragment loadSecondaryDex(MainActivity activity, String fragmentName) {
        File dexInternalStoragePath = new File(activity.getDir("dex", Context.MODE_PRIVATE),
                SECONDARY_DEX);
        File optimizedDexOutputPath = activity.getDir("outdex", Context.MODE_PRIVATE);
        Fragment fragment = null;
        Log.d("vivekwassan", "dexInternalStoragePath.exists() :" + dexInternalStoragePath.exists());
        if (!dexInternalStoragePath.exists()) {
            dexListUpdated = false;
            prepareSecondaryDex(activity, dexInternalStoragePath);
        }
        String uriPath = dexInternalStoragePath.getAbsolutePath();
        try {
            DexClassLoader classLoader = null;
            classLoader = new DexClassLoader(
                    uriPath, optimizedDexOutputPath.getAbsolutePath(), null, activity.getClass().getClassLoader());
            Class<?> myClass = classLoader.loadClass(fragmentName);
            fragment = (Fragment) myClass.newInstance();
            if (!dexListUpdated) {
                ClassLoaderUtils.add(dexInternalStoragePath, optimizedDexOutputPath, (PathClassLoader) activity.getClassLoader());
                dexListUpdated = true;
            }
        } catch (Exception e1) {
            Log.d("vivekwassan", "Exception " + e1);
            e1.printStackTrace();
        }
        return fragment;
    }

    private static void prepareSecondaryDex(Context context, File dexInternalStoragePath) {
        BufferedInputStream bis = null;
        OutputStream dexWriter = null;
        InputStream is = context.getResources().openRawResource(R.raw.classes2);
        try {
            bis = new BufferedInputStream(is);
            dexWriter = new BufferedOutputStream(new FileOutputStream(dexInternalStoragePath));
            byte[] buf = new byte[BUF_SIZE];
            int len;
            while ((len = bis.read(buf, 0, BUF_SIZE)) > 0) {
                dexWriter.write(buf, 0, len);
            }
            dexWriter.close();
            bis.close();
        } catch (IOException e) {
            if (dexWriter != null) {
                try {
                    dexWriter.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
